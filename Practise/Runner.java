/* Q54 */
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.security.*;
import java.nio.file.*;

public class Runner {

   static char ch;

   public static void main(String[] args){
      new Runner().t4q19();
   }

   private void character(){
      System.out.println(ch); // Prints empty character
   }

   private void t1q2(){
      Stream.of("1", "2", "3").sorted().collect(Collectors.toList()).stream().forEach(System.out::println);

      /*
       * Stream<T> sorted(Comparator<? super T> comparator);
       * books.stream().sorted(c1.thenComparing(Book::getTitle));
       * sorted is an intermediate operation, and requires a terminal operation.
       */       
   }

   // Restricting permission.
   private void t1q4(){
      // Possible actions are "read", "write", "execute", "delete", and "readlink".
      Permission perm = new java.io.FilePermission("path to file goes here", "read");
      PermissionCollection perms = perm.newPermissionCollection();
      perms.add(perm);
        
      AccessController.doPrivileged(new PrivilegedAction<Void>() {
          public Void run() {
              // Do something on the file here.
              return null;
          }},
          new AccessControlContext(
              new ProtectionDomain[] {
                  new ProtectionDomain(null, perms)
              }
          )
      );
   }

   /* Exception thrown from finally takes precedence.
    * Exception in thread "main" java.lang.RuntimeException
        at Runner.t1q5(Runner.java:55)
        at Runner.main(Runner.java:12)
    */
   private void t1q5(){
      try{
         throw new Exception();
      } catch(Exception e){
         throw new ArithmeticException();
      } finally {
         throw new RuntimeException(); 
      }
   }

   private void t1q19(){
      /* System.getProperties().entrySet().forEach(
         e -> {
            print(e.getKey() + "," + e.getValue());
         }
      ); */

      System.getProperties().keySet().forEach(
         k -> print(k)
      );
   }

   private void t1q21(){
      /*
      module foo.filter{
         requires api;
         provides api.Filter with foo.DoNothingFilter;
       }
       
       Assuming that api.Filter is an interface with just a single method List<String> filter(List<String> l)
       
      public class DoNothingFilter {
         public DoNothingFilter(int a){ }
         public static Filter provider(){
            return new Filter(){
               public List<String> filter(List<String> l) { return l; }
            };
         }
      }
      
      If a service provider has public static provider() method, then it is not required for the service provider
      to be a subtype of the service type. Only the provider method should return a subtype of the service type.
       */
   }

   private void t4q19(){
      Path p1 = Paths.get("c:\\main\\project\\Starter.java");   
      print(p1.getRoot().toString());
   }

   private void print(Object o){
      System.out.println(o);
   }
}

class A{
   private String s;
   @Override
   public boolean equals(Object a){ //override the equals methods
       return this.s != null && this.s.equals(((A)a).s);
   }
}