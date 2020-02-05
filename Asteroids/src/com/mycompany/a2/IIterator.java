package com.mycompany.a2;

import com.mycompany.a2.objects.GameObject;

public interface IIterator {
	public boolean hasNext();
	public GameObject getNext();
	public void decrementIndex();
}