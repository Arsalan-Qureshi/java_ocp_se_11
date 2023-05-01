package pkg;

public class Polymorphism {
	public static void main(String... args){
		polymorphism();
	}
	
	public static void polymorphism() {
		DarthVader vader = new LukeSkywalker();
		DarkSide darkSide = new DarthVader();
		DarkSide darkSideEffect = new LukeSkywalker();
		
		// java.lang.ClassCastException: class pkg.DarthVader cannot be cast to class pkg.LukeSkywalker
		// DarthVader class needs to be an instance of LukeSkywalker in order to run successfully. This will compile of course.
		// Unrelated classes are not allowed to compile with explicit casting.
		// LukeSkywalker luke = (LukeSkywalker) new DarthVader();
		
		vader.useForce(); //Use the force Luke...
		darkSide.useForce(); //Used force...
		darkSideEffect.useForce(); //Use the force Luke...
	}
	
	public static void print(Object o){ System.out.println(o); }
}

class DarthVader implements DarkSide {
	public void useForce(){ System.out.println("Used force..."); }
}

class LukeSkywalker extends DarthVader {
	public void useForce(){ System.out.println("Use the force Luke..."); }
}

interface DarkSide {
	void useForce();
}