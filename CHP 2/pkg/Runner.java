package pkg;

public class Runner {
	public static void main(String... args){
		int a = 0b11; //3 (11 is 3 for binary)
		int b = 0xA; //10 (A is 10 for hexadecimal)
		double c = 0b111; //7.0
		
		print(a);
		print(b);
		print(c);
	}
	
	public static void print(Object o) {
		System.out.println(o);
	}
}