package life.joker.community.exception;

/**
 * @author joker
 * @date 2023/03/02 19:38
 **/
public enum CustomizeErrorCode implements ICustomizeErrorCode {
    //问题未找到
    QUESTION_NOT_FOUND("你找的问题不在了，要不要换个试试？");
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    CustomizeErrorCode(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }

    CustomizeErrorCode(String message) {
        this.message = message;
    }
}
