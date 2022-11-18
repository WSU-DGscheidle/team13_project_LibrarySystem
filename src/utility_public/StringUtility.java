package utility_public;

/**
 * This Class contain methods for String handling
 *
 */
public class StringUtility {

	/**
	 * A string passed to this method is checked to see if it is null
	 * @param str
	 * @return  true: is null; false: is not null.
	 * 
	 * @author: Skyler
	 * @throws NullStringException 
	 */
	public static boolean isNull(String str ) throws NullStringException {
		//add more codes
		return str == null;    
				
	}
	
	/**
	 *  A string passed to this method is checked to see if it is empty
	 * @param str
	 * @return true: is empty; false: is not empty.
	 * 
	 * @author: Skyler
	 * @throws EmptyStringException 
	 */
	public static boolean isEmpty(String str) throws EmptyStringException {	
		//add more codes
		return (str.isEmpty() || str.isBlank());
	}
	
}//class


class NullStringException  extends Exception  
{  
	//default serial version
	private static final long serialVersionUID = 1L;

	public NullStringException (String str)  
    {          
        super(str);  
    }  
}  

class EmptyStringException  extends Exception  
{  
	//default serial version
	private static final long serialVersionUID = 1L;

	public EmptyStringException (String str)  
    {          
        super(str);  
    }  
}  