import java.util.HashMap;
import java.util.LinkedList;

/**
 * Assignment 4
 * @author Dawson
 *
 * Class Card
 */
public class Card {
    /**
     * class attributes int value
     * class attribute boolean wildcard
     */
    private int value;
    private boolean wildcard;

    /**
     * constructor for Card
     * @param value value
     * @param wildcard wildcard
     */
    public Card(int value, boolean wildcard) {
        this.value = value;
        this.wildcard = wildcard;
    }

    /**
     * get value of Card
     * @return value
     */
    public int getValue() {
        return value;
    }

    /**
     * get is wild card
     * @return wildcard
     */
    public boolean isWildcard() {
        return wildcard;
    }

    /**
     * set bool wildcard value
     * @param isWildcard isWildcard
     */
    public void setWildcard(boolean isWildcard) {
        this.wildcard = isWildcard;
    }

    /**
     * compare 2 cards that are passed to it
     * @param card1 card1
     * @param card2 card2
     * @param playerLinkedList playerLinkedList
     * @return player name
     */
    public static String compareTo(Card card1, Card card2, LinkedList<Player> playerLinkedList){
        //todo add compare method using collection
        //Add a compareTo method that compares two cards – consider both the value and the wildcard status.
        //fixme
        int i = 0;
        if (card1.getValue() > card2.getValue() && !card2.isWildcard()){
            System.out.println("Card 1 wins");
        }else if(card1.getValue() < card2.getValue() && !card1.isWildcard()) {
            System.out.println("Card 2 wins");
            i = 1;
        }else if (card1.getValue() > card2.getValue() && card2.isWildcard()){
            System.out.println("WILDCard 2 wins");
            i = 1;
        }else if(card1.getValue() < card2.getValue() && card1.isWildcard()){
            System.out.println("WILDCard 1 wins");
        }else if(card1.isWildcard() && card2.isWildcard()){
            if(card1.getValue() > card2.getValue()){
                System.out.println("2 wilds! " + "Card 2 wins");
                i = 1;
            }else{
                System.out.println("2 wilds! "+ "Card 1 wins");
            }
        }
        return playerLinkedList.get(i).getName();


    }

    /**
     * two players are passed to the compareTo2 method
     * takes the first card in each player's hand and compares the values and wildcard status
     * whichever player's card wins has their wins increased in the scores HashMap
     * the first card in each person's hand is then removed
     *
     * @param player1 player1
     * @param player2 player2
     * @param scores scores
     * @param playerLinkedList playerLinkedList
     */
    public static void compareTo2(Player player1, Player player2, HashMap<String, Integer> scores, LinkedList<Player> playerLinkedList){
        //todo add compare method using collection
        //Add a compareTo method that compares two cards – consider both the value and the wildcard status.
        int i = 0;
        while (playerLinkedList.getFirst().getHand().size() > 0) {
            String player1Name = player1.getName();
            String player2Name = player2.getName();
            Card card1 = player1.getHand().get(0);
            Card card2 = player2.getHand().get(0);
            String playerName = null;
            if (card1.getValue() > card2.getValue() && !card2.isWildcard()) {
                System.out.println("Card 1 wins");
                playerName = player1Name;
            } else if (card1.getValue() < card2.getValue() && !card1.isWildcard()) {
                System.out.println("Card 2 wins");
                playerName = player2Name;
            } else if (card1.getValue() > card2.getValue() && card2.isWildcard()) {
                System.out.println("WILDCard 2 wins");
                playerName = player2Name;
            } else if (card1.getValue() < card2.getValue() && card1.isWildcard()) {
                System.out.println("WILD Card 1 wins");
                playerName = player1Name;
            } else if (card1.isWildcard() && card2.isWildcard()) {
                if (card1.getValue() > card2.getValue()) {
                    System.out.println("2 wilds! " + "Card 2 wins");
                    playerName = player2Name;
                } else {
                    System.out.println("2 wilds! " + "Card 1 wins");
                    playerName = player1Name;
                }
            }
            int winCount = scores.get(playerName);
            scores.put(playerName, winCount + 1);
            player1.getHand().remove(0);
            player2.getHand().remove(0);
            //System.out.println(i);
        }

    }

}
