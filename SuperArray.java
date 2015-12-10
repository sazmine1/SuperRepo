/*
  Team Aquarray - Sean Bourke, Sadia Azmine
  APCS1 pd9
  HW40 -- Array of Grade 316
  2015-12-02
*/
/*****************************
 * SKELETON for
 * class SuperArray --  A wrapper class for an array. 
 * Maintains functionality:
 *  access value at index
 *  overwrite value at index
 *  report number of meaningful items
 * Adds functionality to std Java array:
 *  resizability
 *  ability to print meaningfully
 *  add item (at end)
 *  insert item
 *  remove item (while maintaining "left-justification")
 *****************************/

public class SuperArray {
 
    //~~~~~INSTANCE VARS~~~~~
    //underlying container, or "core" of this data structure:
    private Comparable[] _data;

    //position of last meaningful value
    private int _lastPos;

    //size of this instance of SuperArray
    private int _size;

		
    //~~~~~METHODS~~~~~
    //default constructor initializes 10-item array
    public SuperArray() 
    { 
	_data = new Comparable[10];
	_lastPos = -1; //flag to indicate no lastpos yet
	_size = 0;	
    }

		
    //output array in [a,b,c] format, eg
    // {1,2,3}.toString() -> "[1,2,3]"
    public String toString() 
    { 
	String foo = "[";
	for( int i = 0; i < _size; i++ ) {
	    foo += _data[i] + ",";
	}
	//shave off trailing comma
	if ( foo.length() > 1 )
	    foo = foo.substring( 0, foo.length()-1 );
	foo += "]";
	return foo;
    }

		
    //double capacity of this SuperArray
    private void expand() 
    { 
	Comparable[] temp = new Comparable[ _data.length * 2 ];
	for( int i = 0; i < _data.length; i++ )
	    temp[i] = _data[i];
	_data = temp;
    }

		
    //accessor -- return value at specified index
    public Comparable get( int index ) {
        return _data[index]; 
        
    }

		
    //mutator -- set value at index to newVal, 
    //           return old value at index
    public Comparable set( int index, Comparable newVal ) 
    { 
 	Comparable temp = _data[index];
	_data[index] = newVal;
	return temp;
    }


    // ~~~~~~~~~~~~~~ PHASE II ~~~~~~~~~~~~~~
    //adds an item after the last item
    public void add( Comparable newVal ) { 
        Comparable b [] = new Comparable [_data.length + 1];
        for (int x = 0; x < _data.length; x++) {
            b[x] = _data[x];
        }
        _data = b;
        _data[_lastPos + 1] = newVal;
        _lastPos += 1;
        _size += 1;
    }


    //inserts an item at index
    //shifts existing elements to the right
    public void add( int index, Comparable newVal ) {
	if (_size == _data.length)
	    expand();
	else if (index > _size){
	    _size ++;
	    _lastPos ++;
	}
	else {
	    Comparable temp = _data[index];
	    _data[index] = newVal;
	    add(index+1, temp);
	}
    }


    //removes the item at index
    //shifts elements left to fill in newly-empted slot
    public void remove( int index ) { 
        if (index > -1) {
	    for (int x = index; x < _data.length - 1; x++) {
		_data[x] = _data[x + 1];
	    }
	    _size -= 1;
	    _lastPos -= 1;
        }
    }

    //return number of meaningful items in _data
    public int size() { 
        return _size;
    }

    // ~~~~~~~~~~~~~~ PHASE III ~~~~~~~~~~~~~~
    //finds index of first appearance of an element
    public int linSearch(Comparable a) {
    	
	//inital counter, so if target is not in array, index will be -1
	int ans = -1;
	for (int x = 0; x < _size; x++) {
	    //checks to see if any index of the array is the same as the target
	    if(_data[x] == a) {
		ans = x;
		return ans;
	    }
	    //stops loop after finding the first matching target
	}
	return ans;
    }

    public boolean isSorted() {
	boolean retVal = false;
	if (_size == 1) {
	    retVal = true;
	}
	else{
	    for (int x = 0; x < _size; x++) {
		if ((_data[x].compareTo(_data[x+1])) == 1) {
		    retVal = false;
		}
		else {
		    retVal = true;
		}
	    }
	}
	return retVal;
    }


