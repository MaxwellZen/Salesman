import java.util.*;
import java.io.*;
public class salesman {
     public static void main(String[] args) {

          long start = System.currentTimeMillis();
          //file reading

          ArrayList<String> cities = new ArrayList(); // list of all cities we get from the input file
          int adj[][] = new int[10][10]; // this 2-D array will contain the distances between adjacent
                                         // cities. It has as many columns and rows as the total
                                         // number of cities. We've been told that there's a maximum of
                                         // ten cities, which is why we have declared the array as 10x10
          Scanner s = new Scanner(System.in);
          while (s.hasNext()) {
               String line = s.nextLine(); // would look like "Faerun to Norrath = 129"
               Scanner l = new Scanner(line);
               String originCity = l.next(); // Read first city in the line
               //if the city isn't there, add it
               if (cities.indexOf(originCity)==-1) {
                    cities.add(originCity);
               }
               l.next(); // Skip the word "to"
               String destinationCity = l.next(); // Read second city in the line
               //if the city isn't there, add it
               if (cities.indexOf(destinationCity)==-1) {
                    cities.add(destinationCity);
               }
               l.next(); // Skip "="
               int dist = l.nextInt(); // Read distance
               //set the distances in adj
               adj[cities.indexOf(originCity)][cities.indexOf(destinationCity)]=dist;
               adj[cities.indexOf(destinationCity)][cities.indexOf(originCity)]=dist;

          }

          //calculate stuff
          // assign each permutation a number and loop through those numbers
          int n = cities.size();
          int fact = 1;
          //calculate n-1 factorial
          for (int i = 2; i < n; i++) {
               fact *= i;
          }
          int min = Integer.MAX_VALUE;
          ArrayList<Integer> remain = new ArrayList(n);
          for (int pp = 0; pp < fact; pp++) {
               int p = pp;

               // add the remaining cities to remain
               for (int i = 1; i < n; i++) {
                    remain.add(i);
               }
               ArrayList<Integer> path = new ArrayList();
               //the path starts at city 0 (we're looking at loops not paths for now)
               path.add(0);
               for (int i = n-1; i > 0; i--) {
                    //add another city to the path and remove that city from remain
                    path.add(remain.remove(p % i));
                    p /= i;
               }
               //NOTES ON WHY THE ABOVE LOOP WORKS:
                    /* So first it's important to note that to generate a path of size n, it is sufficient to generate
                     a sequence of numbers where the first number is from 0 to n-1, second number from 0 to n-2,
                     etc until the last number is 0. Reason why being each number will determine which of the
                     remaining cities will be next in the permutation. The above code does that by taking
                     p mod i, where i decreases from n-1 to 1 (generating a path n-1 cities long). The order in which
                     the permutations appear is irrelevant, all that matters is that each value of p gives a unique
                     path, which can be shown since each step taken is reversible. */
               // calculate the sum and the maximum edge
               int currentsum = 0;
               int cmax = 0;
               for (int i = 0; i < n; i++) {
                    int dist = adj[path.get(i)][path.get((i+1)%n)];
                    currentsum += dist;
                    cmax = Math.max(dist, cmax);
               }
               // the best path within that loop will be the total loop sum minus the longest edge
               min = Math.min(currentsum-cmax, min);
          }
          System.out.println(min);
          double time = (System.currentTimeMillis()-start)/1000.0;
		System.out.println(time);
     }

}
