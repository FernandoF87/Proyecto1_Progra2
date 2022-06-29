package server.model;

import server.exceptions.NotificationException;
import java.util.GregorianCalendar;

/**
 * @version 18/06/2022
 * @author C11836 Jostin Castro Gutierrez, C12916 Fernando Flores Moya, C15079
 * Joshua Mora Garita
 */
public class SessionDirector {

    public Session buildSession(SessionAbstractBuilder builder, String sesionId,
            String topic, String expositor, String detail, String link,
            String platform, GregorianCalendar date, int duration, int capacity, 
            boolean open, boolean notifSent, boolean finalized) throws NotificationException {
        builder.buildSesion();
        builder.buildId(link);
        builder.buildTopic(topic);
        builder.buildExpositor(expositor);
        builder.buildDetail(detail);
        builder.buildLink(link);
        builder.buildPlatform(platform);
        builder.buildDate(date);
        builder.builDuration(duration);
        builder.buildCapacity(capacity);
        builder.buildOpen(open);
        builder.buildNotifSent(notifSent);
        builder.buildFinalized(finalized);
        return builder.getSession();
    }

}