    //main method for testing
    public static void main( String[] args ) 
    {
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  
	  SuperArray curtis = new SuperArray();
	  System.out.println("Printing empty SuperArray curtis...");
	  System.out.println(curtis);

	  for( int i = 0; i < curtis._data.length; i++ ) {
	  curtis.set(i,i*2);
	  curtis._size++; //necessary bc no add() method yet
	  }

	  System.out.println("Printing populated SuperArray curtis...");
	  System.out.println(curtis);

	  System.out.println("testing get()...");
	  for( int i = 0; i < curtis._size; i++ ) {
	  System.out.print( "item at index" + i + ":\t" );
	  System.out.println( curtis.get(i) );
	  }

	  System.out.println("Expanded SuperArray curtis:");
	  curtis.expand();
	  System.out.println(curtis);


	  SuperArray mayfield = new SuperArray();
	  System.out.println("Printing empty SuperArray mayfield...");
	  System.out.println(mayfield);

	  mayfield.add(5);
	  mayfield.add(4);
	  mayfield.add(3);
	  mayfield.add(2);
	  mayfield.add(1);

	  System.out.println("Printing populated SuperArray mayfield...");
	  System.out.println(mayfield);

	  mayfield.remove(3);
	  System.out.println("Printing SuperArray mayfield post-remove...");
	  System.out.println(mayfield);
	  mayfield.remove(3);
	  System.out.println("Printing SuperArray mayfield post-remove...");
	  System.out.println(mayfield);

	  mayfield.add(3,99);
	  System.out.println("Printing SuperArray mayfield post-insert...");
	  System.out.println(mayfield);
	  mayfield.add(2,88);
	  System.out.println("Printing SuperArray mayfield post-insert...");
	  System.out.println(mayfield);
	  mayfield.add(1,77);
	  System.out.println("Printing SuperArray mayfield post-insert...");
	  System.out.println(mayfield);
      
	  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	//*****INSERT ANY ADDITIONAL TEST CALLS HERE*****
	SuperArray lois = new SuperArray();
	System.out.println("Printing empty SuperArray lois...");
	System.out.println(lois);

	lois.add(new Hexadecimal(55));
	lois.add(new Hexadecimal("10"));
	lois.add(new Hexadecimal(12));
	lois.add(new Hexadecimal("7"));
	lois.add(new Hexadecimal(1));

       	System.out.println("Printing populated SuperArray lois...");
	System.out.println(lois);

       	lois.remove(3);
	System.out.println("Printing SuperArray lois post-remove...");
	System.out.println(lois);
	lois.remove(3);
	System.out.println("Printing SuperArray lois post-remove...");
	System.out.println(lois);

	lois.add(3, new Hexadecimal("99"));
	System.out.println("Printing SuperArray lois post-insert...");
	System.out.println(lois);
	lois.add(2, new Hexadecimal(88));
	System.out.println("Printing SuperArray lois post-insert...");
	System.out.println(lois);
	lois.add(1, new Hexadecimal("77"));
	System.out.println("Printing SuperArray lois post-insert...");
	System.out.println(lois);

	Hexadecimal lane = new Hexadecimal("77");
       	System.out.println(lois.linSearch(new Rational(8,8)));
       	System.out.println(lois.linSearch(lane));

	System.out.println(lois.isSorted());
	/*	
		SuperArray clark = new SuperArray();
		System.out.println("Printing empty SuperArray clark...");
		System.out.println(clark);

		clark.add(new Binary(55));
		clark.add(new Hexadecimal("10"));
		clark.add(new Rational(1,2));
		clark.add(new Binary("010"));
		clark.add(new Hexadecimal(1));

		System.out.println("Printing populated SuperArray clark...");
		System.out.println(clark);

		clark.remove(3);
		System.out.println("Printing SuperArray clark post-remove...");
		System.out.println(clark);
		clark.remove(3);
		System.out.println("Printing SuperArray clark post-remove...");
		System.out.println(clark);

		clark.add(3, new Hexadecimal("99"));
		System.out.println("Printing SuperArray clark post-insert...");
		System.out.println(clark);
		clark.add(2, new Rational(8,8));
		System.out.println("Printing SuperArray clark post-insert...");
		System.out.println(clark);
		clark.add(1, new Binary(77));
		System.out.println("Printing SuperArray clark post-insert...");
		System.out.println(clark);

		System.out.println(clark.linSearch(new Rational(8,8)));
		System.out.println(clark.linSearch(new Hexadecimal("13")));

		System.out.println(clark.isSorted());
	*/
    }//end main
		
}//end class
