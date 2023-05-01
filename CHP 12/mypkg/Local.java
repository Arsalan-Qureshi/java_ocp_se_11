package mypkg;

public class Local {
	
	private int x = 11;
	
	public static void main(String... args){
		int z = 13;
		
		/* abstract/final extends/implements*/
		final class Inner{
			
			// Only static final fields allowed.
			// Local variables can be accessed if they are final or effectively final.
			
			int y = z;
		}
		
		System.out.println(new Inner().y);
	}
}