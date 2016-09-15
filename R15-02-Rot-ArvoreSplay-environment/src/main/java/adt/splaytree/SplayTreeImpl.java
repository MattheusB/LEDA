package adt.splaytree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;
import adt.bt.Util;

public class SplayTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements SplayTree<T> {

	private void splay(BSTNode<T> node) {
		if (node != null) {
			if (node.getParent() == null) {
				this.root = node;
			} else {
				BSTNode<T> pai = (BSTNode<T>) node.getParent();

				if (pai.equals(this.root) && pai.getRight().equals(node)) {
					Util.leftRotation(this.root);
				}

				else if (pai.equals(this.root) && pai.getLeft().equals(node)) {
					Util.rightRotation(this.root);

				}

				else if (pai.getParent().getRight().equals(pai) && pai.getRight().equals(node)) {
					Util.leftRotation((BSTNode<T>) pai.getParent());
					Util.leftRotation(pai);
				}

				else if (pai.getParent().getLeft().equals(pai) && pai.getLeft().equals(node)) {
					Util.rightRotation((BSTNode<T>) pai.getParent());
					Util.rightRotation(pai);
				}

				else if (pai.getParent().getRight().equals(pai) && pai.getLeft().equals(node)) {
					Util.rightRotation(pai);
					Util.leftRotation((BSTNode<T>) pai.getParent());
				}

				else if (pai.getParent().getLeft().equals(pai) && pai.getRight().equals(node)) {
					Util.leftRotation(pai);
					Util.rightRotation((BSTNode<T>) pai.getParent());
				}

				splay(node);

			}
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
		if (element != null && !this.root.isEmpty()) {
			BSTNode<T> aux = super.search(this.root, element);
			if (aux.isEmpty()) {
				splay((BSTNode<T>) aux.getParent());
			} else {
				splay(aux);
			}

			return aux;
		}
		return new BSTNode<T>();

	}

	@Override
	public void remove(T element) {

		if (element != null && !this.isEmpty()) {

			BSTNode<T> node = super.search(element);
			BSTNode<T> pai = (BSTNode<T>) node.getParent();

			if (element != null && node != null) {
				super.remove(node);
			}

			splay(pai);
		}
	}

}
