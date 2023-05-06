package mypkg;

public class Outer {
	
	/* abstract/final public/private/protected extends/implements */
	class Inner {
		// static fields and methods are not allowed, except static final fields.
		static final double PI = 3.142;
		int x = 10;
	}
	
	public static void main(String... args){
		System.out.println(new Outer().new Inner().x);
	}
}