import java.util.Scanner;

/**
 * Class which asks the user for input and verifies
 * that the input is in the correct format.
 *
 * @author Vivian Zhang
 * @version ver1.15
 */
public class Input
{
    /**
     * Default constructor which creates the object of the class Input.
     *
     */
    public Input()
    {

    }
    
    /** 
    * Method to accept input from the user as a character
    *
    * @param           message     Message to display to the user, as a String.
    * @param           position    Position of the character to extract from
    *                              the string input.
    *
    * @return                      Input from the user, as an character.                        
    */
    public static char acceptCharInput(String message, int position)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(message);
        return console.nextLine().trim().toLowerCase().charAt(position);
    }

    /** 
    * Method to accept input from the user as an integer.
    *
    * @param           message     Message to display to the user,
    *                              as a String.
    *
    * @return                      Input from the user, as an integer.                       
    */
    public static int acceptIntegerInput(String message)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(message);
        return Integer.parseInt(console.nextLine());
    }

    /** 
     * Method to accept String input from the user
     *
     * @param           message     Message to display to the user, 
     *                              as a String.
     *
     * @return                      Input from the user, as a String.                               
     */    
    public static String acceptStringInput(String message)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(message);
        return console.nextLine();
    }

    /** 
     * Method to accept Yes or No input from the user.
     *
     * @param           message     Message to display to the user,
     *                              as a String.
     *
     * @return                      Returns either the character 'y' or 'n'.                               
     */  
    public static char acceptYesOrNoInput(String message)
    {
        char inputChar = '\0';
        boolean validChoice = false;
        do
        {   
            try
            {
                inputChar = acceptCharInput(message, 0);
                if (inputChar == 'y'|| inputChar == 'n')
                    validChoice = true;
                else
                    System.out.println("You must enter Yes/No (Y/N).");
            }
            catch (Exception e)
            {
                System.out.println("You must enter Yes/No (Y/N).");
            }
            
        }
        while (validChoice == false);
        return inputChar;
    }  

    /** 
     * Method to allow user to press any key to continue game
     *
     * @param           message     Message to display to the user,
     *                              as a String.
     *                           
     */  
    public static void toContinueGame(String message)
    {
        Scanner console = new Scanner(System.in);
        System.out.println(message);
        console.nextLine();
    }
}
