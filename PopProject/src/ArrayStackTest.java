
public class ArrayStackTest {
	public static void main(String[]args) {
		String exp = "231*+9-";
		ResizableArrayStack<String> stack = new ResizableArrayStack<String>();
		System.out.println("postfix eval: " + stack.evaluatePostfix(exp));
	}
}
