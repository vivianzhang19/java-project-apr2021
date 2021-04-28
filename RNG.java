/**
 * Class which generates calculations related to random numbers.
 *
 * @author Vivian Zhang
 * @version ver1.15
 */
public class RNG
{
    public static final int MIN_PROBABILITY = 0;
    public static final int MAX_PROBABILITY = 100;
    
    public static final int MIN_PROB_ADJ = 1;
    public static final int MAX_PROB_ADJ = 5;
    
    public static final int POSITIVE_POLARITY = 1;
    public static final int NEGATIVE_POLARITY = -1;
    public static final int POLARITY_LIKELIHOOD = 50;

    /**
     * Default constructor which creates the object of the class RNG.
     *
     */
    public RNG()
    {

    }

    /**
     * Method to adjust a certain probability by a random number within a
     * specified range (inclusive).
     *
     * @param           currentProb The original probability to be adjusted,
     *                              as an integer.   
     * @param           minProbAdj  The lower boundary of the adjustment range,
     *                              as an integer.
     * @param           maxProbAdj  The upper boundary of the adjustment range,
     *                              as an integer.
     *
     * @return                      The adjusted probability, as an integer.    
     */
    public static int adjustProbByRandomNum(int currentProb, 
                                            int minProbAdj, 
                                            int maxProbAdj)
    {
        /*Generates a random number adjustment that is equally likely to 
        be positive or negative.*/
        int randomAdj = generateRandomNum(minProbAdj, maxProbAdj);
        randomAdj = randomAdj * (sampleRandomVariable(POLARITY_LIKELIHOOD) ?
                                 POSITIVE_POLARITY: NEGATIVE_POLARITY);

        int newProb = currentProb + randomAdj;
        
        if (newProb > MAX_PROBABILITY)
            return MAX_PROBABILITY;
        if (newProb < MIN_PROBABILITY)
            return MIN_PROBABILITY;
        return newProb;
    }

    /**
     * Method to check if a number lies within the specified range (inclusive).
     *
     * @param           lowerValue  The lower boundary of the specified range,
     *                              as an integer.   
     * @param           upperValue  The upper boundary of the specified range,
     *                              as an integer.
     * @param           randomNum   The number that is checked to determine if 
     *                              it lies within the specified range.
     *
     * @return                      Returns true if the number is in range.       
     */
    public static boolean checkIfNumWithinRange(int lowerValue,
                                                int upperValue, 
                                                int randomNum)
    {
        if ((randomNum >= lowerValue) && (randomNum <= upperValue))
        {
            return true;
        }
        return false;
    }

    /**
     * Method to generate a random number within a specified range (inclusive).
     *
     * @param       lowerValue      The lower boundary of the specified range,
     *                              as an integer.   
     * @param       upperValue      The upper boundary of the specified range,
     *                              as an integer.
     *
     * @return                      The random number within , as an integer.          
     */
    public static int generateRandomNum(double lowerValue, double upperValue)
    {
        return (int)((Math.random() * 
                    ((upperValue - lowerValue) + 1)) + lowerValue);
    }

    /*
     * Method to determine whether a sample event would occur, given the 
     * probability of that event occurring.
     *
     * @param           threshold   The probability of an event occurring, 
     *                              as an integer.
     *
     * @return                      True if a sample event occurs.          
     */
    public static boolean sampleRandomVariable(int threshold)
    {
        int randomNum = (int) (Math.random() * MAX_PROBABILITY + 1);
        if (threshold >= randomNum)
            return true;
        return false;      
    }
}
