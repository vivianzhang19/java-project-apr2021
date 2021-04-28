import java.util.ArrayList;

/**
 * Class which creates Game objects.
 *
 * @author Vivian Zhang
 * @version ver1.15
 */
public class Game
{
    private static final int NO_DIRECTION_SELECTED = -1;
    private static final String INPUT_FILE = "exits.txt";
    private static final String OUTPUT_FILE = "Outcome.txt";
    private static int gameCounter = 0;
    private static boolean gameOutcome = true;
    private static int inputDirection = NO_DIRECTION_SELECTED;
    private ArrayList<Portal> portals;
    private Player player;

    /**
     * Default constructor which creates the object of the Game class.
     *
     */
    public Game()
    {
        portals = new ArrayList<Portal>();
        player = new Player();
    }

    /**
     * Non-default constructor which creates the object of the class Player.
     *
     * @param           portals         An arraylist of portals that represent
     *                                  all the portals of the room.
     * @param           player          A Player object
     *  
     */
    public Game(ArrayList<Portal> portals, Player player)
    {
        this.portals = portals;
        this.player = player;
    }

    /**
     * Non-default constructor which creates the object of the class Player.
     *
     * @param           portals         An arraylist of portals that represent
     *                                  all the portals of the room.
     * @param           player          A Player object
     * @param           gameCounter     Stores the count of the game round, 
     *                                  as an integer.
     * @param           gameOutcome     Stores the outcome of the game, 
     *                                  as a boolean. 
     * @param           inputDirection  Stores the last direction input by the
     *                                  user, as an integer. 
     *                                  If no direction, stores -1.
     */
     public Game(ArrayList<Portal> portals, Player player, int gameCounter,
                 boolean gameOutcome, int inputDirection)
    {
        this.portals = portals;
        this.player = player;
        this.gameCounter = gameCounter;
        this.gameOutcome = gameOutcome;
        this.inputDirection = inputDirection;
    }

    /**
     * Method that asks user to input a valid direction to traverse 
     * to a new room.
     *
     * @return                      A valid direction that the player has 
     *                              chosen, as an integer.
     */
    private int acceptDirectionInput()
    {
        int index = NO_DIRECTION_SELECTED;
        boolean validDirection = false;
        do
        {
            String message = "Please choose a direction by selecting one of "
                             + "the following numbers: ";
            for (int i = 0; i < portals.size(); i++)
                message += "(" + (i + 1) + ") " + portals.get(i).getDirection()
                             + " ";
            try
            {
                index = Input.acceptIntegerInput(message) - 1;
                if (portals.size() > index && 
                   (portals.get(index).getIsPortalOpen() || 
                    portals.get(index).getDoesPortalContainExit()))
                {
                    System.out.println("You selected: " + 
                                        portals.get(index).getDirection());
                    validDirection = true;
                }
                else
                    System.out.println("You must select a direction that is "
                                        + " not closed and is one of the above"
                                        + " directions.");
            }
            catch (Exception e)
            {
                System.out.println("Please enter a number only.");
            } 
        }
        while (validDirection == false);
        return index;
    }
    
    /**
     * Method that checks if all the portals in the current room are closed.
     *   
     * @return      Whether all portals are closed (true) or not (false)
     */
    private boolean areAllPortalsClosed()
    {
        for (int i = 0; i < portals.size(); i++)
        {
            if (portals.get(i).getIsPortalOpen() || 
                portals.get(i).getDoesPortalContainExit())
                return false;
        }
        Interface.displayBox("Oh no! " + 
                            "All the portals are closed in this room.");
        return true;
    }

