import java.util.EmptyStackException;

/**
 * A class of stacks whose entries are stored in a chain of nodes.
 * 
 * @author Frank M. Carrano and Timothy M. Henry
 * @version 5.0
 */
public final class LinkedStack<T> implements StackInterface<T> {

		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[]args) {
		LinkedStack first = new LinkedStack();
		
		//adding values into stack
		first.push(1);
		first.push(2);
		first.push(3);
		first.push(4);
		
		//peeking at top value
		System.out.println("Top value is: " + first.peek());
		
		//clearing values
		System.out.println("Clearing values...");
		first.clear(first);
		
		//check if empty
		System.out.println("Is stack empty: " + first.isEmpty());
	}

	Node top;
	
	public class Node{
		 T data;
		 Node next;
	}
	
	public LinkedStack(){
		top = null;
	}
	
	public void push(T data) {
		Node extraNode = top;
		top = new Node();
		top.data = data;
		top.next = extraNode;
	}
	
	public T pop() {
		if(top == null) {
			System.out.println("Stack is empty");
		}
		T data = top.data;
		top = top.next;
		return data;
	}
	
	@SuppressWarnings("unchecked")
	public T peek() {
		if(top == null) {
			return null;
		}else
		return (T) top.data;
	}
	
	public boolean isEmpty() {
		if(top == null) {
			return true;
		}else
			return false;
	}
	
	public void clear(LinkedStack<T> userStack) {
		if(top != null) {
			userStack.pop();
			clear(userStack);
		}
	}

	public T convertToPostfix(T infix) {
		
		
		
		return null;
	}
} 
