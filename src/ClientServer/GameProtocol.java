package ClientServer;

import java.util.*;

/**
 * Assignment 5
 * @author Dawson
 *
 * Class Game Protocol
 */
public class GameProtocol{
    private Status gameStatus;
    private static int currentPlayerIndex = 0;
    private static int currentRound = 1;


    ArrayList<String> players = new ArrayList<>();
    ArrayList<Card> cardsPlayedInRound = new ArrayList<>();
    ArrayList<String> winnersList = new ArrayList<>();
    CardDeck cardDeckClass = new CardDeck();
    ArrayList<Card> deckOfCardsList;
    Map<String, Integer> scoresHashMap = new HashMap<>();

    List<Card> thisRoundsWildCards = new ArrayList<>();
    ArrayList<String> resultsOutput = new ArrayList<>();

    /**
     * GameProtocol
     * @param cardDeck cardDeck
     * @param playerList playerList
     */
//    public GameProtocol(CardDeck cardDeck, LinkedList<Player> playerList) {
//        this.cardDeck = cardDeck;
//        this.playerList = playerList;
//        this.gameStatus = Status.NOT_STARTED;
//        this.cardDeck.deal(this.playerList);
//    }


    /**
     * Game Status
     */
    public enum Status{
        NOT_STARTED, PLAY_IN_PROGRESS, GAME_OVER
    }


    /**
     * get game status
     * @return gameStatus
     */
    public Status getGameStatus() {
        return gameStatus;
    }

    /**
     * Determines if the game is over
     * @return this.gameStatus
     */
    public boolean isGameOver(){
        return this.gameStatus == Status.GAME_OVER;
    }


    /**
     * display the score for the players
     */
    public void displayScores(){
        for (Map.Entry<String, Integer> entry : scoresHashMap.entrySet()) {
            String outputLine = entry.getKey() + " - " + entry.getValue();
            resultsOutput.add(outputLine);
        }
        resultsOutput.add("");
    }



}