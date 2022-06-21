package server.model;

import server.exceptions.NotificationException;
import java.util.GregorianCalendar;

/**
 * @version 16/6/22
 * @author C11836 Jostin Castro Gutierrez, C12916 Fernando Flores Moya, C15079
 * Joshua Mora Garita
 *
 */
public interface SessionAbstractBuilder {

    public void buildSesion();

    public void buildId(String id) throws NotificationException;

    public void buildCategory(String category) throws NotificationException;

    public void buildTopic(String topic) throws NotificationException;

    public void buildExpositor(String expositor) throws NotificationException;

    public void buildDetail(String detail) throws NotificationException;

    public void buildLink(String link) throws NotificationException;

    public void buildPlatform(String platform) throws NotificationException;

    public void buildDate(GregorianCalendar date) throws NotificationException;

    public void builTime(GregorianCalendar time) throws NotificationException;

    public void builDuration(int duration) throws NotificationException;

    public void buildCapacity(int capacity) throws NotificationException;

    public void buildOpen(boolean open) throws NotificationException;

    public Session getSession() throws NotificationException;

}
