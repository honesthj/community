package life.joker.community.mapper;

import life.joker.community.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment row);
}