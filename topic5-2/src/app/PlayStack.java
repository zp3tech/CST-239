package app;

import java.util.Iterator;
import java.util.Stack;

/**
 * An example class to show usage of Stack (Last In First Out).
 * 
 * @author pahlz
 *
 */
public class PlayStack {

	public static void main(String[] args) {
		Stack<String> stringStack = new Stack<String>();
		stringStack.push("Zach Pahl");
		stringStack.push("Mo Pahl");
		stringStack.push("Mai Pahl");
		stringStack.push("JoJo Pahl");

		Stack<Integer> integerStack = new Stack<Integer>();
		integerStack.push(1);
		integerStack.push(-1);
		integerStack.push(5);
		integerStack.push(10);

		System.out.println(integerStack);
		System.out.printf("Integer Stack Tests: size is %d and 2nd element is %d\n", integerStack.size(),
				integerStack.elementAt(1));

		Iterator<String> itr = stringStack.iterator();
		while (itr.hasNext()) {
			System.out.println(stringStack.pop());
		}
	}

}
