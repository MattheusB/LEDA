package adt.btree;

import java.util.ArrayList;
import java.util.List;

public class BTreeImpl<T extends Comparable<T>> implements BTree<T> {

	protected BNode<T> root;
	protected int order;

	public BTreeImpl(int order) {
		this.order = order;
		this.root = new BNode<T>(order);
	}

	@Override
	public BNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return this.root.isEmpty();
	}

	@Override
	public int height() {
		return height(this.root);
	}

	private int height(BNode<T> node) {
		if (node == null || node.isEmpty()) {
			return 0;
		} else if (node.isLeaf()) {
			return 1;
		} else {
			return 1 + height(node.children.getFirst());
		}
	}

	@Override
	public BNode<T>[] depthLeftOrder() {

		BNode<T>[] array;
		List<BNode<T>> lista = new ArrayList<BNode<T>>();

		depthLeftOrderAuxMethod(lista, this.root);

		array = new BNode[lista.size()];

		return lista.toArray(array);
	}

	private void depthLeftOrderAuxMethod(List<BNode<T>> list, BNode<T> node) {

		if (!node.isEmpty()) {

			list.add(node);
			for (BNode<T> child : node.getChildren()) {
				depthLeftOrderAuxMethod(list, child);
			}

		}

	}

	@Override
	public int size() {
		return size(this.root);
	}

	protected int size(BNode<T> node) {
		int tamanho = 0;
		if (!node.isEmpty()) {
			tamanho = tamanho + node.size();
			for (BNode<T> aux : node.getChildren()) {
				tamanho = tamanho + size(aux);
			}
		}
		return tamanho;
	}

	@Override
	public BNodePosition<T> search(T element) {
		return search(element, this.root);
	}

	protected BNodePosition<T> search(T element, BNode<T> node) {
		int indice = 0;
		while (indice < node.size() && node.getElementAt(indice).compareTo(element) < 0) {
			indice++;
		}
		if (indice < node.size() && node.getElementAt(indice).compareTo(element) == 0) {
			return new BNodePosition<T>(node, indice);
		} else if (node.isLeaf()) {
			return new BNodePosition<T>();
		} else {
			return search(element, node.getChildren().get(indice));
		}
	}

	@Override
	public void insert(T element) {
		insertAuxMethod(this.root, element);

	}

	private void insertAuxMethod(BNode<T> node, T element) {

		if (node.isLeaf()) {
			node.addElement(element);
			if (node.elements.size() > node.maxKeys) {
				node.split();
			}

		} else {

			int position = searchPositionInParent(node.getElements(), element);

			insertAuxMethod(node.getChildren().get(position), element);
		}
	}

	private int searchPositionInParent(List<T> list, T mediana) {
		int i = 0;
		while (i < list.size()) {
			if (list.get(i).compareTo(mediana) > 0) {
				return i;
			}
			i++;
		}
		return list.size();
	}

	private void split(BNode<T> node) {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	private void promote(BNode<T> node) {
		// TODO Implement your code here
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	// NAO PRECISA IMPLEMENTAR OS METODOS ABAIXO
	@Override
	public BNode<T> maximum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public BNode<T> minimum(BNode<T> node) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

	@Override
	public void remove(T element) {
		// NAO PRECISA IMPLEMENTAR
		throw new UnsupportedOperationException("Not Implemented yet!");
	}

}