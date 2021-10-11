import java.util.ArrayList;
import java.util.Arrays;

public class ResizableArrayStack<T> implements StackInterface<T> {
	private T[] stack; // Cannot be final due to doubling
	private int numberOfEntries;
	private boolean integrityOK = false;
	private static final int DEFAULT_CAPACITY = 25; // Initial capacity of stack
	private static final int MAX_CAPACITY = 10000;
	
	public static void main(String[]args) {
		String exp = "231*+9-";
		System.out.println("postfix eval: " + evaluatePostfix(exp));
		
		
	}
	
	/** Creates an empty stack whose initial capacity is 25. */
	public ResizableArrayStack() {
		this(DEFAULT_CAPACITY);
	} // end default constructor

	/**
	 * Creates an empty stack having a given initial capacity.
	 * 
	 * @param initialCapacity The integer capacity desired.
	 */
	public ResizableArrayStack(int initialCapacity) {
		checkCapacity(initialCapacity);

		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] tempStack = (T[]) new Object[initialCapacity]; // Unchecked cast
		stack = tempStack;
		numberOfEntries = 0;
		integrityOK = true;
	} // end constructor

	/**
	 * Creates a Stack containing given entries.
	 * 
	 * @param contents An array of objects.
	 */
	public ResizableArrayStack(T[] contents) {
		checkCapacity(contents.length);
		stack = Arrays.copyOf(contents, contents.length);
		numberOfEntries = contents.length;
		integrityOK = true;
	} // end constructor

	/**
	 * Adds a new entry to this Stack.
	 * 
	 * @param newEntry The object to be added as a new entry.
	 * @return True.
	 */
	public void push(T newEntry) {
		checkintegrity();
		if (isArrayFull()) {
			doubleCapacity();
		} // end if

		stack[numberOfEntries] = newEntry;
		numberOfEntries++;
	} // end add

	/**
	 * Retrieves all entries that are in this Stack.
	 * 
	 * @return A newly allocated array of all the entries in this Stack.
	 */
	public T[] toArray() {
		checkintegrity();

		// The cast is safe because the new array contains null entries.
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries]; // Unchecked cast
		for (int index = 0; index < numberOfEntries; index++) {
			result[index] = stack[index];
		} // end for

		return result;
	} // end toArray

	public String toString() {
		T[] array = toArray();
		String str = "{";

		for (T item : array) {
			str += item + ",";
		}
		str += "}";
		return str;
	}

	/**
	 * Sees whether this Stack is empty.
	 * 
	 * @return True if this Stack is empty, or false if not.
	 */
	public boolean isEmpty() {
		return numberOfEntries == 0;
	} // end isEmpty

	/**
	 * Gets the current number of entries in this Stack.
	 * 
	 * @return The integer number of entries currently in this Stack.
	 */
	public int getCurrentSize() {
		return numberOfEntries;
	} // end getCurrentSize

	/**
	 * Counts the number of times a given entry appears in this Stack.
	 * 
	 * @param anEntry The entry to be counted.
	 * @return The number of times anEntry appears in this ba.
	 */
	public int getFrequencyOf(T anEntry) {
		checkintegrity();
		int counter = 0;

		for (int index = 0; index < numberOfEntries; index++) {
			if (anEntry.equals(stack[index])) {
				counter++;
			} // end if
		} // end for

		return counter;
	} // end getFrequencyOf

	/**
	 * Tests whether this Stack contains a given entry.
	 * 
	 * @param anEntry The entry to locate.
	 * @return True if this Stack contains anEntry, or false otherwise.
	 */
	public boolean contains(T anEntry) {
		checkintegrity();
		return getIndexOf(anEntry) > -1; // or >= 0
	} // end contains

	/** Removes all entries from this Stack. */
	public void clear() {
		while (!isEmpty())
			pop();
	} // end clear

	/**
	 * Removes one unspecified entry from this Stack, if possible.
	 * 
	 * @return Either the removed entry, if the removal was successful, or null.
	 */
	public T pop() {
		checkintegrity();
		T result = removeEntry(numberOfEntries - 1);
		return result;
	} // end remove

	/**
	 * Removes one occurrence of a given entry from this Stack.
	 * 
	 * @param anEntry The entry to be removed.
	 * @return True if the removal was successful, or false if not.
	 */
	public boolean remove(T anEntry) {
		checkintegrity();
		int index = getIndexOf(anEntry);
		T result = removeEntry(index);
		return anEntry.equals(result);
	} // end remove

	// Locates a given entry within the array Stack.
	// Returns the index of the entry, if located,
	// or -1 otherwise.
	// Precondition: checkintegrity has been called.
	public int getIndexOf(T anEntry) {
		int where = -1;
		boolean found = false;
		int index = 0;

		while (!found && (index < numberOfEntries)) {
			if (anEntry.equals(stack[index])) {
				found = true;
				where = index;
			} // end if
			index++;
		} // end while

		// Assertion: If where > -1, anEntry is in the array bag, and it
		// equals bag[where]; otherwise, anEntry is not in the array.

		return where;
	} // end getIndexOf

	// Removes and returns the entry at a given index within the array.
	// If no such entry exists, returns null.
	// Precondition: 0 <= givenIndex < numberOfEntries.
	// Precondition: checkintegrity has been called.
	private T removeEntry(int givenIndex) {
		T result = null;

		if (!isEmpty() && (givenIndex >= 0)) {
			result = stack[givenIndex]; // Entry to remove
			int lastIndex = numberOfEntries - 1;
			stack[givenIndex] = stack[lastIndex]; // Replace entry to remove with last entry
			stack[lastIndex] = null; // Remove reference to last entry
			numberOfEntries--;
		} // end if

		return result;
	} // end removeEntry

	// Returns true if the array bag is full, or false if not.
	private boolean isArrayFull() {
		return numberOfEntries >= stack.length;
	} // end isArrayFull

	// Doubles the size of the array bag.
	// Precondition: checkInitialization has been called.
	private void doubleCapacity() {
		int newLength = 2 * stack.length;
		checkCapacity(newLength);
		stack = Arrays.copyOf(stack, newLength);
	} // end doubleCapacity

	// Throws an exception if the client requests a capacity that is too large.
	private void checkCapacity(int capacity) {
		if (capacity > MAX_CAPACITY)
			throw new IllegalStateException(
					"Attempt to create a bag whose capacity exceeds " + "allowed maximum of " + MAX_CAPACITY);
	} // end checkCapacity

	// Throws an exception if receiving object is not initialized.
	private void checkintegrity() {

		if (!integrityOK)
			throw new SecurityException("ArrayBag object is corrupt.");
	} // end checkintegrity

	@Override
	public T peek() {

		return stack[numberOfEntries - 1];
	}

	@Override
	public void clear(LinkedStack<T> userStack) {
		// TODO Auto-generated method stub

	}
	
	 static int evaluatePostfix(String exp)
	    {
	        //initialize stack
	        ResizableArrayStack<Integer> stack=new ResizableArrayStack<>();
	         
	        // Scan one by one
	        for(int i=0;i<exp.length();i++)
	        {
	            char c=exp.charAt(i);
	             
	            // If is an operand (number here),
	            // push to stack.
	            if(Character.isDigit(c))
	            stack.push(c - '0');
	             
	            //  If char is operator, pop two
	            //  from stack apply the operator
	            else
	            {
	                int val1 = stack.pop();
	                int val2 = stack.pop();
	                 
	                switch(c)
	                {
	                    case '+':
	                    stack.push(val2+val1);
	                    break;
	                     
	                    case '-':
	                    stack.push(val2- val1);
	                    break;
	                     
	                    case '/':
	                    stack.push(val2/val1);
	                    break;
	                     
	                    case '*':
	                    stack.push(val2*val1);
	                    break;
	              }
	            }
	        }
	        return stack.pop();   
	    }
	

	// @SuppressWarnings({ "hiding", "unchecked" })
}
