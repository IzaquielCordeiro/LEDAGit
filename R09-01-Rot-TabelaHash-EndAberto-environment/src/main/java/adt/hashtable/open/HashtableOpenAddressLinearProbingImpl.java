package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends AbstractHashtableOpenAddress<T>
{

   public HashtableOpenAddressLinearProbingImpl(int size, HashFunctionClosedAddressMethod method)
   {
      super(size);
      hashFunction = new HashFunctionLinearProbing<T>(size, method);
      this.initiateInternalTable(size);
   }

   @Override
   public void insert(T element)
   {
      if (isFull()) throw new HashtableOverflowException();
      if (element != null)
      {
         int probe = 0;

         while (probe < this.capacity())
         {
            int hash = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);

            if (this.table[hash] == null || this.table[hash].equals(deletedElement))
            {
               this.table[hash] = element;
               elements++;
               break;
            }
            else
            {
               probe++;
               this.COLLISIONS++;
            }
         }
      }

   }

   @Override
   public void remove(T element)
   {
      if (element != null && !this.isEmpty())
      {
         int probe = 0;

         while (probe < this.capacity())
         {
            int hash = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);

            if (this.table[hash] == null) break;

            if (this.table[hash].equals(element))
            {
               this.table[hash] = deletedElement;
               this.elements--;
               break;
            }

            else probe++;
         }
      }
   }

   @Override
   public T search(T element)
   {
      if (element != null || !isEmpty())
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
         while (probe < this.capacity())
         {
            int hash = ((HashFunctionLinearProbing<T>) hashFunction).hash(element, probe);
            if (this.table[hash] == null) break;

            if (this.table[hash].equals(element)) return hash;

            else probe++;
         }
      }
      return -1;
   }

}
