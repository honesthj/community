package life.joker.community.enums;

/**
 * @author joker
 * @date 2023/03/03 16:38
 **/
public enum CommentTypeEnum {
    //回复问题
    QUESTION(1),
    //二级回复，回复评论
    COMMENT(2);
    private Integer type;

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
            if (commentTypeEnum.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }
}
