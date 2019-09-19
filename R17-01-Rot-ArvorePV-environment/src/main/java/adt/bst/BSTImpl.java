package adt.bst;

import adt.bt.BTNode;

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
        return height(this.root);
    }
    protected int height(BTNode<T> node)
    {
        int height = -1;
        if (!node.isEmpty())
        {
            height = 1 + Math.max(height(node.getLeft()), height(node.getRight()));
        }
        return height;
    }
    @Override
    public BSTNode<T> search(T element)
    {
        BSTNode<T> result = new BSTNode<>();
        if (element != null)
        {
            if (!isEmpty())
            {
                result = search(this.root, element);
            }
        }
        return result;
    }
    protected BSTNode<T> search(BSTNode<T> node, T element)
    {
        BSTNode<T> aux = new BSTNode<>();
        if (!node.isEmpty())
        {
            if (node.getData().equals(element))
            {
                aux = node;
            }
            else if (node.getData().compareTo(element) > 0)
            {
                aux = search((BSTNode<T>) node.getLeft(), element);
            }
            else
            {
                aux = search((BSTNode<T>) node.getRight(), element);
            }
        }
        return aux;
    }
    @Override
    public void insert(T element)
    {
        if (element != null)
        {
            insert(this.root, element);
        }
    }
    protected void insert(BSTNode<T> node, T element)
    {
        if (node.isEmpty())
        {
            node.setData(element);
            node.setLeft(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(node).build());
            node.setRight(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(node).build());
        }
        else
        {
            if (node.getData().compareTo(element) > 0)
            {
                insert((BSTNode<T>) node.getLeft(), element);
            }
            else
            {
                insert((BSTNode<T>) node.getRight(), element);
            }
        }
    }
    @Override
    public BSTNode<T> maximum()
    {
        BSTNode<T> max;
        if (isEmpty())
        {
            max = null;
        }
        else
        {
            max = maximum(this.root);
        }
        return max;
    }
    public BSTNode<T> maximum(BSTNode<T> node)
    {
        BSTNode<T> max = node;
        if (!node.getRight().isEmpty())
        {
            max = maximum((BSTNode<T>) node.getRight());
        }
        return max;
    }
    @Override
    public BSTNode<T> minimum()
    {
        BSTNode<T> min = null;
        if (!isEmpty()) min = minimum(this.root);
        return min;
    }
    protected BSTNode<T> minimum(BSTNode<T> node)
    {
        BSTNode<T> min = node;
        if (!node.getLeft().isEmpty()) min = minimum((BSTNode<T>) node.getLeft());
        return min;
    }
    @Override
    public BSTNode<T> sucessor(T element)
    {
        BSTNode<T> sucessor = search(element);
        if (element != null && !this.root.isEmpty() && !sucessor.isEmpty())
        {
            if (!sucessor.getRight().isEmpty()) sucessor = minimum((BSTNode<T>) sucessor.getRight());
            else sucessor = sucessor(sucessor);
        }
        else sucessor = null;
        return sucessor;
    }
    protected BSTNode<T> sucessor(BSTNode<T> node)
    {
        BSTNode<T> sucessor = (BSTNode<T>) node.getParent();
        if (node.getParent() != null)
        {
            if (!sucessor.isEmpty() && sucessor.getRight().equals(node))
                sucessor = sucessor((BSTNode<T>) node.getParent());
        }
        return sucessor;
    }
    @Override
    public BSTNode<T> predecessor(T element)
    {
        BSTNode<T> result = search(element);
        if (element != null && !this.root.isEmpty() && !result.isEmpty())
        {
            if (!result.getLeft().isEmpty()) result = maximum((BSTNode<T>) result.getLeft());
            else result = predecessor(result);
        }
        else result = null;
        return result;
    }
    protected BSTNode<T> predecessor(BSTNode<T> node)
    {
        BSTNode<T> predecessor = (BSTNode<T>) node.getParent();
        if (node.getParent() != null)
        {
            if (!predecessor.isEmpty() && predecessor.getLeft().equals(node))
                predecessor = predecessor((BSTNode<T>) node.getParent());
        }
        return predecessor;
    }
    @Override
    public void remove(T element)
    {
        BSTNode<T> node = search(element);
        if (!node.isEmpty())
        {
            if (node.isLeaf())
            {
                node.setData(null);
                node.setLeft(null);
                node.setRight(null);
            }
            else if (node.getRight().isEmpty() ^ node.getLeft().isEmpty())
            {
                BSTNode<T> parent = (BSTNode<T>) node.getParent();
                if (parent != null)
                {
                    if (!parent.getLeft().equals(node))
                    {
                        if (!node.getLeft().isEmpty())
                        {
                            parent.setRight(node.getLeft());
                            node.getLeft().setParent(parent);
                        }
                        else
                        {
                            parent.setRight(node.getRight());
                            node.getRight().setParent(parent);
                        }
                    }
                    else if (!node.getLeft().isEmpty())
                    {
                        parent.setLeft(node.getLeft());
                        node.getLeft().setParent(parent);
                    }
                    else
                    {
                        parent.setLeft(node.getRight());
                        node.getRight().setParent(parent);
                    }
                }
                else
                {
                    if (node.getLeft().isEmpty()) this.root = (BSTNode<T>) node.getRight();
                    else this.root = (BSTNode<T>) node.getLeft();
                    this.root.setParent(null);
                }
            }
            else
            {
                T sucessor = sucessor(node.getData()).getData();
                remove(sucessor);
                node.setData(sucessor);
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
    protected void preOrder(BSTNode<T> node, List<T> preOrderedList)
    {
        if (!node.isEmpty())
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
    protected void order(BSTNode<T> node, List<T> orderedList)
    {
        if (!node.isEmpty())
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
    protected void postOrder(BSTNode<T> node, List<T> postOrderedList)
    {
        if (!node.isEmpty())
        {
            postOrder((BSTNode<T>) node.getLeft(), postOrderedList);
            postOrder((BSTNode<T>) node.getRight(), postOrderedList);
            postOrderedList.add(node.getData());
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
    protected int size(BSTNode<T> node)
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