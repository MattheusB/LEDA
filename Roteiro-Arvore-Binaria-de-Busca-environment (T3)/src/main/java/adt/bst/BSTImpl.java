package adt.bst;

import java.util.Arrays;

import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented yet!");
	}

	@Override
	public BSTNode<T> search(T element) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented yet!");
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			insert(element, this.root);
		}

	}

	private void insert(T element, BTNode<T> node) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BTNode<T>());
			node.setRight(new BTNode<T>());

		} else if (node.getData().compareTo(element) < 0) {
			insert(element, node.getRight());

		} else if (node.getData().compareTo(element) > 0) {
			insert(element, node.getLeft());
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if (this.isEmpty()) {
			return null;
		} else {
			return maximum(this.root);
		}
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (node.getRight().isEmpty()) {
			return node;
		} else {
			return maximum((BSTNode<T>) node.getRight());
		}

	}

	@Override
	public BSTNode<T> minimum() {
		if (this.isEmpty()) {
			return null;
		} else {
			return minimum(this.root);
		}

	}

	private BSTNode<T> minimum(BSTNode<T> node) {
		if (node.getLeft().isEmpty()) {
			return node;
		} else {
			return minimum((BSTNode<T>) node.getLeft());
		}

	}

	@Override
	public BSTNode<T> sucessor(T element) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented yet!");
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented yet!");
	}

	@Override
	public void remove(T element) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented yet!");
	}

	@Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		preOrder(this.root, array, 0);
		return array;
	}

	private int preOrder(BTNode<T> node, T[] array, int index) {
		if (!node.isEmpty()) {
			array[index] = node.getData();
			index++;
			index = preOrder(node.getLeft(), array, index);
			index = preOrder(node.getRight(), array, index);
		}
		return index;
	}

	@Override
	public T[] order() {
		T[] array = (T[]) new Comparable[this.size()];
		order(this.root, array, 0);
		return array;

	}

	private int order(BTNode<T> node, T[] array, int index) {
		if (!node.isEmpty()) {
			index = order(node.getLeft(), array, index);
			array[index] = node.getData();
			index = order(node.getRight(), array, ++index);

		}
		return index;

	}

	@Override
	public T[] postOrder() {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented yet!");
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size(node.getLeft()) + size(node.getRight());
		}
		return result;
	}

	public static void main(String[] args) {
		BSTImpl<Integer> bst = new BSTImpl<>();
		bst.insert(new Integer(100));
		bst.insert(new Integer(90));
		bst.insert(new Integer(60));
		bst.insert(new Integer(50));
		bst.insert(new Integer(70));
		bst.insert(new Integer(95));
		bst.insert(new Integer(93));
		bst.insert(new Integer(97));
		bst.insert(new Integer(119));
		bst.insert(new Integer(115));
		bst.insert(new Integer(113));
		bst.insert(new Integer(117));
		bst.insert(new Integer(130));
		bst.insert(new Integer(120));
		bst.insert(new Integer(140));

		System.out.println(Arrays.toString(bst.preOrder()));

	}

}
