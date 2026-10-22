
package clientPackage;

import java.io.IOException;
import java.net.Socket;

public class client {

    public static void main(String[] args) {

        System.out.println("Je suis un client pas encore connecté");

        try {
            Socket socket = new Socket("localhost", 1234);

            System.out.println("Je suis un client connecté");

            socket.close();

        } catch (IOException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        }
    }
}
