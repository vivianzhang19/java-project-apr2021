/**
 * Class which stores information of portals within a game room.
 *
 * @author Vivian Zhang
 * @version ver1.15
 */
public class Portal
{
    private String direction;
    private int probOpen;
    private int probExit;
    private int probPolice;
    private boolean isPortalOpen;
    private boolean doesPortalContainExit;
    private boolean doesPortalContainPolice;
    
    /**
     * Default constructor which creates the object of the class Portal.
     *
     */
    public Portal()
    {
        direction = "Unknown";
        probOpen = 0;
        probExit = 0;
        probPolice = 0;
        isPortalOpen = false;
        doesPortalContainExit = false;
        doesPortalContainPolice = false;
    }

    /**
     * Non-Default constructor which creates the object of the class Portal.
     *
     * @param   direction               Accepts the portal direction,
     *                                  as a String.
     * @param   probOpen                Accepts the probability of the portal
     *                                  being open, as an integer.
     * @param   probExit                Accepts the probability of the portal 
     *                                  having an exit, as an integer. 
     * @param   probPolice              Accepts the probability of the Magic 
     *                                  Police appearing after entering the 
     *                                  corresponding portal, as an integer.
     *
     */
    public Portal(String direction, int probOpen, int probExit, int probPolice)
    {   
        this.direction = direction;
        this.probOpen = probOpen;
        this.probExit = probExit;
        this.probPolice = probPolice;
        isPortalOpen = false;
        doesPortalContainExit = false;
        doesPortalContainPolice = false;
    }

    /**
     * Non-Default constructor which creates the object of the class Portal.
     *
     * @param   direction               Accepts the portal direction,
     *                                  as a String.
     * @param   probOpen                Accepts the probability of the portal
     *                                  being open, as an integer.
     * @param   probExit                Accepts the probability of the portal 
     *                                  having an exit, as an integer. 
     * @param   probPolice              Accepts the probability of the Magic 
     *                                  Police appearing after entering the 
     *                                  corresponding portal, as an integer.
     * @param   isPortalOpen            Accepts the status of portal being open
     *                                  /closed, as a boolean.
     * @param   doesPortalContainExit   Accepts the status of portal having
     *                                  exit/no exit, as a boolean.
     * @param   doesPortalContainPolice Accepts the status of portal containing 
     *                                  police/no police, as a boolean     
     */
    public Portal(String direction, int probOpen, int probExit, int probPolice,
                  boolean isPortalOpen, boolean doesPortalContainExit, 
                  boolean doesPortalContainPolice)
    {   
        this.direction = direction;
        this.probOpen = probOpen;
        this.probExit = probExit;
        this.probPolice = probPolice;
        this.isPortalOpen = isPortalOpen;
        this.doesPortalContainExit = doesPortalContainExit;
        this.doesPortalContainPolice = doesPortalContainPolice;
    }

    /*
     * Method to represent a Portal object as a String
     *
     * @return                      Representation of this Portal object,
     *                              as a String.          
     */
    public String display()
    {
        String display = "Direction: " + direction;
        display += "\nProbability Open: " + probOpen + "%, Probability Exit: " 
                   + probExit + "%, Probability Police: " + probPolice +"%";
        display += "\nOpen? : " + (isPortalOpen ? "Open" : "Closed");
        display += "\nContains an exit? : " + 
                   (doesPortalContainExit ? "Exit" : "No Exit");
        display += "\nContains magic police? : " + 
                   (doesPortalContainPolice ? "Police" : "No Police") + "\n";
        return display;
    }

    /**
     * Method to display the probabilities of the Portal object as a String
     * (probability of portal being open/closed, containing exit, containing
     * police)
     *
     * @return                      The probabilities of the Portal object, 
     *                              as a String.
     */
    public String displayProbabilityInfo()
    {
        return "Direction: " + direction + ", Prob Open: " + probOpen +
               "%, Prob Exit: " + probExit + "%, Prob Police: " 
               + probPolice + "%";
    }

    /**
     * Accessor method to get the portal direction.
     *
     * @return                      The portal direction, as a String.
     */
    public String getDirection()
    {
        return direction;
    }

    /**
     * Accessor method to get the probability of the portal having an exit.
     *
     * @return                      The probability of the portal having an
     *                              exit, as an integer.
     */
    public int getProbExit()
    {
        return probExit;
    }

    /**
     * Accessor method to get the probability of the portal being open.
     *
     * @return                      The probability of the portal being open,
     *                              as an integer.
     */
    public int getProbOpen()
    {
        return probOpen;
    }
    
    /**
     * Accessor method to get the probability of the Magic Police appearing
     * after entering the corresponding portal as an integer.
     *
     * @return                      The probability of the Magic Police 
     *                              appearing, as an integer.
     */
    public int getProbPolice()
    {
        return probPolice;
    }

    /**
     * Accessor method to get the status of the portal being open/closed.
     *
     * @return                      The status of the portal being 
     *                              open/closed, as a boolean.
     */
    public boolean getIsPortalOpen()
    {
        return isPortalOpen;
    }

    /**
     * Accessor method to get the status of the portal containing an 
     * exit/no exit.
     *
     * @return                      The status of the portal containing
     *                              an exit/no exit, as a boolean.
     */
    public boolean getDoesPortalContainExit()
    {
        return doesPortalContainExit;
    }
    
    /**
     * Accessor method to get the status of the portal having police/no police.
     *
     * @return                      The status of the portal having police/no
     *                              police, as a boolean.
     */
    public boolean getDoesPortalContainPolice()
    {
        return doesPortalContainPolice;
    }

    /**
     * Mutator method to set the portal direction.
     *
     * @param       direction   The portal direction, as a String.
     */
    public void setDirection(String direction)
    {
        this.direction = direction;
    }

    /**
     * Mutator method to set the probability of the portal having an exit.
     *
     * @param        probExit    The probability of the portal having an exit,
     *                           as an integer.
     */
    public void setProbExit(int probExit)
    {
        this.probExit = probExit;
    }

    /**
     * Mutator method to set the probability of the Magic Police appearing
     * after entering the corresponding portal.
     *
     * @param           probOpen    The probability of the Magic Police
     *                              appearing, as an integer.
     */
    public void setProbOpen(int probOpen)
    {
        this.probOpen = probOpen;
    }

    /**
     * Mutator method to set the probability of the portal being open.
     *
     * @param           probPolice  The probability of the portal being open,
     *                              as an integer.
     */
    public void setProbPolice(int probPolice)
    {
        this.probPolice = probPolice;
    }

    /**
     * Mutator method to set the status of whether the portal is open/closed.
     *
     * @param      isPortalOpen    The status of portal being open/closed, 
     *                             as an integer.
     */
    public void setIsPortalOpen(boolean isPortalOpen)
    {
        this.isPortalOpen = isPortalOpen;
    }

    /**
     * Mutator method to set the status of whether the portal has exit/no exit.
     *
     * @param      doesPortalContainExit    The status of portal having 
     *             exit/no exit, as an integer.
     */
    public void setDoesPortalContainExit(boolean doesPortalContainExit)
    {
        this.doesPortalContainExit = doesPortalContainExit;
    }

    /**
     * Mutator method to set the status of whether the portal has 
     * police/no police.
     *
     * @param      doesPortalContainPolice    The status of portal having 
     * police/no police, as an integer.
     */
    public void setDoesPortalContainPolice(boolean doesPortalContainPolice)
    {
        this.doesPortalContainPolice = doesPortalContainPolice;
    }
}
