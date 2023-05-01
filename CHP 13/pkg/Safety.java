package pkg;

import java.lang.annotation.*;

public class Safety {
	/* Indicates that a method does not perform any potential unsafe operations on its vararges parameter. 
	Can only be applied to constructors or methods that cannot be overridden (private, static, final). */
	@SafeVarargs
	public static void main(String... args){
		
	}
}
