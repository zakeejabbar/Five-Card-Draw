import java.util.*;
/**
 * Extends the Players class. Deals with Computer Players and the AI.
 *
 * @author Zakee Jabbar (zjabba2, 655784971)
 */
public class OpponentPlayer extends Players
{

    /**
     * Constructor for objects of class ComputerPlayer
     */
    public OpponentPlayer(String n, char t)
    {
        super(n,t);
    }

    /**
     * Runs the AI for the computer players
     *
     * @param  dcp  The Discard Pile in the game
     * @param  dp  The Draw Pile in the game
     * @return    None
     */
    public void takeTurn(DiscardPile dcp, DrawPile dp, String handType)
    {        
        
        int numTypeOfHand = this.getTypeOfHand();
        Card[] temp = this.getHand();
        int cardsRemoved = 0;
        List<Integer> indexRemoved = new ArrayList<Integer>();
        int done = 0;
        //check what type of hand it is and then removed the cards necessary.
        if(numTypeOfHand > 0)
        {
            if(numTypeOfHand == 7)
            {
                int index = FourKindHelper(temp);
                indexRemoved.add(index);
                cardsRemoved = 1;
                done = 1;
            }
            if(numTypeOfHand == 3)
            {
                int[] pair = ThreeKindHelper(temp);
                indexRemoved.add(pair[0]);
                indexRemoved.add(pair[1]);
                cardsRemoved = 2;
                done = 1;
            }
            if(numTypeOfHand == 2)
            {
                int index = TwoPairHelper(temp);
                indexRemoved.add(index);
                cardsRemoved = 1;
                done = 1;
            }
            if(numTypeOfHand == 1)
            {
                int[] pair = OnePairHelper(temp);
                if(pair[0] == -1)
                {
                    System.out.println("Negative 1 occurred");
                }
                indexRemoved.add(pair[0]);
                indexRemoved.add(pair[1]);
                indexRemoved.add(pair[2]);
                cardsRemoved = 3;
                done = 1;
            }  

        }
        if(numTypeOfHand == 0  && done == 0)
        {
            if(temp[0].getSuit() == temp[1].getSuit() && temp[1].getSuit() == temp[2].getSuit() 
            && temp[2].getSuit() == temp[3].getSuit())
            {
                indexRemoved.add(4);
                cardsRemoved = 1;
                done = 1;
            }
            else if(temp[1].getSuit() == temp[2].getSuit() && temp[2].getSuit() == temp[3].getSuit() 
            && temp[3].getSuit() == temp[4].getSuit())
            {
                indexRemoved.add(0);
                cardsRemoved = 1;
                done = 1;
            }
        }
        if(numTypeOfHand == 0 && done == 0)
        {
            int index = fourInRow(temp);
            if(index != -1)
            {
                indexRemoved.add(index);
                cardsRemoved = 1;
                done = 1;
            }

        }
        if(this.hasAce() == true && done == 0)
        {
            indexRemoved.add(1);
            indexRemoved.add(2);
            indexRemoved.add(3);
            indexRemoved.add(4);
            cardsRemoved = 4;
            done = 1;

        }
        if(done == 0)
        {
            indexRemoved.add(2);
            indexRemoved.add(3);
            indexRemoved.add(4);
            cardsRemoved = 3;
            done = 1;
        }
        
        //removes cards
        for(Integer index: indexRemoved)
        {
            Card removed = this.removeCard(index);
            dcp.inputDiscarded(removed);
            
        }
        
        //replaces cards
        for(Integer index: indexRemoved)
        {
            this.insertCard(index, dp.drawCard());
        }
        
        this.sortHand();
        
        System.out.println(this.getName() + " discarded " + cardsRemoved + " cards");
        

    }

    // checks if there are 4 in a row
    private int fourInRow(Card[] hand)
    {
        if(hand[0].getValue() - 1 == hand[1].getValue() && hand[1].getValue() - 1 == hand[2].getValue()
        && hand[2].getValue() - 1 == hand[3].getValue())
        {
            return 4;
        }
        if(hand[1].getValue() - 1 == hand[2].getValue() && hand[2].getValue() - 1 == hand[3].getValue()
        && hand[3].getValue() - 1 == hand[4].getValue())
        {
            return 0;
        }            
        return -1;
    }

    // gets the cards to remove for the four of a kind
    private int FourKindHelper(Card[] hand)
    {
        if(hand[0].getValue() == hand[1].getValue() && hand[1].getValue() == hand[2].getValue() 
        && hand[2].getValue() == hand[3].getValue())
        {
            return 4;           
        }
        if(hand[1].getValue() == hand[2].getValue() && hand[2].getValue() == hand[3].getValue() 
        && hand[3].getValue() == hand[4].getValue())
        {
            return 0;         
        }
        return -1;
    }

    //// gets the cards to remove for the three of a kind
    private int[] ThreeKindHelper(Card[] hand)
    {
        if(hand[0].getValue() == hand[1].getValue() 
        && hand[1].getValue() == hand[2].getValue())
        {
            int[] pair = {3,4};
            return pair;

        }
        if(hand[1].getValue() == hand[2].getValue() 
        && hand[2].getValue() == hand[3].getValue())
        {
            int[] pair = {0,4};
            return pair;

        }
        if(hand[2].getValue() == hand[3].getValue() 
        && hand[3].getValue() == hand[4].getValue())
        {
            int[] pair = {0,1};
            return pair;

        }
        int[] pair = {-1, -1};
        return pair;

    }

    //// gets the cards to remove for the two pair
    private int TwoPairHelper(Card[] hand)
    {
        int[] cardCount = new int[15];
        int multipleCardCount = 0;
        int singleCard = 0;
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
            for(int i = 0; i < 14; i++)
            {
                if(cardCount[i] == 1)
                {
                    singleCard = i;
                }
            }
        }

        for(int i =0; i < 5; i++)
        {
            if(hand[i].getValue() == singleCard)
            {
                return i;
            }
        }
        return -1;
    }

    // // gets the cards to remove for the one pair.
    private int[] OnePairHelper(Card[] hand)
    {
        if(hand[0].getValue() == hand[1].getValue())
        {
            int[] pair = {2,3,4};
            return pair;

        }
        if(hand[1].getValue() == hand[2].getValue())
        {
            int[] pair = {0,3,4};
            return pair;

        }
        if(hand[2].getValue() == hand[3].getValue())
        {
            int[] pair = {0,1,4};
            return pair;

        }
        if(hand[3].getValue() == hand[4].getValue())
        {
            int[] pair = {0,1,2};
            return pair;

        }
        int[] pair = {-1, -1};
        return pair;

    }
}
