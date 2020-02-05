package com.mycompany.a2.objects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;

public class SpaceStation extends FixedGameObject{
	private int blinkRate;
	private int size;
	private boolean lightOn;
	
	public SpaceStation(double locationX, double locationY, int color, int blinkRate, int size) {
		super(locationX, locationY, color);
		this.blinkRate = blinkRate;
		this.size = size;
		this.lightOn = false;
	}

	/**
	 * Toggles the light from true to false and vice versa
	 */
	public void toggleLight() {
		lightOn = !(lightOn);
	}
	
	/**
	 * Returns the current blink rate of the SpaceStation
	 */
	public int getBlinkRate() {
		return blinkRate;
	}

	/**
	 * Overrides the parent toString() method
	 */
	public String toString() {
		String parentDesc = super.toString();
		String myDesc = (", Blink Rate = " + blinkRate + ", Light On = " + lightOn);
		return ("Space Station: " + parentDesc + myDesc);
	}
	
	/**
	 * Draws the shape for a space station
	 */
	public void draw(Graphics g, Point2D pointParent, Point2D pointScreen) {
		int xLoc = (int)(pointParent.getX() + getLocationX());
		int yLoc = (int)(pointParent.getY() + getLocationY());

		g.setColor(getColorValue());
		g.fillRect(xLoc, yLoc, size, size);
		g.setColor(ColorUtil.BLACK);
		g.drawRect(xLoc, yLoc, size, size);
	
	}
}
