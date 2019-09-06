/*
 * H.W For CS2400 Implement the Set Data Structure (Generic)  Using the Interface Provided 
 *
 * Only Turn in two java files the 
 *
 * ArraySet.java And the LinkedSet.java 
 *
 * The ArraySet will implement the Set Data Structure using an Array Under the Hood With a Max Size of 10000
 *
 * The LinkedSet will implement the Set Data Structure using the Concept of Singularly Linked List Under the Hood with a Max Size of 10000
 * */

//Used to Test the ArraySet<T> and the LinkedSet<T>
public class Main
{
	public static void main(String[] args)
	{

		LinkedSet<Integer> set = new LinkedSet<>();	
		for(int i = 0; i < 5;i++)
		{
			set.add(i);
		}
		System.out.println(set);

		
		System.out.println(set.remove(4));

		Object[] copyArray = set.toArray();
		for(Object element : copyArray)
		{
			System.out.print(element + " ");
		}
		System.out.println();
	}
}
