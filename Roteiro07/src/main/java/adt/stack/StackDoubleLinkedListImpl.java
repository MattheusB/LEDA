package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> list;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {

		if (!this.isFull()) {
			if (element != null) {
				this.list.insertFirst(element);
			}

		} else {

			throw new StackOverflowException();
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (!isEmpty()) {
			T element = ((DoubleLinkedListImpl<T>) list).getHead().getData();
			list.removeFirst();
			return element;
		} else {
			throw new StackUnderflowException();
		}

	}

	@Override
	public T top() {
		return ((DoubleLinkedListImpl<T>) list).getHead().getData();

	}

	@Override
	public boolean isEmpty() {
		if (list.size() == 0) {
			return true;
		}
		return false;

	}

	@Override
	public boolean isFull() {
		if (list.size() == size) {
			return true;
		}
		return false;

	}

}
