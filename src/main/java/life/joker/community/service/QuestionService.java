package life.joker.community.service;

import com.github.pagehelper.PageHelper;
import life.joker.community.dto.PaginationDTO;
import life.joker.community.dto.QuestionDTO;
import life.joker.community.dto.QuestionQueryDTO;
import life.joker.community.exception.CustomizeErrorCode;
import life.joker.community.exception.CustomizeException;
import life.joker.community.mapper.LoginMapper;
import life.joker.community.mapper.QuestionExtMapper;
import life.joker.community.mapper.QuestionMapper;
import life.joker.community.model.Login;
import life.joker.community.model.Question;
import life.joker.community.model.QuestionExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author joker
 * @date 2023/02/25 12:01
 **/
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private LoginMapper loginMapper;
    static final Integer DEFAULT_VIEW_COUNT_STEP = 1;

    public PaginationDTO list(String search, String tag, Integer page, Integer size) {
        String regexpSearch = null;
        if (StringUtils.isNotBlank(search)) {
            //替换成正则表达式的格式
            regexpSearch = StringUtils.replace(search, " ", "|");
        }
        QuestionQueryDTO questionQueryDTO = new QuestionQueryDTO();
        questionQueryDTO.setSearch(regexpSearch);
        questionQueryDTO.setTag(tag);
        Integer totalcount = questionExtMapper.countBySearch(questionQueryDTO);
        Integer totalPage = totalcount / size + (totalcount % size != 0 ? 1 : 0);
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        PageHelper.startPage(page, size);
        List<Question> questions = questionExtMapper.selectBySearch(questionQueryDTO);
        List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questions) {
            Login login = loginMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setLogin(login);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setData(questionDTOList);
        paginationDTO.setPagination(totalPage, page);
        return paginationDTO;
    }

    public PaginationDTO list(Long loginId, Integer page, Integer size) {
        QuestionExample questionexample = new QuestionExample();
        questionexample.createCriteria().andCreatorEqualTo(loginId);
        Integer totalcount = (int) questionMapper.countByExample(questionexample);
        Integer totalPage = totalcount / size + (totalcount % size != 0 ? 1 : 0);
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        PageHelper.startPage(page, size);
        questionexample.setOrderByClause("gmt_create desc");
        List<Question> questions = questionMapper.selectByExample(questionexample);
        List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questions) {
            Login login = loginMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setLogin(login);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setData(questionDTOList);
        paginationDTO.setPagination(totalPage, page);
        return paginationDTO;
    }

    public QuestionDTO getById(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        Login login = loginMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setLogin(login);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.insertSelective(question);
        } else {
            //更新
            question.setGmtModified(System.currentTimeMillis());
            int updated = questionMapper.updateByPrimaryKeySelective(question);
            if (updated != 1) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }


    public void incView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(DEFAULT_VIEW_COUNT_STEP);
        questionExtMapper.incView(question);
    }

    public List<QuestionDTO> selectRelated(QuestionDTO queryDTO) {
        if (StringUtils.isBlank(queryDTO.getTag())) {
            return new ArrayList<QuestionDTO>();
        }
        //替换成正则表达式的格式
        String regexpTag = StringUtils.replace(queryDTO.getTag(), ",", "|");
        Question question = new Question();
        question.setId(queryDTO.getId());
        question.setTag(regexpTag);
        List<Question> questions = questionExtMapper.selectRelated(question);
        List<QuestionDTO> questionDTOS = questions.stream().map(q -> {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q, questionDTO);
            return questionDTO;
        }).collect(Collectors.toList());
        return questionDTOS;
    }
}