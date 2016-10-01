package adt.rbtree;

import java.util.ArrayList;
import adt.bst.BSTImpl;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T> {

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	protected int blackHeight() {
		return blackHeight((RBNode<T>) this.root);

	}

	private int blackHeight(RBNode<T> node) {

		if (node.isEmpty()) {
			return 0;
		} else {
			if (node.getColour().equals(Colour.BLACK)) {
				return 1 + Math.max(blackHeight((RBNode<T>) node.getLeft()), blackHeight((RBNode<T>) node.getRight()));
			} else {
				return Math.max(blackHeight((RBNode<T>) node.getLeft()), blackHeight((RBNode<T>) node.getRight()));
			}
		}

	}

	protected boolean verifyProperties() {
		boolean resp = verifyNodesColour() && verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes()
				&& verifyBlackHeight();

		return resp;
	}

	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed
	 * by the type Colour.
	 */
	private boolean verifyNodesColour() {
		return true; // already implemented
	}

	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour() {
		return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
																// implemented
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour() {
		return true; // already implemented
	}

	/**
	 * Verifies the property for all RED nodes: the children of a red node must
	 * be BLACK.
	 */
	private boolean verifyChildrenOfRedNodes() {
		return verifyChildrenRedNodes((RBNode<T>) this.root);
	}

	private boolean verifyChildrenRedNodes(RBNode<T> node) {
		if (!node.isEmpty()) {
			if (node.getColour().equals(Colour.RED)) {
				if (((RBNode<T>) node.getLeft()).getColour().equals(Colour.RED)
						|| ((RBNode<T>) node.getRight()).getColour().equals(Colour.RED)) {
					return false;
				} else {
					return verifyChildrenRedNodes((RBNode<T>) node.getLeft())
							&& verifyChildrenRedNodes((RBNode<T>) node.getRight());
				}
			}
		}
		return true;
	}

	/**
	 * Verifies the black-height property from the root. The method blackHeight
	 * returns an exception if the black heights are different.
	 */
	private boolean verifyBlackHeight() {
		return verifyBlackHeight((RBNode<T>) this.root);
	}

	private boolean verifyBlackHeight(RBNode<T> node) {
		if (node.isEmpty()) {
			return true;
		} else if (blackHeight((RBNode<T>) node.getLeft()) == blackHeight((RBNode<T>) node.getRight())) {
			return verifyBlackHeight((RBNode<T>) node.getLeft()) && verifyBlackHeight((RBNode<T>) node.getRight());
		} else {
			return false;
		}
	}

	@Override
	public void insert(T value) {
		if (value != null) {

			insert(value, (RBNode<T>) this.root);
		}

	}

	private void insert(T value, RBNode<T> node) {
		if (node.isEmpty()) {
			node.setData(value);

			node.setLeft(new RBNode<T>());
			node.setRight(new RBNode<T>());

			node.getLeft().setParent(node);
			node.getRight().setParent(node);

			node.setColour(Colour.RED);

			fixUpCase1(node);

		}

		else if (value.compareTo(node.getData()) < 0) {
			insert(value, (RBNode<T>) node.getLeft());
		} else {
			insert(value, (RBNode<T>) node.getRight());
		}

	}

	@Override
	public RBNode<T>[] rbPreOrder() {
		ArrayList<RBNode<T>> lista = new ArrayList<>();

		rbPreOrder((RBNode<T>) this.root, lista);

		RBNode<T>[] array = new RBNode[lista.size()];
		return lista.toArray(array);

	}

	private void rbPreOrder(RBNode<T> node, ArrayList<RBNode<T>> lista) {
		if (!node.isEmpty()) {
			lista.add(node);
			rbPreOrder((RBNode<T>) node.getLeft(), lista);
			rbPreOrder((RBNode<T>) node.getRight(), lista);

		}
	}

	// FIXUP methods
	protected void fixUpCase1(RBNode<T> node) {
		if (node.equals(this.root)) {
			node.setColour(Colour.BLACK);
		} else {
			fixUpCase2(node);
		}

	}

	protected void fixUpCase2(RBNode<T> node) {
		if (((RBNode<T>) node.getParent()).getColour().equals(Colour.RED)) {
			fixUpCase3(node);
		}

	}

	protected void fixUpCase3(RBNode<T> node) {
		RBNode<T> pai = (RBNode<T>) node.getParent();
		RBNode<T> avo = (RBNode<T>) pai.getParent();
		RBNode<T> tio;

		if (avo.getLeft().equals(pai)) {
			tio = (RBNode<T>) avo.getRight();
		} else {
			tio = (RBNode<T>) avo.getLeft();
		}

		if (tio.getColour().equals(Colour.RED)) {
			tio.setColour(Colour.BLACK);
			pai.setColour(Colour.BLACK);

			avo.setColour(Colour.RED);

			fixUpCase1(avo);

		} else {
			fixUpCase4(node);
		}

	}

	protected void fixUpCase4(RBNode<T> node) {
		RBNode<T> pai = (RBNode<T>) node.getParent();
		RBNode<T> avo = (RBNode<T>) pai.getParent();

		if (avo.getLeft().equals(pai) && pai.getRight().equals(node)) {
			RBNode<T> aux = (RBNode<T>) Util.leftRotation(pai);
			if (aux.getParent() == null) {
				this.root = aux;
			}

			fixUpCase5((RBNode<T>) node.getLeft());

		} else if (avo.getRight().equals(pai) && pai.getLeft().equals(node)) {
			RBNode<T> aux = (RBNode<T>) Util.rightRotation(pai);
			if (aux.getParent() == null) {
				this.root = aux;
			}

			fixUpCase5((RBNode<T>) node.getRight());
		} else {
			fixUpCase5(node);
		}

	}

	protected void fixUpCase5(RBNode<T> node) {
		((RBNode<T>) node.getParent()).setColour(Colour.BLACK);
		((RBNode<T>) node.getParent().getParent()).setColour(Colour.RED);

		if (node.getParent().getLeft().equals(node)) {
			RBNode<T> aux = (RBNode<T>) Util.rightRotation((RBNode<T>) node.getParent().getParent());
			if (aux.getParent() == null) {
				this.root = aux;
			}

		} else {
			RBNode<T> aux = (RBNode<T>) Util.leftRotation((RBNode<T>) node.getParent().getParent());

			if (aux.getParent() == null) {
				this.root = aux;
			}

		}

	}
}
