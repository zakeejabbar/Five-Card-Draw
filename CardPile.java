import java.util.*;
/**
 * Class extended by the DrawPile and the DiscardPile classes.
 * Has
 *
 * @author Zakee Jabbar(zjabba2, 655784971)
 */
public class CardPile
{
    private List<Card> pile;
    private int pileSize;

    /**
    * Constructor for objects of class CardPile
    */
    public CardPile(char pileType)
    {
        if (pileType == 'C')
        {
            pile = new ArrayList<Card>();            
        }
        else
        {
            int i, j = 0;
            pile = new ArrayList<Card>();
            char[] Suits = {'S', 'C', 'D', 'H'};
            String[] Ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
            int[] Value = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
            int[] Value2 = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 1};
            char[] Colors = {'B', 'B', 'R', 'R'};

            for(i = 0; i < 4; i++)
            {
                for (j = 0; j < 13;j++)
                {
                    Card temp = new Card(Ranks[j], Suits[i], Colors[i], Value[j], Value2[j]);
                    pile.add(temp);
                }
            }
        
            pileSize = pile.size();
            
        }

    }
    
    
    /**
     * Returns the size of the pile
     *
     * @return    the size of pile currently
     */
    public int pileSize()
    {
        return pileSize;
    }
    
    //shufflles the deck
    public void shufflePile()
    {
        Collections.shuffle(pile);
    }
    
    
    /**
     * removes a card from the front of the pile
     *
     * @return    the card which is removed.
     */
    public Card removeCard()
    {
        pileSize--;
        return pile.remove(0);
       
    }
    
    // adds a card into the pile
    public void addCard(Card c)
    {
        pileSize++;
        pile.add(c);
        
    }
}
