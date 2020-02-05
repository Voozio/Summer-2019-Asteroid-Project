package com.mycompany.a2;

import java.util.Vector;

import com.mycompany.a2.objects.GameObject;

public class GameObjectCollection implements ICollection {
	private Vector<GameObject> collection;
	
	public GameObjectCollection() {
		collection = new Vector<GameObject>();
	}
	
	/**
	 * Adds an object to the collection
	 */
	public void add(GameObject obj) {
		collection.addElement(obj);
	}
	
	/**
	 * Removes the object at the index 
	 */
	public void remove(int index) {
		collection.remove(index);
	}
	
	/**
	 * Retrieves the current iterator index  
	 */
	public int getIndex(GameObjectIterator go) {
		return go.getIndex();
	}
	
	/**
	 * Returns the Game Object Iterator 
	 */
	public IIterator getIterator() {
		return new GameObjectIterator();
	}
	
	private class GameObjectIterator implements IIterator {
		private int currentIndex;
		
		/**
		 * Sets current index to essentially null 
		 */
		public GameObjectIterator() {
			currentIndex = -1;
		}
		
		/**
		 * Returns true if there is an element in the next index 
		 */
		public boolean hasNext() {
			if (collection.size() <= 0)
				return false;
			if (currentIndex == collection.size() - 1)
				return false;
			return true;
		}
		
		/**
		 * Returns the current index 
		 */
		public int getIndex() {
			return currentIndex;
		}
		
		/**
		 * Returns the next element 
		 */
		public GameObject getNext() {
			currentIndex++;
			return collection.elementAt(currentIndex);
		}
		
		/**
		 * Decrements the current index by 1
		 */
		public void decrementIndex() {
			currentIndex -= 1;
		}
	}
}



