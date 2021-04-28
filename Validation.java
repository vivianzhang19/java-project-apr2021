/**
 * Class which tests the validity of user input.
 *
 * @author Vivian Zhang
 * @version ver1.15
 */
public class Validation
{
    /**
     * Default constructor which creates the object of the class Validation.
     *
     */
    public Validation()
    {

    }

    /*
     * Method to determine whether the length of an input string is valid 
     * based on specified minimum and maximum length.
     *
     * @param        input           User input, as a String
     * @param        minLength       Minimum acceptable length of string input,
     *                               as an integer.
     * @param        maxLength       Maximum acceptable length of string input, 
     *                               as an integer.
     *
     * @return                       True if the length of user string input is
     *                               within the specified range.
     */
    public static boolean isStringLengthValid(String input, 
                                              int minLength, 
                                              int maxLength)
    {
        boolean validLengthFlag = false;
        if (input.trim().length() >= minLength && 
            input.trim().length() <= maxLength)
            validLengthFlag = true;
        return validLengthFlag;
    }

    /*
     * Method that returns a valid integer based on the input of an integer.
     *
     * @param               input           Input of an integer, as a String
     *
     * @return                              The number, as an integer; 
     *                                      Returns default value of 0 if the
     *                                      input is invalid.
     */
    public static int returnValidIntegerInput(String input, String message)
    {
        int num = 0;
        try
        {
            num = Integer.parseInt(input);
        }
        catch (Exception e)
        {
            System.out.println(message);
        }
        return num;
    }

    /*
     * Method to check that the user has entered Yes or No.
     *
     * @param               inputChar       A character entered by the user.
     *
     * @return                              True if the character entered is
     *                                      either 'y' or 'n'
     */
    public static boolean validateYesOrNo(char inputChar)
    {
        //Methods of other classes deal with case sensitivity
        // of user's input.
        if ((inputChar == 'y') || (inputChar == 'n'))
            return true;
        return false;
    }
}
