package mypkg;

public class PersonInt implements Person {
	
	/* Default method can be overridden here. */
	public void greet(){
		System.out.println("Hello! It's me!");
	}
	
	public static void main(String... args){
		new PersonInt().greet();
		// Can be called from class reference.
		Person.eat();
	}
}

interface Person {
	/* Default Interface Method. */
	default void greet(){
		System.out.println("Hello!");
	}
	
	/* Static interface Method. */
	static void eat(){
		breathe();
		System.out.println("Eating!");
		breathe();
	}
	
	/*Private Static Interface Method - 
	Reduces code duplication for static methods. */
	private static void breathe(){
		System.out.println("Breathe...!");
	}
}