    /**
     * Method to determine the outcome when the player is asked to bribe with
     * the magic police.
     *
     * @param       numCoins       The number of coins of the player,
     *                             as an integer. 
     * @return                     Whether the bribe was successful/player
     *                             manages to escape (returns true)
     *                             or not successful (returns false).
     */
    private boolean bribePolice(int numCoins)
    {
        String message = "You currently have " + numCoins + " coins."
                         + "\nWould you like to bribe to escape? (Y/N)";
        if (Input.acceptYesOrNoInput(message) == 'y')
        {
            int demandedCoins = RNG.generateRandomNum(0.5 * numCoins, 
                                                      1.5 * numCoins);
            if (demandedCoins > numCoins)
            {
                String newMessage = "The magic police demand " + demandedCoins
                                     + " coins but you only have " 
                                     + numCoins + " coin(s)." 
                                     + "\nThey throw you into jail.";
                Interface.displayBox(newMessage);
                return false;
            }
            else
            {
                String newMessage = "The magic police demand " + demandedCoins
                                    + " coin(s).";
                player.adjustInventoryItemAmt(Player.COINS, 
                                                 -1 * demandedCoins);
                newMessage += "You have enough money and hand over the " + 
                               demandedCoins + " coin(s).\n" + "You now have " 
                               + player.getSpecificInventoryValue(Player.COINS) 
                               + " coin(s) left. " +
                               "You run away from the magic police.";
                Interface.displayBox(newMessage);
                return true;
            }
        }
        return false;
    }

    /**
     * Method to represent the state of the Game object as a String
     *
     * @return                      Representation of the state of the 
     *                              Game object, as a String.          
     */
    public void display()
    {
        for (int i = 0; i < portals.size(); i++)
           System.out.println(portals.get(i).display());
        System.out.println(player.display());
    }

    /**
     * Method to display information about each game round to the user
     * including the game counter, the player information, the room map and
     * details on the portal probabilities.      
     */
    private void displayGameRound()
    {
        Interface.displayBox("Round " + (gameCounter + 1) + ":\n\n"
                             + player.display());
        displayMap();
        System.out.println();
        displayPortalInfo();
    }

    /**
     * Method that prints out the map of the current room that the player 
     * is in.
     *   
     */
    private void displayMap()
    {
        int nthIndex = findIndex("North");
        int sthIndex = findIndex("South");
        int wstIndex = findIndex("West");
        int estIndex = findIndex("East");

        System.out.println();
        System.out.println(Interface.displayVerticalPortals
                          (portals.get(nthIndex).getIsPortalOpen(), 
                           portals.get(nthIndex).getDoesPortalContainExit()));
        System.out.println("    |        |   ");
        System.out.println("____|        |____");
        System.out.println(Interface.displayHorizontalPortals
                          (portals.get(wstIndex).getIsPortalOpen(), 
                           portals.get(wstIndex).getDoesPortalContainExit(), 
                           portals.get(estIndex).getIsPortalOpen(), 
                           portals.get(estIndex).getDoesPortalContainExit()));
        System.out.println("----|        |----");
        System.out.println("    |        |    ");
        System.out.println(Interface.displayVerticalPortals
                          (portals.get(sthIndex).getIsPortalOpen(), 
                           portals.get(sthIndex).getDoesPortalContainExit()));
    }

    /**
     * Method to display the probabilities of all the portals in the room
     * to the player.
     *          
     */
    private void displayPortalInfo()
    {
        String portalInfo = "";
        for (int i = 0; i < portals.size(); i++)
        {
            portalInfo += portals.get(i).displayProbabilityInfo() + "\n";
        }
        Interface.drawTopBottomLines(portalInfo);
    }

    /**
     * Method to determine whether the player manages to escape jail
     *
     * @return                 Whether the player escapes jail (returns true)
     *                         or is forever captured (returns false).
     */
    private boolean doesPlayerEscapeJail()
    {
        Interface.displayJail();
        return doesPlayerJump();
    }

