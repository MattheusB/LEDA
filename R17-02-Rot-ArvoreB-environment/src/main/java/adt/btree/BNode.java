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

		T middler = elements.get(elements.size() / 2);

		int position;
		int leftPosition;
		int rightPosition;

		BNode<T> largest = new BNode<T>(maxChildren);
		BNode<T> smallest = new BNode<T>(maxChildren);

		LinkedList<BNode<T>> children = new LinkedList<BNode<T>>();

		saveSomeElements(middler, largest, smallest);

		if (parent == null && isLeaf()) {

			setElements(new LinkedList<T>());

			addElement(middler);

			addChild(0, smallest);
			addChild(1, largest);
		}

		else if (parent == null && !this.isLeaf()) {
			children = this.children;

			setElements(new LinkedList<T>());

			addElement(middler);

			setChildren(new LinkedList<BNode<T>>());

			addChild(0, smallest);
			addChild(1, largest);

			sortOutChildren(children, smallest, 0, smallest.size() + 1);
			sortOutChildren(children, largest, largest.size() + 1, children.size());
		}

		else if (this.isLeaf()) {
			BNode<T> toPromove = new BNode<T>(maxChildren);

			toPromove.elements.add(middler);
			toPromove.parent = parent;

			smallest.parent = parent;
			largest.parent = parent;

			position = getPosInParent(toPromove.parent.getElements(), middler);

			leftPosition = position;
			rightPosition = position + 1;

			parent.children.set(leftPosition, smallest);
			parent.children.add(rightPosition, largest);

			toPromove.promote();
		} else {

			children = this.children;

			BNode<T> toPromove = new BNode<>(maxChildren);

			toPromove.elements.add(middler);
			toPromove.parent = parent;

			smallest.parent = parent;
			largest.parent = parent;

			position = getPosInParent(toPromove.getElements(), middler);

			leftPosition = position;
			rightPosition = position + 1;

			parent.children.add(leftPosition, smallest);
			parent.children.add(rightPosition, largest);

		}
	}

	protected void promote() {

		int position = getPosInParent(parent.getElements(), getElementAt(0));

		parent.getElements().add(position, getElementAt(0));

		if (parent.size() > maxKeys) {

			parent.split();
		}
	}

	private void saveSomeElements(T mediana, BNode<T> largest, BNode<T> smaller) {

		int i = 0;

		while (i < getElements().size()) {

			if (mediana.compareTo(getElementAt(i)) < 0) {

				largest.addElement(getElementAt(i));
			}
			if (mediana.compareTo(getElementAt(i)) > 0) {

				smaller.addElement(getElementAt(i));
			}
			i++;
		}
	}

	private int getPosInParent(LinkedList<T> list, T mediana) {
		int i = 0;
		while (i < list.size()) {

			if (list.get(i).compareTo(mediana) > 0) {

				return i;
			}
			i++;
		}
		return list.size();
	}

	private void sortOutChildren(LinkedList<BNode<T>> children, BNode<T> parent, int first, int last) {

		int position;
		int i = first;

		while (i < last) {

			position = getPosInParent(parent.getElements(), children.get(i).elements.get(0));

			parent.addChild(position, children.get(i));

			i++;
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
