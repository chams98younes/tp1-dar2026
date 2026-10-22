package serverPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class server2 {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Serveur en attente d'un client...");
            try (Socket socket = serverSocket.accept();
                 DataInputStream in = new DataInputStream(
                         socket.getInputStream());
                 DataOutputStream out = new DataOutputStream(
                         socket.getOutputStream())) {
                System.out.println("Un client est connecte.");
                while (true) {
                    int x = in.readInt();
                    if (x == 0) {
                        System.out.println("Le client a saisi 0.");
                        break;}
                    int resultat = x * 5;
                    System.out.println("Entier recu : " + x);
                    System.out.println("Resultat : " + resultat);
                    out.writeInt(resultat);
                    out.flush();}
                System.out.println("Communication terminee.");}
        } catch (IOException e) {
            System.out.println("Erreur serveur : " + e.getMessage());}
    }
}

