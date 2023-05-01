package pkg;

public class Abs extends Bird {	
	public String getName(){ return "HO-HO"; }
	
	public static void main(String... args){
		var b = new Abs();
		/* Bird is abstract; cannot be instantiated
		var b2 = new Bird(); */
		System.out.println(b.getName() + " - " + b.numberOfTalons);
	}
}

/* Class that cannot be intialized and may contain abstract methods. 
	- A static method cannot be overridden but only hidden, which means it also can not be marked as abstract.
	- Top-level abstract classes can't be marked protected or private.
	- No abstract class can be marked final.
*/
abstract class Bird {
	int numberOfTalons;
	
	// Abstract class can contain a constructor that is used for initialization
	// but cannot be called directly.
	public Bird(){
		numberOfTalons = 3;
	}	
	
	public abstract String getName();
}

abstract class Pony {
	public abstract String getName();
}

/* Multiple inheritance not allowed for abstract classes. */
/* abstract class Horse extends Bird, Pony {
	public abstract String getName();
} */