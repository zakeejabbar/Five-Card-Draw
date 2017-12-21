import java.util.*;
/**
 * Contains the main method. Makes instances of the other classes and runs the game
 *
 * @author Zakee Jabbar( zjabba2, 655784971)
 */
public class Game
{
    /**
     * Constructor for objects of class Game
     */
    public Game()
    {

    }

    /**
     * Runs the 5-Card Draw game
     *
     * @param  Strings args[] for the command line arguments
     * @return    1 if game runs properly, 0 Otherwise
     */
    public static void main(String args[])
    {
        System.out.println("Welcome to 5-Card Draw Poker by Zakee Jabbar");
        int computerPlayers = 0;
        EvaluateHands eH = new EvaluateHands();
        Scanner sc = new Scanner(System.in);
        while(true)
        {
            // Checcks how many playerrs to play with.
            System.out.println("How many computer players would you like to play with?");
            if(sc.hasNextInt())
            {
                int num = sc.nextInt();
                if(num == 0)
                {
                    System.out.println("Its no fun playing by yourself. Enter a number between 1 -4!\n");
                }
                else if( num > 4)
                {
                    System.out.println("Too many computer players! Enter a number between 1 - 4!\n");
                }
                else if (num < 0)
                {
                    System.out.println("There can't be negative number of players!. Enter a number between 1 - 4!\n");
                }
                else
                {
                    computerPlayers = num;
                    break;
                }
            }
            else
            {
                System.out.println("Please enter a number!\n");
                sc.nextLine();
            }
        }

        int i = 0;
        Players[] participants = new Players[computerPlayers + 1];
        for (i = 0; i < computerPlayers; i++)
        {
            participants[i] = new OpponentPlayer("Computer Player " + (i+1), 'c');            
        }
        participants[i] = new UserPlayer("User Player", 'p');
        DrawPile deck = new DrawPile();
        System.out.println("There are " + participants.length + " players in the game.\n");
        System.out.println("There are " + deck.pileSize() + " cards in the draw pile.");
        DiscardPile dump = new DiscardPile();
        System.out.println("The deck is being shuffled.");
        deck.shufflePile();
        System.out.println("The cards are being dealt to " + participants.length + " players.\n\n");

        for(i = 0; i < 5; i++)
        {
            for(int j = 0; j < participants.length; j++)
            {
                participants[j].dealCard(deck.drawCard());
            }
        }

        // does the turns
        for(i = 0; i < participants.length; i++)
        {
            System.out.println("Player: " + participants[i].getName() + "'s turn");
            participants[i].setTypeOfHand(eH.evaluateHand(participants[i]));
            String handType = eH.numToString(participants[i].getTypeOfHand());
            if(participants[i].getType() == 'c')
            {
                ((OpponentPlayer)participants[i]).takeTurn(dump, deck, handType);                
            }
            if(participants[i].getType() == 'p')
            {
                ((UserPlayer)participants[i]).discardAndDraw(dump, deck, handType);
            }
            System.out.println("");
        }

        for(i = 0; i < participants.length; i++)
        {
            System.out.println("Player: " + participants[i].getName());
            participants[i].setTypeOfHand(eH.evaluateHand(participants[i]));
            participants[i].extraCreditSort();
            participants[i].printHand();
            System.out.println("Hand evaluates to: " + eH.numToString(participants[i].getTypeOfHand()));
            System.out.println("");
        }

        // thr rest checks for winner
        List<Players> possibleWin = new ArrayList<Players>();
        int[] handCount = new int[9];
        int maxHand = 0;
        for(i = 0; i < participants.length; i++)
        {
            int currHand = participants[i].getTypeOfHand();
            handCount[currHand] += 1;

            if(currHand > maxHand)
            {
                maxHand = currHand;
            }           
        }

        for(i = 0; i < participants.length; i++)
        {
            if(participants[i].getTypeOfHand() == maxHand)
            {
                possibleWin.add(participants[i]);
            }
        }

        if(possibleWin.size() == 1)
        {
            Players winner = possibleWin.get(0);
            System.out.println("The winner is: " + winner.getName());
        }
        else
        {
            if(maxHand == 8)
            {
                Players[] potentialWinners = possibleWin.toArray(new Players[possibleWin.size()]);

                int highestCard = 0;
                Players highestPlayer = null;
                for(i = 0; i < potentialWinners.length; i++)
                {
                    Card[] hand = potentialWinners[i].getHand();
                    if(i == 0)
                    {
                        highestCard = hand[0].getValue();
                        highestPlayer = potentialWinners[i];                        
                    }
                    if(hand[0].getValue() > highestCard && i != 0)
                    {
                        highestCard = hand[0].getValue();
                        possibleWin.remove(highestPlayer);
                        highestPlayer = potentialWinners[i];
                    }
                    else if(hand[0].getValue() == highestCard && i != 0)
                    {
                        continue;
                    }
                    else if(hand[0].getValue() < highestCard && i != 0)
                    {
                        possibleWin.remove(potentialWinners[i]);

                    }
                }

                if(possibleWin.size() == 1)
                {
                    Players winner =  possibleWin.get(0);
                    System.out.println("The winner is: " + winner.getName());
                }
                else
                {
                    System.out.println("There is a tie!");
                    System.out.println("The winners are: ");

                    for(Players p: possibleWin)
                    {
                        System.out.println(p.getName());
                    }
                }
            }

            if(maxHand == 7)
            {
                Players[] potentialWinners = possibleWin.toArray(new Players[possibleWin.size()]);

                int highestCard = 0;
                Players highestPlayer = null;
                for(i = 0; i < potentialWinners.length; i++)
                {
                    Card[] hand = potentialWinners[i].getHand();
                    if(i == 0)
                    {
                        highestCard = hand[0].getValue();
                        highestPlayer = potentialWinners[i];                        
                    }
                    if(hand[0].getValue() > highestCard && i != 0)
                    {
                        highestCard = hand[0].getValue();
                        possibleWin.remove(highestPlayer);
                        highestPlayer = potentialWinners[i];
                    }
                    else if(hand[0].getValue() == highestCard && i != 0)
                    {
                        continue;
                    }
                    else if(hand[0].getValue() < highestCard && i != 0)
                    {
                        possibleWin.remove(potentialWinners[i]);

                    }
                }

                if(possibleWin.size() == 1)
                {
                    Players winner =  possibleWin.get(0);
                    System.out.println("The winner is: " + winner.getName());
                }
                else
                {
                    System.out.println("There is a tie!");
                    System.out.println("The winners are: ");

                    for(Players p: possibleWin)
                    {
                        System.out.println(p.getName());
                    }
                }

            }
            if(maxHand == 6)
            {
                Players[] potentialWinners = possibleWin.toArray(new Players[possibleWin.size()]);

                int highestCard = 0;
                Players highestPlayer = null;
                for(i = 0; i < potentialWinners.length; i++)
                {
                    Card[] hand = potentialWinners[i].getHand();
                    if(i == 0)
                    {
                        highestCard = hand[0].getValue();
                        highestPlayer = potentialWinners[i];                        
                    }
                    if(hand[0].getValue() > highestCard && i != 0)
                    {
                        highestCard = hand[0].getValue();
                        possibleWin.remove(highestPlayer);
                        highestPlayer = potentialWinners[i];
                    }
                    else if(hand[0].getValue() == highestCard && i != 0)
                    {
                        continue;
                    }
                    else if(hand[0].getValue() < highestCard && i != 0)
                    {
                        possibleWin.remove(potentialWinners[i]);

                    }
                }

                if(possibleWin.size() == 1)
                {
                    Players winner =  possibleWin.get(0);
                    System.out.println("The winner is: " + winner.getName());
                }
                else
                {
                    System.out.println("There is a tie!");
                    System.out.println("The winners are: ");

                    for(Players p: possibleWin)
                    {
                        System.out.println(p.getName());
                    }
                }

            }
            if(maxHand == 5)
            {
                Players[] potentialWinners = possibleWin.toArray(new Players[possibleWin.size()]);

                int highestCard = 0;
                Players highestPlayer = null;
                for(i = 0; i < potentialWinners.length; i++)
                {
                    Card[] hand = potentialWinners[i].getHand();
                    if(i == 0)
                    {
                        highestCard = hand[0].getValue();
                        highestPlayer = potentialWinners[i];                        
                    }
                    if(hand[0].getValue() > highestCard && i != 0)
                    {
                        highestCard = hand[0].getValue();
                        possibleWin.remove(highestPlayer);
                        highestPlayer = potentialWinners[i];
                    }
                    else if(hand[0].getValue() == highestCard && i != 0)
                    {
                        continue;
                    }
                    else if(hand[0].getValue() < highestCard && i != 0)
                    {
                        possibleWin.remove(potentialWinners[i]);

                    }
                }
                if(possibleWin.size() == 1)
                {
                    Players winner =  possibleWin.get(0);
                    System.out.println("The winner is: " + winner.getName());
                    return;
                }
                else
                {
                    potentialWinners = possibleWin.toArray(new Players[possibleWin.size()]);
                    for(i = 0; i < potentialWinners.length; i++)
                    {
                        Card[] hand = potentialWinners[i].getHand();
                        if(i == 0)
                        {
                            highestCard = hand[4].getValue();
                            highestPlayer = potentialWinners[i];                        
                        }
                        if(hand[4].getValue() > highestCard && i != 0)
                        {
                            highestCard = hand[4].getValue();
                            possibleWin.remove(highestPlayer);
                            highestPlayer = potentialWinners[i];
                        }
                        else if(hand[4].getValue() == highestCard && i != 0)
                        {
                            continue;
                        }
                        else if(hand[4].getValue() < highestCard && i != 0)
                        {
                            possibleWin.remove(potentialWinners[i]);

                        }
                    }
                }
                if(possibleWin.size() == 1)
                {
                    Players winner =  possibleWin.get(0);
                    System.out.println("The winner is: " + winner.getName());
                }
                else
                {
                    System.out.println("There is a tie!");
                    System.out.println("The winners are: ");

                    for(Players p: possibleWin)
                    {
                        System.out.println(p.getName());
                    }
                }
            }
            if(maxHand == 4)
            {
                Players[] potentialWinners = possibleWin.toArray(new Players[possibleWin.size()]);

                int highestCard = 0;
                Players highestPlayer = null;
                for(i = 0; i < potentialWinners.length; i++)
                {
                    Card[] hand = potentialWinners[i].getHand();
                    if(i == 0)
                    {
                        highestCard = hand[0].getValue();
                        highestPlayer = potentialWinners[i];                        
                    }
                    if(hand[0].getValue() > highestCard && i != 0)
                    {
                        highestCard = hand[0].getValue();
                        possibleWin.remove(highestPlayer);
                        highestPlayer = potentialWinners[i];
                    }
                    else if(hand[0].getValue() == highestCard && i != 0)
                    {
                        continue;
                    }
                    else if(hand[0].getValue() < highestCard && i != 0)
                    {
                        possibleWin.remove(potentialWinners[i]);

                    }
                }

                if(possibleWin.size() == 1)
                {
                    Players winner =  possibleWin.get(0);
                    System.out.println("The winner is: " + winner.getName());
                }
                else
                {
                    System.out.println("There is a tie!");
                    System.out.println("The winners are: ");

                    for(Players p: possibleWin)
                    {
                        System.out.println(p.getName());
                    }
                }

            }
            if(maxHand == 3)
            {
                Players[] potentialWinners = possibleWin.toArray(new Players[possibleWin.size()]);

                int highestCard = 0;
                Players highestPlayer = null;
                for(i = 0; i < potentialWinners.length; i++)
                {
                    Card[] hand = potentialWinners[i].getHand();
                    if(i == 0)
                    {
                        highestCard = hand[0].getValue();
                        highestPlayer = potentialWinners[i];                        
                    }
                    if(hand[0].getValue() > highestCard && i != 0)
                    {
                        highestCard = hand[0].getValue();
                        possibleWin.remove(highestPlayer);
                        highestPlayer = potentialWinners[i];
                    }
                    else if(hand[0].getValue() == highestCard && i != 0)
                    {
                        continue;
                    }
                    else if(hand[0].getValue() < highestCard && i != 0)
                    {
                        possibleWin.remove(potentialWinners[i]);

                    }
                }

                if(possibleWin.size() == 1)
                {
                    Players winner =  possibleWin.get(0);
                    System.out.println("The winner is: " + winner.getName());
                }
                else
                {
                    System.out.println("There is a tie!");
                    System.out.println("The winners are: ");

                    for(Players p: possibleWin)
                    {
                        System.out.println(p.getName());
                    }
                }
            }
            if(maxHand == 2)
            {
                Players[] potentialWinners = possibleWin.toArray(new Players[possibleWin.size()]);

                int highestCard = 0;
                Players highestPlayer = null;
                for(i = 0; i < potentialWinners.length; i++)
                {
                    Card[] hand = potentialWinners[i].getHand();
                    if(i == 0)
                    {
                        highestCard = hand[0].getValue();
                        highestPlayer = potentialWinners[i];                        
                    }
                    if(hand[0].getValue() > highestCard && i != 0)
                    {
                        highestCard = hand[0].getValue();
                        possibleWin.remove(highestPlayer);
                        highestPlayer = potentialWinners[i];
                    }
                    else if(hand[0].getValue() == highestCard && i != 0)
                    {
                        continue;
                    }
                    else if(hand[0].getValue() < highestCard && i != 0)
                    {
                        possibleWin.remove(potentialWinners[i]);

                    }
                }

                if(possibleWin.size() == 1)
                {
                    Players winner =  possibleWin.get(0);
                    System.out.println("The winner is: " + winner.getName());
                    return;
                }
                else
                {
                    potentialWinners = possibleWin.toArray(new Players[possibleWin.size()]);
                    for(i = 0; i < potentialWinners.length; i++)
                    {
                        Card[] hand = potentialWinners[i].getHand();
                        if(i == 0)
                        {
                            highestCard = hand[2].getValue();
                            highestPlayer = potentialWinners[i];                        
                        }
                        if(hand[2].getValue() > highestCard && i != 0)
                        {
                            highestCard = hand[2].getValue();
                            possibleWin.remove(highestPlayer);
                            highestPlayer = potentialWinners[i];
                        }
                        else if(hand[2].getValue() == highestCard && i != 0)
                        {
                            continue;
                        }
                        else if(hand[2].getValue() < highestCard && i != 0)
                        {
                            possibleWin.remove(potentialWinners[i]);

                        }
                    }
                }
                if(possibleWin.size() == 1)
                {
                    Players winner =  possibleWin.get(0);
                    System.out.println("The winner is: " + winner.getName());
                    return;
                }
                else
                {
                    potentialWinners = possibleWin.toArray(new Players[possibleWin.size()]);
                    for(i = 0; i < potentialWinners.length; i++)
                    {
                        Card[] hand = potentialWinners[i].getHand();
                        if(i == 0)
                        {
                            highestCard = hand[4].getValue();
                            highestPlayer = potentialWinners[i];                        
                        }
                        if(hand[4].getValue() > highestCard && i != 0)
                        {
                            highestCard = hand[4].getValue();
                            possibleWin.remove(highestPlayer);
                            highestPlayer = potentialWinners[i];
                        }
                        else if(hand[4].getValue() == highestCard && i != 0)
                        {
                            continue;
                        }
                        else if(hand[4].getValue() < highestCard && i != 0)
                        {
                            possibleWin.remove(potentialWinners[i]);

                        }
                    }
                }
                if(possibleWin.size() == 1)
                {
                    Players winner =  possibleWin.get(0);
                    System.out.println("The winner is: " + winner.getName());
                }
                else
                {
                    System.out.println("There is a tie!");
                    System.out.println("The winners are: ");

                    for(Players p: possibleWin)
                    {
                        System.out.println(p.getName());
                    }
                }
            }
            if(maxHand == 1)
            {
                Players[] potentialWinners = possibleWin.toArray(new Players[possibleWin.size()]);

                int highestCard = 0;
                Players highestPlayer = null;
                for(i = 0; i < potentialWinners.length; i++)
                {
                    Card[] hand = potentialWinners[i].getHand();
                    if(i == 0)
                    {
                        highestCard = hand[0].getValue();
                        highestPlayer = potentialWinners[i];                        
                    }
                    if(hand[0].getValue() > highestCard && i != 0)
                    {
                        highestCard = hand[0].getValue();
                        possibleWin.remove(highestPlayer);
                        highestPlayer = potentialWinners[i];
                    }
                    else if(hand[0].getValue() == highestCard && i != 0)
                    {
                        continue;
                    }
                    else if(hand[0].getValue() < highestCard && i != 0)
                    {
                        possibleWin.remove(potentialWinners[i]);

                    }
                }

                if(possibleWin.size() == 1)
                {
                    Players winner =  possibleWin.get(0);
                    System.out.println("The winner is: " + winner.getName());
                    return;
                }
                else
                {
                    potentialWinners = possibleWin.toArray(new Players[possibleWin.size()]);
                    for(i = 0; i < potentialWinners.length; i++)
                    {
                        Card[] hand = potentialWinners[i].getHand();
                        if(i == 0)
                        {
                            highestCard = hand[2].getValue();
                            highestPlayer = potentialWinners[i];                        
                        }
                        if(hand[2].getValue() > highestCard && i != 0)
                        {
                            highestCard = hand[2].getValue();
                            possibleWin.remove(highestPlayer);
                            highestPlayer = potentialWinners[i];
                        }
                        else if(hand[2].getValue() == highestCard && i != 0)
                        {
                            continue;
                        }
                        else if(hand[2].getValue() < highestCard && i != 0)
                        {
                            possibleWin.remove(potentialWinners[i]);

                        }
                    }
                }
                if(possibleWin.size() == 1)
                {
                    Players winner =  possibleWin.get(0);
                    System.out.println("The winner is: " + winner.getName());
                    return;
                }
                else
                {
                    potentialWinners = possibleWin.toArray(new Players[possibleWin.size()]);
                    for(i = 0; i < potentialWinners.length; i++)
                    {
                        Card[] hand = potentialWinners[i].getHand();
                        if(i == 0)
                        {
                            highestCard = hand[3].getValue();
                            highestPlayer = potentialWinners[i];                        
                        }
                        if(hand[3].getValue() > highestCard && i != 0)
                        {
                            highestCard = hand[3].getValue();
                            possibleWin.remove(highestPlayer);
                            highestPlayer = potentialWinners[i];
                        }
                        else if(hand[3].getValue() == highestCard && i != 0)
                        {
                            continue;
                        }
                        else if(hand[3].getValue() < highestCard && i != 0)
                        {
                            possibleWin.remove(potentialWinners[i]);

                        }
                    }
                }
                if(possibleWin.size() == 1)
                {
                    Players winner =  possibleWin.get(0);
                    System.out.println("The winner is: " + winner.getName());
                }
                else
                {
                    potentialWinners = possibleWin.toArray(new Players[possibleWin.size()]);
                    for(i = 0; i < potentialWinners.length; i++)
                    {
                        Card[] hand = potentialWinners[i].getHand();
                        if(i == 0)
                        {
                            highestCard = hand[4].getValue();
                            highestPlayer = potentialWinners[i];                        
                        }
                        if(hand[4].getValue() > highestCard && i != 0)
                        {
                            highestCard = hand[4].getValue();
                            possibleWin.remove(highestPlayer);
                            highestPlayer = potentialWinners[i];
                        }
                        else if(hand[4].getValue() == highestCard && i != 0)
                        {
                            continue;
                        }
                        else if(hand[4].getValue() < highestCard && i != 0)
                        {
                            possibleWin.remove(potentialWinners[i]);

                        }
                    }
                }
                if(possibleWin.size() == 1)
                {
                    Players winner =  possibleWin.get(0);
                    System.out.println("The winner is: " + winner.getName());
                }
                else
                {
                    System.out.println("There is a tie!");
                    System.out.println("The winners are: ");

                    for(Players p: possibleWin)
                    {
                        System.out.println(p.getName());
                    }
                }
            }
            if(maxHand == 0)
            {
                Players[] potentialWinners = possibleWin.toArray(new Players[possibleWin.size()]);

                int highestCard = 0;
                Players highestPlayer = null;
                for(i = 0; i < potentialWinners.length; i++)
                {
                    Card[] hand = potentialWinners[i].getHand();
                    if(i == 0)
                    {
                        highestCard = hand[0].getValue();
                        highestPlayer = potentialWinners[i];                        
                    }
                    if(hand[0].getValue() > highestCard && i != 0)
                    {
                        highestCard = hand[0].getValue();
                        possibleWin.remove(highestPlayer);
                        highestPlayer = potentialWinners[i];
                    }
                    else if(hand[0].getValue() == highestCard && i != 0)
                    {
                        continue;
                    }
                    else if(hand[0].getValue() < highestCard && i != 0)
                    {
                        possibleWin.remove(potentialWinners[i]);

                    }
                }

                if(possibleWin.size() == 1)
                {
                    Players winner =  possibleWin.get(0);
                    System.out.println("The winner is: " + winner.getName());
                }
                else
                {
                    potentialWinners = possibleWin.toArray(new Players[possibleWin.size()]);
                    for(i = 0; i < potentialWinners.length; i++)
                    {
                        Card[] hand = potentialWinners[i].getHand();
                        if(i == 0)
                        {
                            highestCard = hand[1].getValue();
                            highestPlayer = potentialWinners[i];                        
                        }
                        if(hand[1].getValue() > highestCard && i != 0)
                        {
                            highestCard = hand[1].getValue();
                            possibleWin.remove(highestPlayer);
                            highestPlayer = potentialWinners[i];
                        }
                        else if(hand[2].getValue() == highestCard && i != 0)
                        {
                            continue;
                        }
                        else if(hand[1].getValue() < highestCard && i != 0)
                        {
                            possibleWin.remove(potentialWinners[i]);

                        }
                    }
                }
                if(possibleWin.size() == 1)
                {
                    Players winner =  possibleWin.get(0);
                    System.out.println("The winner is: " + winner.getName());
                }
                else
                {
                    potentialWinners = possibleWin.toArray(new Players[possibleWin.size()]);
                    for(i = 0; i < potentialWinners.length; i++)
                    {
                        Card[] hand = potentialWinners[i].getHand();
                        if(i == 0)
                        {
                            highestCard = hand[2].getValue();
                            highestPlayer = potentialWinners[i];                        
                        }
                        if(hand[2].getValue() > highestCard && i != 0)
                        {
                            highestCard = hand[2].getValue();
                            possibleWin.remove(highestPlayer);
                            highestPlayer = potentialWinners[i];
                        }
                        else if(hand[2].getValue() == highestCard && i != 0)
                        {
                            continue;
                        }
                        else if(hand[2].getValue() < highestCard && i != 0)
                        {
                            possibleWin.remove(potentialWinners[i]);

                        }
                    }
                }
                if(possibleWin.size() == 1)
                {
                    Players winner =  possibleWin.get(0);
                    System.out.println("The winner is: " + winner.getName());
                }
                else
                {
                    potentialWinners = possibleWin.toArray(new Players[possibleWin.size()]);
                    for(i = 0; i < potentialWinners.length; i++)
                    {
                        Card[] hand = potentialWinners[i].getHand();
                        if(i == 0)
                        {
                            highestCard = hand[3].getValue();
                            highestPlayer = potentialWinners[i];                        
                        }
                        if(hand[3].getValue() > highestCard && i != 0)
                        {
                            highestCard = hand[3].getValue();
                            possibleWin.remove(highestPlayer);
                            highestPlayer = potentialWinners[i];
                        }
                        else if(hand[3].getValue() == highestCard && i != 0)
                        {
                            continue;
                        }
                        else if(hand[3].getValue() < highestCard && i != 0)
                        {
                            possibleWin.remove(potentialWinners[i]);

                        }
                    }
                }
                if(possibleWin.size() == 1)
                {
                    Players winner =  possibleWin.get(0);
                    System.out.println("The winner is: " + winner.getName());
                }
                else
                {
                    potentialWinners = possibleWin.toArray(new Players[possibleWin.size()]);
                    for(i = 0; i < potentialWinners.length; i++)
                    {
                        Card[] hand = potentialWinners[i].getHand();
                        if(i == 0)
                        {
                            highestCard = hand[4].getValue();
                            highestPlayer = potentialWinners[i];                        
                        }
                        if(hand[4].getValue() > highestCard && i != 0)
                        {
                            highestCard = hand[4].getValue();
                            possibleWin.remove(highestPlayer);
                            highestPlayer = potentialWinners[i];
                        }
                        else if(hand[4].getValue() == highestCard && i != 0)
                        {
                            continue;
                        }
                        else if(hand[4].getValue() < highestCard && i != 0)
                        {
                            possibleWin.remove(potentialWinners[i]);

                        }
                    }
                }
                if(possibleWin.size() == 1)
                {
                    Players winner =  possibleWin.get(0);
                    System.out.println("The winner is: " + winner.getName());
                }
                else
                {
                    System.out.println("There is a tie!");
                    System.out.println("The winners are: ");

                    for(Players p: possibleWin)
                    {
                        System.out.println(p.getName());
                    }
                }

            }
        }

    }
}
