package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			if (isEmpty()) {
				this.data = element;
			} else if (this.previous == null && this.next == null) {
				RecursiveDoubleLinkedListImpl<T> aux = new RecursiveDoubleLinkedListImpl<>(this.data, this.next, this);
				this.data = element;
				this.next = aux;

			} else {
				RecursiveDoubleLinkedListImpl<T> aux = new RecursiveDoubleLinkedListImpl<>(this.data, this.next, this);
				this.data = element;
				this.next = aux;
				((RecursiveDoubleLinkedListImpl<T>) this.next.next).previous = (RecursiveDoubleLinkedListImpl<T>) this.next;
			}
		}

	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			if (this.next == null) {
				this.data = null;
			} else {
				this.data = this.next.data;
				this.next = next.next;
				if (this.next != null) {
					((RecursiveDoubleLinkedListImpl<T>) this.next).previous = this;
				}

			}
		}

	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			if (this.next == null) {
				if (this.previous == null) {
					this.data = null;
				} else {
					this.previous.next = null;
				}
			} else {
				((RecursiveDoubleLinkedListImpl<T>) this.next).removeLast();
			}
		}

	}

	@Override
	public void remove(T element) {
		if (element != null && !isEmpty()) {
			if (this.data.equals(element)) {
				if (this.next == null) {
					this.data = null;
				} else {
					this.data = this.next.data;
					this.next = next.next;
				}

			} else if (this.next != null) {
				this.next.remove(element);
			}
		}

	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
				this.data = element;
			} else if (this.next == null) {
				RecursiveDoubleLinkedListImpl<T> aux = new RecursiveDoubleLinkedListImpl<>(element, null, this);
				this.next = aux;
			} else {
				this.next.insert(element);

			}
		}

	}

}