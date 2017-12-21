
/**
 * Players super class. Extended by UserPlayer and Computer Player.
 *
 * @author Zakee Jabbar (zjabba2, 655784971)
 */
public class Players
{
    private Card[] hand;
    private int index;
    private String name;
    private char type;
    private int typeOfHand;

    /**
     * Constructor for objects of class Players
     */
    public Players(String n, char t)
    {
        hand = new Card[5];
        index = 0;
        name = n;
        type = t;
        typeOfHand = 0;
    }

    /**
     * Deals a card to the player.
     *
     * @param  c  an object of the Card Class
     * @return    None
     */
    public void dealCard(Card c)
    {
        if (index > 4)
        {
            System.out.println("Trying to add more than 5 cards!!!");
        }
        hand[index] = c;
        index++;
        this.sortHand();
    }

    /**
     * Prints out the players hand.
     *
     * @return None
     */
    public void printHand()
    {
        for(int i = 0; i < 5; i++)
        {
            System.out.print((i+1) + ") " + hand[i].getRank() + hand[i].getSuit() + "  ");

        }
        System.out.println("");
    }

    //returns the name of the player
    public String getName()
    {
        return name;
    }

    // sorts the hand
    public void sortHand()
    {
        int n = index;
        for (int i = 0; i < n-1; i++)
        {
            int mI = i;
            for (int j = i+1; j < n; j++)
            {
                if (hand[j].getValue() > hand[mI].getValue())
                {
                    mI = j;
                }
            }

            Card temp = hand[mI];
            hand[mI] = hand[i];
            hand[i] = temp;
        }
    }
    
    // checks if there is an ace
    public boolean hasAce()
    {
        for(int i = 0; i < hand.length; i++)
        {
            if(hand[i].getRank().equals("A"))
            {
                return true;
            }
        }
        return false;       
    }
    // returns the hand 
    public Card[] getHand()
    {
        return hand;
    }
    
    // sets the evaluation of the hand
    public void setTypeOfHand(int num)
    {
        typeOfHand = num;

    }

    // returns type of hand
    public int getTypeOfHand()
    {
        return typeOfHand;
    }
    
    // returns type of player
    public char getType()
    {
        return type;
    }

    // removes a card
    public Card removeCard(int index)
    {
        Card temp = new Card("0", 'S', 'B', 0, 0);
        Card current = hand[index];
        hand[index] = temp;
        return current;
    }

    // inserts a card at an index
    public void insertCard(int index, Card c)
    {
        hand[index] = c;
    }

    // does the extra credit card sorting
    public void extraCreditSort()
    {
        if(typeOfHand == 1)
        {
            hand = OnePairHelper(hand);
        }
        else if(typeOfHand == 2)
        {
            hand = TwoPairHelper(hand);

        }
        else if(typeOfHand == 3)
        {
            hand= ThreeKindHelper(hand);

        }
        else if(typeOfHand == 6)
        {
            hand = FullHouseHelper(hand);

        }
        else if(typeOfHand == 7)
        {
            hand = FourKindHelper(hand);

        } 
    }

    // the rest of the method help with the extra credit card sorting.
    private Card[] ThreeKindHelper(Card[] hand)
    {
        Card[] sorted = new Card[5];
        if(hand[0].getValue() == hand[1].getValue() 
        && hand[1].getValue() == hand[2].getValue())
        {
            return hand;

        }
        if(hand[1].getValue() == hand[2].getValue() 
        && hand[2].getValue() == hand[3].getValue())
        {
            sorted[0] = hand[1];
            sorted[1] = hand[2];
            sorted[2] = hand[3];
            sorted[3] = hand[0];
            sorted[4] = hand[4];
            return sorted;

        }
        if(hand[2].getValue() == hand[3].getValue() 
        && hand[3].getValue() == hand[4].getValue())
        {
            sorted[0] = hand[2];
            sorted[1] = hand[3];
            sorted[2] = hand[4];
            sorted[3] = hand[0];
            sorted[4] = hand[1];
            return sorted;

        }
        return hand;

    }

