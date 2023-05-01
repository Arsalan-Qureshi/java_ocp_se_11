package mypkg;

public class Anonymous {
	
	abstract class Car{
		abstract int CC();
	}
	
	public static void main(String... args){
		
		Car car = new Anonymous().new Car(){
			// Static methods not allowed.
			final static int tyres = 4;
			int CC(){
				return 1000;
			}
		};
		
		System.out.println(car.CC()); //1000
	}
}