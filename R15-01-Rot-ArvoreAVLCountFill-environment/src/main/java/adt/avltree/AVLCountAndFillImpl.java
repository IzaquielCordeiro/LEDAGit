package adt.avltree;

import adt.bst.BSTNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AVLCountAndFillImpl<T extends Comparable<T>> extends AVLTreeImpl<T> implements AVLCountAndFill<T>
{

   private int LLcounter;
   private int LRcounter;
   private int RRcounter;
   private int RLcounter;

   public AVLCountAndFillImpl()
   {

   }

   @Override
   protected void rebalance(BSTNode<T> node)
   {
      if (node == null || node.isEmpty()) return;
      int bf = calculateBalance(node);
      if (bf < -1)
      {
         if (calculateBalance((BSTNode<T>) node.getLeft()) >= 0)
         {
            leftRotation((BSTNode<T>) node.getLeft());
            LRcounter++;
         }

         else LLcounter++;

         rightRotation(node);

      }
      else if (bf > 1)
      {
         if (calculateBalance((BSTNode<T>) node.getRight()) <= 0)
         {
            RLcounter++;
            rightRotation((BSTNode<T>) node.getRight());
         }

         else RRcounter++;

         leftRotation(node);
      }
   }

   @Override
   public int LLcount()
   {
      return LLcounter;
   }

   @Override
   public int LRcount()
   {
      return LRcounter;
   }

   @Override
   public int RRcount()
   {
      return RRcounter;
   }

   @Override
   public int RLcount()
   {
      return RLcounter;
   }

   @Override
   public void fillWithoutRebalance(T[] array)
   {
      List<T[]> arraysList = new ArrayList<>();
      Arrays.sort(array);
      arraysList.add(array);

      int cont = 0;

      while (cont < arraysList.size())
      {
         T[] arrayAux = arraysList.get(cont);

         int middle = arrayAux.length / 2;

         T[] aux1 = Arrays.copyOfRange(arrayAux, 0, middle);
         T[] aux2 = Arrays.copyOfRange(arrayAux, middle + 1, arrayAux.length);

         if (arrayAux.length > 1)
         {
            arraysList.add(aux1);
            arraysList.add(aux2);
         }

         insert(arrayAux[middle]);
         cont++;
      }

   }

}
