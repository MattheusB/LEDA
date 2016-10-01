package sorting.variationsOfBubblesort;

import java.util.Arrays;

import sorting.AbstractSorting;
import util.Util;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (validacao(array, leftIndex, rightIndex)) {
			final double fator = 1.25;
			int gap = (int) ((rightIndex - leftIndex + 1)/fator);
			boolean trocou = true;
			while (gap > 1 || trocou) {
				if (gap > 1) {
					gap = (int) (gap / fator);
				}

				trocou = false;

				for (int j = leftIndex; gap + j <= rightIndex; j++) {
					if (array[j].compareTo(array[gap + j]) > 0) {
						Util.swap(array, j, gap + j);
						trocou = true;
					}

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