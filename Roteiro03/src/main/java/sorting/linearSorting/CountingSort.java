package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array.length <= leftIndex || array == null) {
			return;
		}
		int maior = maiorElemento(array, leftIndex, rightIndex);
		Integer[] aux = new Integer[maior + 1];

		// Transforma elementos nulls em 0
		for (int o = leftIndex; o < aux.length; o++) {
			aux[o] = 0;
		}

		// Contagem
		for (int j = leftIndex; j <= rightIndex; j++) {
			aux[array[j]]++;
		}

		// Acumulando
		for (int k = leftIndex + 1; k < aux.length; k++) {
			aux[k] += aux[k - 1];
		}

		// Ordenando
		Integer[] ordenado = new Integer[array.length];
		for (int m = rightIndex; m >= leftIndex; m--) {
			aux[array[m]]--;
			ordenado[aux[array[m]]] = array[m];

		}

		// Copiando
		for (int n = leftIndex; n <= rightIndex; n++) {
			array[n] = ordenado[n];
		}
	}

	private int maiorElemento(Integer[] array, int leftIndex, int rightIndex) {
		int maior = 0;

		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i] > maior) {
				maior = array[i];
			}
		}

		return maior;
	}

}
