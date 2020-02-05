package com.mycompany.a2.objects;

public abstract class FixedGameObject extends GameObject{
	private int id;
	private static int count; // A fixed counter across all FixedGameObjects

	public FixedGameObject(double locationX, double locationY, int color) {
		super(locationX, locationY, color);
		id = count;
		count += 1;
	}

	/**
	 * Overrides the parent toString() method
	 */
	public String toString() {
		String parentDesc = getLocationString() + ", " + getColor();
		String myDesc = ", ID = " + id;
		return parentDesc + myDesc;
	}
}
