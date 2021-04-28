import java.util.Scanner;
import java.util.ArrayList;

/**
 * Class which generates basic graphical interface of the game for the user.
 *
 * @author Vivian Zhang
 * @version ver1.15
 */
public class Interface 
{
    /**
     * Default constructor which creates the object of the class Interface.
     *
     */
    public Interface()
    {
        
    }

    /** 
    * Method to print out a message for the user, surrounded by a box
    * The box size adjusts based on the size of each line in the 
    * message (where each line is separated by a delimiter).
    *
    * @param           message     Message to display to the user, as a String.              
    */
    public static void displayBox(String message)
    {
        int length = 0;
        String[] splitString = message.split("\n");
        ArrayList<String> messageArrayList = new ArrayList<String>();
        for (int i = 0; i < splitString.length; i++)
        {
            String line = splitString[i];
            if (line.length() > length)
                length = line.length();
            messageArrayList.add(line);
        }

        drawTopBottomOfBox(length);
        for (String line: messageArrayList)
        {
            int numberSpaces = length - line.length();
            System.out.print("| " + line);
            for (int i = 0; i < numberSpaces; i++)
            {
                System.out.print(" ");
            }
            System.out.print(" |\n");   
        }
        drawTopBottomOfBox(length);
    }

    /** 
    * Method to display the status of the horizontal portals of the room that 
    * the user is in. (i.e. status of the East or West portals)
    *
    * @param     isWestPortalOpen     Whether the West portal is open, 
    *                                 as a boolean.
    * @param     isExitInWestPortal   Whether the West portal contains an exit, 
    *                                 as a boolean.
    * @param     isEastPortalOpen     Whether the East portal is open,
    *                                 as a boolean.
    * @param     isExitInEastPortal   Whether the East portal contains an exit,
    *                                 as a boolean.
    *
    * @return                         Shows whether the portal is open, closed,
    *                                 has an exit, as a String.                               
    */
    public static String displayHorizontalPortals(boolean isWestPortalOpen, 
                                                  boolean isExitInWestPortal,
                                                  boolean isEastPortalOpen, 
                                                  boolean isExitInEastPortal)
    {
        String lineOne = "";
        String lineTwo = "";
        String lineThree = "";
        
        if (isExitInWestPortal)
        {
            lineOne += "         ";
            lineTwo += "EXIT    X";
            lineThree += "         ";
        }
        else if (!isWestPortalOpen && !isExitInWestPortal)
        {
            lineOne += "|        ";
            lineTwo += "|       X";
            lineThree += "|        ";
        }
        else
        {
            lineOne += "         ";
            lineTwo += "        X";
            lineThree += "         ";
        }

        if (isExitInEastPortal)
        {
            lineTwo += "     EXIT";
        }
        else if (!isEastPortalOpen && !isExitInEastPortal)
        {
            lineOne += "        |";
            lineTwo += "        |";
            lineThree += "        |";
        }
        return lineOne + "\n" + lineTwo + "\n" + lineThree;
    }

    /** 
    * Method that displays the introduction message of the game to the user.
    *
    * @param      name      The name that is entered by the user, as a String.                  
    */
    public static void displayIntroMessage(String name)
    {
        String message = "Welcome to the land of Javalice, " + name + ".\n\n";
        message += "Unfortunately, a new king has ascended the throne and ";
        message += "declared all magic to be forbidden.\nIn an effort to ";
        message += "punish all users of magic, the king has created an ";
        message += "infinite number\nof mazes you must navigate through, ";
        message += "with the help of portals (or magical doorways). \n\n";
        message += "Your goal is to ultimately find the correct portal that ";
        message += "will lead you to the final exit\nand out of Javalice, ";
        message += "whilst escaping capture from the King and his faithful\n";
        message += "servants, the magic police.\n\n";
        message += "Good luck.";
        displayBox(message);
    }

    /** 
    * Method to print out a diagram of the player being in jail.
    */
    public static void displayJail()
    {
        System.out.println("+-----------------+");
        System.out.println("|      JAIL       |");
        System.out.println("|                 |");
        System.out.println("|        X        |");
        System.out.println("|                 |");
        System.out.println("|                 |");
        System.out.println("+-----------------+");
    }

    /** 
    * Method to display the status of the vertical portals of the room that 
    * the user is in. (i.e. status of the North or South portals)
    *
    * @param       isPortalOpen     Whether the portal is open, as a boolean.
    * @param       isExitInPortal   Whether the portal contains an exit,
    *                               as a boolean.
    *
    * @return                       Shows whether the portal is open, closed
    *                               or has an exit, as a String                       
    */
    public static String displayVerticalPortals(boolean isPortalOpen, 
                                                boolean isExitInPortal)
    {
        if (isExitInPortal)
            return ("    |  EXIT  |   ");
        else if (isPortalOpen)
            return ("    |        |   ");
        else 
            return ("    |--------|   ");
    }

    /** 
    * Method to print out a message that is outlined with top and bottom 
    * dotted lines
    *
    * @param           message     Message to display to the user, as a String.     
    */
    public static void drawTopBottomLines (String message)
    {
        String line ="-------------------------------------------------------";
        line += "---------------";
        System.out.println(line);
        System.out.print(message);
        System.out.println(line);
    }

    /** 
    * Method to print out the top and bottom lines of the box, which length changes
    * with the length of the message.
    *
    * @param           message     Message to display to the user, as a String.     
    */
    private static void drawTopBottomOfBox (int length)
    {
        System.out.print("+");
        for (int i = 0; i <= length + 1; i++)
            System.out.print("-");
        System.out.print("+\n");
    }    
}

