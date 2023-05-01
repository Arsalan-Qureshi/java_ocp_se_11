package mypkg;

public enum Season {
	WINTER{
		int temp(){return -1;}
	};
	
	/* Not allowed as all enum constructors are implicitly private.
	public Season(){
		
	}*/
	
	// Every enum is required to implement this abstract method. Just use a default implementation to make it easier.
	abstract int temp();
	
	public static void main(String... args){
		System.out.println(Season.valueOf("WINTER")); //WINTER
		System.out.println(Season.WINTER); //WINTER
		
		for(var s : Season.values()){
			System.out.print(s + " ");
		}
		System.out.println();
		
	}
}