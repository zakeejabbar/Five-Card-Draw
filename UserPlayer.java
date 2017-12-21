import java.util.*;
/**
 * Deals with the User Player.
 *
 * @author Zakee Jabbar (zjabba2, 655784971)
 */
public class UserPlayer extends Players
{
    /**
     * Constructor for objects of class UserPlayer
     */
    public UserPlayer(String n, char t)
    {
        super(n,t);
    }

    /**
     * Takes the user input and discards those cards to the discard pile.
     * Then it draws the remaining cards. Deals with user errors in input.
     *
     * @param  dcp  The discardPile of the game.
     * @param  dp   The drawPile of the game.
     * @return    None
     */
    public void discardAndDraw(DiscardPile dcp, DrawPile dp, String handType)
    {
        System.out.println("The cards in your hand are: ");
        this.printHand();
        System.out.println("Your hand currently evaluates to " + handType);

        boolean hasAce = this.hasAce();
        int numToRemove = 0;
        boolean flag = true;

        if(hasAce == true)
        {
            System.out.println("Since you have an Ace you can keep the Ace and discard the other four cards.");
        }

        Scanner sc = new Scanner(System.in);
        // errpr handling
        while(flag)
        {
            System.out.println("How many cards would you like to get rid off?");
            if(sc.hasNextInt())
            {
                int num = sc.nextInt();
                if(num > 4)
                {
                    System.out.println("You can't get rid of that many cards!");
                }                   
                else if(num > 3 && hasAce == false)
                {
                    System.out.println("You can't get rid of four cards. You don't have an ace!\n");
                }
                else if(num > 4 && hasAce == true)
                {
                    System.out.println("You can't get rid of more than 4 cards.\n");
                }
                else
                {
                    numToRemove = num;
                    flag= false;
                }
            }
            else
            {
                System.out.println("Please enter a number!\n");
                sc.nextLine();
            }
        }
        if(numToRemove == 0)
        {
            System.out.println("You chose not to remove any cards");
            return;
        }
        flag = true;
        String[] values;
        List<Integer> indexRemoved = new ArrayList<Integer>();     
        int restart = 0;
        //error handling
        while(flag)
        {
            System.out.println("Which cards would you like to remove? Enter the numbers, comma separated");
            String discardEntries = sc.next();
            values = discardEntries.split("\\s*,\\s*");
            if(values.length > numToRemove)
            {
                System.out.println("Too many values inputted");
                continue;
            }
            if(values.length < numToRemove)
            {
                System.out.println("Not enough values inputted.");
                continue;
            }

            for(int i = 0;i < values.length;i++)
            {
                try{
                    int index = Integer.parseInt(values[i]) - 1;
                    if(index > 4)
                    {
                        System.out.println("Card number doesn't exist!");
                        restart = 1;
                        break;
                    }
                    if(index == 0 && numToRemove == 4)
                    {
                        System.out.println("Can't discard the ace if discarding four cards");
                        restart = 1;
                        break;
                    }
                }catch(NumberFormatException e)
                {
                    System.out.println("Please enter only numbers!");
                    restart = 1;
                    break;
                }       
            }
            if(restart == 1)
            {
                restart = 0;
                continue;
            }

            if(restart == 0)
            {
                for(int i = 0;i < values.length;i++)
                {
                    indexRemoved.add(Integer.parseInt(values[i]) - 1);
                }          
                flag = false;
            }
        }

        // remove cards
        for(Integer index: indexRemoved)
        {
            Card removed = this.removeCard(index);
            dcp.inputDiscarded(removed);

        }

        // add cards
        for(Integer index: indexRemoved)
        {
            this.insertCard(index, dp.drawCard());
        }
        this.sortHand();

        System.out.println("Your new hand is: ");
        this.printHand();
    }
}
