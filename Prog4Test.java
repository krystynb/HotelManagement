/**
   Simple main program to test the basic functionality of the Prog4 class.
   @author J. Cordova
*/
public class Prog4Test
{
   public static void main (String args[])
   {
      Prog4 acm1 = new Prog4("hotels1.csv");
      System.out.println(acm1.actions("trans1.txt"));
   }       
}
