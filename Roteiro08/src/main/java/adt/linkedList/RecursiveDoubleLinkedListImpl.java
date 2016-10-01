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
				this.next = new RecursiveDoubleLinkedListImpl<>();
				this.previous = new RecursiveDoubleLinkedListImpl<>();
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
			if (this.next.isEmpty()) {
				this.data = null;
				this.next = null;
				this.previous = null;
			} else {
				this.data = this.next.data;
				((RecursiveDoubleLinkedListImpl<T>) this.next.next).previous = this;
				this.next = next.next;

			}
		}

	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			if (this.next.isEmpty()) {
				if (this.previous.isEmpty()) {
					this.data = null;
					this.previous = null;
					this.next = null;

				} else {
					this.previous.next = this.next;
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
				this.next = new RecursiveDoubleLinkedListImpl<>();
				this.previous = new RecursiveDoubleLinkedListImpl<>();
			} else if (this.next == null) {
				RecursiveDoubleLinkedListImpl<T> aux = new RecursiveDoubleLinkedListImpl<>(element, null, this);
				this.next = aux;

			} else {
				this.next.insert(element);

			}
		}

	}

}
