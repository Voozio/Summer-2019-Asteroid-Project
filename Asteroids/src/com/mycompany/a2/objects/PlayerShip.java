package com.mycompany.a2.objects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;

public class PlayerShip extends Ship {
	private SteerableMissileLauncher launcher;
	private int size;
	
	/**
	 * Initializes a SteerableMissileLauncher object with same location, color, speed and direction
	 */
	public PlayerShip(double locationX, double locationY, int color, int speed, int direction, int missileCount, int size) {
		super(locationX, locationY, color, speed, direction, missileCount);
		launcher = new SteerableMissileLauncher(locationX, locationY, ColorUtil.BLACK, speed, direction);
		this.size = size;
	}

	/**
	 * Returns Missile Launcher direction
	 */
	public int getMLDirection() {
		return launcher.getDirectionML();
	}
	
	/**
	 * Returns the current size
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Increases the speed by a set amount
	 */
	public void increaseSpeed(int width) {
		if (getSpeed() < width/29)
			setSpeed(getSpeed() + width/120);
		else
			setSpeed(width/24);
		launcher.setSpeed(getSpeed());
	}

	/**
	 * Decreases the speed by a set amount
	 */
	public void decreaseSpeed(int width) {
		if (getSpeed() > width/121)
			setSpeed(getSpeed() - width/120);
		else
			setSpeed(0);
		launcher.setSpeed(getSpeed());
	}

	/**
	 * Turns the PlayerShip left by a set amount
	 */
	public void turnLeft() {
		if (getDirection() < 345)
			setDirection(getDirection() + 15);
		else
			setDirection((getDirection() + 15) - 360);
	}

	/**
	 * Turns the PlayerShip right by a set amount
	 */
	public void turnRight() {
		if (getDirection() >= 15)
			setDirection(getDirection() - 15);
		else
			setDirection(360 - (15 - getDirection()));
	}

	/**
	 * Reloads the PlayerShip to its max missile capacity
	 */
	public void reload() {
		setMissileCount(10);
	}
	
	/**
	 * Revolves the Missile Launcher left
	 */
	public void turnMLLeft() {
		launcher.turnLeft();
	}
	
	/**
	 * Revolves the Missile Launcher right
	 */
	public void turnMLRight() {
		launcher.turnRight();
	}
	
	/**
	 * Calls the move method for the Missile Launcher
	 */
	public void moveML(int time) {
		launcher.move(time);
	}
	
	/**
	 * Sets the width to the size of the MapView
	 */
	public void setMLWidth(int width) {
		launcher.setWidth(width);
	}
	
	/**
	 * Sets the height to the size of the MapView
	 */
	public void setMLHeight(int height) {
		launcher.setHeight(height);
	}
	
	/**
	 * Sets a new location for the Missile Launcher
	 */
	public void setMLLocation(double locationX, double locationY) {
		launcher.setLocation(locationX, locationY);
	}
	
	/**
	 * Overrides the parent toString() method
	 */
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = ", ML Direction = " + launcher.getDirectionML();
		return ("Player Ship: " + parentDesc + myDesc);
	}

	/**
	 * Draws the shape for a Player Ship
	 */
	public void draw(Graphics g, Point2D pointParent, Point2D pointScreen) {
		int theta1 = 290 - getDirection();
		int theta2 = 250 - getDirection();
		double deltaX1 = Math.cos(Math.toRadians(theta1)) * size;
		double deltaY1 = Math.sin(Math.toRadians(theta1)) * size;
		double deltaX2 = Math.cos(Math.toRadians(theta2)) * size;
		double deltaY2 = Math.sin(Math.toRadians(theta2)) * size;
		
		int xLoc = (int)(pointParent.getX() + getLocationX());
		int yLoc = (int)(pointParent.getY() + getLocationY());
		int newX1 = (int)(xLoc + deltaX1);
		int newY1 = (int)(yLoc + deltaY1);
		int newX2 = (int)(xLoc + deltaX2);
		int newY2 = (int)(yLoc + deltaY2);

		g.setColor(getColorValue());
		g.fillTriangle(xLoc, yLoc, newX1, newY1, newX2, newY2);
		g.setColor(ColorUtil.BLACK);
		g.drawLine(xLoc, yLoc, newX1, newY1); 
		g.drawLine(newX1, newY1, newX2, newY2); 
		g.drawLine(newX2, newY2, xLoc, yLoc);
		launcher.draw(g, pointParent, getLocationX(), getLocationY(), size);
	}
}
