package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * Performs consistency validations within a AVL Tree instance
 *
 * @param <T>
 * @author Claudio Campelo
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T>
{

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.


	@Override
	public void insert(T element)
	{
		super.insert(root, element);
		rebalanceUp(search(element));
	}


	@Override
	public void remove(T element)
	{
		BSTNode<T> newNode = search(element);
		super.remove(element);

		rebalanceUp(newNode);
	}

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node)
	{
		if (node == null || node.isEmpty()) return 0;

		return height(node.getRight()) - height(node.getLeft());
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node)
	{
		int bf = calculateBalance(node);
		if (bf < -1)
		{
			if (calculateBalance((BSTNode<T>) node.getLeft()) > 0) leftRotation((BSTNode<T>) node.getLeft());

			rightRotation(node);
		}
		else if (bf > 1)
		{
			if (calculateBalance((BSTNode<T>) node.getRight()) < 0) rightRotation((BSTNode<T>) node.getRight());

			leftRotation(node);
		}
	}

	protected void leftRotation(BSTNode<T> node)
	{
		BSTNode<T> newNode = Util.leftRotation(node);
		BSTNode<T> p = (BSTNode<T>) newNode.getParent();

		if (newNode.getParent() == null) this.root = newNode;

		else
		{
			if (p.getLeft().equals(node)) p.setLeft(newNode);
			else p.setRight(newNode);
		}
	}

	protected void rightRotation(BSTNode<T> node)
	{
		BSTNode<T> newNode = Util.rightRotation(node);
		BSTNode<T> p = (BSTNode<T>) newNode.getParent();

		if (newNode.getParent() == null) this.root = newNode;

		else
		{
			if (p.getLeft().equals(node)) p.setLeft(newNode);
			else p.setRight(newNode);
		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node)
	{
		if (node != null)
		{
			rebalance(node);
			rebalanceUp((BSTNode) node.getParent());
		}
	}
}
