package pkg;

import java.io.*;

public class Runner extends Parent {
	public static void main(String... args){
		// defaultAccessability();
		
		/* try {
			print(new Runner().noOfEyes());
		} catch (IOException e) {
			e.printStackTrace();
		} */
		
		// hiddenMethod() in parent will be hidden by the override. Override rules are still in place for this.
		hiddenMethod();
	}
	
	public static void defaultAccessability(){
		DefaultClass dc = new DefaultClass();
		// We are able to access the following attributes because of their default access.
		dc.noOfApples = 10;
		dc.noOfPears = 11;
		/* This lines does not compile because noOfEdenApples is private in DefaultClass.
		dc.noOfEdenApples = 2; */
		dc.noOfEdenTrees = 1;
		print(dc);
	}
	
	// Compiles because of a covariant return type.
	// Nothing is covariant with void except void.
	public Integer noOfEyes() throws FileNotFoundException {
		return Integer.valueOf(4);
	}
	
	/* Does not compile as default access is more restrictive than public.
	Integer noOfEyes(){
		return Integer.valueOf(4);
	} */
	
	/* Newer or broader checked exception is not allowed, so this does not also compile.
	public Integer noOfEyes() throws IOException{
		return Integer.valueOf(4);
	} */
	
	//--- Hiding Static Methods
	
	/*
	hiddenMethod() in Runner cannot override hiddenMethod() in Parent
        public void hiddenMethod(){
                    ^
  overridden method is static
	public void hiddenMethod(){
		print("Sure about that?");
	} */
	
	public static void hiddenMethod(){
		print("Sure about that?");
	}

	public static void print(Object o){ System.out.println(o); }
}

class Parent {
	public Object noOfEyes() throws FileNotFoundException {
		return Integer.valueOf(2);
	}
	
	public static void hiddenMethod(){
		System.out.println("I am hidden!");
	}
}