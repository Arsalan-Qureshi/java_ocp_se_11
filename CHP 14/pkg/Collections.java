package pkg;

import java.util.*;

public class Collections {
	public static void main(String... args){
		// Might not have been called from another package.
		// new Collections().mapMethods();
		new Collections().sortedMap();
	}
	
	private void listCreation(){
		// Elements can only be replaced, not added or deleted.
		Arrays.asList("laptop", "mouse", "keyboard").forEach(System.out::println);
		// No modification operation allowed.
		List.of("fire", "earth", "water", "air").forEach(System.out::println);
		// No modification operation allowed.
		List.copyOf(List.of("fire", "earth", "water", "air")).forEach(System.out::println);
	}
	
	private void listMethods(){
		// List Methods
		List<Integer> list = Arrays.asList(10, 20, 30);
		list.replaceAll(x -> x * 2);
		list.forEach(System.out::println);
	}
	
	private void setMethods(){
		/* Throws java.lang.IllegalArgumentException: with a duplicate element.
		Set<Character> letters = Set.of('a', 'b', 'b');
		Set<Character> copy = Set.copyOf(letters); */
		
		Set<Character> letters = new HashSet<>();
		letters.add('a'); //true
		letters.add('b'); //true
		letters.add('b'); //returns false
		// Printing will be done in random order.
		letters.forEach(System.out::println);
		
		Set<Integer> tree = new TreeSet<>();
		tree.add(100);
		tree.add(44);
		tree.add(21);
		tree.add(2);
		// Printing is done in naturally sorted order.
		tree.forEach(System.out::println);
	}
	
	private void queueMethods(){
		Queue<Integer> queue = new LinkedList<>();
		// queue.add("Ferrari"); Will throw error.
		System.out.println(queue.add(20)); //true
		System.out.println(queue.element()); //20 Throws exception if queue is empty.
		System.out.println(queue.offer(30)); //true
		System.out.println(queue.remove()); //20 Throws exception if queue is empty.
		System.out.println(queue.poll()); //30 Returns null for empty queue.
		System.out.println(queue.poll()); //null
		System.out.println(queue.peek()); //Next element or null for empty queue.
	}
	
	private void mapMethods(){
		Map.of("1", "Normal", "2", "Overdue");
		// has a copyOf as well.
		// The below is just syntactically sexier!
		// Create an immutable map.
		Map<String, String> mein = Map.ofEntries(
			Map.entry("1", "Normal"),
			Map.entry("2", "Overdue")
		);
		
		Map<String, String> map = new HashMap<>();
		map.put("1", "Normal");
		map.put("2", "Overdue");
		print(map.containsKey("1"));
		print(map.containsValue("Normal"));
		// map.entrySet().forEach(System.out::println); 
		/* Return a set of key value pairs.
		1=Normal
		2=Overdue */
		map.forEach((k,v) -> print(k + ":" + v));
		print(map.get("1")); // Normal
		print(map.get("3")); // Null
		print(map.getOrDefault("3", "0")); // 0
		print(map.isEmpty()); // false
		map.keySet().forEach(Collections::print); // 1 2
		print(map.putIfAbsent("1", "Normal")); // Normal
		print(map.putIfAbsent("3", "Collection")); // Add value and return null.
		print(map.remove("3")); // Collection
		print(map.replace("3", "Collection")); // null
		print(map.replace("1", "Collection")); // Normal
		map.forEach((k,v) -> print(k + ":" + v)); // 1:Collection 2:Overdue
		map.replaceAll((k, v) -> v.concat("."));
		map.forEach((k,v) -> print(k + ":" + v)); // 1:Collection. 2:Overdue.
		print(map.size()); //2
		map.values().forEach(Collections::print); // Collection. Overdue.
		map.merge("3", "Normal", (v1, v2) -> v1.length() > v2.length() ? v1 : v2); // Add 3 with Normal.
		map.forEach((k,v) -> print(k + ":" + v)); // 1:Collection. 2:Overdue. 3:Normal
		map.merge("3", "Normal", (v1, v2) -> null); // Remove 3.
		map.forEach((k,v) -> print(k + ":" + v)); // 1:Collection. 2:Overdue.
		map.merge("1", "Collections", (v1, v2) -> v1.length() > v2.length() ? v1 : v2); // Replace 1 with longer value.
		map.forEach((k,v) -> print(k + ":" + v)); // 1:Collections 2:Overdue.
		
		// mein.clear(); throws java.lang.UnsupportedOperationException for an immutable map.
		map.clear();
	}
	
	private static void print(Object message){
			System.out.println(message);
	}
	
	private static void print(boolean message){
			System.out.println(message);
	}

	
	private void commonMethods(){
		// Common Collection Methods
		List<String> fruits = new ArrayList<>();
		System.out.println(fruits.add("banana"));
		System.out.println(fruits.remove("apples")); //false
		// System.out.println(fruits.remove(1)); //Will throw an Index exception.
		System.out.println(fruits.isEmpty()); //false
		System.out.println(fruits.size()); //1
		System.out.println(fruits.contains("banana")); //true
		System.out.println(fruits.removeIf(s -> s.startsWith("b"))); //true
		System.out.println(fruits.size()); //0
		
		fruits.add("peach");
		fruits.add("guava");
		
		fruits.forEach(System.out::println);
		
		fruits.clear();
	}

	/*
	 * Keys are sorted. TreeMap implements this interface.
	*/
	private void sortedMap(){
		SortedMap<String, Integer> sortedMap = new TreeMap<>();
 
		sortedMap.put("z", 6);
		sortedMap.put("y", 7);
        sortedMap.put("x", 8);
 
        print(sortedMap.get("x")); // 8
        print(sortedMap.remove("y")); // 7

        for (String key : sortedMap.keySet()) {
            System.out.println("Key: " + key + ", Value: " + sortedMap.get(key));
        }
		/* Key: x, Value: 8
		Key: z, Value: 6 */
	}
}