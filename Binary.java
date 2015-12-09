/*
  Shaumik Ashraf, Sadia Azmine
  APCS1 pd9
  HW 44 -- This or That or Fourteen Other Things
  2015-12-08
*/
//skeleton file for class Binary

public class Binary {

    private int _decNum;
    private String _binNum;


    /*=====================================
      default constructor
      pre:  n/a
      post: initializes _decNum to 0, _binNum to "0"
      =====================================*/
    public Binary() { 
	/****** YOUR IMPLEMENTATION HURRR ******/
	_decNum = 0;
	_binNum = "0";
    }


    /*=====================================
      overloaded constructor
      pre:  n >= 0
      post: sets _decNum to n, _binNum to equiv string of bits
      =====================================*/
    public Binary( int n ) {
	
	if (n < 0) {
	    System.out.println("Error: This number is invalid.");
	}
	else {
	    _decNum = n;
	    _binNum = decToBin(n);
	}
    }


    /*=====================================
      overloaded constructor
      pre:  s is String representing non-negative binary number
      post: sets _binNum to input, _decNum to decimal equiv
      =====================================*/
    public Binary( String s ) {
			
	if (s.length() <= 0) {
	    System.out.println("Error: Invalid binary number.");
	}
	else {
	    _binNum = s;
	    _decNum = binToDec(s);
	}
    }


    /*=====================================
      String toString() -- returns String representation of this Object
      pre:  n/a
      post: returns String of 1's and 0's representing value of this Object
      =====================================*/
    public String toString() { 
			
	return _binNum;
    }


    /*=====================================
      String decToBin(int) -- converts base-10 input to binary
      pre:  n >= 0
      post: returns String of bits
      eg  decToBin(0) -> "0"
      decToBin(1) -> "1"
      decToBin(2) -> "10"
      decToBin(3) -> "11"
      decToBin(14) -> "1110"
      =====================================*/
    public static String decToBin( int n ) {
	/****** YOUR IMPLEMENTATION HURRR ******/
	String retStr = "";
	int rem = 0;
	while (n != 0) {
	    rem = n % 2;
	    n = n / 2;
	    retStr = rem + retStr;
	}
	return retStr;
    }


    /*=====================================
      String decToBinR(int) -- converts base-10 input to binary, recursively
      pre:  n >= 0
      post: returns String of bits
      eg  decToBinR(0) -> "0"
      decToBinR(1) -> "1"
      decToBinR(2) -> "10"
      decToBinR(3) -> "11"
      decToBinR(14) -> "1110"
      =====================================*/
    public static String decToBinR( int n ) { 
	/****** YOUR IMPLEMENTATION HURRR ******/
	String retStr = "";
	int rem = 0;
	if (n == 0) {
	    retStr = "0";
	}
	else {
	    rem = n % 2;
	    retStr = rem + retStr;
	    decToBinR((int) n/2);
	}
	return retStr;
    }

    /*=====================================
      String binToDec(String) -- converts base-10 input to binary
      pre:  s represents non-negative binary number
      post: returns String of bits
      eg  
      binToDec("0") -> 0
      binToDec("1") -> 1
      binToDec("10") -> 2
      binToDec("11") -> 3
      binToDec("1110") -> 14
      =====================================*/
    public static int binToDec(String s) {
	/****** YOUR IMPLEMENTATION HURRR ******/
	int sum = 0;
	for (int x = 0; x < s.length() - 1; x++) {
	    sum += Integer.parseInt(s.substring(x, x+1)) * (Math.pow(2, s.length()-x));
	}
	return sum;
    }


    /*=====================================
      String binToDecR(String) -- converts base-10 input to binary, recursively
      pre:  s represents non-negative binary number
      post: returns String of bits
      eg  
      binToDecR("0") -> 0
      binToDecR("1") -> 1
      binToDecR("10") -> 2
      binToDecR("11") -> 3
      binToDecR("1110") -> 14
      =====================================*/
    public static int binToDecR( String s ) { 
	/****** YOUR IMPLEMENTATION HURRR ******/
	int sum = 0;
	if (s.length() == 0) {
	    sum += 0;
	}
	else {
	    sum += Integer.parseInt(s.substring(0,1)) * (Math.pow(2,s.length()));
	    binToDecR(s.substring(1));
	}
	return sum;
    }


    /*=============================================
      boolean equals(Object) -- tells whether 2 Objs are equivalent
      pre:  other is an instance of class Binary
      post: Returns true if this and other are aliases (pointers to same 
      Object), or if this and other represent equal binary values
      =============================================*/
    public boolean equals( Object other ) { 
	/****** YOUR IMPLEMENTATION HURRR ******/
	 
	//First, check for aliasing.
	boolean retVal = this == other;
 
	//Next, if this and input Object are different objects,
        if ( !retVal )
 
	    //...check to see if input Object is a Tile
	    retVal = other instanceof Binary
 
		//...and that its state variables match those of this Tile
		&& this._binNum.equals(((Binary)other)._binNum)
		&& this._decNum ==(((Binary) other)._decNum);
 
	return retVal;
    }


    /*=============================================
      int compareTo(Object) -- tells which of two Binary objects is greater
      pre:  other is instance of class Binary
      post: Returns 0 if this Object is equal to the input Object,
      negative integer if this<input, positive integer otherwise
      =============================================*/
    public int compareTo( Object other ) {
	/****** YOUR IMPLEMENTATION HURRR ******/
	int diff = 0;
	if (other instanceof Binary) {
	    if (this.equals(other)) {
		diff = 0;
	    }
	    if (this._decNum >((Binary)other)._decNum) {
		diff = this._decNum -((Binary)other)._decNum;
	    }
	    else {
		diff = this._decNum -((Binary)other)._decNum;		
	    }
	}
	else {
	    throw new ClassCastException("Error in compareTo() : parameter not of class Binary\n");
	}
	return diff;
    }


    //main method for testing
    public static void main( String[] args ) {

	System.out.println();
	System.out.println( "Testing ..." );
		
	Binary b1 = new Binary(5);
	Binary b2 = new Binary(5);
	Binary b3 = b1;
	Binary b4 = new Binary(7);
		
	System.out.println( b1 );
	System.out.println( b2 );
	System.out.println( b3 );       
	System.out.println( b4 );       
		
	System.out.println( "\n==..." );
	System.out.println( b1 == b2 ); //should be false
	System.out.println( b1 == b3 ); //should be true
		
	System.out.println( "\n.equals()..." );
	System.out.println( b1.equals(b2) ); //should be true
	System.out.println( b1.equals(b3) ); //should be true
	System.out.println( b3.equals(b1) ); //should be true
	System.out.println( b4.equals(b2) ); //should be false
	System.out.println( b1.equals(b4) ); //should be false
		
	System.out.println( "\n.compareTo..." );
	System.out.println( b1.compareTo(b2) ); //should be 0
	System.out.println( b1.compareTo(b3) ); //should be 0
	System.out.println( b1.compareTo(b4) ); //should be neg
	System.out.println( b4.compareTo(b1) ); //should be pos
	//			try{
	System.out.println( b1.compareTo("Asdf") ); //should thorw Error
	//} //!!!!!!!!!!!!!!!!!!!!!!!!!
	
	/*=========================================
	  =========================================*/
    }//end main()

} //end class
