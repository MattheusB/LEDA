package sorting.divideAndConquer.hybridMergesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do
 * MergeSort que pode fazer uso do InsertionSort (um algoritmo híbrido) da
 * seguinte forma: o MergeSort é aplicado a entradas maiores a um determinado
 * limite. Caso a entrada tenha tamanho menor ou igual ao limite o algoritmo usa
 * o InsertionSort.
 * 
 * A implementação híbrida deve considerar os seguintes detalhes: - Ter
 * contadores das quantidades de MergeSorts e InsertionSorts aplicados, de forma
 * que essa informação possa ser capturada pelo teste. - A cada chamado do
 * método de sort(T[] array) esses contadores são resetados. E a cada chamada
 * interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e
 * INSERTIONSORT_APPLICATIONS são incrementados. - O InsertionSort utilizado no
 * algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;

	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;

	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array.length > SIZE_LIMIT) {
			if (leftIndex < rightIndex) {
				int meio = (leftIndex + rightIndex) / 2;

				sort(array, leftIndex, meio);
				sort(array, meio + 1, rightIndex);
				mergeSort(array, leftIndex, meio, rightIndex);
			}
			MERGESORT_APPLICATIONS += 1;
		}

		else {
			insertionSort(array, leftIndex, rightIndex);
			INSERTIONSORT_APPLICATIONS += 1;

		}

	}

	private void insertionSort(T[] array, int leftIndex, int rightIndex) {
		for (int i = leftIndex; i < rightIndex; i++) {
			int j = i + 1;
			while (j > leftIndex && j <= rightIndex) {
				if (array[j].compareTo(array[j - 1]) < 0) {
					Util.swap(array, j, j - 1);
				}

				j = j - 1;
			}
		}

	}

	private void mergeSort(T[] array, int leftIndex, int meio, int rightIndex) {
		T[] auxiliar = (T[]) new Comparable[array.length];

		int i = leftIndex;
		int j = meio + 1;
		int k = leftIndex;

		for (int w = 0; w < array.length; w++) {
			auxiliar[w] = array[w];
		}

		while (i <= meio && j <= rightIndex) {
			if (auxiliar[i].compareTo(auxiliar[j]) <= 0) {
				array[k] = auxiliar[i];
				i++;

			} else {
				array[k] = auxiliar[j];
				j++;
			}

			k++;
		}

		while (i <= meio) {
			array[k] = auxiliar[i];
			i++;
			k++;
		}

		while (j <= rightIndex) {
			array[k] = auxiliar[j];
			j++;
			k++;
		}

	}
}
