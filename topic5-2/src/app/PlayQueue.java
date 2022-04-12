package app;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * An example class to show Queue usage (First In First Out).
 * 
 * @author pahlz
 *
 */
public class PlayQueue {

	public static void main(String[] args) {
		Queue<String> stringQueue = new LinkedList<String>();
		stringQueue.offer("Zachary Pahl");
		stringQueue.add("Morgan Pahl");
		stringQueue.offer("Mai Ly Pahl");
		stringQueue.add("Jocelyn Pahl");

		Queue<Integer> integerQueue = new LinkedList<Integer>();
		integerQueue.add(1);
		integerQueue.offer(-1);
		integerQueue.add(5);
		integerQueue.offer(10);

		System.out.println(integerQueue);
		System.out.printf("Integer Queue Tests: size is %d and head element is %d\n", integerQueue.size(),
				integerQueue.peek());

		Iterator<String> itr = stringQueue.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}

	}

}
