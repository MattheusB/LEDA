package adt.btree;

import java.util.Collections;
import java.util.LinkedList;

public class BNode<T extends Comparable<T>> {
	protected LinkedList<T> elements; // PODERIA TRABALHAR COM ARRAY TAMBEM
	protected LinkedList<BNode<T>> children; // PODERIA TRABALHAR COM ARRAY
												// TAMBEM
	protected BNode<T> parent;
	protected int maxKeys;
	protected int maxChildren;

	public BNode(int order) {
		this.maxChildren = order;
		this.maxKeys = order - 1;
		this.elements = new LinkedList<T>();
		this.children = new LinkedList<BNode<T>>();
	}

	@Override
	public String toString() {
		return this.elements.toString();
	}

	@Override
	public boolean equals(Object obj) {
		boolean resp = false;
		if (obj != null) {
			if (obj instanceof BNode) {
				if (this.size() == ((BNode<T>) obj).size()) {
					resp = true;
					int i = 0;
					while (i < this.size() && resp) {
						resp = resp && this.getElementAt(i).equals(((BNode<T>) obj).getElementAt(i));
						i++;
					}
				}
			}
		}
		return resp;
	}

	public boolean isEmpty() {
		return this.size() == 0;
	}

	public int size() {
		return this.elements.size();
	}

	public boolean isLeaf() {
		return this.children.size() == 0;
	}

	public boolean isFull() {
		return this.size() == maxKeys;
	}

	public void addElement(T element) {
		this.elements.add(element);
		Collections.sort(elements);
	}

	public void removeElement(T element) {
		this.elements.remove(element);
	}

	public void removeElement(int position) {
		this.elements.remove(position);
	}

	public void addChild(int position, BNode<T> child) {
		this.children.add(position, child);
		child.parent = this;
	}

	public void removeChild(BNode<T> child) {
		this.children.remove(child);
	}

	public int indexOfChild(BNode<T> child) {
		return this.children.indexOf(child);
	}

	public T getElementAt(int index) {
		return this.elements.get(index);
	}

	protected void split() {

		T mediana = this.elements.get(this.elements.size() / 2);

		BNode<T> left = new BNode<T>(maxChildren);
		BNode<T> right = new BNode<T>(maxChildren);

		adicionaElementos(mediana, left, right);

		if (this.parent == null && this.isLeaf()) {

			setElements(new LinkedList<T>());

			addElement(mediana);

			addChild(0, left);
			addChild(1, right);
		}

		else if (this.parent == null && !this.isLeaf()) {
			LinkedList<BNode<T>> children = this.children;

			setElements(new LinkedList<T>());

			addElement(mediana);

			setChildren(new LinkedList<BNode<T>>());

			addChild(0, left);
			addChild(1, right);

			remakeChildren(children, left, 0, left.size() + 1);
			remakeChildren(children, right, right.size() + 1, children.size());
		} else if (this.isLeaf()) {
			BNode<T> aux = new BNode<T>(maxChildren);

			aux.elements.add(mediana);
			aux.parent = parent;

			left.parent = parent;
			right.parent = parent;

			int posicao = positionParent(aux.parent.getElements(), mediana);

			int leftPosition = posicao;
			int rightPosition = posicao + 1;

			parent.children.set(leftPosition, left);
			parent.children.add(rightPosition, right);

			aux.promote();
		} else {
			LinkedList<BNode<T>> children = this.children;

			BNode<T> aux = new BNode<>(maxChildren);

			aux.elements.add(mediana);
			aux.parent = parent;

			left.parent = parent;
			right.parent = parent;

			int posicao = positionParent(aux.getElements(), mediana);

			int leftPosition = posicao;
			int rightPosition = posicao + 1;

			parent.children.add(leftPosition, left);
			parent.children.add(rightPosition, right);

		}

	}

	private void adicionaElementos(T mediana, BNode<T> left, BNode<T> right) {

		int indice = 0;

		while (indice < getElements().size()) {

			if (mediana.compareTo(getElementAt(indice)) < 0) {

				left.addElement(getElementAt(indice));
			}
			if (mediana.compareTo(getElementAt(indice)) > 0) {

				right.addElement(getElementAt(indice));
			}
			indice++;
		}

	}

	protected void promote() {

		int posicao = positionParent(this.parent.getElements(), getElementAt(0));

		this.parent.getElements().add(posicao, getElementAt(0));

		if (this.parent.size() > this.maxKeys) {

			this.parent.split();
		}
	}

	private int positionParent(LinkedList<T> lista, T mediana) {
		int indice = 0;

		while (indice < lista.size()) {

			if (lista.get(indice).compareTo(mediana) > 0) {

				return indice;
			}
			indice++;
		}
		return lista.size();
	}

	private void remakeChildren(LinkedList<BNode<T>> children, BNode<T> parent, int inicio, int fim) {

		for (int i = inicio; i < fim; i++) {
			int position = positionParent(parent.getElements(), children.get(i).elements.get(0));
			parent.addChild(position, children.get(i));
		}
	}

	public LinkedList<T> getElements() {
		return elements;
	}

	public void setElements(LinkedList<T> elements) {
		this.elements = elements;
	}

	public LinkedList<BNode<T>> getChildren() {
		return children;
	}

	public void setChildren(LinkedList<BNode<T>> children) {
		this.children = children;
	}

	public BNode<T> copy() {
		BNode<T> result = new BNode<T>(maxChildren);
		result.parent = parent;
		for (int i = 0; i < this.elements.size(); i++) {
			result.addElement(this.elements.get(i));
		}
		for (int i = 0; i < this.children.size(); i++) {
			result.addChild(i, ((BNode<T>) this.children.get(i)).copy());
		}

		return result;
	}

	public BNode<T> getParent() {
		return parent;
	}

	public void setParent(BNode<T> parent) {
		this.parent = parent;
	}

	public int getMaxKeys() {
		return maxKeys;
	}

	public void setMaxKeys(int maxKeys) {
		this.maxKeys = maxKeys;
	}

	public int getMaxChildren() {
		return maxChildren;
	}

	public void setMaxChildren(int maxChildren) {
		this.maxChildren = maxChildren;
	}

}
