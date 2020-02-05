package com.mycompany.a2.objects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;

public class Asteroid extends MovingGameObject{
	private int size;
	
	public Asteroid(double locationX, double locationY, int color, int speed, int direction, int size) {
		super(locationX, locationY, color, speed, direction);
		this.size = size;
	}

	/**
	 * Returns the current size of the asteroid
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Sets the new speed of the asteroid
	 */
	public void setSpeed(int speed) {
		super.setSpeed(speed);
	}

	/**
	 * Sets the new direction of the asteroid
	 */
	public void setDirection(int direction) {
		super.setDirection(direction);
	}

	/**
	 * Overrides the parent toString() method
	 */
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = ", Size = " + size;
		return ("Asteroid: " + parentDesc + myDesc);
	}
	
	/**
	 * Draws the shape for an asteroid
	 */
	public void draw(Graphics g, Point2D pointParent, Point2D pointScreen) {
		int xLoc = (int)(pointParent.getX() + getLocationX());
		int yLoc = (int)(pointParent.getY() + getLocationY());

		g.setColor(getColorValue());
		g.fillArc(xLoc, yLoc, getSize()/2, getSize()/2, 0, 360);
		g.setColor(ColorUtil.BLACK);
		g.drawArc(xLoc, yLoc, getSize()/2, getSize()/2, 0, 360);
	}
}
