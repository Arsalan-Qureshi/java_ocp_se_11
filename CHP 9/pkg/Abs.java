public class Abs extends Bird {
	public String getName(){ return "HO-HO"; }
	
	public static void main(String... args){
		System.out.println(new Abs().getName());
	}
}

/* Class that cannot be intialized and may contain abstract methods. */
abstract class Bird {
	public abstract String getName();
}

abstract class Pony {
	public abstract String getName();
}

/* Multiple inheritance not allowed for abstract classes. */
/* abstract class Horse extends Bird, Pony {
	public abstract String getName();
} */