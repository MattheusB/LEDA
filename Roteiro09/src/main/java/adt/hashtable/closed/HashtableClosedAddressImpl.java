package adt.hashtable.closed;
  
   import util.Util;
   import adt.hashtable.hashfunction.HashFunction;
   import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
   import adt.hashtable.hashfunction.HashFunctionFactory;
   import adt.hashtable.hashfunction.HashFunctionClosedAddress;
   import java.util.LinkedList;
  
  public class HashtableClosedAddressImpl<T> extends AbstractHashtableClosedAddress<T> {
  
  	/**
  	 * A hash table with closed address works with a hash function with closed
  	 * address. Such a function can follow one of these methods: DIVISION or
  	 * MULTIPLICATION. In the DIVISION method, it is useful to change the size
  	 * of the table to an integer that is prime. This can be achieved by
  	 * producing such a prime number that is bigger and close to the desired
  	 * size.
  	 * 
 	 * For doing that, you have auxiliary methods: Util.isPrime and
  	 * getPrimeAbove as documented bellow.
  	 * 
  	 * The length of the internal table must be the immediate prime number
  	 * greater than the given size. For example, if size=10 then the length must
  	 * be 11. If size=20, the length must be 23. You must implement this idea in
  	 * the auxiliary method getPrimeAbove(int size) and use it.
  	 * 
  	 * @param desiredSize
  	 * @param method
  	 */
  
  	@SuppressWarnings({ "rawtypes", "unchecked" })
  	public HashtableClosedAddressImpl(int desiredSize, HashFunctionClosedAddressMethod method) {
  		int realSize = desiredSize;
 
  		if (method == HashFunctionClosedAddressMethod.DIVISION) {
  			realSize = this.getPrimeAbove(desiredSize); // real size must the
  														// the immediate prime
  														// above
  		}
  		initiateInternalTable(realSize);
  		HashFunction function = HashFunctionFactory.createHashFunction(method, realSize);
  		this.hashFunction = function;
  	}
  
  	// AUXILIARY
  	/**
  	 * It returns the prime number that is closest (and greater) to the given
  	 * number. You can use the method Util.isPrime to check if a number is
  	 * prime.
  	 */
  	int getPrimeAbove(int number) {
  
  		int answer = number;
  		while (!Util.isPrime(answer)) {
  			answer += 1;
  		}
  		return answer;
  	}
  
  	@SuppressWarnings("unchecked")
  	@Override
 	public void insert(T element) {
  		if (element != null && search(element) == null) {
  
  			int hashKey = ((HashFunctionClosedAddress<T>) hashFunction).hash(element);
  
  			if (table[hashKey] == null) {
  				LinkedList<T> array = new LinkedList<>();
  				table[hashKey] = array;
  			}
  
  			if (!((LinkedList<T>) table[hashKey]).isEmpty()) {
  				COLLISIONS++;
  			}
  
  			((LinkedList<T>) table[hashKey]).add(element);
  			elements++;
  		}
  	}
  
  	@SuppressWarnings("unchecked")
  	@Override
  	public void remove(T element) {
  
  		if (element != null && search(element) != null) {
  			int hashKey = ((HashFunctionClosedAddress<T>) hashFunction).hash(element);
  			((LinkedList<T>) table[hashKey]).remove(element);
  			elements--;
  
  		}
  	}
  
  	@SuppressWarnings("unchecked")
  	@Override
  	public T search(T element) {
  
  		T answer = null;
  		if (element != null) {
 
 			int hashKey = ((HashFunctionClosedAddress<T>) hashFunction).hash(element);
 			if (table[hashKey] != null && ((LinkedList<T>) table[hashKey]).contains(element)) {
 				answer = element;
 			}
 
 		}
 		return answer;
 	}
 
 	@SuppressWarnings("unchecked")
 	@Override
 	public int indexOf(T element) {
 		int hash = -1;
 		if (element != null) {
 
 			int hashKey = ((HashFunctionClosedAddress<T>) hashFunction).hash(element);
			if (table[hashKey] != null && ((LinkedList<T>) table[hashKey]).contains(element)) {
 				hash = hashKey;
			}

		}
 		return hash;
 	}
 
 }
