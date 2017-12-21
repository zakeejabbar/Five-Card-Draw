
/**
 * This class extends CardPile class. 
 *
 * @author Zakee Jabbar(zjabba2, 655784971)
 */
public class DrawPile extends CardPile
{

    /**
     * Constructor for objects of class DrawPile
     */
    public DrawPile()
    {
        super('R');
    }

    /**
     * draws a card from the pile
     *
     * @return    the card removed
     */
    public Card drawCard()
    {
        Card temp = super.removeCard();
        return temp;
       
    }
}
