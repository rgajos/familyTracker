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
    private long notificationId;

    public Notification() {
    }

    public Notification(String time, String msg, long notificationId) {
        this.time = time;
        this.msg = msg;
        this.notificationId = notificationId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(long notificationId) {
        this.notificationId = notificationId;
    }
    
    
}
