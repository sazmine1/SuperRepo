/* 
   Team a_Rational_Interface - Shaumik Ashraf, Sadia Azmine
   APCS1 pd9
   HW41 -- In America, the Driver Sits on the Left
   2015-12-03
*/

public class Rational implements Comparable {
    
    private int p; //numerator
    private int q;//denominator
    //sets initial values for numerator and denominator
    public Rational() {
        p = 0;
        q = 1;
    }
    
    //allows one to create a new Rational Object with a certain numerator and denominator
    public Rational (int num, int den) {
        //will cause an error message and reset to default
        if (den == 0) {
            p = 0;
            q = 1;
            System.out.println("I'm sorry the number you are trying to make is undefined");
        }
        else {
            p = num;
            q = den;
        }
    }
    
    //overwrites inherited toString
    public String toString() {
        return p + "/" + q;
    }
    
    //stroes the value of the rational
    public double floatValue() {
        return (p*1.0)/q;
    }
    
    //accessor method for p
    public int getP(){
        return p;
    }
    
    //accesor method for q
    public int getQ(){
        return q;
    }
    
    //multiplies two rational classes together and changes the value of the initial class
    public void multiply(Rational s) {
        p = p*s.getP();
        q = q*s.getQ();
    }
    
    //divides two rational classes and changes the value of the initial class
    public void divide(Rational s){
        p = p*s.getQ();
        q = q*s.getP();
    }
    

    //////////////////////     PHASE 2    ///////////////////
	  
    //adds two rational classes and changes value of initial class
    public void add(Rational s){
        p = (this.getP() * s.getQ()) + (s.getP() * this.getQ());
	//numerators multiplied by the other rational object's denominator then added
        q = this.getQ() * s.getQ(); 
    }
    
    //subtracts two rational classes and changes value of initial class
    public void subtract(Rational s){
        p = (this.getP() * s.getQ()) - (s.getP() * this.getQ());
        q = this.getQ() * s.getQ();
    }
    
    // Finds the GCD of two integer inputs recursively.
    public int gcdER (int a, int b){
        int remainder = a%b;
	if (remainder == 0){
	    return b;
	}
	//recursively uses gcd on b and the remainder until a%b is 0
	//Note that if b > a, the first recusive step will be gcd(b,a)
	return gcd(b,remainder);
    }
    
    //changes rational to reduced form
    public void reduce() {
        p = p / gcdER(p, q); //num divided by gcd of num and denom
        q = q / gcdER(p, q); //denom divided by gcd of num and denom
    }

    //////////////////////     PHASE 3     ///////////////////
    
    public static int gcd(int p, int q) {
	int remainder = p%q;
	if (remainder == 0){
	    return p;
	}
	//recursively uses gcd on b and the remainder until a%b is 0
	//Note that if b > a, the first recusive step will be gcd(b,a)
	return gcd(q,remainder);
    }
    
    //DEFINE compareTo method from Comparable interface HERE
    public int compareTo(Object o){
        
        if(o == null) {
        	//nullPointExc
        	throw new NullPointerException("Error in compareTo() : null objects cannot be compared\n");
        }
        else if( o instanceof Rational ) {
        	if ((this.getP() * ((Rational)o).getQ()) == (((Rational)o).getP() * this.getQ()))  {
        		return 0; //Returns 0 if the two numbers are equal
    		}
    		else if ((this.getP() * ((Rational)o).getQ()) > (((Rational)o).getQ() * this.getP())) {
	    	return ((this.getP()*((Rational)o).getQ())-(((Rational)o).getP()*this.getQ()));
    		}//Returns difference of cross-multiplication if the calling number is larger than the parameter
    		else {
	    	return (-1);
    		}//Returns -1 if the calling number is smaller than the parameter
        }
        else {
        	//classCastExc
        	throw new ClassCastException("Error in compareTo(): Objects of different classes cannot be conmpared\n");
        }
    }
    
