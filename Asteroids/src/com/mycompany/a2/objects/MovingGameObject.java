package com.mycompany.a2.objects;

public abstract class MovingGameObject extends GameObject implements IMovable {
	private int speed;
	private int direction;
	private int width, height;

	public MovingGameObject(double locationX, double locationY, int color, int speed, int direction) {
		super(locationX, locationY, color);
		this.speed = speed;
		this.direction = direction;
		width = 0;
		height = 0;
	}

	/**
	 * Returns the current speed of the MovingGameObject
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Returns the current direction of the MovingGameObject
	 */
	public int getDirection() {
		return direction;
	}
	
	/**
	 * Sets the width to the size of the MapView
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * Sets the height to the size of the MapView
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Sets a new speed for the MovingGameObject
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * Sets a new direction for the MovingGameObject
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}

	/**
	 * Moves the MovingGameObject by a set distance
	 */
	public void move(int time) {
		int theta = 90 - getDirection();
		double deltaX = Math.cos(Math.toRadians(theta)) * (speed * ((double)time / 1000));
		double deltaY = Math.sin(Math.toRadians(theta)) * (speed * ((double)time / 1000));
		double newX = getLocationX() + deltaX;
		double newY = getLocationY() + deltaY;
		
		if (newX > width)
			newX -= width - 1;
		else if (newX < 0)
			newX += width + 1;
			
		if (newY > height)
			newY -= height -1;
		else if (newY < 0)
			newY += height + 1;
		
		setLocation(newX, newY);
	}

	/**
	 * Overrides the parent toString() method
	 */
	public String toString() {
		String parentDesc = getLocationString() + ", " + getColor();
		String myDesc = ", Speed = " + speed + ", Direction = " + direction;
		return parentDesc + myDesc;
	}
}
