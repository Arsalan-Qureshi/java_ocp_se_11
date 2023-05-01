package pkg;

import java.lang.annotation.*;

public class Zoo {	

	public static class Monkey {}
	
	/* Without @Repeatable annotation, an annotation can be applied only once. 
	These annotations were added in Java 8. */
	@Risk(danger="Silly")
	@Risk(danger="Aggressive", level=5)
	@Risk(danger="Violent", level=10)
	private Monkey monkey;
}

@Repeatable(Risks.class)
@interface Risk {
	String danger();
	int level() default 1;
}

@interface Risks {
	Risk[] value();
}
