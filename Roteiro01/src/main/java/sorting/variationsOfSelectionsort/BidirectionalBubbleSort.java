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
public class BidirectionalBubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		for (int i = leftIndex; i < rightIndex; i++) {
			for (int j = i; j < rightIndex; j++) {
				if (array[j].compareTo(array[j + 1]) > 0) {
					Util.swap(array, j, j + 1);
				}
			}

			for (int j = rightIndex; j > leftIndex; j--) {
				if (array[j - 1].compareTo(array[j]) > 0) {
					Util.swap(array, j - 1, j);
				}
			}
		}
	}
}