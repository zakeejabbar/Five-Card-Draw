import java.util.*;
/**
 * Evaluates the hand and gives each hand a ranking. Also deals with ties.
 *
 * @author Zakee Jabbar(zjabba2, 655784971)
 */
public class EvaluateHands
{

    /**
     * Constructor for objects of class EvaluateHands
     */
    public EvaluateHands()
    {

    }

    /**
     * Main function for the class. Calls the other functions for each type of hand.
     *
     * @param  p  a player in the current game
     * @return    a number that corresponds to a certain type of hand.
     */
    public int evaluateHand(Players p)
    {
        Card[] temp = p.getHand();
        if(isStraightFlush(temp) == true)
        {
            return 8;
        }
        else if(isFourKind(temp) == true)
        {
            return 7;
        }
        else if(isFullHouse(temp) == true)
        {
            return 6;
        }
        else if(isFlush(temp) == true)
        {
            return 5;
        }
        else if(isStraight(temp) == true)
        {
            return 4;
        }
        else if(isThreeKind(temp) == true)
        {
            return 3;
        }
        else if(isTwoPair(temp) == true)
        {
            return 2;
        }
        else if(isOnePair(temp) == true)
        {
            return 1;
        }
        else
        {
            return 0;
        }       

    }

    // checks if it is a straight flush
    private boolean isStraightFlush(Card[] hand)
    {
        if(isStraight(hand) == true && isFlush(hand) == true)
        {
            return true;
        }
        return false;

    }

    //checks if it is four of a kind
    private boolean isFourKind(Card[] hand)
    {
        if(hand[0].getValue() == hand[1].getValue() && hand[1].getValue() == hand[2].getValue() 
        && hand[2].getValue() == hand[3].getValue())
        {
            return true;            
        }
        if(hand[1].getValue() == hand[2].getValue() && hand[2].getValue() == hand[3].getValue() 
        && hand[3].getValue() == hand[4].getValue())
        {
            return true;            
        }
        return false;

    }

    // checks if it is a full house
    private boolean isFullHouse(Card[] hand)
    {
        if(hand[0].getValue() == hand[1].getValue() && hand[1].getValue() == hand[2].getValue() 
        && hand[3].getValue() == hand[4].getValue())
        {
            return true;            
        }
        if(hand[0].getValue() == hand[1].getValue() && hand[2].getValue() == hand[3].getValue() 
        && hand[3].getValue() == hand[4].getValue())
        {
            return true;            
        }
        return false;

    }

    //checks if it is a flush.
    private boolean isFlush(Card[] hand)
    {
        if(hand[0].getSuit() == hand[1].getSuit() && hand[1].getSuit() == hand[2].getSuit() 
        && hand[2].getSuit() == hand[3].getSuit() && hand[3].getSuit() == hand[4].getSuit())
        {
            return true;            
        }
        return false;
    }
    
    // checks if it is a straight. Also checks the case where ace is has the value of 1
    private boolean isStraight(Card[] hand)
    {
        if(hand[0].getValue() - 1 == hand[1].getValue() && hand[1].getValue() - 1 == hand[2].getValue()
        && hand[2].getValue() - 1 == hand[3].getValue() && hand[3].getValue() - 1 == hand[4].getValue())
        {
            return true;
        }
        if(hand[1].getValue2() - 1 == hand[2].getValue2() && hand[2].getValue2() - 1 == hand[3].getValue2()
        && hand[3].getValue2() - 1 == hand[4].getValue2() && hand[4].getValue2() - 1 == hand[0].getValue2())
        {
            return true;            
        }
        return false;

    }
    
    //checks if it is a three of a kind
    private boolean isThreeKind(Card[] hand)
    {
        if(hand[0].getValue() == hand[1].getValue() 
        && hand[1].getValue() == hand[2].getValue())
        {
            return true;

        }
        if(hand[1].getValue() == hand[2].getValue() 
        && hand[2].getValue() == hand[3].getValue())
        {
            return true;

        }
        if(hand[2].getValue() == hand[3].getValue() 
        && hand[3].getValue() == hand[4].getValue())
        {
            return true;

        }
        return false;
    }
    
    //checks if it is a two pair
    private boolean isTwoPair(Card[] hand)
    {
        int[] cardCount = new int[15];
        int multipleCardCount = 0;
        for(int i =0; i < 5; i++)
        {
            cardCount[hand[i].getValue()] += 1;            
        }
        
        for(int i = 0; i < 14; i++)
        {
            if(cardCount[i] > 1)
            {
                multipleCardCount += 1;
            }
        }
        
        if(multipleCardCount == 2)
        {
            return true;
        }
        return false;
    }

    // checks if it is a onePair
    private boolean isOnePair(Card[] hand)
    {
        for(int i = 0; i < 4; i++)
        {
            if(hand[i].getValue() == hand[i+1].getValue())
            {
                return true;
            }
        }
        return false;
    }
    
    // returns the string version of the number
    public String numToString(int handNum)
    {
        String handString;
        
        switch (handNum) {
            case 0:  handString = "High Card";
                     break;
            case 1:  handString = "One-pair";
                     break;
            case 2:  handString = "Two-pair";
                     break;
            case 3:  handString = "Three of a kind";
                     break;
            case 4:  handString = "Straight";
                     break;
            case 5:  handString = "Flush";
                     break;
            case 6:  handString = "Full House";
                     break;
            case 7:  handString = "Four of a kind";
                     break;
            case 8:  handString = "Straight flush";
                     break;
            default: handString = "Invalid hand";
                     break;
        }
        
        
        return handString;
        
    }
    
}
