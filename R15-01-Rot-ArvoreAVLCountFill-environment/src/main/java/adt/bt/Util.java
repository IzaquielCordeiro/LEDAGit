package adt.bt;

import adt.bst.BSTNode;

public class Util
{


	/**
	 * A rotacao a esquerda em node deve subir e retornar seu filho a direita
	 *
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node)
	{
		BTNode<T> right = node.getRight();

		right.setParent(node.getParent());
		node.setParent(right);

		BTNode<T> rightLeft = right.getLeft();
		node.setRight(rightLeft);
		right.setLeft(node);

		if (rightLeft != null) rightLeft.setParent(node);

		return (BSTNode<T>) right;
	}

	/**
	 * A rotacao a direita em node deve subir e retornar seu filho a esquerda
	 *
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node)
	{
		BTNode<T> left = node.getLeft();

		left.setParent(node.getParent());
		node.setParent(left);

		BTNode<T> leftRight = left.getRight();
		node.setLeft(leftRight);
		left.setRight(node);

		if (leftRight != null) leftRight.setParent(node);

		return (BSTNode<T>) left;
	}

	public static <T extends Comparable<T>> T[] makeArrayOfComparable(int size)
	{
		@SuppressWarnings("unchecked") T[] array = (T[]) new Comparable[size];
		return array;
	}
}
