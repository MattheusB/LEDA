package adt.splaytree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;
import adt.bt.Util;

public class SplayTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements SplayTree<T> {

	private void splay(BSTNode<T> node) {
		if (node != null && !node.isEmpty() && !node.equals(this.root)) {

			if (node.getParent().equals(this.root)) {
				if (node.getParent().getLeft().equals(node)) {
					Util.rightRotation(this.root);
				} else {
					Util.leftRotation(this.root);
				}
				this.root = node;
			}

			else if (node.getParent().getParent().getRight().equals(node.getParent())) {
				if (node.getParent().getRight().equals(node)) {

					Util.leftRotation((BSTNode<T>) node.getParent().getParent());
					Util.leftRotation((BSTNode<T>) node.getParent());
				} else {
					Util.rightRotation((BSTNode<T>) node.getParent());
					Util.leftRotation((BSTNode<T>) node.getParent());

				}
			} else if (node.getParent().getParent().getLeft().equals(node.getParent())) {
				if (node.getParent().getLeft().equals(node)) {
					Util.rightRotation((BSTNode<T>) node.getParent().getParent());
					Util.rightRotation((BSTNode<T>) node.getParent());
				} else {
					Util.leftRotation((BSTNode<T>) node.getParent());
					Util.rightRotation((BSTNode<T>) node.getParent());

				}

			}

			if (node.getParent() == null) {
				this.root = node;
			}

			splay(node);

		}

	}

	@Override
	public void insert(T element) {
		if (element != null) {
			BSTNode<T> node = insert(element, this.root);
			splay(node);
		}

	}

	private BSTNode<T> insert(T element, BTNode<T> node) {
		if (node.isEmpty()) {
			node.setData(element);

			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());

			node.getLeft().setParent(node);
			node.getRight().setParent(node);

			return (BSTNode<T>) node;

		} else if (element.compareTo(node.getData()) < 0) {
			return insert(element, node.getLeft());

		} else {
			return insert(element, node.getRight());
		}

	}

	@Override
	public BSTNode<T> search(T element) {

		if (element != null) {

			BTNode<T> aux = this.root;
			while (!aux.isEmpty()) {

				if (aux.getData().compareTo(element) == 0) {
					splay((BSTNode<T>) aux);
					return (BSTNode<T>) aux;
				} else if (aux.getData().compareTo(element) < 0) {
					aux = aux.getRight();
				}

				else {
					aux = aux.getLeft();
				}
			}
			if (aux.getParent() != null)
				splay((BSTNode<T>) aux.getParent());
		}
		return new BSTNode<>();
	}

	@Override
	public void remove(T element) {

		if (!this.isEmpty() && element != null) {

			BTNode<T> aux = super.search(element);

			if (!aux.isEmpty()) {
				BSTNode<T> parent = (BSTNode<T>) aux.getParent();
				remove(aux);

				if (parent != null) {
					splay(parent);
				}
			} else {

				splay((BSTNode<T>) aux.getParent());
			}
		}
	}

}
