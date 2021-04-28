/**
 * Class which creates Pair objects (consists of a key and value)
 * Currently, it is limited and only accepts String for key and
 * Integer for values.
 *
 * @author Vivian Zhang
 * @version ver1.15
 */
public class Pair
{
    private String key;
    private Integer value;

    /**
     * Default constructor which creates the object of the class Pair.
     *
     */
    public Pair()
    {
    
    }

    /**
     * Non-default constructor which creates the object of the class Pair.
     *
     * @param           key         The key for this pair, 
                                    as a String.
     * @param           value       The value to use for this pair,
                                    as an integer.
     *  
     */
    public Pair(String key, Integer value)
    {
        this.key = key;
        this.value = value;
    }

    /*
     * Method to represent a pair object as a String
     *
     * @return                      Representation of this pair object,
     *                              as a String.          
     */
    public String display()
    {
        return key + ": " + value;
    }

    /*
     * Method to get the key for this pair.
     *
     * @return                      Key for this pair, as a String.          
     */
    public String getKey()
    {
        return key;
    }
   
    /*
     * Method to get the value for this pair.
     *
     * @return                      Value for this pair, as a Integer.          
     */
    public Integer getValue()
    {
        return value;
    }

    /*
     * Method to set the key for this pair.
     *
     * @param             key       Key for this pair, as a String.          
     */
    public void setKey(String key)
    {
        this.key = key;
    }

    /*
     * Method to set the value for this pair.
     *
     * @param           value     Value for this pair, as a String.          
     */
    public void setValue(Integer value)
    {
        this.value = value;
    }
}
