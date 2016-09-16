package adt.bt;

import adt.bst.BSTNode;

public class Util {

	/**
	 * A rotacao a esquerda em node deve subir o seu filho a direita e
	 * retorna-lo em seguida
	 * 
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {

		if (node != null && !node.isEmpty()) {

			BTNode<T> pai = node.getParent();
			BTNode<T> aux = node.getRight();

			aux.setParent(pai);
			if (pai != null) {

				if (pai.getRight().equals(node)) {
					pai.setRight(aux);
				}

				else {
					pai.setLeft(aux);
				}
			}

			node.setRight(aux.getLeft());
			if (node.getRight() != null) {
				node.getRight().setParent(node);
			}
			aux.setLeft(node);
			node.setParent(aux);

			return (BSTNode<T>) aux;
		}
		return null;
	}

	/**
	 * A rotacao a direita em node deve subir seu filho a esquerda s retorna-lo
	 * em seguida
	 * 
	 * @param node
	 * @return
	 */
	public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {

		if (node != null && !node.isEmpty()) {

			BTNode<T> pai = node.getParent();
			BTNode<T> aux = node.getLeft();

			aux.setParent(pai);
			if (pai != null) {

				if (pai.getLeft().equals(node)) {
					pai.setLeft(aux);
				}

				else {
					pai.setRight(aux);
				}
			}

			node.setLeft(aux.getRight());
			if (node.getLeft() != null) {
				node.getLeft().setParent(node);
			}
			aux.setRight(node);
			node.setParent(aux);

			return (BSTNode<T>) aux;
		}
		return null;
	}

}
