package life.joker.community.service;

import com.github.pagehelper.PageHelper;
import life.joker.community.dto.PaginationDTO;
import life.joker.community.dto.QuestionDTO;
import life.joker.community.exception.CustomizeErrorCode;
import life.joker.community.exception.CustomizeException;
import life.joker.community.mapper.LoginMapper;
import life.joker.community.mapper.QuestionExtMapper;
import life.joker.community.mapper.QuestionMapper;
import life.joker.community.model.Login;
import life.joker.community.model.Question;
import life.joker.community.model.QuestionExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public PaginationDTO list(Integer page, Integer size) {
        Integer totalcount = (int) questionMapper.countByExample(new QuestionExample());
        Integer totalPage = totalcount / size + (totalcount % size != 0 ? 1 : 0);
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        PageHelper.startPage(page, size);
        List<Question> questions = questionMapper.selectByExample(new QuestionExample());
        List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questions) {
            Login login = loginMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setLogin(login);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        paginationDTO.setPagination(totalPage, page);
        return paginationDTO;
    }

    public PaginationDTO list(Integer loginId, Integer page, Integer size) {
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
        paginationDTO.setQuestions(questionDTOList);
        paginationDTO.setPagination(totalPage, page);
        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND.getMessage());
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
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND.getMessage());
            }
        }
    }


    public void incView(Integer id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }
}