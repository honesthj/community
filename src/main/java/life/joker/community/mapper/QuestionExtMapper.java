package life.joker.community.mapper;

import life.joker.community.model.Question;

public interface QuestionExtMapper {
    int incView(Question row);

    int incCommentCount(Question row);
}