package server.model;

import server.view.AdminView;

/**
 *
 * @author Fernando Flores Moya
 */
public class SessionSystem {

    public static void main(String[] args) {
        Server server = new Server();
        AdminView adminView = new AdminView(new javax.swing.JFrame(),true, Server.getData(), server);
        adminView.setVisible(true);
      //  server.runServer();
    }
}
