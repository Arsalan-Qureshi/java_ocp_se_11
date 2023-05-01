package mypkg;

public class StaticOuter {
	
	/* abstract/final public/protected/private extends/implements */
	static class Inner{
		static final void greet(){}
		int x = 10;
	}
	
	public static void main(String... args){
		System.out.println(new Inner().x);
	}
}