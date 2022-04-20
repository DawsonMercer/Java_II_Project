package ClientServer;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


/**
 * Assignment 5
 * @author Dawson
 *
 * class GameClient
 */
public class GameClient {

    /**
     * GameClient Main Method
     * state game location
     * Connect to the game server
     * display player's hand and loop through to display the player's hand to client
     *
     * @param args
     */
    public static void main(String[] args) {
        String hostName = "localhost";
        int portNumber = 4401;

        try (
                Socket clientSocket = new Socket(hostName, portNumber);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                ObjectOutputStream toServer = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream fromServer = new ObjectInputStream(clientSocket.getInputStream());

        ) {
            Player player = null;
            System.out.println("Welcome Player - OneHundreds");

            System.out.print("Enter name: ");
            String name = bufferedReader.readLine();
            toServer.writeObject(new Player(name));
            if (fromServer.readObject().equals(true)) {
                System.out.println(fromServer.readObject());
                toServer.writeObject("Added Client Player "+ player.getName());

            } else {
                System.out.println(fromServer.readObject());
            }

            StringBuilder playerHand = new StringBuilder();
            int count = 0;
            for (Card currentCard : player.getHand()) {
                if (count != player.getHand().size() - 1) {
                    playerHand.append(currentCard.getValue()).append(" ").append("Wildcard Status: "+currentCard.isWildcard()).append(", ");
                } else {
                    playerHand.append(currentCard.getValue()).append(" ").append("Wildcard Status: "+currentCard.isWildcard());
                }
                count++;
            }
            System.out.println("\nPlayer Hand: " + playerHand);

        } catch (UnknownHostException e) {
            System.err.println(e);
            System.exit(1);
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}


