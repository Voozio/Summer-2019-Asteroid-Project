package com.mycompany.a2.objects;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;

public class Missile extends MovingGameObject {
	private int fuelLevel;
	private int size;
	private String owner;

	public Missile(double locationX, double locationY, int color, int speed, int direction, int fuelLevel, String owner, int size) {
		super(locationX, locationY, color, speed, direction);
		this.fuelLevel = fuelLevel;
		this.owner = owner;
		this.size = size;
	}

	/**
	 * Decrements the fuel level by 1 tick
	 */
	public void decrementFuelLevel() {
		fuelLevel -= 1;
	}

	/**
	 * Returns the current fuel level of the MovingGameObject
	 */
	public int getFuelLevel() {
		return fuelLevel;
	}
	
	/**
	 * Returns the current owner
	 */
	public String getOwner() {
		return owner;
	}

	/**
	 * Sets a new direction for the MovingGameObject
	 */
	public void setDirection(int direction) {
		super.setDirection(direction);
	}

	/**
	 * Sets a new speed for the MovingGameObject
	 */
	public void setSpeed(int speed) {
		super.setSpeed(speed);
	}

	/**
	 * Overrides the parent toString() method
	 */
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = ", Fuel Level = " + fuelLevel + ", Owner = " + owner;
		return ("Missile: " + parentDesc + myDesc);
	}
	
	/**
	 * Draws the shape for a missile
	 */
	public void draw(Graphics g, Point2D pointParent, Point2D pointScreen) {
		double theta = Math.toRadians(90 - getDirection());
		double deltaX = Math.cos(theta) * size;
		double deltaY = Math.sin(theta) * size;
		
		int xLoc = (int)(pointParent.getX() + getLocationX());
		int yLoc = (int)(pointParent.getY() + getLocationY());
		int newX = (int) (xLoc + deltaX);
		int newY = (int) (yLoc + deltaY);

		g.setColor(getColorValue());
		g.drawLine(xLoc, yLoc, newX, newY); 
	}
}
