package serverPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class server3 {
    public static void main(String[] args) {
        final int port = 1234;
        final String serverIP = "192.168.56.1"; // A modifier
        try {
            InetAddress adresse = InetAddress.getByName(serverIP);
            InetSocketAddress pointConnexion =
                    new InetSocketAddress(adresse, port);
            try (ServerSocket serverSocket = new ServerSocket()) {
                serverSocket.bind(pointConnexion);
                System.out.println("Serveur demarre sur : "
                        + serverSocket.getInetAddress().getHostAddress()
                        + ":" + port);
                while (true) {
                    try (Socket socket = serverSocket.accept();
                         DataInputStream in = new DataInputStream(
                                 socket.getInputStream());
                         DataOutputStream out = new DataOutputStream(
                                 socket.getOutputStream())) {
                        System.out.println("Client connecte : "
                                + socket.getInetAddress());
                        boolean continuer = true;
                        while (continuer) {
                            String operation = in.readUTF();
                            if (operation.equals("EXIT")) {
                                continuer = false;
                                continue;}
                            double x = in.readDouble();
                            double y = in.readDouble();
                            double resultat = 0;
                            String erreur = "";
                            switch (operation) {
                                case "1":
                                    resultat = x + y;
                                    break;
                                case "2":
                                    resultat = x - y;
                                    break;
                                case "3":
                                    resultat = x * y;
                                    break;
                                case "4":
                                    if (y == 0) {
                                        erreur = "Division par zero impossible.";
                                    } else {
                                        resultat = x / y;}
                                    break;
                                default:
                                    erreur = "Operation invalide.";}
                            out.writeUTF(erreur);
                            if (erreur.isEmpty()) {
                                out.writeDouble(resultat);}
                            out.flush();}
                        System.out.println("Client deconnecte.");}}}
        } catch (IOException e) {
            System.out.println("Erreur serveur : " + e.getMessage());}}}
