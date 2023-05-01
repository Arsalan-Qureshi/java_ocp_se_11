package pkg;

import java.util.stream.*;

class ParallelStream {
	public static void main(String... args){
		 // toConcurrentMap(); // {5=lions,bears, 6=tigers}
		
		// groupingByConcurrent(); // {5=[lions, bears], 6=[tigers]}
	}
	
	public static void toConcurrentMap(){
		// Creates a ConcurrentHashMap
		print(Stream.of("lions", "tigers", "bears").parallel()
			.collect(
				Collectors.toConcurrentMap(
					String::length,
					v -> v,
					(s1, s2) -> s1 + "," + s2)
				)
			);
	}
	
	public static void groupingByConcurrent(){
		// Creates a ConcurrentMap<Integer, List<String>>
		print(Stream.of("lions", "tigers", "bears").parallel()
			.collect(
				Collectors.groupingByConcurrent(
					String::length
				)
			));
	}
	
	public static void print(Object o){
		System.out.println(o);
	}
}