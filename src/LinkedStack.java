import java.util.EmptyStackException;

/**
 * A class of stacks whose entries are stored in a chain of nodes.
 * 
 * @author Frank M. Carrano and Timothy M. Henry
 * @version 5.0
 */
public final class LinkedStack<T> implements StackInterface<T> {

		
	//@SuppressWarnings("unchecked")
	public static void main(String[]args) {
		LinkedStack first = new LinkedStack();
		first.push("a");
		first.push(1);
		System.out.println("Peeking: " + first.peek());
		first.pop();
		System.out.println(first.isEmpty());
		//System.out.println(first.pop());
		//first.push("A");
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
	
} 
