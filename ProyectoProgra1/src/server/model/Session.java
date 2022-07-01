package server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * @version 16/6/22
 * @author C11836 Jostin Castro Gutierrez, C12916 Fernando Flores Moya, C15079
 * Joshua Mora Garita
 *
 */
public class Session implements Serializable, Cloneable {

    private String sesionId, topic, expositor;
    private String detail, link, platform, category;
    private GregorianCalendar date;
    private int duration, capacity;
    private boolean open, notifSent;
    private byte state;
    private ArrayList<String> participantList;
    private HashMap<String, String> waitingParticipantsList;
    
    public static final byte INACTIVE_STATE = 0;
    public static final byte ACTIVE_STATE = 1;
    public static final byte FINALIZED_STATE = 2;

    public Session() {
        participantList = new ArrayList();
        state = FINALIZED_STATE;
        notifSent = false;
    }

    public Session(String sesionId, String topic, String expositor, String detail, String link, String platform, String category,
            GregorianCalendar date, int duration, int capacity, boolean open, boolean notifSent, byte state) {
        this.sesionId = sesionId;
        this.topic = topic;
        this.expositor = expositor;
        this.detail = detail;
        this.link = link;
        this.platform = platform;
        this.category = category;
        this.date = date;
        this.duration = duration;
        this.capacity = capacity;
        this.open = open;
        this.notifSent = notifSent;
        this.state = state;
        participantList = new ArrayList();
        if (!open) {
            waitingParticipantsList = new HashMap<>();
        }
    }

    public boolean addUser(String userId, boolean accepted) {
        if (open) {
            if (participantList.size() < capacity) {
                participantList.add(userId);
                return true;
            }
            return false;
        } else {
            if (accepted) {
                participantList.add(waitingParticipantsList.get(userId));
                waitingParticipantsList.remove(userId);
                return true;
            } else {
                waitingParticipantsList.put(userId, userId);
                System.out.println("Esperando a aceptar a " + userId);
                System.out.println("Lista de espera: " + waitingParticipantsList);
                return true;
            }
        }
    }

    public boolean removeUser(String userId) {
        return participantList.remove(userId);
    }

    public byte availableSpaces() {
        return (byte) (capacity - participantList.size());
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

    // Metodos nuevos
    public boolean isNotifSent() {
        return notifSent;
    }

    public void setNotifSent(boolean notifSent) {
        this.notifSent = notifSent;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public boolean isParticipant(String userId) {
        for (String id : participantList) {
            if (id.equals(userId)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getParticipantList() {
        return participantList;
    }

    public Session clone() {
        GregorianCalendar dateClone = (GregorianCalendar) (date.clone());
        return new Session(sesionId, topic, expositor, detail, link, platform, category,
                dateClone, duration, capacity, open, notifSent, state);
    }
}
