package life.joker.community.enums;

/**
 * @author joker
 * @date 2023/03/08 20:42
 **/
public enum NotificationStatusEnum {
    //通知未读状态
    UNREAD(0),
    //通知已读状态
    READ(1);
    private int status;

    public int getStatus() {
        return status;
    }

    NotificationStatusEnum(int status) {
        this.status = status;
    }
}
