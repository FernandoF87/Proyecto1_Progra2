package model;

import java.util.GregorianCalendar;

/**
 * @version 16/6/22
 * @author C11836 Jostin Castro Gutierrez, C12916 Fernando Flores Moya, C15079
 * Joshua Mora Garita
 *
 */
public interface SesionAbstractBuilder {

    public void buildSesion();

    public void buildId(String id);

    public void buildTopic(String topic);

    public void buildExpositor(String expositor);

    public void buildDetail(String detail);

    public void buildLink(String link);

    public void buildPlatform(String platform);

    public void buildDate(GregorianCalendar date);

    public void builTime(GregorianCalendar time);

    public void builDuration(int duration);

    public void buildCapacity(int capacity);

    public void buildOpen(boolean open);

}
