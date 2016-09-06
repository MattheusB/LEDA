package adt.bst;

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
		return height(this.root);
	}

	protected int height(BTNode<T> node) {
		if (node == null || node.isEmpty()) {
			return -1;
		} else {
			return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
		}
	}

	@Override
	public BSTNode<T> search(T element) {
		if (element != null) {
			return search(this.root, element);
		}
		return new BSTNode<T>();

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
		if (element != null && !this.isEmpty() && !node.isEmpty()) {
			if (!node.getRight().isEmpty()) {
				BSTNode<T> newNode = minimum((BSTNode<T>) node.getRight());
				return newNode;
			} else {
				BTNode<T> ancestral = node.getParent();

				while (ancestral != null && !ancestral.isEmpty() && !node.equals(ancestral.getLeft())) {
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
		if (element != null && !this.isEmpty() && !node.isEmpty()) {

			if (!node.getLeft().isEmpty()) {
				BSTNode<T> newNode = maximum((BSTNode<T>) node.getLeft());
				return newNode;
			} else {
				BTNode<T> ancestral = node.getParent();
				while (ancestral != null && !ancestral.isEmpty() && !node.equals(ancestral.getRight())) {
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
		BTNode<T> node = search(element);
		if (element != null && !node.isEmpty()) {
			remove(node);
		}

	}

	private void remove(BTNode<T> node) {
		if (node.getLeft().isEmpty() && node.getRight().isEmpty()) {
			node.setData(null);
			node.setLeft(null);
			node.setRight(null);

		}

		else if (!node.getRight().isEmpty() && node.getLeft().isEmpty()) {
			node.getRight().setParent(node.getParent());
			if (node.getParent() != null) {
				if (node.getParent().getLeft().equals(node)) {
					node.getParent().setLeft(node.getRight());

				} else {
					node.getParent().setRight(node.getRight());
				}

			} else {
				this.root = (BSTNode<T>) root.getRight();
				node.setRight(null);
			}
			

		}

		else if (!node.getLeft().isEmpty() && node.getRight().isEmpty()) {
			node.getLeft().setParent(node.getParent());
			if (node.getParent() != null) {
				if (node.getParent().getRight().equals(node)) {
					node.getParent().setRight(node.getLeft());

				} else {
					node.getParent().setLeft(node.getLeft());
				}
			} else {
				this.root = (BSTNode<T>) root.getLeft();
				node.setLeft(null);

			}

		}

		else {
			BTNode<T> sucessor = sucessor(node.getData());
			T nodeData = node.getData();
			node.setData(sucessor.getData());
			sucessor.setData(nodeData);
			remove(sucessor);

		}
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

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}