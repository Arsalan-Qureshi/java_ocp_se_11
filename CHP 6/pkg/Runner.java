package pkg;

import java.util.*;

public class Runner {
	public static void main(String... args){
		/* This is perfectly valid. -_-*/
		print(new ArrayList<>(
			Set.of("mickie", "minnie")
		));
	}
	
	public static void print(Object o) {
		System.out.println(o);
	}
}