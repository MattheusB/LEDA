package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		try {
			stack1.push(element);
		} catch (StackOverflowException e) {
			e.printStackTrace();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException();
		} else {
			T valor = null;
			while (!stack1.isEmpty()) {

				// Invertendo os elementos da stack1 no stack2
				try {
					stack2.push(stack1.pop());
				} catch (StackOverflowException | StackUnderflowException e) {
					e.printStackTrace();
				}
			}

			// Retirando o elemento da stack2
			try {
				valor = stack2.pop();
			} catch (StackUnderflowException e) {
				e.printStackTrace();
			}
			while (!stack2.isEmpty()) {

				// Passando os elementos da stak2 para a stack1
				try {
					stack1.push(stack2.pop());
				} catch (StackOverflowException | StackUnderflowException e) {
					e.printStackTrace();
				}
			}
			return valor;
		}

	}

	@Override
	public T head() {
		if (isEmpty()) {
			return null;
		} else {
			while (!stack1.isEmpty()) {
				try {
					stack2.push(stack1.pop());
				} catch (StackOverflowException e) {
					e.printStackTrace();
				} catch (StackUnderflowException e) {
					e.printStackTrace();
				}
			}
			return stack2.top();
		}

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
