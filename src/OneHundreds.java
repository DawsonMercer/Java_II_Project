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
    /**
     * get input from user for number of player adn get name of players
     * create deck of cards and shuffle the deck
     * call the playGame method
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("=====One Hundreds=====");
        Scanner scanner = new Scanner(System.in);
        int input=0;
        while(true){
            System.out.println("Enter Number of Players: (2-4) ");
            input = scanner.nextInt();
            if (input < 2 || input >4){
                continue;

            }else{
                break;
            }

        }

        int i = 1;
        LinkedList<Player> playerLinkedList = new LinkedList<>();
        while (i <= input){
            System.out.println("Enter name for Player " + i + ": ");
            String name = scanner.next();
            LinkedList<Card> hand = new LinkedList<>();
            Player player = new Player(name, hand);
            playerLinkedList.add(player);
            i++;

        }

        HashMap<String, Integer> scores = new HashMap<>();
        for(Player player : playerLinkedList){
            scores.put(player.getName(), 0);
        }

        ArrayList<Card> deckOfCards = CardDeck.generateDeck();
        CardDeck.shuffleDeck(deckOfCards);


        dealCards(playerLinkedList, deckOfCards);
        playGame(playerLinkedList, scores);

        if(CardDeck.cardsRemaining(deckOfCards) != 0){
            System.out.println("Remaining cards");
            for(Card card : deckOfCards){
                System.out.println("Value: "+card.getValue() + (card.isWildcard() ? "-WILD" : ""));
            }
        }

        //display score
        AtomicInteger maxWins = new AtomicInteger();
        //AtomicReference<String> winner = new AtomicReference<>();
        System.out.println("\n===Final SCORE===");
        scores.forEach((name, score) ->{
            System.out.println(name + " Score: " + score);
            if(score > maxWins.get()){
                maxWins.set(score);
                //winner.set(name);
            }
        });
        System.out.println("\nAnd the winner is....");
        scores.forEach((name, score)->{
            if(score == maxWins.get()){
                System.out.println(name +" - " +maxWins);
            }
        });

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
        }while(deckOfCards.size() >= playerLinkedList.size());
    }


    /**
     * for players in the playerLinkedList get the first card in the player's hadn and add it to the comparePlayerCardsList
     * if the current player's card beats the winningPlayer's card, they are the winner and will be used in the next iteration of the loop
     *
     * @param playerLinkedList playerLinkedList
     * @param scores scores
     */
    public static void playGame(LinkedList<Player> playerLinkedList, HashMap<String, Integer> scores){
        do{
            LinkedList<Card> comparePlayerCardsList = new LinkedList<Card>();
            int winningPlayer = 0;
            for(Player player: playerLinkedList){
                Card card = player.getHand().get(0);
                player.getHand().remove(0);
                System.out.println(player.getName() + " - " + card.getValue() +(card.isWildcard() ? "-WILD" : ""));
                comparePlayerCardsList.add(card);
                if(card.compareTo(comparePlayerCardsList.get(winningPlayer))>0){
                    winningPlayer = playerLinkedList.indexOf(player);
                }
            }
            String winnersName = playerLinkedList.get(winningPlayer).getName();
            System.out.println("Winner: "+ winnersName + " " + comparePlayerCardsList.get(winningPlayer).getValue() + (comparePlayerCardsList.get(winningPlayer).isWildcard() ? "-WILD " : ""));
            System.out.println("");
            int totalWins = scores.get(winnersName) +1;
            scores.put(winnersName, totalWins);

        }while(playerLinkedList.getFirst().getHand().size() > 0);

    }
}
