package sorting.variationsOfSelectionsort;

import java.util.Arrays;

import sorting.AbstractSorting;
import util.Util;

/**
 * This algorithm simulates a logical partitioning of the input array by
 * considering different indexing, that is, the first sub-array is indexed by
 * even elements and the second sub-array is indexed by odd elements. Then, it
 * applies a complete selectionsort in the first sub-array considering
 * neighbours (even). After that, it applies a complete selectionsort in the
 * second sub-array considering neighbours (odd). After that, the algorithm
 * performs a merge between elements indexed by even and odd numbers.
 */
public class OddEvenSelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (validacao(array, leftIndex, rightIndex)) {
			OddEvenSelection(array, leftIndex, rightIndex);
			mergeSort(array, leftIndex, rightIndex);

		}

	}

	private void OddEvenSelection(T[] array, int leftIndex, int rightIndex) {
		for (int i = leftIndex; i <= rightIndex; i++) {
			int menor = i;
			for (int j = i; j < array.length; j += 2) {
				if (array[j].compareTo(array[menor]) < 0) {
					menor = j;
				}
			}
			Util.swap(array, i, menor);
		}

	}

	private void mergeSort(T[] array, int leftIndex, int rightIndex) {
		T[] aux = Arrays.copyOf(array, array.length);
		int i = leftIndex;
		int j = leftIndex + 1;
		int k = leftIndex;

		while (i <= rightIndex && j <= rightIndex) {
			if (aux[i].compareTo(aux[j]) < 0) {
				array[k] = aux[i];
				i += 2;
			} else {
				array[k] = aux[j];
				j += 2;
			}
			k++;
		}

		while (i <= rightIndex) {
			array[k] = aux[i];
			k++;
			i += 2;
		}

		while (j <= rightIndex) {
			array[k] = aux[j];
			k++;
			j += 2;
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
