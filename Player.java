import java.util.ArrayList;

/**
 * Class which creates Player objects
 *
 * @author Vivian Zhang
 * @version ver1.15
 */
public class Player
{
    private String playerName;
    private ArrayList<Pair> inventory;
    public static final int INVENTORY_LIMIT = 3;
    public static final int INVALID = -1;

    public static final int COINS = 0;
    public static final int INVISIBILITY_CLOAKS = 1;
    public static final int JUMPS = 2;

    /**
     * Default constructor which creates the object of the class Player.
     *
     */
    public Player()
    {
        playerName = "unknown";
        inventory = new ArrayList<Pair>();
        initialiseInventory();
    }

    /**
     * Non-default constructor which creates the object of the class Player.
     *
     * @param           playerName         Name of the player, as a String.
     * @param           inventory          An arraylist of pairs that represent 
     *                                     the player's inventory.
     *  
     */
    public Player(String playerName, ArrayList<Pair> inventory)
    {
        this.playerName = playerName;
        this.inventory = inventory;
    }

    /**
     * Method to adjust the inventory item by referencing the index and the amount to {@link AdjustmentEvent}
     *
     * @param       index               Index of the inventory item, as an integer
     * @param       adjustmentAmount    Amount to adjust the value of inventory item by, as an integer.
     */
    public void adjustInventoryItemAmt(int index, int adjustmentAmount)
    {
        setSpecificInventoryValue(index, getSpecificInventoryValue(index) + adjustmentAmount);
        return;
    }

    /**
     * Method to represent a Player object as a String
     *
     * @return                      Representation of this Player object,
     *                              as a String.          
     */
    public String display()
    {
        String display = "Player name: " + playerName;
        for (int i = 0; i < inventory.size(); i++)
        {
            display += ", " + inventory.get(i).getKey() + ": " + inventory.get(i).getValue();
        }
        return display;
    }

    /**
     * Accessor method to get the inventory as an arraylist.
     *         
     * @return                      The inventory, as an arraylist of pairs.
     */
    public ArrayList<Pair> getInventory()
    {
        return inventory;
    }

    /**
     * Accessor method to get the player name.
     *
     * @return                      The player name, as a String.
     */
    public String getPlayerName()
    {
        return playerName;
    }

    /**
     * Accessor method to get the specific inventory item as a pair.
     *
     * @param         index         Index of the inventory item, as an integer.
     * @return                      An inventory item, as a pair.
     */
    public Pair getSpecificInventoryItem(int index)
    {
        if (inventory.size() >= index)
            return inventory.get(index);
        else
            return null;
    }

    /**
     * Accessor method to get the value of a specific inventory item.
     * 
     * @param           index       Index of the inventory item, as an integer.
     *
     * @return                      Value of the specific inventory item, as an integer
     */
    public int getSpecificInventoryValue(int index)
    {
        if (inventory.size() >= index)
            return inventory.get(index).getValue();
        return INVALID;
    }
    
    /*
     * Method to initialise the inventory arraylist in the default constructor
     *     
     */
    private void initialiseInventory()
    {
        inventory.add(new Pair("Coins", 10));
        inventory.add(new Pair("Invisibility Cloaks", 0));
        inventory.add(new Pair("Jumps", 3));
    }

    /**
     * Method to check if the inventory is full
     *
     * @return          Returns whether inventory is full or not
     */
    public boolean isInventoryFull()
    {
        if (getSpecificInventoryValue(INVISIBILITY_CLOAKS) == INVENTORY_LIMIT)
            return true;
        return false;
    }

    /**
     * Mutator method to set the player's inventory
     *
     * @param        inventory    The inventory of the player,
     *                            as an arraylist of Pair objects.
     */
    public void setInventory(ArrayList<Pair> inventory)
    {
        this.inventory = inventory;
    }

    /**
     * Mutator method to set the player name
     *
     * @param        playerName    The player name, as a String
     */
    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }

    /**
     * Mutator method to set a specific player's inventory item
     *
     * @param        inventoryItem    An inventory item, as a Pair object.
     */
    public void setSpecificInventoryItem(int index, Pair inventoryItem)
    {
        if (inventory.size() >= index)
            inventory.set(index, inventoryItem);
    }
    
    /**
     * Mutator method to set the value of specific player's inventory item
     *
     * @param         index         Index of the inventory item, as an integer
     * @param           int         New value of the inventory item, as a Pair.
     */
    public void setSpecificInventoryValue(int index, int value)
    {
        if (inventory.size() >= index)
            inventory.get(index).setValue(value);
    }
}
