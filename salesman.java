import java.util.*;
import java.io.*;
public class sales {
     public static void main(String[] args) {

          //file reading
          ArrayList<String> cities = new ArrayList();
          ArrayList<ArrayList<Integer>> adj = new ArrayList();
          Scanner s = new Scanner(System.in);
          while (s.hasNext()) {
               String line = s.nextLine();
               Scanner l = new Scanner(line);
               String c = l.next();

               if c isn't in cities:
                    add to cities
                    for each existing row {
                         add a column
                    }
                    make a new row:
                    ArrayList<Integer> temp = new ArrayList(cities.size());
                    for each city: temp.add(0);
                    add the row

               l.next();
               String d = l.next();
               if d isn't in cities {
                    add to cities
                    for each existing row {
                         add a column
                    }
                    make a new row:
                    ArrayList<Integer> temp = new ArrayList(cities.size());
                    for each city: temp.add(0);
                    add the row
               }
               l.next();
               int dist = l.nextInt();
               update adj[c][d], and adj[d][c]

          }

          //calculate stuff
          // assign each permutation a number and loop through those number
          int fact = 1;
          for (int i = 1; i <= cities.size(); i++) {
               fact *= i;
          }
          int min = 10000;
          for (int perm = 0; perm < fact; perm++) {
               int p = perm; //copies the number so future operations don't affect iteration
               // generate the path
               ArrayList<Integer> remain = new ArrayList();
               for (int i = 0; i < cities.size(); i++) {
                    remain.add(i);
               }
               ArrayList<Integer> path = new ArrayList();
               for (int i = 0; i < cities.size(); i++) {
                    path.add(remain[p % remain.size()]);
                    remain.remove(p%remain.size());
                    p /= (remain.size()+1);
               }
               // calculate the sum
               int currentsum = 0;
               for (int i = 0; i < path.size() - 1; i++) {
                    currentsum += adj[path[i]][path[i+1]];
               }
               if (currentsum<min) {
                    min=currentsum;
                    bestpath=path;
               }
          }
          Print the result
     }

}
