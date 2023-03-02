package life.joker.community.exception;

/**
 * @author joker
 * @date 2023/03/02 19:03
 **/
public class CustomizeException extends RuntimeException {
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public CustomizeException(String message) {
        this.message = message;
    }
}