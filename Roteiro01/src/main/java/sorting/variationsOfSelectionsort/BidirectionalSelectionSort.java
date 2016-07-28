package sorting.variationsOfSelectionsort;

import sorting.AbstractSorting;
import util.Util;

/**
 * This selection sort variation has two internal iterations. In the first, it
 * takes the smallest elements from the array, and puts it in the first
 * position. In the second, the iteration is done backwards, that is, from right
 * to left, and this time the biggest element is selected and stored in the last
 * position. Then it repeats the process, excluding the positions already filled
 * in, until the whole array is ordered.
 */
public class BidirectionalSelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		for (int i = leftIndex; i < rightIndex; i++) {
			int menorIndice = i;
			for (int j = menorIndice + 1; j <= rightIndex; j++) {
				if (array[menorIndice].compareTo(array[j]) > 0) {
					menorIndice = j;

				}
			}

			if (menorIndice != i) {
				Util.swap(array, menorIndice, i);
			}
			int maiorIndice = i;
			for (int j = maiorIndice - 1; j >= leftIndex; j--) {
				if (array[j].compareTo(array[maiorIndice]) > 0) {
					maiorIndice = j;

				}
			}

			if (maiorIndice != i) {
				Util.swap(array, maiorIndice, i);
			}
		}
	}
}
