package server.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.GregorianCalendar;
import server.exceptions.NotificationException;

/**
 * @version 18/06/2022
 * @author C11836 Jostin Castro Gutierrez, C12916 Fernando Flores Moya, C15079
 * Joshua Mora Garita
 */
public class SessionDirector {

    private int intCont = 0;
    private String contId;

    private File file = new File("src/server/files/sessionId.txt");

    public Session buildSession(SessionAbstractBuilder builder, String topic,
            String expositor, String detail, String link, String category,
            String platform, GregorianCalendar date, int duration, int capacity,
            boolean open) throws NotificationException {
        readId();
        contId = "" + intCont;
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
        writeId();
        return builder.getSession();
    }

    private void readId() {

        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            String cont = bf.readLine();
            if (cont == null) {
                intCont = 0;
            } else {
                intCont = Integer.parseInt(cont);
            }
            bf.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void writeId() {
        try {
            file.delete();
            file.createNewFile();

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "utf-8"));

            bw.write("" + intCont);
            bw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
