package adt.bst;

import java.util.ArrayList;
import java.util.List;

public class BSTImpl<T extends Comparable<T>> implements BST<T>
{

   protected BSTNode<T> root;

   public BSTImpl()
   {
      root = new BSTNode<T>();
   }

   public BSTNode<T> getRoot()
   {
      return this.root;
   }




   @Override
   public boolean isEmpty()
   {
      return root.isEmpty();
   }

   @Override
   public int height()
   {
      if (this.isEmpty()) return -1;
      return height(this.root)-1;
   }

   private int height(BSTNode<T> node)
   {
      if(node == null || node.isEmpty()) return 0;
      return 1 + Math.max(height((BSTNode<T>)node.getLeft()), height((BSTNode<T>)node.getRight()));
   }

   @Override
   public BSTNode<T> search(T element)
   {
      return search(root, element);
   }

   private BSTNode<T> search(BSTNode<T> node, T element)
   {
      if (element != null)
      {
         if (!node.isEmpty())
         {
            if (node.getData().equals(element)) return node;
            else
            {
               if (element.compareTo(node.getData()) < 0) return search((BSTNode<T>)node.getLeft(), element);
               if (element.compareTo(node.getData()) > 0) return search((BSTNode<T>)node.getRight(), element);
            }
         }
      }
      return new BSTNode<T>();
   }

   @Override
   public void insert(T element)
   {
      if (element != null)
      {
         if (root.isEmpty())
         {
            root = new BSTNode.Builder<T>()
                    .data(element)
                    .right(new BSTNode<T>())
                    .left(new BSTNode<T>())
                    .parent(new BSTNode<T>())
                    .build();
         }
         else
         {
            insert(root, element);
         }
      }
   }

   private void insert(BSTNode<T> node, T element)
   {
      BSTNode<T> newNode = new BSTNode.Builder<T>()
              .data(element)
              .right(new BSTNode<T>())
              .left(new BSTNode<T>())
              .parent(node)
              .build();

      if (element.compareTo(node.getData()) < 0)
      {
         if(node.getLeft().isEmpty()) node.setLeft(newNode);
         else insert((BSTNode<T>) node.getLeft(), element);
      }
      else if (element.compareTo(node.getData()) > 0)
      {
         if(node.getRight().isEmpty()) node.setRight(newNode);
         else insert((BSTNode<T>) node.getRight(), element);
      }
   }


   @Override
   public BSTNode<T> maximum()
   {
      return maximum(root);
   }

   private BSTNode<T> maximum(BSTNode<T> node)
   {
      if(node == null || node.getRight() == null) return null;

      if (node.getRight().isEmpty()) return node;

      return maximum((BSTNode<T>) node.getRight());
   }

   @Override
   public BSTNode<T> minimum()
   {
      return minimum(root);
   }

   private BSTNode<T> minimum(BSTNode<T> node)
   {
      if(node == null || node.getLeft() == null) return null;

      if (node.getLeft().isEmpty()) return node;

      return minimum((BSTNode<T>) node.getLeft());
   }

   @Override
   public BSTNode<T> sucessor(T element)
   {
      BSTNode<T> node = search(root, element);
      return sucessor(node);
   }

   private BSTNode<T> sucessor(BSTNode<T> node)
   {
      BSTNode<T> sucessor = null;

      if(node != null && node.getRight() != null)
      {
         if(!node.getRight().isEmpty()) return minimum((BSTNode<T>)node.getRight());

         if (!node.isEmpty())
         {
            BSTNode<T> p = (BSTNode<T>) node.getParent();

            if(!p.isEmpty())
            {
               if(p.getLeft().equals(node)) sucessor = p;
               else sucessor = sucessor(p);
            }
         }
      }

      return sucessor;
   }

   @Override
   public BSTNode<T> predecessor(T element)
   {
      BSTNode<T> node = search(root, element);
      return predecessor(node);
   }

   private BSTNode<T> predecessor(BSTNode<T> node)
   {
      BSTNode<T> predecessor = null;

      if(node != null && node.getLeft() != null)
      {
         if (!node.isEmpty())
         {
            if(!node.getLeft().isEmpty()) return maximum(((BSTNode<T>)node.getLeft()));

            BSTNode<T> p = (BSTNode<T>)node.getParent();

            if(!p.isEmpty())
            {
               if(p.getRight().equals(node)) predecessor = p;
               else predecessor = predecessor(p);
            }
         }
      }
      return predecessor;
   }

   @Override
   public void remove(T element)
   {
      if (element != null) remove(search(element));
   }

   private void remove(BSTNode<T> node)
   {
      if(node != null && !node.isEmpty())
      {
         if(node.isLeaf()) node.setData(null);

         else
         {
            BSTNode<T> ls = (BSTNode<T>) node.getLeft();
            BSTNode<T> rs = (BSTNode<T>) node.getRight();

            if(!ls.isEmpty()^!rs.isEmpty())
            {
               if(!ls.isEmpty())
               {
                  node.setData(ls.getData());
                  remove(ls);
               }
               if(!rs.isEmpty())
               {
                  node.setData(rs.getData());
                  remove(rs);
               }
            }
            else
            {
               BSTNode<T> sucessor = sucessor(node);
               node.setData(sucessor.getData());
               remove(sucessor);
            }
         }
      }
   }

   @Override
   public T[] preOrder()
   {
      T[] preOrderedArray = (T[]) new Comparable[size()];
      List<T> list = new ArrayList<T>();
      preOrder(root, list);
      preOrderedArray = list.toArray(preOrderedArray);

      return preOrderedArray;
   }

   private void preOrder(BSTNode<T> node, List<T> preOrderedList)
   {
      if(!node.isEmpty())
      {
         preOrderedList.add(node.getData());

         preOrder((BSTNode<T>) node.getLeft(), preOrderedList);
         preOrder((BSTNode<T>) node.getRight(), preOrderedList);
      }
   }

   @Override
   public T[] order()
   {
      T[] orderedArray = (T[]) new Comparable[size()];
      List<T> list = new ArrayList<T>();
      order(root, list);
      orderedArray = list.toArray(orderedArray);

      return orderedArray;
   }

   private void order(BSTNode<T> node, List<T> orderedList)
   {
      if(!node.isEmpty())
      {
         order((BSTNode<T>) node.getLeft(), orderedList);
         orderedList.add(node.getData());
         order((BSTNode<T>) node.getRight(), orderedList);
      }
   }

   @Override
   public T[] postOrder()
   {
      T[] postOrderArray = (T[]) new Comparable[size()];
      List<T> list = new ArrayList<T>();
      postOrder(root, list);
      postOrderArray = list.toArray(postOrderArray);
      return postOrderArray;
   }

   private void postOrder(BSTNode<T> node, List<T> postOrderedList)
   {
      if(!node.isEmpty())
      {
         postOrder((BSTNode<T>) node.getRight(), postOrderedList);
         postOrderedList.add(node.getData());
         postOrder((BSTNode<T>) node.getLeft(), postOrderedList);
      }
   }

   /**
    * This method is already implemented using recursion. You must understand
    * how it work and use similar idea with the other methods.
    */
   @Override
   public int size()
   {
      return size(root);
   }

   private int size(BSTNode<T> node)
   {
      int result = 0;
      // base case means doing nothing (return 0)
      if (!node.isEmpty())
      { // indusctive case
         result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
      }
      return result;
   }

}
