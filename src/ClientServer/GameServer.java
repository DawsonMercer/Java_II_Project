package ClientServer;


import java.io.*;
import java.net.*;
import java.util.LinkedList;

/**
 * Assignment 5
 * @author Dawson
 *
 * Class GameServer
 */

public class GameServer {
    public static final int NUM_OF_PLAYERS = 4;

    /**
     * Main server loop
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {

        int portNumber = 4401; //Integer.parseInt(args[0]);
        boolean listening = true;
        LinkedList<Player> playerList = new LinkedList<>();

        LinkedList<ObjectOutputStream> outStreamList = new LinkedList<ObjectOutputStream>();
        LinkedList<ObjectInputStream> inStreamList = new LinkedList<ObjectInputStream>();

        //Track the connections
        LinkedList<Socket> clientSocketList = new LinkedList<>();

        //Wait until we have all the connections we need
        System.out.println("= = OneHundreds Server = =");
        System.out.println("Waiting for players...");
        int count = 0;
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (listening & clientSocketList.size() < NUM_OF_PLAYERS) {
                Socket socket = serverSocket.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                outStreamList.add(oos);
                inStreamList.add(ois);
                clientSocketList.add(socket);

                Player player = (Player) ois.readObject();
                playerList.add(player);
                count += 1;
                oos.writeObject(true);
                System.out.println("Added Player");
                oos.writeObject("Player " + count + "- Name: " + player.getName() + " added.");


            }
            System.out.println("Welcome clients - Number of players:" + NUM_OF_PLAYERS);

        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