    /**
     * Method to determine if player jumps when player is given choice to jump
     * backwards 
     *
     * @return                  Whether the player chooses not to jump or 
     *                          is unable to jump (returns false) or the 
     *                          player manages to jump (returns true).       
     */
    private boolean doesPlayerJump()
    {
        if (player.getSpecificInventoryValue(Player.JUMPS) == 0)
        {
            System.out.println("Unfortunately, you have no more jumps left.");
            gameOutcome = false;
            return false;
        }
        String message = "\nYou are currently stuck... However, you have " +
                         player.getSpecificInventoryValue(Player.JUMPS) + 
                         " jump(s) left." +
                         "\nWould you like to jump backwards? (Y/N)";
                    
        if (Input.acceptYesOrNoInput(message) == 'y')
        {
            player.adjustInventoryItemAmt(Player.JUMPS, -1);
            System.out.println("... Jumping backwards...");
            /* Jumping backwards will only update the status of portals being 
            open/closed, having exit/no exit, having police/no police. 
            The probabilities of the portals will not be adjusted */
            generateNewRoomWithSameProb();
            return true;
        }
        else 
        {
            gameOutcome = false;
            return false;
        }
    }

    /**
     * Method that represents the player having the chance of encountering a
     * magic box containing certain items.
     *
     */
    private void encounterMagicBox()
    {
        if (gameCounter == 0)
            return;
        if (RNG.sampleRandomVariable(50))
        {
            String message = "You encountered a magic box. Would you like to " 
                             + "open it? (Y/N)";
            if (Input.acceptYesOrNoInput(message) == 'y')
            {
                obtainMagicBoxItem();
            }
            return;
        }
    }

    /**
     * Method to determine whether the player encounters magic police in
     * each room.
     *
     * @return                Whether the player encounters the police
     *                        (returns true) or does not (returns false).        
     */
    private boolean encounterMagicPolice()
    {
        /* If the player has not managed to select any direction, then no magic
        police will occur. EXAMPLE: in the rare case that the first 3 rooms
        (even after jumping backwards) are all closed rooms - should not 
        activate police */
        if (gameCounter == 0 || inputDirection == NO_DIRECTION_SELECTED)
            return false;

        if (portals.get(inputDirection).getDoesPortalContainPolice())
            return true;

        return false;
    }

    /**
     * Method to determine the index of a specific direction input by the user
     * based on the arraylist of Portal objects.
     * 
     * @param       inputDirection          A valid direction that is input by  
     *                                      user, as a String
     *
     * @return                              Index of the direction, 
     *                                      as an integer.
     */
    private int findIndex(String inputDirection)
    {
        int index = -1;
        for (int i = 0; i < portals.size(); i++)
        {
            if (portals.get(i).getDirection().equals(inputDirection))
            {
                index = i;
                break;
            } 
        }
        return index;
    }

    /**
     * Method to represent the behaviour of each game round
     *
     * @return                      Whether the game round results in 
     *                              the game ending (returns false) or in the
     *                              game still continuing/player still needs to 
     *                              navigate through more rooms (returns true).         
     */
    private boolean gameRound()
    {
        if (encounterMagicPolice() && !isEncounterMagicPoliceSuccess())
            return doesPlayerEscapeJail();

        encounterMagicBox();

        if (areAllPortalsClosed())
            return doesPlayerJump();

        //Direction will be either open or exit
        inputDirection = acceptDirectionInput();

        if (portals.get(inputDirection).getDoesPortalContainExit())
            return false;
        generateNewRoomWithNewProb(inputDirection);
        return true;
    }

