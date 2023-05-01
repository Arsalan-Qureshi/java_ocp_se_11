package pkg;

import java.util.*;
import java.util.concurrent.*;

public class ConcurrentCollections {
	public static void main(String... args){
		// concurrentModification();
		
		concurrentArrays();
	}
	
	public static void concurrentModification(){
		/* Use the below line throws ConcurrentModificationException as food will not be updated during the second iteration in the
		for loop below.
		var food = new HashMap<Integer, String>(); */
		var food = new ConcurrentHashMap<Integer, String>();
		food.put(1, "apple");
		food.put(2, "pear");
		for(Integer i : food.keySet())
			food.remove(i);
	}
	
	public static void concurrentArrays() {
		/* The same goes for the CopyOnWriteArraySet */
		List<Integer> num = new CopyOnWriteArrayList<>(List.of(4, 3, 42));
		for(var n : num) {
			print(n);
			// Original iterator will still point to the original array.
			num.add(9);
		}
		
		/* Over here the updated array is reflected. */
		print(num.size()); // 6
		num.forEach(System.out::println); // 4, 3, 42, 9, 9, 9
	}
	
	public static void print(Object o){
		System.out.println(o);
	}
}