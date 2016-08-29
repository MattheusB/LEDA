
   package adt.hashtable.open;
   
   import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
   import adt.hashtable.hashfunction.HashFunctionOpenAddress;
   import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;
   
   public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
   		extends AbstractHashtableOpenAddress<T> {
  
  	public HashtableOpenAddressQuadraticProbingImpl(int size,
 			HashFunctionClosedAddressMethod method, int c1, int c2) {
  		super(size);
  		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
  		this.initiateInternalTable(size);
  	}
  
  	@Override
  	public void insert(T element) {
  		if (element != null) {
  			if (isFull()) {
  				throw new HashtableOverflowException();
  			}
  
  			int prob = 0;
  			int indice = ((HashFunctionOpenAddress<T>) hashFunction).hash(element, prob);
  
  			while (prob != this.table.length && (search(element) == null)) {
  				if (table[indice] == null || table[indice].equals(deletedElement)) {
  					table[indice] = element;
  					elements++;
  				} else {
  					prob++;
  					indice = ((HashFunctionOpenAddress<T>) hashFunction).hash(element, prob);
  					COLLISIONS++;
  				}
  			}
  		}
  	}
  	@Override
  	public void remove(T element) {
  		if(element != null) {
  			if(search(element) != null) {
  				table[indexOf(element)] = new DELETED();
  				elements--;
  			}
  		}
  	}
  
  	@Override
  	public T search(T element) {
  		if(element != null) {
  			if(indexOf(element) != -1) {
  				return element;
  			}
  		}
  		return null;
  	}
  
  	@Override
  	public int indexOf(T element) {
  		if(element != null) {
  			
  			int prob = 0;
  			int indice = ((HashFunctionOpenAddress<T>) hashFunction).hash(element, prob);
  			
  			while(prob != this.table.length) {
  				if(table[indice] == null) {
  					return -1;
  				}
  				if(table[indice].equals(element)) {
  					return indice;
  				} else {
  					prob += 1;
  					indice = ((HashFunctionOpenAddress<T>) hashFunction).hash(element, prob);
  				}
  			}
  		}
  		return -1;
  	}
  }
This page was automatically generated by Maven
