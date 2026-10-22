

package serverPackage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

    public static void main(String[] args) throws IOException {

        ServerSocket serversocket = new ServerSocket(1234);

        System.out.println(
                "Je suis le serveur en attente de la connexion d'un client"
        );

        Socket socket = serversocket.accept();

        System.out.println("Un client est connecté");

        socket.close();
        serversocket.close();
    }
}

