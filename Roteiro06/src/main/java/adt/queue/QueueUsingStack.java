package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		if (size < 0) {
			size = 0;
		}
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (element != null) {
			if (isFull()) {
				throw new QueueOverflowException();
			} else {
				try {
					stack1.push(element);
				} catch (StackOverflowException e) {
					e.printStackTrace();
				}
			}

		}

	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		} else {

			// Invertendo os elementos da stack1 no stack2
			try {
				while (!stack1.isEmpty()) {
					stack2.push(stack1.pop());
				}
			} catch (StackOverflowException | StackUnderflowException e) {
				e.printStackTrace();
			}
		}
		T valor = null;
		// Retirando o elemento da stack2
		try {
			valor = stack2.pop();
		} catch (StackUnderflowException e) {
			e.printStackTrace();
		}

		// Passando os elementos da stak2 para a stack1
		try {
			while (!stack2.isEmpty()) {
				stack1.push(stack2.pop());
			}
		} catch (StackOverflowException | StackUnderflowException e) {
			e.printStackTrace();
		}
		return valor;

	}

	@Override
	public T head() {
		if (isEmpty()) {
			return null;
		} else {

			try {
				while (!stack1.isEmpty()) {
					stack2.push(stack1.pop());
				}
			} catch (StackOverflowException | StackUnderflowException e) {
				e.printStackTrace();
			}
		}
		T valor = stack2.top();

		try {
			while (!stack2.isEmpty()) {
				stack1.push(stack2.pop());
			}
		} catch (StackOverflowException | StackUnderflowException e) {
			e.printStackTrace();
		}
		return valor;

	}

	@Override
	public boolean isEmpty() {
		if (stack1.isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isFull() {
		if (stack1.isFull()) {
			return true;
		}
		return false;

	}

}
