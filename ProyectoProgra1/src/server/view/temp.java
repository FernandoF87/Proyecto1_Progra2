/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server.view;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @version @author Jostin Castro
 */
public class temp {

    public static void main(String[] args) {
        GregorianCalendar date = new GregorianCalendar();
        LocalDateTime l = LocalDateTime.ofInstant(date.toInstant(), date.getTimeZone().toZoneId());
        System.out.println(l);
        GregorianCalendar date2 = new GregorianCalendar(2022, Calendar.JULY, 7);
        LocalDateTime l2 = LocalDateTime.ofInstant(date2.toInstant(), date2.getTimeZone().toZoneId());
        System.out.println(l2);
        Duration duration = Duration.between(l, l2);
        System.out.println(duration.getSeconds());
    }
}
