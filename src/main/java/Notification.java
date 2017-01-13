/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Radek
 */
public class Notification {

    private String time;
    private String msg;
    private long notificationPeopleId;
    private int notificationType;

    public Notification() {
    }

    public Notification(String msg, long notificationId, String time, int notificationType) {
        this.msg = msg;
        this.notificationPeopleId = notificationId;
        this.time = time;
        this.notificationType = notificationType;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getNotificationPeopleId() {
        return notificationPeopleId;
    }

    public void setNotificationPeopleId(long notificationPeopleId) {
        this.notificationPeopleId = notificationPeopleId;
    }

    public int getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(int notificationType) {
        this.notificationType = notificationType;
    }

    @Override
    public String toString() {
        return "Notification{"
                + "msg='" + msg + '\''
                + ", time='" + time + '\''
                + ", notificationId=" + notificationPeopleId
                + ", notificationType=" + notificationType
                + '}';
    }
}
