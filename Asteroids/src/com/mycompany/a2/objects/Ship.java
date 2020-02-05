package com.mycompany.a2.objects;

public abstract class Ship extends MovingGameObject{
	private int missileCount;

	public Ship(double locationX, double locationY, int color, int speed, int direction, int missileCount) {
		super(locationX, locationY, color, speed, direction);
		this.missileCount = missileCount;
	}

	/**
	 * Returns the current missile count of the Ship
	 */
	public int getMissileCount() {
		return missileCount;
	}
	
	/**
	 * Sets a new missile count for the Ship
	 */
	public void setMissileCount(int missleCount) {
		this.missileCount = missleCount;
	}

	/**
	 * Decrements the missile count of the Ship by 1
	 */
	public void decrementMissileCount() {
		missileCount -= 1;
	}

	/**
	 * Overrides the parent toString() method
	 */
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = ", Missile Count = " + missileCount;
		return parentDesc + myDesc;
	}
}
