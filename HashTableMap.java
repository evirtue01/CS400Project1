// --== CS400 File Header Information ==--
// Name: Ethan McKellips
// Email: emckellips@wisc.edu
// Team: Red
// Group: IG
// TA: Sid
// Lecturer: Florian
// Notes to Grader: N/A

// Imports
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This class is meant to replicate a hash table
 * 
 * @author Ethan McKellips
 *
 * @param <KeyType>   - the key that is associated with the value
 * @param <ValueType> - the value being inserted into the hash table
 */
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
	LinkedList<KeyType>[] keys;
	LinkedList<ValueType>[] values;

	/**
	 * Constructor where user enters the capacity of the hash table
	 * 
	 * @param capacity the capacity of the hash table
	 */
	public HashTableMap(int capacity) {
		keys = (LinkedList<KeyType>[]) new LinkedList[capacity];
		values = (LinkedList<ValueType>[]) new LinkedList[capacity];
		for (int i = 0; i < keys.length; i++) {
			keys[i] = new LinkedList();
			values[i] = new LinkedList();
		}
	}

	/**
	 * Default constructor
	 */
	public HashTableMap() {
		keys = (LinkedList<KeyType>[]) new LinkedList[10];
		values = (LinkedList<ValueType>[]) new LinkedList[10];
		for (int i = 0; i < keys.length; i++) {
			keys[i] = new LinkedList();
			values[i] = new LinkedList();
		}
	}

	/**
	 * Puts the value and key in their respective linked list arrays in accordance
	 * to value's hash code.
	 * 
	 * @param KeyType   key the value's key
	 * @param ValueType value in question
	 * @return true if the value and key were successfully added, false otherwise
	 */
	public boolean put(KeyType key, ValueType value) {
		// Returns false if key is null
		if (key == null) {
			System.out.println("The key being inserted here is null, which is not valid.");
			return false;
		}

		for (int i = 0; i < keys.length; i++) {
			if (keys[i].size() != 0) {
				for (int j = 0; j < keys[i].size(); j++) {
					if (keys[i].get(j).equals(key)) {
						return false;
					}
				}
			}
		}

		// Determines hash code of value being inserted, then adds it into its
		// appropriate spot
		int elemNum = hash(value, keys.length);

		keys[elemNum].add(key);
		values[elemNum].add(value);

		if ((double) size() >= .85 * keys.length) {
			rehash(keys, keys.length);
		}
		return true;
	}

	/**
	 * Retrieves a value by searching for its key
	 * 
	 * @param key the key being searched for
	 * @return the value, if successfully found
	 */
	public ValueType get(KeyType key) throws NoSuchElementException {
		boolean found = false;
		ValueType val = null;
		for (int i = 0; i < keys.length; i++) {
			if (keys[i].size() == 0) {
				continue;
			} else {
				for (int j = 0; j < keys[i].size(); j++) {
					if (keys[i].get(j).equals(key)) {
						val = values[i].get(j);
						found = true;
					}
				}
			}
		}
		if (!found) {
			throw new NoSuchElementException("The value was not found with the given key.");
		} else {
			return val;
		}
	}

	/**
	 * Determines the size of the hash table
	 * 
	 * @return size of hash table
	 */
	public int size() {
		int count = 0;
		for (int i = 0; i < keys.length; i++) {
			if (keys[i].size() != 0) {
				for (int j = 0; j < keys[i].size(); j++) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * Searches the hash table for a given key
	 * 
	 * @param key key being searched for
	 * @return true if the hash table contains a key, false otherwise
	 */
	public boolean containsKey(KeyType key) {
		for (int i = 0; i < keys.length; i++) {
			if (keys[i].size() != 0) {
				for (int j = 0; j < keys[i].size(); j++) {
					if (keys[i].get(j).equals(key)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Removes a value from the hash table by searching for its key.
	 * 
	 * @param key key being searched for
	 * @return valuetype value that was removed
	 */
	public ValueType remove(KeyType key) {
		ValueType val = null;
		for (int i = 0; i < keys.length; i++) {
			if (keys[i].size() != 0) {
				for (int j = 0; j < keys[i].size(); j++) {
					if (keys[i].get(j).equals(key)) {
						val = values[i].get(j);
						values[i].remove(j);
						keys[i].remove(j);
					}
				}
			}
		}
		return val;
	}

	/**
	 * Clears the entire hash table.
	 */
	public void clear() {
		for (int i = 0; i < keys.length; i++) {
			keys[i] = null;
			keys[i] = new LinkedList();
		}
		for (int i = 0; i < values.length; i++) {
			values[i] = null;
			values[i] = new LinkedList();
		}
	}

	/**
	 * Rehashes the hash table when the size exceeds the load factor of the table's
	 * current capacity
	 * 
	 * @param oldTable the key hash table that is going to be expanded
	 * @param capacity the old capacity of the hash table, which is to be expanded
	 */
	private void rehash(LinkedList<KeyType>[] oldTable, int capacity) {
		// Declare and initialize new Linked List arrays with capacities double the
		// previous
		int newCapacity = capacity * 2;
		LinkedList<KeyType>[] newKey = (LinkedList<KeyType>[]) new LinkedList[newCapacity];
		LinkedList<ValueType>[] newValues = (LinkedList<ValueType>[]) new LinkedList[newCapacity];

		// Make each element a new Linked list
		for (int i = 0; i < newKey.length; i++) {
			newKey[i] = new LinkedList();
			newValues[i] = new LinkedList();
		}

		// Assigns both keys and values in their respesctive spots in accordance to
		// their hash code element #
		ValueType temp = null;
		for (int i = 0; i < oldTable.length; i++) {
			if (oldTable[i].size() == 0) {
				continue;
			} else {
				for (int j = 0; j < oldTable[i].size(); j++) {
					temp = values[i].get(j);
					int hash = hash(temp, newCapacity);
					newKey[hash].add(oldTable[i].get(j));
					newValues[hash].add(temp);
				}
			}
		}

		// Finally, assigns the two arrays with their doubled, finalized versions
		keys = newKey;
		values = newValues;

	}

	/**
	 * Calculates an element number where a value is going to be placed
	 * 
	 * @param value    value being used to formulate the hash code
	 * @param capacity the capacity used to determine the element number
	 * @return the element number where the key and/or value is being placed
	 */
	private int hash(ValueType value, int capacity) {
		int hashCode = Math.abs(value.hashCode());
		int elemNum = hashCode % capacity;
		return elemNum;
	}

}
