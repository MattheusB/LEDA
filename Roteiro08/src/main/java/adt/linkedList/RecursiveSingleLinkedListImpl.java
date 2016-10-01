package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}

	public RecursiveSingleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return this.data == null;
	}

	@Override
	public int size() {
		if (isEmpty()) {
			return 0;
		} else if (this.next == null) {
			return 1;
		} else {
			return 1 + this.next.size();
		}

	}

	@Override
	public T search(T element) {
		if (element != null) {
			if (isEmpty()) {
				return null;
			} else if (this.data.equals(element)) {
				return this.data;
			} else if (this.next == null) {
				return null;
			} else {
				return this.next.search(element);
			}
		}
		return null;

	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
				this.data = element;
				this.next = new RecursiveSingleLinkedListImpl<>();
			} else if (this.next == null) {
				RecursiveSingleLinkedListImpl<T> aux = new RecursiveSingleLinkedListImpl<>(element, null);
				this.next = aux;
			} else {
				this.next.insert(element);

			}
		}

	}

	@Override
	public void remove(T element) {
		if (element != null && !isEmpty()) {
			if (this.next == null && this.data.equals(element)) {
				this.data = null;
			} else if (this.data.equals(element)) {
				this.data = this.next.data;
				this.next = next.next;
			} else if (this.next != null) {
				this.next.remove(element);
			}
		}

	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Object[this.size()];
		if (isEmpty()) {
			return array;
		} else {
			int indice = 0;
			return toArray(indice, array, this);
		}

	}

	private T[] toArray(int indice, T[] array, RecursiveSingleLinkedListImpl<T> element) {
		if (!element.isEmpty()) {
			if (indice < array.length) {
				array[indice] = element.data;
				return toArray(indice + 1, array, element.next);

			}
		}
		return array;

	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
