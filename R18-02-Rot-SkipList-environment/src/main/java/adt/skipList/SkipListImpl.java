package adt.skipList;

public class SkipListImpl<T> implements SkipList<T> {

	protected SkipListNode<T> root;
	protected SkipListNode<T> NIL;

	protected int height;
	protected int maxHeight;

	protected boolean USE_MAX_HEIGHT_AS_HEIGHT;
	protected double PROBABILITY = 0.5;

	public SkipListImpl(int maxHeight) {
		if (USE_MAX_HEIGHT_AS_HEIGHT) {
			this.height = maxHeight;
		} else {
			this.height = 1;
		}
		this.maxHeight = maxHeight;
		root = new SkipListNode(Integer.MIN_VALUE, maxHeight, null);
		NIL = new SkipListNode(Integer.MAX_VALUE, maxHeight, null);
		connectRootToNil();
	}

	/**
	 * Faz a ligacao inicial entre os apontadores forward do ROOT e o NIL Caso
	 * esteja-se usando o level do ROOT igual ao maxLevel esse metodo deve
	 * conectar todos os forward. Senao o ROOT eh inicializado com level=1 e o
	 * metodo deve conectar apenas o forward[0].
	 */
	private void connectRootToNil() {
		if (this.USE_MAX_HEIGHT_AS_HEIGHT) {
			for (int i = 0; i < this.maxHeight; i++) {
				this.root.forward[i] = this.NIL;
			}
		} else {
			this.root.forward[0] = this.NIL;

		}
	}

	private void connectRootToNil(int height) {
		for (int i = this.height; i < height; i++) {
			root.forward[i] = NIL;
		}
	}

	/**
	 * Metodo que gera uma altura aleatoria para ser atribuida a um novo no no
	 * metodo insert(int,V)
	 */
	private int randomLevel() {
		int randomLevel = 1;
		double random = Math.random();
		while (Math.random() <= PROBABILITY && randomLevel < maxHeight) {
			randomLevel = randomLevel + 1;
		}
		return randomLevel;
	}

	@Override
	public void insert(int key, T newValue, int height) {
		if (newValue != null && height > 0 && height <= this.maxHeight) {

			if (USE_MAX_HEIGHT_AS_HEIGHT == false) {
				connectRootToNil(height);
			}

			SkipListNode<T> aux = this.root;
			SkipListNode<T>[] update = new SkipListNode[this.maxHeight];

			// Pesquisa
			for (int i = height - 1; i >= 0; i--) {
				while (key > aux.getForward(i).key) {
					aux = aux.getForward(i);
				}

				update[i] = aux;
			}

			aux = aux.forward[0];

			// Adicao
			if (aux.getKey() == key) {
				aux.value = newValue;
			} else {
				aux = new SkipListNode<T>(key, height, newValue);

				for (int i = 0; i < height; i++) {
					aux.forward[i] = update[i].forward[i];
					update[i].forward[i] = aux;
				}

			}

			this.height = height();

		}

	}

	@Override
	public void remove(int key) {
		if (key != this.root.key && key != this.NIL.key) {
			SkipListNode<T> aux = this.root;
			SkipListNode<T>[] update = new SkipListNode[this.height];

			// Pesquisa
			for (int i = height - 1; i >= 0; i--) {
				while (key > aux.getForward(i).key) {
					aux = aux.getForward(i);
				}
				update[i] = aux;

			}

			aux = aux.forward[0];

			// remocao
			if (aux.key == key) {
				for (int i = 0; i < height; i++) {
					if (!update[i].forward[i].equals(aux)) {
						return;
					}
					update[i].forward[i] = aux.forward[i];

				}

				this.height = height();

				if (USE_MAX_HEIGHT_AS_HEIGHT == false) {

					for (int i = height; i < this.maxHeight; i++) {

						root.forward[i] = null;

						if (root.forward[0] == null) {
							connectRootToNil();
						}
					}
				}
			}
		}

	}

	@Override
	public int height() {
		int height = maxHeight - 1;

		while (height > -1
				&& (this.root.forward[height] == null || this.root.forward[height].key == Integer.MAX_VALUE)) {
			--height;
		}

		return ++height;

	}

	@Override
	public SkipListNode<T> search(int key) {
		if (this.root.key == key) {
			return this.root;
		}
		if (this.NIL.key == key) {
			return this.NIL;
		}

		SkipListNode<T> aux = this.root;

		for (int i = this.height - 1; i >= 0; i--) {
			while (key > aux.getForward(i).getKey()) {
				aux = aux.getForward(i);
			}

		}
		aux = aux.getForward(0);

		if (aux != null && aux.getKey() == key) {
			return aux;
		}

		return null;

	}

	@Override
	public int size() {
		SkipListNode<T> aux = this.root;
		int tamanho = 0;

		while (!aux.forward[0].equals(this.NIL)) {
			tamanho++;
			aux = aux.getForward(0);
		}

		return tamanho;

	}

	@Override
	public SkipListNode<T>[] toArray() {
		SkipListNode<T> aux = this.root;

		SkipListNode<T>[] array = new SkipListNode[this.size() + 2];

		for (int i = 0; i < array.length; i++) {
			array[i] = aux;
			aux = aux.getForward(0);
		}
		return array;

	}

}