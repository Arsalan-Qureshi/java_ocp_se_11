package pkg;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Runner {
	public static void main(String... args) {
		// convenienceMethods();
		
		// optional();
		
		// streams();
		
		// primitiveStreams();
		
		collectors();
	}
	
	public static void convenienceMethods() {
		Predicate<String> egg = s -> s.contains("egg");
		Predicate<String> brown = s -> s.contains("brown");
		Predicate<String> brownEggs = egg.and(brown);
		Predicate<String> otherEggs = egg.and(brown.negate());
		Predicate<String> eggOrBrown = egg.or(brown);
		
		Consumer<String> c1 = x -> System.out.println(x);
		Consumer<String> c2 = x -> System.out.println(x);
		Consumer<String> c3 = c1.andThen(c2); // Function also has a andThen() method.
		c3.accept("Goku"); // Goku gets printed twice. The same argument is passed independently to both consumers.
		
		Function<Integer, Integer> before = x -> x + 1;
		Function<Integer, Integer> after = x -> x * 1;
		Function<Integer, Integer> combined = after.compose(before); // before executes first.
		System.out.println(combined.apply(3)); //8
	}
	
	// Introduced in Java 8.
	public static void optional(){
		print(Optional.empty());
		print(Optional.of(97).isPresent());
		print(Optional.of(97).get());
		print(Optional.ofNullable(97));
		print(Optional.ofNullable(null));
		Optional.ofNullable(98).ifPresent(System.out::println);
		print(Optional.empty().orElse(99)); // returns 99
		print(Optional.empty().orElse(Double.NaN)); // returns NaN
		print(Optional.empty().orElseGet(() -> "Reginald Pooftah!"));
		// Optional.empty().orElseThrow(); Throws NoSuchElementException
		Optional.empty().orElseThrow(() -> new RuntimeException("It was me all along!")); // Thorw your own exception!
	}
	
	public static void streams(){
		/* Finite Streams */
		Stream<String> empty = Stream.empty();
		Stream<Integer> singleElement = Stream.of(1);
		Stream<Integer> multipleElements = Stream.of(1, 2, 3);
		
		var list = List.of("a", "b", "c");
		Stream<String> fromList = list.stream();
		
		Stream<Double> randoms = Stream.generate(Math::random);
		Stream<Integer> oddNumbers = Stream.iterate(1, n -> n + 2);
		Stream<Integer> oddNumbersUnder10 = Stream.iterate(1,
											n-> n < 10,
											n -> n + 2);
		
		/* Common Terminal Operations */
		print(Stream.of(1, 2, 3).count());
		print(Stream.of(1, 2, 3).min((s1, s2) -> s1 - s2));
		print(Stream.empty().min((s1, s2) -> 0)); // Comparator is not called. Stream.empty is returned.
		
		print(Stream.of("bat", "cat", "croc").findAny()); // returns bat usually
		print(Stream.of("bat", "cat", "croc").findFirst()); // returns bat
		
		/* A predicate gets passed. */
		print(Stream.of(1, 2, 3).allMatch(x -> x % 2 == 0)); // false
		print(Stream.of(1, 2, 3).anyMatch(x -> x % 2 == 0)); // true
		print(Stream.of(1, 2, 3).noneMatch(x -> x % 2 == 0)); // false
		
		Stream.of("A", "quick", "brown", "fox").forEach(System.out::println);
		
		/* Streams do not implement the iterable interface. */
		/* When the identity is not specified in a reduce method an Optional is returned. */
		print(Stream.of("w", "o", "l", "f").reduce("", (s, c) -> s + c));
		
		/* The type of i is inferred from the initial value. s is of type String from  the stream.*/
		print(Stream.of("w","o","l","f").reduce(0, (i,s) -> i + s.length(), (a,b) -> a + b)); //4
		
		/* Collect */
		print(Stream.of("w","o","l","f").collect(TreeSet::new, TreeSet::add, TreeSet::addAll)); // [f, l, o, w] natural order is used.
		print(Stream.of("w","o","l","f").collect(Collectors.toCollection(TreeSet::new))); // [f, l, o, w] natural order is used.
		print(Stream.of("w","o","l","f").collect(Collectors.toSet())); //[f, w, l, o] We can get any implementation of set i.e. HashSet or TreeSet.
		
		/* Common intermediate operations. */
		Stream.of("monkey", "gorilla", "chimpanzee").filter(x -> x.startsWith("m")).forEach(System.out::println);
		Stream.of(2, 3, 3).distinct().forEach(System.out::println); // Remove duplicates.
		Stream.iterate(1, n -> n + 1).skip(5).limit(2).forEach(System.out::println); // 6 7
		Stream.of("monkey", "gorilla", "chimpanzee").map(String::length).forEach(System.out::println); //6 7 10
		
		var one = List.of();
		var two = List.of(1, 2, 4);
		var three = List.of(7, 8, 9);
		var total = Stream.of(one, two, three);
		
		total.flatMap(x -> x.stream()).forEach(System.out::print); //124789 Empty list also gets removed and elements are all made top-level in total.
		print("");
		Stream.of("a", "c", "b").sorted().forEach(System.out::println);  // Sorting done according to natural order.
		Stream.of("a", "c", "b").sorted(Comparator.reverseOrder()).forEach(System.out::println);  // c b a
		
		/* peek(Consumer<? super T> action) is useful for debugging. */
		Stream.of("brown bear", "black bear", "grizzly bear").filter(s -> s.startsWith("b")).peek(System.out::println).count(); // 1
	}
	
	public static void primitiveStreams(){
		/* IntStream: Used for primitive types int, short, byte, and char
		*  LongStream: Used for Long.
		*  DoubleStream: Used for float and double.
		*/
		var empty = DoubleStream.empty();
		var pi = DoubleStream.of(3.14);
		var varargs = DoubleStream.of(1.1, 1.2, 1.3);
		
		print(varargs.average());
		print(DoubleStream.of(1.1, 1.2, 1.3).max()); // OptionalDouble[1.3]
		print(DoubleStream.of(1.1, 1.2, 1.3).min()); // OptionalDouble[1.1] uses getAsDouble()
		IntStream.range(1, 5).forEach(System.out::println); //1 2 3 4
		LongStream.rangeClosed(1, 5).forEach(System.out::println); //1 2 3 4 5
		print(IntStream.range(1, 100).sum()); // 4950
		print(IntStream.of(1, 2, 3).boxed()); // Becomes Stream<Integer>
		
		/* Extract multiple data against the same stream. */
		var stats = IntStream.of(1, 2, 3).summaryStatistics();
		print(stats.getMin());
		print(stats.getMax());
		print(stats.getAverage());
		print(stats.getSum());
		print(stats.getCount());
		
		BooleanSupplier b1 = () -> 2 == 2;
		print(b1.getAsBoolean()); //true
	}
	
	public static void collectors(){
		/* Collectors available via static methods on the Collectors interface. */
		print(Stream.of("a", "b", "c").collect(Collectors.joining(", "))); // a, b, c
		print(Stream.of("a", "b", "c").collect(Collectors.averagingInt(String::length))); // 1.0
		print(Stream.of("lions", "tigers", "bears").collect(Collectors.toMap(s -> s, String::length))); // {lions=5, bears=5, tigers=6}
		// Final BinaryOperator argument is for duplicate values.
		print(Stream.of("lions", "tigers", "bears").collect(Collectors.toMap(String::length, s -> s,
																		(s1, s2) -> s1 + "," + s2))); // {5=lions,bears, 6=tigers}
		// Final argument is a supplier.
		print(Stream.of("lions", "tigers", "bears").collect(Collectors.toMap(String::length, s -> s,
																		(s1, s2) -> s1 + "," + s2,
																		TreeMap::new))); // {5=lions,bears, 6=tigers}
																		
		print(Stream.of("lions", "tigers", "bears").collect(Collectors.groupingBy(String::length))); // {5=[lions, bears], 6=[tigers]} Map<Integer, List<String>>
		print(Stream.of("lions", "tigers", "bears").collect(Collectors.groupingBy(String::length, Collectors.toSet()))); // {5=[lions, bears], 6=[tigers]} Map<Integer, Set<String>>
		print(Stream.of("lions", "tigers", "bears").collect(Collectors.groupingBy(String::length,
													TreeMap::new, Collectors.toSet()))); // {5=[lions, bears], 6=[tigers]} TreeMap<Integer, Set<String>>
		
		print(Stream.of("lions", "tigers", "bears").collect(Collectors.partitioningBy(s -> s.length() <= 5))); // {false=[tigers], true=[lions, bears]} Map<Boolean, List<String>>
		print(Stream.of("lions", "tigers", "bears").collect(Collectors.partitioningBy(s -> s.length() <= 5, Collectors.toSet()))); // {false=[tigers], true=[lions, bears]} Map<Boolean, Set<String>>
		
		print(Stream.of("lions", "tigers", "bears").collect(
			Collectors.groupingBy(
				String::length,
				Collectors.mapping(
					s -> s.charAt(0),
					Collectors.minBy((a, b) -> a - b)
				)
			)
		)); // {5=Optional[b], 6=Optional[t]}
	}
	
	public static void print(Object o){
		System.out.println(o);
	}
}