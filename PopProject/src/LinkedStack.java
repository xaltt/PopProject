
/**
 * A class of stacks whose entries are stored in a chain of nodes.
 * 
 * @author Frank M. Carrano and Timothy M. Henry
 * @version 5.0
 */
public final class LinkedStack<T> implements StackInterface<T> {


	Node top;

	public class Node {
		T data;
		Node next;
	}

	public LinkedStack() {
		top = null;
	}

	public void push(T data) {
		Node extraNode = top;
		top = new Node();
		top.data = data;
		top.next = extraNode;
	}

	public T pop() {
		if (top == null) {
			System.out.println("Stack is empty");
		}
		T data = top.data;
		top = top.next;
		return data;
	}

	
	public T peek() {
		if (top == null) {
			return null;
		} else
			return (T) top.data;
	}

	public boolean isEmpty() {
		if (top == null) {
			return true;
		} else
			return false;
	}

	public void clear(LinkedStack<T> userStack) {
		if (top != null) {
			userStack.pop();
			clear(userStack);
		}
	}

	// This method looks for the precedence of each operator and returns
	// higher value for higher precedence.
	static int priority(char ch) {
		switch (ch) {
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 2;
		case '^':
			return 3;

		}
		return -1;
	}

	public String convertToPostfix(String infix) {

		// initializing empty String for result
		String result = new String("");

		// initializing empty stack
		LinkedStack<Character> stack = new LinkedStack<>();

		for (int i = 0; i < infix.length(); ++i) {
			char c = infix.charAt(i);
			//If is operand, add to output
			if (Character.isLetterOrDigit(c)) {
				result += c;

			//if is '(' then push
			}else if (c == '(') {
				stack.push(c);
			
			//if is ')' then pop and output stack until the '(' is found
			}else if (c == ')') {
				while (!stack.isEmpty() && stack.peek() != '(')
					result += stack.pop();

				stack.pop();
			} else {//if an operator is encountered, then...
				while (!stack.isEmpty() && priority(c) <= priority(stack.peek())) {

					result += stack.pop();
				}
				stack.push(c);
			}

		}

		// pop all operators from stack
		while (!stack.isEmpty()) {
			if (stack.peek() == '(')
				return "Invalid Expression";
			result += stack.pop();
		}
		return result;
	}
}