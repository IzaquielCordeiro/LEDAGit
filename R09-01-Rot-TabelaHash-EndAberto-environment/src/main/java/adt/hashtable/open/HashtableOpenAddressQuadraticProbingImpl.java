package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T>
{

   public HashtableOpenAddressQuadraticProbingImpl(int size, HashFunctionClosedAddressMethod method, int c1, int c2)
   {
      super(size);
      hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
      this.initiateInternalTable(size);
   }

   @Override
   public void insert(T element)
   {
      if (isFull()) throw new HashtableOverflowException();

      if (element != null)
      {
         int probe = 0;

         while (probe < capacity())
         {
            int hash = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, probe);
            if (table[hash] == null || table[hash].equals(deletedElement))
            {
               table[hash] = element;
               elements++;
               break;
            }
            else
            {
               probe++;
               COLLISIONS++;
            }
         }

      }
   }

   @Override
   public void remove(T element)
   {
      if (element != null)
      {
         int probe = 0;

         while (probe < capacity())
         {
            int hash = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, probe);

            if (table[hash] == null) break;
            if (table[hash].equals(element))
            {
               table[hash] = deletedElement;
               elements--;
               break;
            }
            else
            {
               probe++;
            }
         }

      }
   }

   @Override
   public T search(T element)
   {
      if (element != null && !isEmpty())
      {
         if (indexOf(element) != -1) return element;
      }
      return null;
   }

   @Override
   public int indexOf(T element)
   {
      if (element != null && !isEmpty())
      {
         int probe = 0;

         while (probe < capacity())
         {
            int hash = ((HashFunctionQuadraticProbing<T>) hashFunction).hash(element, probe);
            if (table[hash] == null) break;

            if (table[hash].equals(element)) return hash;

            else probe++;
         }
      }
      return -1;
   }
}