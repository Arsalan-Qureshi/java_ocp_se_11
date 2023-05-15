package pkg;

import java.util.Collections;
import java.util.*;

public class Sorting {
    public static void main(String... args) {
        var soldiers = new ArrayList<Soldier>();
        soldiers.add(new Sorting().new Soldier(168, 73));
        soldiers.add(new Sorting().new Soldier(172, 68));
        Collections.sort(soldiers);
        System.out.println(soldiers);
		
		/*
		Comparator<Soldier> byWeight = new Comparator<Soldier>(){
			public int compare(Soldier s1, Soldier s2){
				return s1.weight - s2.weight;
			}
		}; 
		*/

        // Sorting is done in ascending order by default by the method reference.
        Comparator<Soldier> byWeight = Comparator.comparing(Soldier::getWeight).reversed();

        // Comparator.comparing{Double|Int|Long}(Soldier::byWeight).thenComparing{Double|Int|Long}(function) In case the first comparator returns 0.
        // Comparator.naturalOrder(); // Sort using Comparable implementation. reverseOrder() does the opposite.

        Collections.sort(soldiers, byWeight);
        System.out.println(soldiers);

        /* TreetSet Implementation
        Set<Recruit> recruits = new TreeSet<>();
		// This operation will not be allowed as Recruit does not implement comparable.
        recruits.add(new Recruit()); */
		
		Set<Recruit> recruits = new TreeSet<>((r1, r2) -> r1.pid - r2.pid);
		// This is allowed once we specify a custom comparator.
        recruits.add(new Recruit());		
    }
	
	static class Recruit {
		int pid;
    }

    class Soldier implements Comparable<Soldier> {
        private int height;
        private int weight;

        public Soldier(int height, int weight) {
            this.height = height;
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }

        public String toString() {
            return String.valueOf(height).concat(" ").concat(String.valueOf(weight));
        }

        public int compareTo(Soldier s) {
            /* height - s.height sorts in ascending order. */
            return Integer.compare(s.height, height);
        }
    }
}