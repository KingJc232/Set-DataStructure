/*
 * Developer: Jose Ceballos
 * File: ArraySet.java
 * Description: Implements the Set Data Structure using an Array Under the Hood
 * Class: CS2400
 * */


//Generic Data Structure 
public class ArraySet <T> implements SetInterface<T>
{

	//Data Fields
	public static final int MAX_SIZE = 10000; //Maximum Amount of Elements the ArraySet can Hold
	public static final int DEFAULT_SIZE = 50; //The array starts with enough memory for 50 generic objects 

	private T[] data; //Generic Array Used to Store the Objects of the ArraySet (UndeHood Implementation)
	private int qtyOfItems; //Keeps track of the size of the Array and the next available index of the Array

	//Default Constructor
	public ArraySet()
	{
		this(DEFAULT_SIZE); //Using the Initializor Constructor 
	}

	//Initializor Constructor
	public ArraySet(int initialSize)
	{
		this.qtyOfItems = 0; //intially there are zero Items in the Array 
		this.data = (T[])(new Object[Math.min(initialSize, this.MAX_SIZE)]); //Initializing the Generic Array to the Specified Amount
	}


	//Methods from SetInterface
	/** Gets the current number of entries in this set.
        @return  The integer number of entries currently in the set. */
	public int getCurrentSize()
	{
		return this.qtyOfItems;
	}
   
	/** Sees whether this set is empty.
        @return  True if the set is empty, or false if not. */
	public boolean isEmpty()
	{
		return this.qtyOfItems == 0;
	}
   
	/** Adds a new entry to this set, avoiding duplicates.
	    @param newEntry  The object to be added as a new entry.
	    @return  True if the addition is successful, or
	             false if the item already is in the set. */
	public boolean add(T newEntry)
	{
		//Checking if Set is at Max Capacity 
		if(this.qtyOfItems >= 10000)
		{
			return false; //Max Capacity Cant Add More items 
		}
		
		//Checks if a  newEntry is already in the set using helper method "checkForDuplicate" return type boolean
		if(checkForDuplicate(newEntry))
		{
			return false; //Duplicate Found DONT ADD return false 
		}

		//Checking if the Set needs more memory 
		if(this.qtyOfItems >= this.data.length)
		{
			this.resizeArray(); //Helper Method Doubles the Memory of the this.data Generic Array
		}

		//Adding the Entry to the Set And Incrementing the this.qtyOfItems Post Incrementation
		this.data[this.qtyOfItems++] = newEntry; 
		
		return true; //Entry added to the set 
			
	}

	/** Doubles the this.data Generic Array Memory And Copies all the elements to the newly formed Array and sets this.data to it 
	    @return void*/
	private void resizeArray()
	{
		//Creating a Generic Array That has Double the Memory And Checking it Doesnt Exceed the MAX_SIZE
		T[] holder = (T[])(new Object[Math.min(this.data.length * 2, this.MAX_SIZE)]);

		//Copying all the elements to the holder generic array
		for(int i = 0; i < this.data.length;i++)
		{
			holder[i] = this.data[i]; 
			//Nulling the old elements to clean up the unused data 
			this.data[i] = null;
		}

		this.data = holder; //Updating the Generic Array Reference to have Double the Capactiy 
	}
	
	/** Checks if an element is already found in the Set using the elements .equals() method
	    @return A boolean True if element found in Set False if Element not found in set 
	 */
	private boolean checkForDuplicate(T newEntry)
	{
		//Checking all the elements in the Array for a duplicate 
		for(int i = 0; i < this.qtyOfItems; i++)
		{
			if(this.data[i].equals(newEntry))
			{
				return true; //Found a Duplicate 
			}
		}
		return false; //Did Not Find A duplicate
	}


	/** Removes a specific entry from this set, if possible.
	    @param anEntry  The entry to be removed.
        @return  True if the removal was successful, or false if not. */
	public boolean remove(T anEntry)
	{
		
		//Iterating the set to locate the Entry to remove it
		for(int i = 0; i < this.qtyOfItems; i++)
		{
			if(this.data[i].equals(anEntry))
			{
				//Can Do this because Sets are Unordered
				this.data[i] = this.data[this.qtyOfItems -1]; //Setting the Element to remove to the last elements Value
				this.remove(); //Removing the Last Element and Decrementing qtyOfItems; 		
				return true; //Removal successful 
			}
		}

		return false;//Removal unsuccessful
	}

   	/** Removes one unspecified entry from this set, if possible.
        @return  Either the removed entry, if the removal
                was successful, or null. */
	public T remove()
	{
		//Ensuring the Set is not Empty 
		if(this.isEmpty())
		{
			return null;//Empty Set
		}
		//Removing the Last Value in the Set Bc its the easiest 
		T removedElementValue = this.data[this.qtyOfItems - 1];//Saving the Last Value
		this.data[--this.qtyOfItems] = null; //Predecrementing this.qtyOfItems and nulling element to clean with GC
		return removedElementValue; //Returning the Value of the Element Removed
	}
   
	/** Removes all entries from this set. */
	public void clear()
	{
		while(this.isEmpty() == false)
		{
			this.remove(); //Removing all the Items in the Set
		}
	}

	/** Tests whether this set contains a given entry.
	    @param anEntry  The entry to locate.
	    @return  True if the set contains anEntry, or false if not. */
	public boolean contains(T anEntry)
	{
		//Testing Every Element in the Set 
		for(int i = 0; i < this.qtyOfItems; i++)
		{
			if(this.data[i].equals(anEntry))
			{
				return true; //Found anEntry
			}
		}
		return false; //Entry Not Found
	}

	/** Retrieves all entries that are in this set.
		 @return  A newly allocated array of all the entries in the set. */
	public T[] toArray()
	{
		//Allocating enough Memory for a new Generic Array To return 
		//Not Returning the this.data Generic Array Because that breaks Encapsulation 
		T[] copyArray = (T[])(new Object[this.qtyOfItems]);


		//Copying all the elements in the this.data Generic Array to the copyArray
		for(int i = 0; i < this.qtyOfItems;i++)
		{
			copyArray[i] = this.data[i];
		}

		return copyArray;
	}

	/** @Override toString
	    @return a String */
	public String toString()
	{
		//Holds all the Elements in the Set
		String holder = "[";
		String comma = " ";
		for(int i = 0; i < this.qtyOfItems;i++)
		{
			holder += (comma + this.data[i]);
			comma = " , ";
		}
		holder += " ]";
		return holder;
	}
}
