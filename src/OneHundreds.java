import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Assignment 4
 * @author Dawson
 *
 * class OneHundred
 */
public class OneHundreds {
    public static void main(String[] args) {

        //todo add range 2-4 if outside error
        System.out.println("Enter Number of Players: (2-4) ");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        int i = 1;

        LinkedList<Player> playerLinkedList = new LinkedList<Player>();
        while (i <= input){
            System.out.println("Enter name for Player " + i + ": ");
            String name = scanner.next();
            LinkedList<Card> hand = new LinkedList<Card>();
            Player player = new Player(name, hand);
            playerLinkedList.add(player);
            i++;

        }

        HashMap<String, Integer> scores = new HashMap<String, Integer>();
        for(Player player : playerLinkedList){
            scores.put(player.getName(), 0);
        }

        ArrayList<Card> deckOfCards = CardDeck.generateDeck();
        CardDeck.shuffleDeck(deckOfCards);


        dealCards(playerLinkedList, deckOfCards);
        playGame(playerLinkedList, scores);
        //playGame2(playerLinkedList, scores);
        if(CardDeck.cardsRemaining(deckOfCards) != 0){
            System.out.println("Remaining cards");
            for(Card card : deckOfCards){
                System.out.println("Value: "+card.getValue() + "- Wild:"+ card.isWildcard());
            }
        }

        //display score
        AtomicInteger maxWins = new AtomicInteger();
        AtomicReference<String> winner = new AtomicReference<>();
        scores.forEach((name, score) ->{
            System.out.println("Name: " + name + " Score: " + score);
            if(score > maxWins.get()){
                maxWins.set(score);
                winner.set(name);
            }
        });
        System.out.println("And the winner is....");
        System.out.println(winner +" - " +maxWins);


    }

    /**
     * take the top card off of the deck and give it to the player
     * remove that card from the deck
     * continue until there are less cards availeble than there are players
     * @param playerLinkedList playerLinkedList
     * @param deckOfCards deckOfCards
     */
    public static void dealCards(LinkedList<Player> playerLinkedList, ArrayList<Card> deckOfCards){
        do{
            for (Player player : playerLinkedList){
                player.getHand().add(deckOfCards.get(0));
                deckOfCards.remove(0);
            }
            //System.out.println("Deck size" + deckOfCards.size());
        }while(deckOfCards.size() >= playerLinkedList.size());
    }

    public static void playGame(LinkedList<Player> playerLinkedList, HashMap<String, Integer> scores){
        int i = 0;
        while (i < playerLinkedList.getFirst().getHand().size()){
            LinkedList<Card> comparePlayerCards = new LinkedList<Card>();
            for(Player player : playerLinkedList){
                System.out.println(player.getName() + player.getHand().get(i).getValue());
                Card card = player.getHand().get(i);
                comparePlayerCards.add(card);
            }
            String winnerName = Card.compareTo(comparePlayerCards.get(0), comparePlayerCards.get(1), playerLinkedList);
            int winCount = scores.get(winnerName);
            scores.put(winnerName, winCount + 1);
            i++;
            System.out.println(scores);

        }

    }

    /**
     * for players in the playerLinkedList
     *
     * @param playerLinkedList playerLinkedList
     * @param scores scores
     */
    public static void playGame2(LinkedList<Player> playerLinkedList, HashMap<String, Integer> scores){
        int i = 0;
        //LinkedList<Card> comparePlayerCards = new LinkedList<Card>();
        for(Player player : playerLinkedList){
            System.out.println(player.getName() + player.getHand().get(i).getValue());
            //Card card = player.getHand().get(i);
            //comparePlayerCards.add(card);
        }
        Card.compareTo2(playerLinkedList.get(0), playerLinkedList.get(1), scores, playerLinkedList);
        System.out.println("CompareTo2: scores- "+ scores);


    }
}
