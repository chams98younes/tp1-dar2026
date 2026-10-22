package clientPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class client3 {
    public static void main(String[] args) {
        final String serverIP = "192.168.56.1"; // IP du serveur
        final int port = 1234;
        try {
            InetAddress adresse = InetAddress.getByName(serverIP);
            InetSocketAddress pointConnexion =
                    new InetSocketAddress(adresse, port);
            try (Socket socket = new Socket()) {
                socket.connect(pointConnexion);
                try (DataInputStream in = new DataInputStream(
                        socket.getInputStream());
                     DataOutputStream out = new DataOutputStream(
                             socket.getOutputStream());
                     Scanner clavier = new Scanner(System.in)) {
                    System.out.println("Connecte au serveur !");
                    while (true) {
                        System.out.println("\n=== CALCULATRICE ===");
                        System.out.println("1. Addition");
                        System.out.println("2. Soustraction");
                        System.out.println("3. Multiplication");
                        System.out.println("4. Division");
                        System.out.println("0. Quitter");
                        System.out.print("Votre choix : ");
                        int choix = clavier.nextInt();
                        if (choix == 0) {
                            out.writeUTF("EXIT");
                            out.flush();
                            System.out.println("Fin de la connexion.");
                            break;}
                        if (choix < 1 || choix > 4) {
                            System.out.println("Choix invalide.");
                            continue;}
                        System.out.print("Entrez le premier nombre : ");
                        double x = clavier.nextDouble();
                        System.out.print("Entrez le deuxieme nombre : ");
                        double y = clavier.nextDouble();
                        out.writeUTF(String.valueOf(choix));
                        out.writeDouble(x);
                        out.writeDouble(y);
                        out.flush();
                        String erreur = in.readUTF();
                        if (!erreur.isEmpty()) {
                            System.out.println("Erreur : " + erreur);
                        } else {
                            double resultat = in.readDouble();
                            System.out.println("Resultat : " + resultat);}
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur de connexion : "
                    + e.getMessage());
            System.out.println(
                    "Verifiez l'IP, le port et le pare-feu.");}}}
