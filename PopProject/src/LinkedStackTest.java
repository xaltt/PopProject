
public class LinkedStackTest {
	public static void main(String[] args) {
		String expression = "a*b/(c-a)+d*e";
		LinkedStack<String> linkedStack = new LinkedStack<String>();
		System.out.println(linkedStack.convertToPostfix(expression));
	}
}
