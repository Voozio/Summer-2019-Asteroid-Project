package com.mycompany.a2;

import com.mycompany.a2.objects.GameObject;

public interface ICollection {
	public void add(GameObject obj);
	public void remove(int index);
	public IIterator getIterator();
}
