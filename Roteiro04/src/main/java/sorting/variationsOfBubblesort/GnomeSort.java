package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The implementation of the algorithm must be in-place!
 */
public class GnomeSort<T extends Comparable<T>> extends AbstractSorting<T> {
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (validacao(array, leftIndex, rightIndex)) {
			int pivot = leftIndex;
			while (pivot < rightIndex) {
				if (array[pivot].compareTo(array[pivot + 1]) > 0) {
					Util.swap(array, pivot, pivot + 1);
					if (pivot > leftIndex) {
						pivot--;
					}
				} else {
					pivot++;
				}

			}
		}

	}

	private boolean validacao(T[] array, int leftIndex, int rightIndex) {
		boolean saida = true;
		if (array == null || array.length == 0) {
			saida = false;
		} else if (leftIndex < 0) {
			saida = false;
		} else if (rightIndex > array.length) {
			saida = false;
		} else if (leftIndex > rightIndex) {
			saida = false;

		} else if (leftIndex < 0) {
			saida = false;
		} else if (!verificaElementos(array)) {
			saida = false;
		}

		return saida;
	}

	private boolean verificaElementos(T[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == null) {
				return false;
			}
		}
		return true;
	}
}
