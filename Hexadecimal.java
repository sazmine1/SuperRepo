/*
  Shaumik Ashraf, Sadia Azmine
  APCS1 pd9
  HW 44 -- This or That or Fourteen Other Things
  2015-12-08
*/
//Hexadecimal class 
/*NOTE: All hexadecimal digits in UPPER case ONLY*/

public class Hexadecimal implements Comparable {
    
    //Vars~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    private final static String HEXDIGITS = "0123456789ABCDEF";
    private int _decNum;
    private String _hexNum;
    
    //Constructors~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public Hexadecimal() {
	//sets _decNum to 0
	_decNum = 0;
	//sets _hexNum to "0"
	_hexNum = "0";
    }
    
    public Hexadecimal( int n ) {
    	
    	//makes sure n >= 0
    	if (n < 0) {
    	    System.err.println("Error: This number is invalid.");
    	}
    	else {
    	    //sets _decNum to n
    	    _decNum = n;
    	    //set _hexNum to its String representation through the decToHex method
    	    _hexNum = decToHex(n);
    	}
    }
    
    public Hexadecimal( String s ) {
        //makes sure input string isn't empty or invalid
    	if ( "".equals(s) ) {
    	    System.err.println("Error: Invalid number.");
    	}
    	else {
    	    //sets _hexNum to s
    	    _hexNum = s;
    	    //sets _decNum to its integer representation of s through the hexToDec method
    	    _decNum = hexToDec(s);
    	}
    }
    
    //Static Methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    //THIS METHOD FAILS!!!
    //converts hexadecimal number as string to int and returns it, assumes valid String arguement
    public static int hexToDec( String s ) {
        
        int rn=0; //return value; return-number
        
        //Iterate through s and HEXDIGITS to find the corresponding value of each "character" in s and add it to rn
        for(int i=0; i<s.length(); i++) {
            rn += (HEXDIGITS.indexOf(s.substring(i, i + 1)) * Math.pow(16, s.length() - 1 - i)); 
            
        }
        
        return(rn);
        
    }
        
    //converts hexadecimal number string to int and returns it, assumes valid arguement, defined recursively
    public static int hexToDecR( String s ) {
        
	if( s.length()==1 ) {
            return( HEXDIGITS.indexOf(s.substring(0,1)) );
        }
        else {
            return( (HEXDIGITS.indexOf(s.substring(0,1))*((int)Math.pow(16,s.length() - 1))) 
                    + hexToDecR( s.substring(1) ) );
        }
        
    }
    
    //convert int to hexadecimal number as string, assumes valid arguement (non-negative int)
    public static String decToHex( int n ) {
        String retStr = "";
        int rem = 0;
        while (n != 0) {
            rem = n % 16;
            n = n / 16;
            retStr = HEXDIGITS.substring(rem, rem + 1) + retStr;
        }
        return retStr;
    }
    
    //convert int to hexadecimal number as string, assumes non-negative int, defined recursively
    public static String decToHexR(int n) {
    
        String retStr = "";
    
        if( n<16 ) { //base case, last hexadecimal digit
            return( HEXDIGITS.substring(n,n+1) );
        }
        else { 
            retStr = HEXDIGITS.substring(n%16,n%16+1) + decToHexR(n/16) + retStr;
            return retStr;
        }
        
    }
    
    //other methods~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    /*overwrite Object.equals()
      if arguement object is Hexadecimal and has same value as calling object, returns true
      otherwise returns false
    */
    public boolean equals( Object other ) { 
    	 
    	//First, check for aliasing.
    	boolean retVal = this == other;
     
    	//Next, use compareTo(),
        retVal = this.compareTo(other)==0;
     
    	return retVal;
    }

    /*compares calling Hexadecimal to argument object
      if arguement object is not of class Hexadecimal an Exception is thrown
      if calling object is equal arguemnt object then 0 is returned
      if calling object is less than arguemnt object then -1 is returned
      if calling object is greater than arguemnt object then 1 is returned
    */
    public int compareTo(Object other) {
        
        if(other == null) {
        	throw new NullPointerException("Error: compareTo() - invalid argument, null object\n");
        }
        else if( !(other instanceof Hexadecimal) ) {
            throw new ClassCastException("Error : compareTo() - invalid arguement, non-Hexadecimal object\n");
        }
        else if( this._decNum == ((Hexadecimal)other)._decNum ) {
            return(0);
        }
        else if( this._decNum > ((Hexadecimal)other)._decNum ) {
            return(1);
        }
        else {
            return(-1);
        }
        
    }
    
    //return calling object as hexadecimal number string
    public String toString() {
        return( _hexNum );
    }
    
    //return Hexadecimal as primitive int
    public int intValue() {
        return( _decNum );
    }
    
    //main (for testing)~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static void main(String[] args) {
        
        //Initialize Hexadecimal instances and test constructors
        Hexadecimal a = new Hexadecimal();
        Hexadecimal b = new Hexadecimal( 255 );
        Hexadecimal c = new Hexadecimal( "1A32" ); //this constructor fails!!
        
        //print (also tests toString())
        System.out.println("Testing constructors....");
        System.out.println("a::\t hex:\t" + a + " dec:\t" + a.intValue() );
        System.out.println("b::\t hex:\t" + b + " dec:\t" + b.intValue() );
        System.out.println("c::\t hex:\t" + c + " dec:\t" + c.intValue() );
        
        //Test converters (hexToDec(), decToHex(), hexToDecR(), decToHexR())
        System.out.println("Testing converters....");
        System.out.println("A132F in base 10 format:\t" + hexToDec("A132F"));
        System.out.println("73 in base 16 format:\t" + decToHexR(73));
        System.out.println("034D in base 10 format:\t" + hexToDecR("034D"));
        System.out.println("90 in base 16 format: \t" + decToHex(90));
        
        
        //testing compareTo() and equals
        System.out.println("Testing comparers....");
        System.out.println("b.compareTo(113)->" + b.compareTo( new Hexadecimal(113) ) );
        System.out.println("b.compareTo(456)->" + b.compareTo( new Hexadecimal(456) ) );
        System.out.println("b.compareTo(255)->" + b.compareTo( new Hexadecimal(255) ) );
        try {
            System.out.println("b.compareTo( 'hello' )->" + b.compareTo( "hello" ) );
        } catch( Exception e ) {
            System.err.println(e);
        }
        System.out.println("b.equals(113)->" + b.equals( new Hexadecimal(113) ) );
        System.out.println("b.equals(255)->" + b.equals( new Hexadecimal(255) ) );
        System.out.println("b.equals(a)->" + b.equals(a));
        System.out.println("b.equals(c)->" + b.equals(c));
        
        ///Done
        System.out.println("Done.");
    }
    
}

