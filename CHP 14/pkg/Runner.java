package pkg;

public class Runner {
	public static void main(String... args){
		// Lambdas are basically functional interfaces i.e. interfaces with one abstract method.
		Behavior b1 = s -> System.out.println(s);
		Behavior b2 = System.out::println; //Method reference
		b1.speak("Hey there!");
		b2.speak("Hey yourself!");
		
		/* Primitive to Wrapper class conversion. */
		System.out.println(Integer.valueOf(10) instanceof Integer); //true
		System.out.println(Integer.valueOf(10) instanceof Object); //true
		System.out.println(Byte.valueOf((byte)10) instanceof Byte); // true Byte, Short, and Float require an explicit cast.
	}
}

@FunctionalInterface
interface Behavior {
	void speak(String sentence);
}