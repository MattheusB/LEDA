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
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public BSTNode<T> search(T element) {
		if (element != null) {
			return search(this.root, element);
		}
		return null;

	}

	private BSTNode<T> search(BTNode<T> node, T element) {
		if (node.isEmpty() || node.getData().compareTo(element) == 0) {
			return (BSTNode<T>) node;

		} else if (element.compareTo(node.getData()) < 0) {
			return search(node.getLeft(), element);
		} else {
			return search(node.getRight(), element);
		}
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
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.getLeft().setParent(node);
			node.getRight().setParent(node);

		} else if (element.compareTo(node.getData()) < 0) {
			insert(element, node.getLeft());

		} else if (element.compareTo(node.getData()) > 0) {
			insert(element, node.getRight());
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

	private BSTNode<T> maximum(BTNode<T> node) {
		if (node.getRight().isEmpty()) {
			return (BSTNode<T>) node;

		} else {
			return maximum(node.getRight());
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
		BTNode<T> node = search(element);
		if (element != null && !this.isEmpty()) {
			if (!node.getRight().isEmpty()) {
				BSTNode<T> newNode = minimum((BSTNode<T>) node.getRight());
				return newNode;
			} else {
				BTNode<T> ancestral = node.getParent();
				while (ancestral != null && !node.equals(ancestral.getLeft())) {
					ancestral = ancestral.getParent();
					node = node.getParent();
				}
				return (BSTNode<T>) ancestral;

			}
		} else {
			return null;
		}
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BTNode<T> node = search(element);
		if (element != null && !this.isEmpty()) {
			if (!node.getLeft().isEmpty()) {
				BSTNode<T> newNode = maximum((BSTNode<T>) node.getLeft());
				return newNode;
			} else {
				BTNode<T> ancestral = node.getParent();
				while (ancestral != null && !node.equals(ancestral.getRight())) {
					ancestral = ancestral.getParent();
					node = node.getParent();
				}
				return (BSTNode<T>) ancestral;
			}
		} else {
			return null;
		}

	}

	@Override
	public void remove(T element) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implemented yet!");
	}

	@Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		preOrder(this.root, array, 0);
		return array;

	}

	private int preOrder(BTNode<T> node, T[] array, int indice) {
		if (!node.isEmpty()) {
			array[indice] = node.getData();
			indice++;
			indice = preOrder(node.getLeft(), array, indice);
			indice = preOrder(node.getRight(), array, indice);
		}
		return indice;
	}

	@Override
	public T[] order() {
		T[] array = (T[]) new Comparable[this.size()];
		order(this.root, array, 0);
		return array;

	}

	private int order(BTNode<T> node, T[] array, int indice) {
		if (!node.isEmpty()) {
			indice = order(node.getLeft(), array, indice);
			array[indice] = node.getData();
			indice++;
			indice = order(node.getRight(), array, indice);
		}
		return indice;
	}

	@Override
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		postOrder(this.root, array, 0);
		return array;

	}

	private int postOrder(BTNode<T> node, T[] array, int indice) {
		if (!node.isEmpty()) {
			indice = postOrder(node.getLeft(), array, indice);
			indice = postOrder(node.getRight(), array, indice);
			array[indice] = node.getData();
			indice++;
		}
		return indice;
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

	}

}
