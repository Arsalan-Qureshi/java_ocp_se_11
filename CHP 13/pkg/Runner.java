package pkg;

import java.lang.annotation.*;

// Exercises below uses a shorthand annotation for providing an array that contains a single element.
@Exercise(hoursPerDay = 3, exercises = "Strip Curl")
public class Runner {
	// Does not require the element name to be specified.
	@Cardio(2)
	int cardio;
	
	public static void main(String... args){
		/*for (Field f: Runner.class.getFields()) {
			Exercise exercise = f.getAnnotation(Exercise.class);
			if (exercise != null)
			   System.out.println(exercise.columnName());
		}*/
		System.out.println(new Runner().cardio);
	}
}

@Retention(RetentionPolicy.RUNTIME)
@interface Exercise {
	int hoursPerDay();
	String[] exercises();
}

/*
In order to be used without a name:
- Element named value() can be optional or required.
- Must not contain any other required elements.
- Annotation usage must not provide values for any other elements.
*/
@Target(ElementType.FIELD)
@interface Cardio {
	int value();
}

/*
 * The declaration below compiles. Only Test cannot be used without an element name now.
 */
@Target(ElementType.TYPE)
@interface Test {
  int value();
  String hours();
}