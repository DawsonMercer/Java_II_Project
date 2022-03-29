import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Assignment 4
 * @author Dawson
 *
 * class CardDecl
 */
public class CardDeck {

    /**
     * generates a deck of cards based on the min and max values
     * assigns 4 random cards to a wild card value
     * @return CardDeck deck
     */
    public static ArrayList<Card> generateDeck(){
        int min = 1;
        int max = 100;
        int value = 1;
        ArrayList<Card> deck = new ArrayList<Card>();

        ArrayList<Integer> wildcardsArray = new ArrayList<Integer>();
        while (wildcardsArray.size() < 4) {
          int random =ThreadLocalRandom.current().nextInt(min, max + 1);
          if (!wildcardsArray.contains(random)) {
              wildcardsArray.add(random);
          }
        }
        //System.out.println("without duplicates"+ wildcardsArray);
        while (value < max+1){
          Card card = new Card(value, false);
          deck.add(card);
          if (wildcardsArray.contains(card.getValue())){
              System.out.println("WildCard " + card.getValue());
              card.setWildcard(true);
          }
          value ++;
        }
//        System.out.println(wildcardsArray);
//        for (Card card : deck){
//          System.out.println(card.getValue() + " - " + card.isWildcard());
//        }
        return deck;
    }

    /**
     * shuffl4e cards in the CardDeck
     * @param deck deck
     */
    public static void shuffleDeck(ArrayList<Card> deck){
        Collections.shuffle(deck);

    }

    /**
     * print all cards in the CardDeck
     * @param deck deck
     */
    public static void printDeck(ArrayList<Card> deck){
        deck.forEach(card -> System.out.println(card.getValue() + "-"+ card.isWildcard()));

    }

    /**
     * return number of cards left in deck
     * @param deck deck
     * @return deck.size()
     */
    public static int cardsRemaining(ArrayList<Card> deck){
        return deck.size();
    }
}
