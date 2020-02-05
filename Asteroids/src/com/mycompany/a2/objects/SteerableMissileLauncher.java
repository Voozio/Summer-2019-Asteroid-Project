package com.mycompany.a2.objects;

import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;

public class SteerableMissileLauncher extends MissileLauncher implements ISteerable {
	/**
	 * Sets the direction of the missile launcher to the same starting direction of the PlayerShip
	 */
	public SteerableMissileLauncher(double locationX, double locationY, int color, int speed, int direction) {
		super(locationX, locationY, color, speed, direction);
	}
	
	/**
	 * Turns the SteerableMissileLauncher left by a set amount
	 */
	public void turnLeft() {
		setMLDirection(getDirectionML() + 15);
		if (getDirectionML() > 359) {
			setMLDirection(getDirectionML() - 360);
		}
	}

	/**
	 * Turns the SteerableMissileLauncher right by a set amount
	 */
	public void turnRight() {
		setMLDirection(getDirectionML() - 15);
		if (getDirectionML() < 0) {
			setMLDirection(getDirectionML() + 360);
		}
	}
	
	/**
	 * Draws the shape for a Steereable Missile Launcher
	 */
	public void draw(Graphics g, Point2D pointParent, double psX, double psY, int size) {
		double theta = Math.toRadians(90 - getDirection());
		double deltaX = Math.cos(theta) * size/3;
		double deltaY = Math.sin(theta) * size/3;

		int xLoc = (int)(pointParent.getX() + psX);
		int yLoc = (int)(pointParent.getY() + psY);
		int newX = (int)(xLoc + deltaX);
		int newY = (int)(yLoc + deltaY);
		
		g.setColor(getColorValue());
		g.drawLine(xLoc, yLoc, newX, newY);
	}
}
