import java.util.*;
import java.io.*;
public class salesman {
     public static void main(String[] args) {

          long start = System.currentTimeMillis();
          //file reading
          ArrayList<String> cities = new ArrayList();
          ArrayList<ArrayList<Integer>> adj = new ArrayList();
          Scanner s = new Scanner(System.in);
          while (s.hasNext()) {
               String line = s.nextLine();
               Scanner l = new Scanner(line);
               String c = l.next();
               if (cities.indexOf(c)==-1) {
                    cities.add(c);
                    //make a new row
                    ArrayList<Integer> temp = new ArrayList(cities.size());
                    for (int i = 0; i < cities.size(); i++) temp.add(0);
                    //add a column to each existing row
                    for (int i = 0; i < adj.size()-1; i++) {
                         adj.get(i).add(0);
                    }
                    adj.add(temp);
               }
               l.next();
               String d = l.next();
               if (cities.indexOf(d)==-1) {
                    cities.add(d);
                    //make a new row
                    ArrayList<Integer> temp = new ArrayList(cities.size());
                    for (int i = 0; i < cities.size(); i++) temp.add(0);
                    //add a column to each existing row
                    for (int i = 0; i < adj.size(); i++) {
                         adj.get(i).add(0);
                    }
                    adj.add(temp);
               }
               l.next();
               int dist = l.nextInt();
               //set the distances
               adj.get(cities.indexOf(c)).set(cities.indexOf(d), dist);
               adj.get(cities.indexOf(d)).set(cities.indexOf(c), dist);

          }

          //calculate stuff
          // assign each permutation a number and loop through those numbers
          int n = cities.size();
          int fact = 1;
          //calculate n-1 factorial
          for (int i = 1; i < n; i++) {
               fact *= i;
          }
          int min = 10000;
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
               // calculate the sum and the maximum edge
               int currentsum = 0;
               int cmax = 0;
               for (int i = 0; i < n - 1; i++) {
                    int dist = adj.get(path.get(i)).get(path.get(i+1));
                    currentsum += dist;
                    cmax = Math.max(dist, cmax);
               }
               int dist = adj.get(path.get(0)).get(path.get(n-1));
               currentsum += dist;
               cmax = Math.max(dist, cmax);
               // the best path within that loop will be the total loop sum minus the longest edge
               min = Math.min(currentsum-cmax, min);
          }
          System.out.println(min);
          double time = (System.currentTimeMillis()-start)/1000.0;
		System.out.println(time);
     }

}
