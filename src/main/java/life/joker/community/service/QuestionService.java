package life.joker.community.service;

import com.github.pagehelper.PageHelper;
import life.joker.community.dto.PaginationDTO;
import life.joker.community.dto.QuestionDTO;
import life.joker.community.mapper.LoginMapper;
import life.joker.community.mapper.QuestionMapper;
import life.joker.community.model.Login;
import life.joker.community.model.Question;
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
    private LoginMapper loginMapper;

    public PaginationDTO list(Integer page, Integer size) {
        Integer totalcount = questionMapper.count();
        Integer totalPage = totalcount / size + (totalcount % size != 0 ? 1 : 0);
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        PageHelper.startPage(page, size);
        List<Question> questions = questionMapper.list();
        List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questions) {
            Login login = loginMapper.findById(question.getCreator());
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
        Integer totalcount = questionMapper.countByLoginId(loginId);
        Integer totalPage = totalcount / size + (totalcount % size != 0 ? 1 : 0);
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        PageHelper.startPage(page, size);
        List<Question> questions = questionMapper.listByLoginId(loginId);
        List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questions) {
            Login login = loginMapper.findById(question.getCreator());
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
        Question question = questionMapper.findById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        Login login = loginMapper.findById(question.getCreator());
        questionDTO.setLogin(login);
        return questionDTO;
    }
}