package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		if (size < 0) {
			size = 0;
		}
		array = (T[]) new Object[size];
		tail = -1;

	}

	@Override
	public T head() {
		if (isEmpty()) {
			return null;
		} else {
			return array[0];
		}
	}

	@Override
	public boolean isEmpty() {
		if (tail == -1) {
			return true;
		}
		return false;

	}

	@Override
	public boolean isFull() {
		if (tail == array.length - 1) {
			return true;
		}
		return false;
	}

	private void shiftLeft() {
		for (int i = 0; i < tail; i++) {
			array[i] = array[i + 1];
		}

	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (element != null) {
			if (isFull()) {
				throw new QueueOverflowException();
			} else if (isEmpty()) {
				this.tail = 0;
				array[tail] = element;

			} else {
				tail++;
				array[tail] = element;
			}

		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		} else {
			T valor = array[0];
			shiftLeft();
			tail--;
			return valor;
		}

	}

}