    /**
     * Method that updates all relevant attributes for new room
     * 1. the statuses of all portals (whether they are open/closed,
     * contains exit, contains police) and
     * 2. probabilities of portals being open/closed, containing exits
     * or containing police (if the user selected a direction
     * that is not an Exit.)
     *
     * @param           selectedDirection   A direction, as an integer.    
     */
    private void generateNewRoomWithNewProb(int selectedDirection)
    {
        //If direction is open
        if (!portals.get(selectedDirection).getDoesPortalContainExit())
        {  
            portals.get(selectedDirection).setProbExit
                       (RNG.adjustProbByRandomNum
                       (portals.get(selectedDirection).getProbExit(), 
                        RNG.MIN_PROB_ADJ, RNG.MAX_PROB_ADJ));
            portals.get(selectedDirection).setProbPolice
                       (RNG.adjustProbByRandomNum
                       (portals.get(selectedDirection).getProbPolice(), 
                       RNG.MIN_PROB_ADJ, RNG.MAX_PROB_ADJ));
        }
        //If selected direction is an exit, then there are no changes to
        // probabilities, just portal statuses.
        generateNewRoomWithSameProb();
    }

    /**
     * Method that updates all statuses of the portals for the new room
     * (Whether the portal is open/closed, contains exit, contains police).
     *   
     */
    private void generateNewRoomWithSameProb()
    {
        for (int i = 0; i < portals.size(); i++)
        {
            portals.get(i).setIsPortalOpen(RNG.sampleRandomVariable
                           (portals.get(i).getProbOpen()));
            portals.get(i).setDoesPortalContainExit(RNG.sampleRandomVariable
                           (portals.get(i).getProbExit()));
            portals.get(i).setDoesPortalContainPolice(RNG.sampleRandomVariable
                           (portals.get(i).getProbPolice()));
        }
    }

    /**
     * Accessor method to get the game counter
     *
     * @return                      Game counter, as an integer.
     */
    public int getGameCounter()
    {
        return gameCounter;
    }

    /**
     * Accessor method to get the game outcome
     *
     * @return                      Whether the game will be a loss
     *                              (return false) or win (return true),
     *                              as a boolean
     */
    public boolean getGameOutcome()
    {
        return gameOutcome;
    }

    /**
     * Accessor method to get the direction input by the player
     *
     * @return                      The direction, as an integer.
     *                              Returns -1 if direction has not 
     *                              been selected yet by the player.
     */
    public int getInputDirection()
    {
        return inputDirection;
    }
    /**
     * Accessor method to get object of Player class
     *
     * @return                      A player object
     */
    public Player getPlayer()
    {
        return player;
    }
    
    /**
     * Accessor method to get arraylist of Portal objects
     *
     * @return                      Arraylist of Portal objects, representing
     *                              portals of the room.
     */
    public ArrayList<Portal> getPortals()
    {
        return portals;
    }

    /**
     * Accessor method to get a specific Portal object 
     *
     * @param          index        Index of the Portal object, as an integer.
     *
     * @return                      A Portal object, otherwise, null.
     */
    public Portal getSpecificPortal(int index)
    {
        if (portals.size() >= index)
            return portals.get(index);
        else
            return null;
    }

    /**
     * Method to ask for the player to input their name and validates the 
     * length of the name
     *
     */
    private void inputPlayerName()
    {
        boolean validNameFlag = false;
        String name = "";
        int minNameLength = 3;
        int maxNameLength = 12;
        do
        {
            name = Input.acceptStringInput("Please enter your name.");
            if (Validation.isStringLengthValid(name, minNameLength, 
                                               maxNameLength))
            {
                validNameFlag = true;
                player.setPlayerName(name);
            }    
            else
                System.out.println("Your name must be between " + 
                                   minNameLength + " and " + maxNameLength +
                                   " characters long.");
        }
        while (validNameFlag == false);
        System.out.println("Thank you, " + name + ". Please make sure your "
                           + "game screen is fully expanded for the best "
                           + "experience.");
    }

