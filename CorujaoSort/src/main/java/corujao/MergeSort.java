package corujao;

import java.util.Arrays;

import sorting.AbstractSorting;

public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (validacao(array, leftIndex, rightIndex) && leftIndex < rightIndex) {
			int meio = ((rightIndex + leftIndex) / 2);
			sort(array, leftIndex, meio);
			sort(array, meio + 1, rightIndex);
			mergeSort(array, leftIndex, meio, rightIndex);

		}
	}

	private void mergeSort(T[] array, int leftIndex, int meio, int rightIndex) {
		int i = leftIndex;
		int j = meio + 1;
		int k = leftIndex;
		T[] aux = Arrays.copyOf(array, array.length);

		while (i <= meio && j <= rightIndex) {
			if (aux[i].compareTo(aux[j]) < 0) {
				array[k] = aux[i];
				i++;
			} else {
				array[k] = aux[j];
				j++;
			}

			k++;

		}

		while (i <= meio) {
			array[k] = aux[i];
			i++;
			k++;
		}

		while (j <= rightIndex) {
			array[k] = aux[j];
			j++;
			k++;
		}

	}

	private boolean validacao(T[] array, int leftIndex, int rightIndex) {
		boolean saida = true;
		if (array == null || array.length == 0) {
			saida = false;
		}

		else if (leftIndex < 0) {
			saida = false;
		} else if (leftIndex > rightIndex) {
			saida = false;
		} else if (rightIndex >= array.length) {
			saida = false;
		} else if (!verificaElementos(array, leftIndex, rightIndex)) {
			saida = false;
		}

		return saida;

	}

	private boolean verificaElementos(T[] array, int leftIndex, int rightIndex) {
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i] == null) {
				return false;
			}
		}
		return true;
	}

}