    private Card[] OnePairHelper(Card[] hand)
    {
        Card[] sorted = new Card[5];
        if(hand[0].getValue() == hand[1].getValue())
        {
            return hand;

        }
        if(hand[1].getValue() == hand[2].getValue())
        {
            sorted[0] = hand[1];
            sorted[1] = hand[2];
            sorted[2] = hand[0];
            sorted[3] = hand[3];
            sorted[4] = hand[4];
            return sorted;

        }
        if(hand[2].getValue() == hand[3].getValue())
        {
            sorted[0] = hand[2];
            sorted[1] = hand[3];
            sorted[2] = hand[0];
            sorted[3] = hand[1];
            sorted[4] = hand[4];
            return sorted;

        }
        if(hand[3].getValue() == hand[4].getValue())
        {
            sorted[0] = hand[3];
            sorted[1] = hand[4];
            sorted[2] = hand[0];
            sorted[3] = hand[1];
            sorted[4] = hand[2];
            return sorted;

        }
        return hand;

    }

    private Card[] TwoPairHelper(Card[] hand)
    {
        int[] cardCount = new int[15];
        int multipleCardCount = 0;
        int singleCard = 0;
        int highPair = 0;
        int lowPair = 0;
        Card[] sorted = new Card[5];
        int[] highIndexes = {-1, -1};
        int[] lowIndexes = {-1, -1};
        int singleIndex = -1;
        int highDone = 0;
        for(int i =0; i < 5; i++)
        {
            cardCount[hand[i].getValue()] += 1;            
        }

        for(int i = 0; i < 15; i++)
        {
            if(cardCount[i] ==  1)
            {
                singleCard = i;
            }
            if (cardCount[i] == 2 && highDone == 0)
            {
                highPair = i;
                highDone = 1;
            }
            if(cardCount[i] == 2 && highPair != 0)
            {
                lowPair = i;
            }
        }

        if(lowPair > highPair)
        {
            int temp = highPair;
            highPair = lowPair;
            lowPair = temp;
        }

        for(int i =0; i < 5; i++)
        {
            if(hand[i].getValue() == singleCard)
            {
                singleIndex = i;
            }
            if(hand[i].getValue() == highPair)
            {
                if(highIndexes[0] == -1)
                {
                    highIndexes[0] = i;
                }
                else
                {
                    highIndexes[1] = i;
                }
            }
            if(hand[i].getValue() == lowPair)
            {
                if(lowIndexes[0] == -1)
                {
                    lowIndexes[0] = i;
                }
                else
                {
                    lowIndexes[1] = i;
                }
            }
        }

        sorted[0] = hand[highIndexes[0]];
        sorted[1] = hand[highIndexes[1]];
        sorted[2] = hand[lowIndexes[0]];
        sorted[3] = hand[lowIndexes[1]];
        sorted[4] = hand[singleIndex];

        return sorted;
    }

    private Card[] FourKindHelper(Card[] hand)
    {
        Card[] sorted = new Card[5];
        if(hand[0].getValue() == hand[1].getValue() && hand[1].getValue() == hand[2].getValue() 
        && hand[2].getValue() == hand[3].getValue())
        {
            return hand;           
        }
        if(hand[1].getValue() == hand[2].getValue() && hand[2].getValue() == hand[3].getValue() 
        && hand[3].getValue() == hand[4].getValue())
        {
            sorted[0] = hand[1];
            sorted[1] = hand[2];
            sorted[2] = hand[3];
            sorted[3] = hand[4];
            sorted[4] = hand[0];
            return sorted;        
        }
        return hand;
    }

    private Card[] FullHouseHelper(Card[] hand)
    {
        Card[] sorted = new Card[5];
        if(hand[0].getValue() == hand[1].getValue() && hand[1].getValue() == hand[2].getValue() 
        && hand[3].getValue() == hand[4].getValue())
        {
            return hand;         
        }
        if(hand[0].getValue() == hand[1].getValue() && hand[2].getValue() == hand[3].getValue() 
        && hand[3].getValue() == hand[4].getValue())
        {
            sorted[0] = hand[2];
            sorted[1] = hand[3];
            sorted[2] = hand[4];
            sorted[3] = hand[0];
            sorted[4] = hand[1];
            return sorted;                
        }
        return hand;

    }

}