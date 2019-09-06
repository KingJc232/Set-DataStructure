/*
 * Developer Jose Ceballos
 * File: LinkedSet.java
 * Description: Implements the Set Data Structure using the concept of singularly linked list under the Hood
 * Class: CS2400
 * */


//LinkedSet Generic Data Structure Under hood uses singularly linked list 
public class LinkedSet <T>  implements SetInterface<T>
{
	public static final int MAX_SIZE = 10000; //Max Number Of Elements allowed in the Set
	private int qtyOfItems; // holds the size of the LinkedSet
	private Node head; //Points to the first node in the chain

	//Default Constructor
	public LinkedSet()
	{
		this.qtyOfItems = 0; //Initially Zero Elements in Set
		this.head = null; //Initially Head Not Connected to a Node
	}


	//Methods Provided by SetInterface
	/** Gets the current number of entries in this set.
        @return  The integer number of entries currently in the set. */
	public int getCurrentSize()
	{
		return this.qtyOfItems; //Returning the Amount of Items Used
	}

	/** Sees whether this set is empty.
        @return  True if the set is empty, or false if not. */
	public boolean isEmpty()
	{
		return this.qtyOfItems == 0; //If Zero True else False 
	}

	/** Adds a new entry to this set, avoiding duplicates.
	    @param newEntry  The object to be added as a new entry.
	    @return  True if the addition is successful, or
	             false if the item already is in the set. */
	public boolean add(T newEntry)
	{
		//Ensures the Set is within limitiations
		if(this.qtyOfItems >= this.MAX_SIZE)
		{
			return false; //Set Maxed Out
		}
		
		//Ensures the newEntry is Unique 
		if(this.checkForDuplicate(newEntry))
		{
			return false; //Dont Add newEntry is a Duplicate 
		}

		Node newNode = new Node(newEntry, null); //Creating a Node to Store the added data 

		if(this.isEmpty())
		{
			//First Node in LinkedSet
			this.head = newNode; 
		}
		else
		{
			//Linking the newNode to the linkedSet
			newNode.next = this.head; 
			this.head = newNode; //Updating the First Node 
		}
	
		this.qtyOfItems++; //Increasing the Size of the LinkedSet 
		return true; //newEntry Added Successfully 
	}

	/** Helper Method Checks if the Entry is around found in the LinkedData 
	    @return true if duplicate else returns false*/
	private boolean checkForDuplicate(T newEntry)
	{
		//Creating a tmp Node ptr to iterate through the LinkedData
		Node setIterator = head; //starting at the beginning 
		while(setIterator != null)
		{
			//Checking all the data in the nodes to see if the newEntry is a duplicate  
			if(setIterator.data.equals(newEntry))
			{
				return true; //Duplicate Found
			}
			setIterator = setIterator.next; //Going to the Next Node 
		}
		return false; //Duplicate Not Found
	}

	/** Removes a specific entry from this set, if possible.
	    @param anEntry  The entry to be removed.
        @return  True if the removal was successful, or false if not. */
	public boolean remove(T anEntry)
	{
		Node setIterator = head; //Used to Iterator through the set starting at the beginning 

		//Going through all Elements
		while(setIterator != null)
		{	
			//Checking if anEntry in LinkedSet
			if(setIterator.data.equals(anEntry))
			{
				setIterator.data = this.head.data; //copying the data of the first element Overriding anEntry from set

				this.remove(); //Removing the First element 
				
				return true; //An Entry Removed From the Set 
			}
			setIterator = setIterator.next; //Going to the next element 
		}
		
		return false;//Entry was not removed 
	}

   	/** Removes one unspecified entry from this set, if possible.
        @return  Either the removed entry, if the removal
                was successful, or null. */
  	 public T remove()
	 {
		 //Checking if the LinkedSet is Empty 
		 if(this.isEmpty())
		 {
			 return null;
		 }

		 T removedEntry = this.head.data; //Removing the first Element in set and Saving its Data  

		 Node saveNext = this.head.next; //Saving the next position in the LinkedSet

		 this.head.next = null; //Cutting Ties with the removed Element so GC cleans it 

		 this.head = saveNext; //Updating the Head of the LinkedSet

		 this.qtyOfItems--; //Decrementing the size of the LinkedSet
		 return removedEntry; //Returning the removed Element
	 }

	/** Removes all entries from this set. */
	public void clear()
	{
		while(this.isEmpty() == false)
		{
			this.remove(); //Remove the Elements in the LinkedSet
		}
	}

	/** Tests whether this set contains a given entry.
	    @param anEntry  The entry to locate.
	    @return  True if the set contains anEntry, or false if not. */
	public boolean contains(T anEntry)
	{
		Node setIterator = head; //Iterates through the set starting at the beginning 

		while(setIterator != null)
		{
			//Checks if the set contains anEntry
			if(setIterator.data.equals(anEntry))
			{
				return true; //Found Entry
			}
			setIterator = setIterator.next; //Go to Next Element
		}

		return false;//Did Not Find Entry
	}

	/** Retrieves all entries that are in this set.
		 @return  A newly allocated array of all the entries in the set. */
	public T[] toArray()
	{

		T[] copyArray = (T[]) new Object[this.qtyOfItems]; //Creating a Generic Array with enough memory to store elements in set

		Node setIterator = head; //Used to iterate through the the set starting at the beginning 

		int arrayIndex = 0; //Used to Index the copyArray
		while(setIterator != null)
		{
			copyArray[arrayIndex] = setIterator.data; 
			arrayIndex++; //Go to next Memory Location 
			setIterator = setIterator.next; //Go to next element 
		}
		return copyArray; 
	}

	/** @Override 
	    @return a String containing all the data in the LinkedSet*/
	public String toString()
	{
		String holder = "[";
		String comma = " ";
		Node setIterator = head; //Used to iterate the set
		while(setIterator != null)
		{
			holder += comma + setIterator.data;
			comma = " , ";
			setIterator = setIterator.next; //Go to next Element
		}
		holder += " ]";
		return holder;
	}

	//Nested Class Should Never see the light of day
	private class Node
	{	
		//No Modifiers Means Class and Package can Access them
		T  data; //Every Node Stores a Generic Data 
		Node next; //Points to the Next Node in the Chain
	
		//Default Constructor
		public Node()
		{
			this(null,null);
		}	

		public Node(T data, Node next)
		{
			this.data = data;
			this.next = next;
		}
	}


}
