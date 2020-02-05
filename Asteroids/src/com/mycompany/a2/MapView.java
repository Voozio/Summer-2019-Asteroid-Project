package com.mycompany.a2;

import java.util.Observer;
import java.util.Observable;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point2D;
import com.mycompany.a2.objects.GameObject;
import com.mycompany.a2.objects.IDrawable;

public class MapView extends Container implements Observer {
	private IGameWorld gw;
	
	public MapView() {
		gw = new GameWorld();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		gw = (IGameWorld) arg;
		IIterator gameIterator = gw.getIterator();
		
		if (!gameIterator.hasNext()) { // Checks to see if there are any elements in the vector
			System.out.println("There are currently no objects on the map.");
			return;
		}
		System.out.println("\n   World Map\n---------------");
		while (gameIterator.hasNext()) { // Checks to see if there's another element in the vector
			System.out.println(gameIterator.getNext()); // Calls the toString() method of each element
		}
		System.out.println();
		this.repaint();
	}
	
	//Paints the MapView container with all the objects
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		IIterator gameIterator = gw.getIterator();
		GameObject obj;
		Point2D pointParent = new Point2D(getX(), getY());
		Point2D pointScreen = new Point2D(getAbsoluteX(), getAbsoluteY());
		
		while (gameIterator.hasNext()) {
			obj = gameIterator.getNext();
			if (obj instanceof IDrawable)
				((IDrawable)obj).draw(g, pointParent, pointScreen);
		}
	}
}