    /**
     * Method to determine the outcome of the player's encounter with 
     * the magic police
     *
     * @return                     Whether the players's encounter was
     *                             successful (returns true) or not 
     *                             and the player is jailed (returns false).
     */
    private boolean isEncounterMagicPoliceSuccess()
    {
        Interface.displayBox("Oh no! You have encountered the magic police! " 
                             + "They are currently blocking your path.");
        int numInvisibilityCloaks = player.getSpecificInventoryValue(
                                    Player.INVISIBILITY_CLOAKS);
        if (numInvisibilityCloaks > 0)
        {
            String message = "You have " + numInvisibilityCloaks + 
                             " invisibility cloaks.";
            message += "\nWould you like to use it? (Y/N)";
            if (Input.acceptYesOrNoInput(message) == 'y')
            {
                player.adjustInventoryItemAmt(Player.INVISIBILITY_CLOAKS,
                                                 -1);
                message = "You used a invisibility cloak and now have " +
                          numInvisibilityCloaks + " cloak(s) left.";
                System.out.println(message);
                Interface.displayBox("You have escaped the magic police.");
                return true;
            }
            else
                return false;
        }

        if (player.getSpecificInventoryValue(Player.COINS) > 0)
        {
            return bribePolice(player.getSpecificInventoryValue(Player.COINS));
        }
        Interface.displayBox("Oh no! You don't have any coins left to bribe or" 
                            + " an invisibility cloak. You have been jailed.");
        return false;
    }

    /**
     * Method to begin the program.
     *
     * @param           args        An array of Strings representing command
     *                              line arguments.
     */
    public static void main(String[] args)
    {
        Game game = new Game();
        game.startProgram();
    }

    /**
     * Method that represents the player obtaining a specific magic box item 
     * if they manage to encounter the magic box and decide to open it.
     *
     */
    private void obtainMagicBoxItem()
    {
        int probCoins = 30;
        int probMagicPoliceAlarm = 25;
        int probInvisibilityCloak = 15;
        int probCoal = 30;

        int lowerBound = RNG.MIN_PROBABILITY;
        int upperBound = probCoins;
        int randomNum = RNG.generateRandomNum(RNG.MIN_PROBABILITY, 
                                              RNG.MAX_PROBABILITY);

        if (RNG.checkIfNumWithinRange(lowerBound, upperBound, randomNum))
        {
            int minCoin = 10;
            int maxCoin = 35;
            int numCoinsReceived = RNG.generateRandomNum(minCoin, maxCoin);
            Interface.displayBox("You received " + numCoinsReceived 
                                 + " coins.");
            player.adjustInventoryItemAmt(Player.COINS, numCoinsReceived);
            return;
        }

        lowerBound = upperBound + 1;
        upperBound += probMagicPoliceAlarm;
        if (RNG.checkIfNumWithinRange(lowerBound, upperBound, randomNum))
        {
            int policeAdjustment = 3;
            Interface.displayBox("OH NO! You found an alarm, which suddenly"
                                 + " emitted a loud screech.");
            System.out.println("(The probability of police occurring has "
                                + "increased by " + policeAdjustment + 
                                "% in all directions.)");
            for (int i = 0; i < portals.size(); i++)
            {
                portals.get(i).setProbPolice(portals.get(i).getProbPolice() 
                                             + policeAdjustment);
            }
            displayPortalInfo();
            return;
        }
        
        lowerBound = upperBound + 1;
        upperBound += probInvisibilityCloak;
        if (RNG.checkIfNumWithinRange(lowerBound, upperBound, randomNum))
        {
            Interface.displayBox("You found an invisibility cloak.");
            String message = "Would you like to take it? (Y/N)";
            if (Input.acceptYesOrNoInput(message) == 'y')
            {
                if (!player.isInventoryFull())
                {
                    player.adjustInventoryItemAmt(Player.INVISIBILITY_CLOAKS,
                                                     1);
                           
                    Interface.displayBox("You took the invisibility cloak.");
                }
                else
                    System.out.println("Inventory is full." 
                                        + " You cannot hold any more items.");        
            }
            return;
        }

        lowerBound = upperBound + 1;
        upperBound += probCoal;
        if (RNG.checkIfNumWithinRange(lowerBound, upperBound, randomNum))
        {
            Interface.displayBox("You found some coal. It does nothing but "
                                 + "add weight. You leave it behind.");
            return;
        }
        return;
    }

