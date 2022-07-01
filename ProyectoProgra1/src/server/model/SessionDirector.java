package server.model;

import java.util.GregorianCalendar;
import server.exceptions.NotificationException;

/**
 * @version 18/06/2022
 * @author C11836 Jostin Castro Gutierrez, C12916 Fernando Flores Moya, C15079
 * Joshua Mora Garita
 */
public class SessionDirector {

    private int intCont = 0;
    private String contId = "" + intCont;

    public Session buildSession(SessionAbstractBuilder builder, String topic,
            String expositor, String detail, String link, String category,
            String platform, GregorianCalendar date, int duration, int capacity,
            boolean open) throws NotificationException {
        builder.buildSesion();
        builder.buildPlatform(platform);
        builder.buildId(contId);
        builder.buildTopic(topic);
        builder.buildExpositor(expositor);
        builder.buildDetail(detail);
        builder.buildLink(link);
        builder.buildCategory(category);

        builder.buildDate(date);
        builder.builDuration(duration);
        builder.buildCapacity(capacity);
        builder.buildOpen(open);
        intCont++;
        contId = "" + intCont;
        return builder.getSession();
    }

}
