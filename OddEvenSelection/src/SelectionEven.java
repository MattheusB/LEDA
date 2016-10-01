import java.util.Arrays;

public class SelectionEven {
	public static void main(String[] args) {
		int[] array = new int[] { 1, 6, -1, 8, 13, 56, 3, -8, -15 };
		sort(array);
		System.out.println(Arrays.toString(array));

	}

	public static void sort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int key = i;
			for (int j = i; j < array.length; j++) {
				if (array[j] < array[key]) {
					key = j;
				}
			}
			swap(array, key, i);
		}
	}

	public static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}
