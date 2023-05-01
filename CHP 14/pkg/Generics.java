package pkg;

import java.util.*;

public class Generics {
	public static void main(String... args){
		Elephant e = new Elephant();
		Crate<Elephant> crate = new Crate<>();
		crate.packCrate(e);
		System.out.println(crate.emptyCrate() instanceof Elephant);
		
		String s = "String Object";
		
		Handler.prepare(s);
		Handler.ship(crate);
		/* The type can also be specified explicitly. */
		Handler.<Integer>ship(Integer.valueOf(100));
		
		wildcardBound();
	}
	
	
/*
* A wildcard generic type is an unknown generic type represented with a question mark.
*/
	public static void wildcardBound(){
		List<?> a1 = new ArrayList<String>();
		List<? extends Exception> a2 = new ArrayList<RuntimeException>();
		List<? super Exception> a3 = new ArrayList<Object>();
		
		// Upper Bound Wildcard
		// Any class that extends Number or Number itself can be used as a formal parameter type.
		// For upper bounds and unbounded wildcards a list becomes logically immutable.
		List<? extends Number> list = new ArrayList<Integer>();
		
		// Lower Bound Wildcard
		// Below code compiles. We tell Java that list will be a list of String Objects or a list of some objects that are Superclass of String.
		// Moreover list is also mutable now.
		addSound(new ArrayList<String>());
	}
	
	public static void addSound(List<? super String> list){
		list.add("quack");
	}
}

class Crate<T>{
	private T contents;
	
	public T emptyCrate(){
		return contents;
	}
	
	public void packCrate(T contents){
		this.contents = contents;
	}
}


class Elephant{
	void eat() { }
	void sleep() { }
}

/* Formal Type Parameters can also be declared on the method level via the <T> symbol.
* A method declared generic parameter type is independent of the class generics.
*/
class Handler {
	public static <T> void prepare(T t){
		System.out.println("Preparing " + t);
	}
	public static <T> Crate<T> ship(T t){
		System.out.println("Shipping " + t);
		return new Crate<T>();
	}
}

/* 
* We cannot call a constructor with generics, or create an array of a generic type or call instanceof on it.
* We also cannot use a primitive type as a generic type parameter, or create static variables as a generic type parameter.
*/