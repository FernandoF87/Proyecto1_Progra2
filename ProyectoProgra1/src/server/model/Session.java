package server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Class that represents a session with several details and a list of participants
 * 
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
    private ArrayList< String> waitingParticipantsList;

    public static final byte INACTIVE_STATE = 0;
    public static final byte ACTIVE_STATE = 1;
    public static final byte FINALIZED_STATE = 2;

    /**
     * Constructor to be used by builder, initiates list of participants and
     * list of pending users in the case of closed sessions
     */
    public Session() {
        participantList = new ArrayList();
        state = INACTIVE_STATE;
        notifSent = false;
        if (!open) {
            waitingParticipantsList = new ArrayList<>();
        }
    }

    /**
     * Constructor to be used in clone method with all parameters
     *
     * @param sesionId the id of the session
     * @param topic the topic of the session
     * @param expositor the name of the expositor
     * @param detail an additional detail of the session
     * @param link the link to access the session
     * @param platform the name of the platform
     * @param category the description of the category
     * @param date the time and date it will start
     * @param duration the minutes it will last
     * @param capacity the number of people that can enroll
     * @param open a boolean determining if it's open or closed
     * @param notifSent a boolean that determines if the 5 minute notification
     * has been sent
     * @param state the state of the session (inactive, active, finalized)
     */
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
            waitingParticipantsList = new ArrayList<>();
        }
    }

    /**
     * Adds the user to the participant list or puts them in the waiting list
     * accordingly
     *
     * @param userId the email of the user
     * @param accepted a boolean determining if they are in a waiting list or
     * not
     * @return if the user was able to be added or not
     */
    public boolean addUser(String userId, boolean accepted) {
        if (open) {
            if (participantList.size() < capacity) {
                participantList.add(userId);
                return true;
            }
            return false;
        } else {
            if (accepted) {
                int index = waitingParticipantsList.indexOf(userId);
                participantList.add(waitingParticipantsList.get(index));
                waitingParticipantsList.remove(userId);
                return true;
            } else {
                waitingParticipantsList.add(userId);
                System.out.println("Esperando a aceptar a " + userId);
                System.out.println("Lista de espera: " + waitingParticipantsList);
                return true;
            }
        }
    }

    /**
     * Removes the user from both the participant list and waiting list
     *
     * @param userId the email of the user
     * @return if the user was removed from the participant list
     */
    public boolean removeUser(String userId) {
        if (waitingParticipantsList != null) {
            waitingParticipantsList.remove(userId);
        }
        return participantList.remove(userId);
    }

    public byte availableSpaces() {
        if (open) {
            return (byte) (capacity - participantList.size());
        }
        return (byte) (capacity - (participantList.size() + waitingParticipantsList.size()));
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

    public boolean isWaiting(String userId) {

        return waitingParticipantsList.contains(userId);
    }

    public ArrayList<String> getParticipantList() {
        return participantList;
    }

    public ArrayList<String> getWaitingParticipantsList() {
        return waitingParticipantsList;
    }

    @Override
    public Session clone() {
        GregorianCalendar dateClone = (GregorianCalendar) (date.clone());
        return new Session(sesionId, topic, expositor, detail, link, platform, category,
                dateClone, duration, capacity, open, notifSent, state);
    }

    public void deleteWaitingUser(String userId) {

        waitingParticipantsList.remove(userId);

    }

}
