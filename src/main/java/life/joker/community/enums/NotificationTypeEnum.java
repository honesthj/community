package life.joker.community.enums;

/**
 * @author joker
 * @date 2023/03/08 20:30
 **/
public enum NotificationTypeEnum {
    //通知类型，有人回复了问题
    REPLY_QUESTION(1, "回复了问题"),
    //通知类型，有人回复了评论
    REPLY_COMMENT(2, "回复了评论"),
    ;
    private int type;
    private String name;

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    NotificationTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String nameOfType(int type) {
        for (NotificationTypeEnum notificationTypeEnum : NotificationTypeEnum.values()) {
            if (notificationTypeEnum.getType() == type) {
                return notificationTypeEnum.getName();
            }
        }
        return "";
    }
}
