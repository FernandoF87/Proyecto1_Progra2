package server.model;

import server.view.LoginAdmin;

/**
 *
 * @author Fernando Flores Moya
 */
public class SessionSystem {

    public static void main(String[] args) {
        Server server = new Server();
        LoginAdmin loginAdmin = new LoginAdmin(new javax.swing.JFrame(), true, server);
        loginAdmin.setVisible(true);
        server.start();
    }
}
