package mypkg;

public class Anonymous {
	
	abstract class Car{
		abstract int CC();
	}
	
	public static void main(String... args){
		int local = 1000;
		
		Car car = new Anonymous().new Car(){
			// Static methods not allowed.
			final static int tyres = 4;
			int CC(){
				// Can be used as local if effectively final.
				return local;
			}
		};
		
		System.out.println(car.CC()); //1000
	}
}