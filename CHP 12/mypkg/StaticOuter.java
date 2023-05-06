package mypkg;

public class StaticOuter {
	
	// abstract/final public/protected/private extends/implements
	// How is this even allowed? -_-
	// abstract static class Inner extends StaticOuter //compiles
	static class Inner{
		static final void greet(){}
		int x = 10;
	}
	
	public static void main(String... args){
		// No need to access via Outer class.
		System.out.println(new Inner().x);
	}
}