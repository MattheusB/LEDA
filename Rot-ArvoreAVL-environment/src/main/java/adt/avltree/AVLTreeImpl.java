package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		if (node != null || !node.isEmpty()) {
			return height(node.getRight()) - height(node.getLeft());
		} else {
			return 0;
		}

	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		if (node != null && !node.isEmpty()) {
			int balance = calculateBalance(node);

			if (balance > 1) {
				if (calculateBalance((BSTNode<T>) node.getRight()) < 0) {
					rightRotation((BSTNode<T>) node.getRight());
					leftRotation(node);
				} else {
					leftRotation(node);
				}
			} else if (balance < -1) {
				if (calculateBalance((BSTNode<T>) node.getLeft()) > 0) {
					leftRotation((BSTNode<T>) node.getLeft());
					rightRotation(node);
				} else {
					rightRotation(node);
				}
			}
		}

	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		if (node != null) {
			rebalance((BSTNode<T>) node);
			rebalanceUp((BSTNode<T>) node.getParent());

		}

	}

	// AUXILIARY
	protected void leftRotation(BSTNode<T> node) {

		BSTNode<T> aux = Util.leftRotation(node);

		if (node.equals(this.root)) {
			this.root = (BSTNode<T>) node.getParent();
		} else {
			if (aux.getParent().getRight().equals(node)) {
				aux.getParent().setRight(aux);
			} else {
				aux.getParent().setLeft(aux);
			}
		}
	}

	// AUXILIARY
	protected void rightRotation(BSTNode<T> node) {

		BSTNode<T> aux = Util.rightRotation(node);

		if (node.equals(this.root)) {
			this.root = (BSTNode<T>) node.getParent();
		} else {
			if (aux.getParent().getRight().equals(node)) {
				aux.getParent().setRight(aux);

			} else {
				aux.getParent().setLeft(aux);

			}
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

			rebalanceUp((BSTNode<T>) node);

		} else if (element.compareTo(node.getData()) < 0) {
			insert(element, node.getLeft());

		} else if (element.compareTo(node.getData()) > 0) {
			insert(element, node.getRight());
		}

	}

	@Override
	public void remove(T element) {
		if (element != null) {
			BTNode<T> node = search(element);
			remove(node);
		}

	}

	private void remove(BTNode<T> node) {
		if (node != null && !node.isEmpty()) {

			if (node.getLeft().isEmpty() && node.getRight().isEmpty()) {
				node.setData(null);
				node.setLeft(null);
				node.setRight(null);
				rebalanceUp((BSTNode<T>) node);

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

				rebalanceUp((BSTNode<T>) node);

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

				rebalanceUp((BSTNode<T>) node);

			}

			else {
				BTNode<T> sucessor = sucessor(node.getData());
				T nodeData = node.getData();
				node.setData(sucessor.getData());
				sucessor.setData(nodeData);
				remove(sucessor);

			}
		}
	}

}
