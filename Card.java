
/**
 * Basic information of a card. 
 * Contains all methods to obtain information about a single card.
 *
 * @author Zakee Jabbar (zjabba2, 655784971)
 */
public class Card
{
    private String rank;
    private char suit;
    private char color;
    private int value;
    private int value2;
    

    /**
     * Constructor for objects of class Card
     */
    public Card(String rank, char suit, char color, int value, int value2)
    {
        this.rank = rank;
        this.suit = suit;
        this.color = color;
        this.value = value;
        this.value2 = value2;
    }

    /**
     * Get the rank of the card
     *
     * @return    the rank of the card
     */
    public String getRank()
    {
        return rank;
    }    
    
    
    /**
     * Get the suit of the card
     *
     * @return    the suit of the card
     */
    public char getSuit()
    {
        return suit;
    }
    
    
    /**
     * Get the color of the card
     *
     * @return    the color of the card
     */
    public char getColor()
    {
        return color;
    }
    
    
    /**
     * Get the value of the card
     *
     * @return    the value of the card
     */
    public int getValue()
    {
        return value;
    }
    
    
    /**
     * Get the secondary value of the card
     *
     * @return    the secondary value of the card
     */
    public int getValue2()
    {
        return value2;
    }
    
    
}
