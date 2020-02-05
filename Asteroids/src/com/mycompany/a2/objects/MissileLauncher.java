package com.mycompany.a2.objects;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;

public class MissileLauncher extends MovingGameObject {

	public MissileLauncher(double locationX, double locationY, int color, int speed, int direction) {
		super(locationX, locationY, color, speed, direction);
	}

	/**
	 * Returns the current direction of the SteerableMissileLauncher
	 */
	public int getDirectionML() {
		return getDirection();
	}
	
	/**
	 * Sets the direction of the Missile Launcher
	 */
	public void setMLDirection(int directionML) {
		setDirection(directionML);
	}

	@Override
	public void draw(Graphics g, Point2D pointParent, Point2D pointScreen) {
		
	}
	
	/**
	 * Draws the shape for the missile launcher
	 */
	public void draw(Graphics g, Point2D pointParent, double psX, double psY, int size) {
		double theta = Math.toRadians(90 - getDirection());
		double deltaX = Math.cos(theta) * size/1.5;
		double deltaY = Math.sin(theta) * size/1.5;

		int xLoc = (int)(pointParent.getX() + psX + size/2);
		int yLoc = (int)(pointParent.getY() + psY + size/4);
		int newX = (int) (xLoc + deltaX);
		int newY = (int) (yLoc + deltaY);
		
		g.setColor(getColorValue());
		g.drawLine(xLoc, yLoc, newX, newY);
	}
}