    //////////////////////     PHASE 4     ///////////////////
    public boolean equals( Object a ) {
    	
    	//First, check that the object is not null
    	if ( a == null) {
    		//nullPointerExc
    		throw new NullPointerException("Error in equals(): Object is null and cannot be compared\n");
    	}
    	
	//Next, check for aliasing.
	boolean retVal = this == a;
 
	//Next, if this and input Object are different objects,
        if ( !retVal ) {
 
	    //...check to see if input Object is a Rational
	    // Note that we reduce aliases because we don't actually want the original rationals to simplify
	    if (a instanceof Rational) {
		// Checks for same sign (positive or negative)
		boolean b1 = ((Rational)a).floatValue() > 0;
		boolean b2 = this.floatValue() > 0;
		if (!(b1 ^ b2)){
		    Rational _a = new Rational(((Rational)a).p,((Rational)a).q);
		    Rational _this = new Rational(this.p,this.q);
		    _a.reduce();
		    _this.reduce();
			
		    //...and that its state variables match those of this Tile
		    retVal = (_this.getP() == _a.getP()) && (_this.getQ() == _a.getQ());
		}
	    }
	    else {
	    	//classCastExc
	    	throw new ClassCastException("Error in equals(): Objects of different classes cannot be compared\n");
	    }
	}
	return retVal;
    }
  
    //main method for testing purposes
    public static void main(String[] args){
        Rational r = new Rational(2,3); //Stores the rational number 2/3
        Rational s = new Rational(1,2); //Stores the rational number 1/2
        System.out.println("Rational r: \t" + r);
        System.out.println("Rational s: \t" + s);

	//tests Phase 1
	System.out.println("Testing Phase 1...");	
	r.multiply(s); //Multiplies r by s, changes r to 2/6.  s remains ½
	System.out.println("r * s = \t" + r);
	System.out.println("r now equals 2/6");	
	r.divide(s); //Multiplies r by s, changes r to 4/6.  s remains ½
	System.out.println("r / s = \t" + r);
	System.out.println("r now equals 4/6");	

	//tests Phase 2
	System.out.println("Testing Phase 2...");	
	r.add(s); //Adds r and s, r becomes 14/12
	System.out.println("r + s = \t" + r);
	System.out.println("r now equals 14/12");	
	r.subtract(s); //subtracts s from r, r becomes 16/24
	System.out.println("r - s = \t" + r);
	System.out.println("r now equals 16/24");	
	r.reduce(); // r becomes 2/3
	System.out.println("when reduced r now equals: \t" + r);	
	Rational a = new Rational(1,0);
	System.out.println(a);
		
	//tests Phase 3
	System.out.println("Testing Phase 3...");
	System.out.println("A 0 indicates two Rationals are equal.");
        System.out.println("How does r compare to r? \t" + r.compareTo(r)); //should return 0
	System.out.println("A positive number indicates that the first Rational is greater than the second.");
	System.out.println("How does r compare to s? \t" + r.compareTo(s)); //should return 1
	System.out.println("A negative number indicates that the first Rational is less than the second.");
	System.out.println("How does s compare to r? \t" + s.compareTo(r)); //should return -1

	//tests Phase 4
	System.out.println("Testing Phase 4...");	
	Rational i = new Rational(1,4);
	Rational j = new Rational(2,-8);
	System.out.println("Is r equal to s? \t" + r.equals(s));
        System.out.println("Rational i: \t" + i);
        System.out.println("Rational j: \t" + j);	
	System.out.println("Is i equal to j? \t" + i.equals(j));
	System.out.println("Rational i: \t" + i);
	System.out.println("Is i equal to i? \t" + i.equals(i));
	System.out.println("Rational i: \t" + i);
        System.out.println("Rational j: \t" + j);
	System.out.println("Is j equal to i? \t" + j.equals(i));
	System.out.println("Rational a is not defined but has a numerator of 1 and denominator of 0.");
	System.out.println("Is i equal to a? \t" + i.equals(a));
    }
} 
//end class Rational
