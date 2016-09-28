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
		if (node != null || !node.isEmpty()) {
			if (node.isLeaf()) {
				return 1;
			} else {
				return 1 + height(node.children.getFirst());
			}
		}
		return 0;
	}

	@Override
	public BNode<T>[] depthLeftOrder() {

		BNode<T>[] array;
		List<BNode<T>> lista = new ArrayList<BNode<T>>();

		depthLeftOrder(lista, this.root);

		array = new BNode[lista.size()];

		return lista.toArray(array);
	}

	private void depthLeftOrder(List<BNode<T>> lista, BNode<T> node) {

		if (!node.isEmpty()) {

			lista.add(node);
			for (BNode<T> aux : node.getChildren()) {
				depthLeftOrder(lista, aux);
			}

		}

	}

	@Override
	public int size() {
		return size(this.root);
	}

	public int size(BNode<T> node) {
		int tamanho = 0;
		if (!node.isEmpty()) {
			tamanho += node.size();
			for (int i = 0; i < node.getChildren().size(); i++) {
				tamanho += size(node.getChildren().get(i));
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
		}
		if (node.isLeaf()) {
			return new BNodePosition<T>();
		}
		return search(element, node.getChildren().get(indice));
	}

	@Override
	public void insert(T element) {
		BNode<T> node = encontraFolha(element, this.root);
		node.addElement(element);

		while (node.getParent() != null) {
			if (node.isFull()) {
				split(node);
				promote(node);
			}

			node = node.getParent();
		}
		if (node.isFull()) {
			BNode<T> aux = new BNode<>(node.getMaxChildren());

			aux.addChild(0, node);
			node.setParent(aux);

			this.root = aux;

			split(node);
			promote(node);
		}
	}

	private BNode<T> encontraFolha(T element, BNode<T> node) {
		int indice = 0;
		while ((indice < node.size()) && (node.getElementAt(indice).compareTo(element) < 0)) {
			indice++;
		}
		if (node.isLeaf()) {
			return node;
		}
		return encontraFolha(element, node.getChildren().get(indice));
	}

	private void split(BNode<T> node) {
		node.split();
	}

	private void promote(BNode<T> node) {
		node.promote();

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