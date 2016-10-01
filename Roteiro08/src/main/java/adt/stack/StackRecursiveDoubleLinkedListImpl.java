package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> list;
	protected int size;

	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new RecursiveDoubleLinkedListImpl<>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (element != null) {
			if (list.size() < size) {
				list.insert(element);
			} else {
				throw new StackOverflowException();
			}

		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (list.size() > 0) {
			T aux = top();
			list.removeLast();
			return aux;

		} else {
			throw new StackUnderflowException();
		}

	}

	@Override
	public T top() {
		if (isEmpty()) {
			return null;
		} else {
			T[] array = list.toArray();
			return array[list.size() - 1];

		}

	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.size == list.size();
	}

}
