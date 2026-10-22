package clientPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;

public class client2 {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 1234);
             DataInputStream in = new DataInputStream(
                     socket.getInputStream());
             DataOutputStream out = new DataOutputStream(
                     socket.getOutputStream());
             Scanner clavier = new Scanner(System.in)) {
            System.out.println("Client connecte au serveur.");
            while (true) {
                System.out.print("Saisir un entier (0 pour quitter) : ");
                int x = clavier.nextInt();
                out.writeInt(x);
                out.flush();
                if (x == 0) {
                    System.out.println("Fin de la communication.");
                    break;}
                int resultat = in.readInt();
                System.out.println(x + " * 5 = " + resultat);}
        } catch (InputMismatchException e) {
            System.out.println("Erreur : veuillez saisir un entier.");
        } catch (IOException e) {
            System.out.println("Erreur de connexion : " + e.getMessage());}
    }
}

