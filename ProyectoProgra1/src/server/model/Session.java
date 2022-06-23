package server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * @version 16/6/22
 * @author C11836 Jostin Castro Gutierrez, C12916 Fernando Flores Moya, C15079
 * Joshua Mora Garita
 *
 */
public class Session implements Serializable {

    private String sesionId, topic, expositor;
    private String detail, link, platform, category;
    private GregorianCalendar date, time;
    private int duration, capacity;
    private boolean open;
    private ArrayList<String> participantList;

    public Session() {
        participantList = new ArrayList();
    }

    public Session(String sesionId, String topic, String expositor, String detail, String link, String platform, String category, 
            GregorianCalendar date, GregorianCalendar time, int duration, int capacity, boolean open) {
        this.sesionId = sesionId;
        this.topic = topic;
        this.expositor = expositor;
        this.detail = detail;
        this.link = link;
        this.platform = platform;
        this.category = category;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.capacity = capacity;
        this.open = open;
        participantList = new ArrayList();
        
    }
    
    public boolean addUser(String userId) {
        if (participantList.size() < capacity) {
            participantList.add(userId);
            return true;
        }
        return false;
    }
    
    public boolean removeUser(String userId) {
        return participantList.remove(userId);
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSesionId() {
        return sesionId;
    }

    public void setSesionId(String sesionId) {
        this.sesionId = sesionId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getExpositor() {
        return expositor;
    }

    public void setExpositor(String expositor) {
        this.expositor = expositor;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public GregorianCalendar getTime() {
        return time;
    }

    public void setTime(GregorianCalendar time) {
        this.time = time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

}
