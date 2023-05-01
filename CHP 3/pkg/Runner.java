package pkg;

public class Runner {
	public static void main(String... args){
		operatorPrecedence();
	}
	
	public static void operatorPrecedence(){
		/* PPO MAS RELSTA*/
		/* Pakistan Post Office Must Assess Situation Relating Eagles Looting State Treasury Apartments*/
		
		/* Other Unary Operators -, !, ~, +, (type)*/
		print(+(-2)); //-2
		print(~2); //-3 00000010 becomes 11111101 Perform bitwise complement of a numerical value.
		
		/*
			Signed Left Shift << The left shift operator moves all bits by a given number of bits to the left.
			Signed Right Shift >> The right shift operator moves all bits by a given number of bits to the right.
			Unsigned Right Shift >>> It is the same as the signed right shift, but the vacant leftmost position is filled with 0 instead of the sign bit.
		*/
		print(2 << 2); // 8 - 0010 will become 1000 i.e. 8  
		print(8 >> 2); // 2 - 1000 will become 0010 i.e. 2
		print(0b10000001 >>> 1); // 64 - Becomes 01000000
	}
	
	public static void print(Object o) {
		System.out.println(o);
	}
}