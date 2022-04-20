package ClientServer;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Assignment 5
 * @author Dawson
 *
 * Class Player
 */
public class Player implements Serializable {
    /**
     * class variable String name
     * class variable LinkedList<ClientServer.Card> hand
     */
    private String name;
    private final LinkedList<Card> hand;

    /**
     * player constructor
     * @param name name
     */
    public Player(String name) {
        this.name = name;
        this.hand = new LinkedList<>();
    }

    /**
     * get name of player
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * set the name of the player
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get the player's hand of cards (LinkedList)
     * @return hand
     */
    public LinkedList<Card> getHand() {
        return hand;
    }

    /**
     * print all cards in the players hand
     */
    private void printHand(){
       this.hand.forEach(System.out::println);

    }

}
