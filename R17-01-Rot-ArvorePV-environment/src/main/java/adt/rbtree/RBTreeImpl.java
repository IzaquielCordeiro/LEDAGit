package adt.rbtree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

import java.util.ArrayList;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T>
{

   public RBTreeImpl()
   {
      this.root = new RBNode<T>();
   }

   protected int blackHeight()
   {
      int bh = 0;

      bh += blackHeight((RBNode<T>) this.root);

      return bh;
   }

   private int blackHeight(RBNode<T> node)
   {
      int altura = 0;
      if (node != null && !node.isEmpty())
      {
         if (node.getColour() == Colour.BLACK)
         {
            altura = 1 + Math.max(blackHeight((RBNode<T>) node.getLeft()), blackHeight((RBNode<T>) node.getRight()));
         }
         else
         {
            altura = Math.max(blackHeight((RBNode<T>) node.getLeft()), blackHeight((RBNode<T>) node.getRight()));
         }
      }
      return altura;
   }

   protected boolean verifyProperties()
   {
      boolean resp = verifyNodesColour() && verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes() && verifyBlackHeight();

      return resp;
   }

   /**
    * The colour of each node of a RB tree is black or red. This is guaranteed
    * by the type Colour.
    */
   private boolean verifyNodesColour()
   {
      return true; // already implemented
   }

   /**
    * The colour of the root must be black.
    */
   private boolean verifyRootColour()
   {
      return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
      // implemented
   }

   /**
    * This is guaranteed by the constructor.
    */
   private boolean verifyNILNodeColour()
   {
      return true; // already implemented
   }

   /**
    * Verifies the property for all RED nodes: the children of a red node must
    * be BLACK.
    */
   private boolean verifyChildrenOfRedNodes()
   {
      return verifyChildrenOfRedNodes((RBNode<T>) this.root);
   }

   private boolean verifyChildrenOfRedNodes(RBNode<T> node)
   {
      if (node != null && !node.isEmpty())
      {
         RBNode<T> ls = (RBNode<T>) node.getLeft();
         RBNode<T> rs = (RBNode<T>) node.getRight();

         if (node.getColour().equals(Colour.RED))
         {
            if (!ls.isEmpty() && ls.getColour().equals(Colour.RED)) return false;
            if (!rs.isEmpty() && rs.getColour().equals(Colour.RED)) return false;

            return verifyChildrenOfRedNodes(ls) && verifyChildrenOfRedNodes(rs);
         }
      }

      return true;
   }

   /**
    * Verifies the black-height property from the root.
    */
   private boolean verifyBlackHeight()
   {
      return verifiBlackHeight(getRoot());
   }

   private boolean verifiBlackHeight(BSTNode<T> node)
   {
      boolean result = true;

      if (!node.isEmpty())
      {
         if (blackHeight((RBNode<T>) node.getLeft()) == blackHeight((RBNode<T>) node.getRight())) result = verifiBlackHeight((BSTNode<T>) node.getLeft()) && verifiBlackHeight((BSTNode<T>) node.getRight());
      }
      return result;
   }

   @Override
   public void insert(T value)
   {
      if (value != null) insertR((RBNode<T>) root, value);
   }

   protected RBNode insertR(RBNode<T> node, T value)
   {
      RBNode<T> next = node;
      if (node.isEmpty())
      {
         node.setData(value);
         node.setColour(Colour.RED);

         node.setLeft(new RBNode<T>());
         node.getLeft().setParent(node);

         node.setRight(new RBNode<T>());
         node.getRight().setParent(node);

         fixUpCase1(node);
      }
      else
      {
         if (node.getData().compareTo(value) == 1) next = insertR(getLeft(node), value);
         else if (node.getData().compareTo(value) == -1) next = insertR(getRight(node), value);
      }
      return next;
   }

   @Override
   public RBNode<T>[] rbPreOrder()
   {
      return rbPreOrder(root, new ArrayList<RBNode<T>>()).toArray(new RBNode[size()]);
   }

   private ArrayList<RBNode<T>> rbPreOrder(BSTNode<T> node, ArrayList<RBNode<T>> rbNodes)
   {
      if (node != null && !node.isEmpty())
      {
         rbNodes.add((RBNode<T>) node);

         rbPreOrder((BSTNode<T>) node.getLeft(), rbNodes);
         rbPreOrder((BSTNode<T>) node.getRight(), rbNodes);
      }

      return rbNodes;
   }

   protected void fixUpCase1(RBNode<T> node)
   {
      if (node.equals(getRoot())) node.setColour(Colour.BLACK);

      else fixUpCase2(node);
   }

   protected void fixUpCase2(RBNode<T> node)
   {
      if (getParent(node).getColour() != Colour.BLACK) fixUpCase3(node);
   }

   protected void fixUpCase3(RBNode<T> node)
   {
      RBNode<T> parent = getParent(node);
      RBNode<T> grandfather = getParent(parent);

      RBNode<T> uncle = getUncle(node);

      if (uncle.getColour() == Colour.RED)
      {
         parent.setColour(Colour.BLACK);
         uncle.setColour(Colour.BLACK);
         grandfather.setColour(Colour.RED);

         fixUpCase1(grandfather);
      }
      else fixUpCase4(node);
   }

   protected void fixUpCase4(RBNode<T> node)
   {
      RBNode<T> parent = getParent(node);

      if (!((isLeftChild(node) && isLeftChild(parent)) || (!isLeftChild(node) && !isLeftChild(parent))))
      {
         if (isLeftChild(node)) rightRotation(getParent(node));

         else leftRotation(getParent(node));

         fixUpCase5(parent);
      }
      else fixUpCase5(node);
   }

   protected void fixUpCase5(RBNode<T> node)
   {
      RBNode<T> parent = getParent(node);
      RBNode<T> grandfather = getParent(parent);

      parent.setColour(Colour.BLACK);
      grandfather.setColour(Colour.RED);

      if (isLeftChild(node)) rightRotation(grandfather);

      else leftRotation(grandfather);
   }

   protected void leftRotation(BSTNode<T> node)
   {
      BSTNode<T> newNode = Util.leftRotation(node);
      if (newNode.getParent() == null) root = newNode;
   }

   protected void rightRotation(BSTNode<T> node)
   {
      BSTNode<T> newNode = Util.rightRotation(node);
      if (newNode.getParent() == null) root = newNode;
   }

   protected RBNode<T> getParent(BSTNode<T> node)
   {
      return (RBNode<T>) node.getParent();
   }

   private RBNode<T> getUncle(RBNode<T> node)
   {
      RBNode<T> parent = getParent(node);
      RBNode<T> grandfather = (RBNode<T>) parent.getParent();

      if(isLeftChild(parent)) return getRight(grandfather);
      return getLeft(grandfather);
   }

   private boolean isLeftChild(BSTNode<T> node)
   {
      return node.equals(getParent(node).getLeft());
   }

   private boolean isRightChild(BSTNode<T> node)
   {
      return node.equals(getParent(node).getRight()) ;
   }

   protected RBNode<T> getLeft(BTNode<T> node)
   {
      return (RBNode<T>) node.getLeft();
   }

   protected RBNode<T> getRight(BTNode<T> node)
   {
      return (RBNode<T>) node.getRight();
   }

}
