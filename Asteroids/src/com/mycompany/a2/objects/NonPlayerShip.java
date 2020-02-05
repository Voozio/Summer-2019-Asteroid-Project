package com.mycompany.a2.objects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;

public class NonPlayerShip extends Ship{
	private MissileLauncher launcher;
	private int size;

	public NonPlayerShip(double locationX, double locationY, int color, int speed, int direction, int missileCount, int size) {
		super(locationX, locationY, color, speed, direction, missileCount);
		launcher = new MissileLauncher(locationX, locationY, ColorUtil.BLACK, speed, direction);
		this.size = size;
	}
	
	/**
	 * Returns the current Missile Launcher direction
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
	 * Sets a new speed for the Ship
	 */
	public void setSpeed(int speed) {
		super.setSpeed(speed);
	}

	/**
	 * Sets a new direction for the Ship
	 */
	public void setDirection(int direction) {
		super.setDirection(direction);
	}

	/**
	 * Calls the move method for the Missile Launcher
	 */
	public void moveML(int time) {
		launcher.move(time);
	}
	
	/**
	 * Overrides the parent toString() method
	 */
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = ", Size = " + size;
		return ("Non-Player Ship: " + parentDesc + myDesc);
	}
	
	/**
	 * Draws the shape for a Non-Player Ship
	 */
	public void draw(Graphics g, Point2D pointParent, Point2D pointScreen) {
		int xLoc = (int)(pointParent.getX() + getLocationX());
		int yLoc = (int)(pointParent.getY() + getLocationY());

		g.setColor(getColorValue());
		g.fillArc(xLoc, yLoc, size, size/2, 0, 360); 
		g.setColor(ColorUtil.BLACK);
		g.drawArc(xLoc, yLoc, size, size/2, 0, 360);
		launcher.draw(g, pointParent, getLocationX(), getLocationY(), size);
	}
}
