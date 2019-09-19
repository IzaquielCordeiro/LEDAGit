package adt.linkedList.set;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

public class SetLinkedListImpl<T> extends SingleLinkedListImpl<T> implements SetLinkedList<T>
{

   @Override
   public void removeDuplicates()
   {
      T[] aux = this.toArray();
      SingleLinkedListImpl<T> newSet = new SingleLinkedListImpl<T>();

      for(int i=0; i<aux.length; i++)
      {
         if(newSet.search(aux[i]) == null) newSet.insert(aux[i]);
      }

      this.head = newSet.getHead();
   }


   @Override
   public SetLinkedList<T> union(SetLinkedList<T> otherSet)
   {
      SetLinkedList<T> newSet = new SetLinkedListImpl<T>();

      for (T element : super.toArray()) newSet.insert(element);

      for (T element : otherSet.toArray()) newSet.insert(element);

      newSet.removeDuplicates();

      return newSet;

   }

   @Override
   public SetLinkedList<T> intersection(SetLinkedList<T> otherSet)
   {
      SetLinkedList<T> newSet = new SetLinkedListImpl<T>();

      for (T e1 : super.toArray())
      {
         for (T e2 : otherSet.toArray())
         {
            if (e1.equals(e2)) newSet.insert(e1);
         }
      }

      newSet.removeDuplicates();

      return newSet;

   }

   @Override
   public void concatenate(SetLinkedList<T> otherSet)
   {
      SingleLinkedListNode<T> auxHead = this.getHead();

      if (auxHead != null)
      {
         while (!auxHead.isNIL())
         {
            auxHead = auxHead.getNext();
         }
         auxHead.setData(((SingleLinkedListImpl<T>) otherSet).getHead().getData());
         auxHead.setNext(((SingleLinkedListImpl<T>) otherSet).getHead().getNext());

      }

      this.removeDuplicates();
   }
}