    /**
     * Method to read the probabilities of portals from a file 
     * and create relevant Portal objects for the rooms that the 
     * player will be in, with other attributes.
     *        
     */
    private void readFile()
    {
        FileIO fileIO = new FileIO(INPUT_FILE);
        String[] input = fileIO.readFile().split("\\n");

        for (int i = 0; i < input.length; i++)
        {
            String[] splitInput = input[i].split(",");
            
            int probOpen = Validation.returnValidIntegerInput(splitInput[1], 
                            "Error in reading probability of portal being "
                            + "open. Applying value of 0.");
            int probExit = Validation.returnValidIntegerInput(splitInput[2], 
                            "Error in reading probability of portal having "
                            + "exit. Applying value of 0.");
            int probPolice = Validation.returnValidIntegerInput(splitInput[3], 
                            "Error in reading probability of portal having "
                            + "police. Applying value of 0.");
            
            boolean isOpen = RNG.sampleRandomVariable(probOpen);
            boolean doesContainExit = RNG.sampleRandomVariable(probExit);
            boolean doesContainPolice = RNG.sampleRandomVariable(probPolice);
            
            Portal portal = new Portal(splitInput[0], probOpen, probExit, 
                                       probPolice, isOpen, 
                                       doesContainExit, 
                                       doesContainPolice);
            portals.add(portal);
        }
    }   

    /**
     * Mutator method to set the game counter
     *
     * @param        gameCounter    The counter of the game rounds,
     *                              as an integer.
     */
    public void setGameCounter(int gameCounter)
    {
        this.gameCounter = gameCounter;
    }

    /**
     * Mutator method to set the game outcome
     *
     * @param        gameOutcome    Whether the game will be a loss
     *                              (false) or will be a win (true)
     */
    public void setGameOutcome(boolean gameOutcome)
    {
        this.gameOutcome = gameOutcome;
    }

    /**
     * Mutator method to set the direction input by the player
     *
     * @param        inputDirection    The direction input by the player,
     *                                 as an integer.
     */
    public void setInputDirection(int inputDirection)
    {
        this.inputDirection = inputDirection;
    }

     /**
     * Mutator method to set the Player object
     *
     * @param        player    A Player object
     */
    public void setPlayer(Player player)
    {
        this.player = player;
    }

    /**
     * Mutator method to set the arraylist of Portal objects
     *
     * @param        portals    Arraylist of Portal objects
     */
    public void setPortals(ArrayList<Portal> portals)
    {
        this.portals = portals;
    }

    /**
     * Mutator method to set a specific Portal object
     *
     * @param        index      Index of the portal, as an integer
     * @param        portals    Arraylist of portals
     *
     */
    public void setSpecificPortal(int index, Portal portal)
    {
        if (portals.size() >= index)
            portals.set(index, portal);
    }

    /**
     * Method to start the game 
     *
     */
    public void startProgram()
    {
        readFile();
        inputPlayerName();
        Input.toContinueGame("Press any key to continue.");
        Interface.displayIntroMessage(player.getPlayerName());
        Input.toContinueGame("Press any key to continue.");
        boolean continueGameFlag = true;
        do 
        {
            displayGameRound();
            continueGameFlag = gameRound();
            gameCounter++;
        }
        while (continueGameFlag == true);
        writeFile();
    }

    /**
     * Method to write to file the outcome of the game
     *         
     */
     private void writeFile()
     {
         System.out.println("The game has ended. Writing results to the file ("
                             + OUTPUT_FILE + ").");
         FileIO fileIO = new FileIO(OUTPUT_FILE);
         String winMessage = "Congratulations! You have won the game.";
         String lossMessage = "Bad luck! You have lost the game.";
         fileIO.writeFile((gameOutcome ? winMessage : lossMessage) + 
                          "\nThe game took " + gameCounter + " rounds.");
     }
}
