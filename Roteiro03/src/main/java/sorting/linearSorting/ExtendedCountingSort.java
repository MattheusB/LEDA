package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos: - Alocar o tamanho minimo
 * possivel para o array de contadores (C) - Ser capaz de ordenar arrays
 * contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array.length <= leftIndex || array == null) {
			return;
		}
		int maior = maiorElemento(array, leftIndex, rightIndex);
		int menor = menorElemento(array, leftIndex, rightIndex);

		if (menor < 0) {
			menor = menor * -1;
		}

		Integer[] aux = new Integer[maior - menor + 1];

		// Transforma null em 0
		for (int i = leftIndex; i < aux.length; i++) {
			aux[i] = 0;
		}

		// Contagem
		for (int j = leftIndex; j <= rightIndex; j++) {
			aux[array[j] - menor]++;
		}

		// Acumulando
		for (int k = leftIndex + 1; k < aux.length; k++) {
			aux[k] += aux[k - 1];
		}

		// Ordenando
		Integer[] ordenado = new Integer[array.length];
		for (int m = rightIndex; m >= leftIndex; m--) {
			aux[array[m] - menor]--;
			ordenado[aux[array[m] - menor]] = array[m];

		}

		// Copiando
		for (int n = leftIndex; n <= rightIndex; n++) {
			array[n] = ordenado[n];
		}

	}

	public int maiorElemento(Integer[] array, int leftIndex, int rightIndex) {
		int maior = array[0];
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i] > maior) {
				maior = array[i];
			}
		}
		return maior;
	}

	public int menorElemento(Integer[] array, int leftIndex, int rightIndex) {
		int menor = array[0];
		for (int i = leftIndex; i <= rightIndex; i++) {
			if (array[i] < menor) {
				menor = array[i];
			}
		}

		return menor;
	}

}
