package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		if (size < 0) {
			size = 0;
		}
		array = (T[]) new Object[size];
		top = -1;
	}

	@Override
	public T top() {
		if (isEmpty()) {
			return null;
		}
		return array[top];
	}

	@Override
	public boolean isEmpty() {
		if (this.top == -1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isFull() {
		if (top == array.length - 1) {
			return true;
		}
		return false;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (isFull()) {
			throw new StackOverflowException();
		}

		else if (isEmpty()) {
			this.top = 0;
			this.array[top] = element;
		} else {
			top++;
			this.array[top] = element;
		}

	}

	@Override
	public T pop() throws StackUnderflowException {

		if (isEmpty()) {
			throw new StackUnderflowException();
		} else {
			T valor = array[top];
			top--;
			return valor;
		}

	}

}
