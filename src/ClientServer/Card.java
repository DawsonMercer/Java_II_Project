package ClientServer;

import java.io.Serializable;

/**
 * Assignment 5
 * @author Dawson
 *
 * Class Card
 */
public class Card implements Comparable<Card>, Serializable {
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
     * compare this.Card with card2 on value and wildcard status
     * @param card2 card
     * @return 1 or -1 depending on if this.Card or card2 was the winner
     */
    @Override
    public int compareTo(Card card2){
        if(this.getValue() > card2.getValue() && !this.isWildcard() && !card2.isWildcard()){
            return 1;
        }else if(this.getValue() < card2.getValue() && !this.isWildcard() && !card2.isWildcard()){
            return -1;
        }else if(this.isWildcard() && card2.isWildcard()){
            if(this.getValue() < card2.getValue()){
                return 1;
            }else{
                return -1;
            }
        }else if(this.isWildcard() && !card2.isWildcard()){
            return 1;

        }else{
            return -1;

        }

    }

}
