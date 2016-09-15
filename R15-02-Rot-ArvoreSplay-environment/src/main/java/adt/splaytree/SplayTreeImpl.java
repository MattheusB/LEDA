package adt.splaytree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;
import adt.bt.Util;

public class SplayTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements SplayTree<T> {

	private void splay(BSTNode<T> node) {

		if (node != null && !node.isEmpty() && !node.equals(this.root)) {

			BSTNode<T> pai = (BSTNode<T>) node.getParent();
			BSTNode<T> avo = (BSTNode<T>) node.getParent().getParent();

			while (pai != null) {

				if (pai.equals(this.root) && pai.getRight().equals(node)) {
					Util.leftRotation(this.root);
					this.root = node;
				}

				else if (pai.equals(this.root) && pai.getLeft().equals(node)) {
					Util.rightRotation(this.root);
					this.root = node;

				}

				else if (avo.getRight().equals(pai) && pai.getRight().equals(node)) {
					Util.leftRotation((BSTNode<T>) node.getParent().getParent());
					Util.leftRotation((BSTNode<T>) node.getParent());
				}

				else if (avo.getLeft().equals(pai) && pai.getLeft().equals(node)) {
					Util.rightRotation((BSTNode<T>) node.getParent().getParent());
					Util.rightRotation((BSTNode<T>) node.getParent());
				}

				else if (avo.getRight().equals(pai) && pai.getLeft().equals(node)) {
					Util.rightRotation((BSTNode<T>) node.getParent());
					Util.leftRotation((BSTNode<T>) node.getParent().getParent());
				}

				else if (avo.getLeft().equals(pai) && pai.getRight().equals(node)) {
					Util.leftRotation((BSTNode<T>) node.getParent());
					Util.rightRotation((BSTNode<T>) node.getParent().getParent());
				}

				pai = (BSTNode<T>) node.getParent();

				if (pai != null) {
					avo = (BSTNode<T>) pai.getParent();

					if (avo == null) {
						this.root = pai;
					} else {
						this.root = node;
					}
				}
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

			if (node.getParent() != null) {
				splay((BSTNode<T>) node);
			}

		} else if (element.compareTo(node.getData()) < 0) {
			insert(element, node.getLeft());

		} else {
			insert(element, node.getRight());
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
