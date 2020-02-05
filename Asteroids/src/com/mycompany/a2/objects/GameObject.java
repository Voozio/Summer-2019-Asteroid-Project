package com.mycompany.a2.objects;

import com.codename1.ui.geom.Point2D;
import com.codename1.charts.util.ColorUtil;
import java.lang.Math;

public abstract class GameObject implements IDrawable {
	private Point2D location; // A Point2D object used to make location modifying easier
	private int color;

	public GameObject(double locationX, double locationY, int color) {
		location = new Point2D(locationX, locationY);
		this.color = color;
	}

	/**
	 * Returns the current location of the GameObject
	 */
	public String getLocationString() {
		return "Location = (" + Math.round(getLocationX() * 10) / 10.0 + ", " + Math.round(getLocationY() * 10) / 10.0 + ")";
	}

	/**
	 * Returns the current x-coordinate of the GameObject
	 */
	public double getLocationX() {
		return location.getX();
	}

	/**
	 * Returns the current y-coordinate of the GameObject
	 */
	public double getLocationY() {
		return location.getY();
	}

	/**
	 * Returns the current color in a readable format using ColorUtil
	 */
	public String getColor() {
		return "Color = ["+ ColorUtil.red(color) + ", "+ ColorUtil.green(color) + ", "+ ColorUtil.blue(color) + "]";
	}
	
	/**
	 * Returns the current color value
	 */
	public int getColorValue() {
		return color;
	}

	/**
	 * Sets a new location for the GameObject
	 */
	public void setLocation(double locationX, double locationY) {
		location = new Point2D(locationX, locationY);
	}

	/**
	 * Sets a new color for the GameObject
	 */
	public void setColor(int color) {
		this.color = color;
	}
}
