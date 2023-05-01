package pkg;

public class Runner {
	public static void main(String... args){
		switchStatements();
	}
	
	public static void switchStatements(){
		/* 
			List of data  types supported by switch statements:
			int, byte, short, char, String, enum, var (if the type resolves to a supported type) 		
			Supreme Court VIBES
			
			Switch case values can only be literals (also includes expressions that can be resolved at compile time),
			enum constants, or final constant variables.
		*/
		
		int variable = 10;
		
		/* Without any break statement even, this will compile. */
		switch(variable){
			case 2:
				print(2);
		}
	}
	
	public static void print(Object o) {
		System.out.println(o);
	}
}