
/**
 * Stores the cards discarded
 *
 * @author Zakee Jabbar(zjabba2, 655784971)
 */
public class DiscardPile extends CardPile
{

    /**
     * Constructor for objects of class DiscardPile
     */
    public DiscardPile()
    {
        super('C');
    }

    /**
     * adds the card into the discarded pile
     *
     * @param  discarded  the card discarded
     */
    public void inputDiscarded(Card discarded)
    {
        super.addCard(discarded);
    }
}
