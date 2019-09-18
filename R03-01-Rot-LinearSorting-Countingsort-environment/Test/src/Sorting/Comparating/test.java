package Sorting.Comparating;

import Sorting.Code;
import Sorting.Linearing.Counting;

import java.util.Arrays;

public class test
{
   public static void main(String[] args)
   {
      Code implementation = new Counting();

      runTests(implementation);
   }

   private static void runTests(Code implementation)
   {
      int[] test1 = {3,6,4,2,7,10};


      implementation.sort(test1, 0, test1.length-1);
      System.out.println(Arrays.toString(test1));
   }
}
