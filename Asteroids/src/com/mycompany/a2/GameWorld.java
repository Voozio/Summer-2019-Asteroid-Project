package com.mycompany.a2;

import com.mycompany.a2.objects.*;
import java.util.Random;
import java.util.Observable;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;

public class GameWorld extends Observable implements IGameWorld {
	private Random random = new Random();
	private GameObjectCollection store = new GameObjectCollection();
	private int mapWidth, mapHeight;
	private int numLives;
	private int playerScore;
	private int numPSMissiles;
	private boolean soundOn;
	private int clock;
	private boolean hasPS;
	private int maxSpeed;
	private Sound sGameOver;
	
	private final int GLOBAL_MIN = 0;
	private final int MAX_DEGREES = 359;
	private final int MAX_PS_Missile_COUNT = 10;
	private final int MAX_NPS_Missile_COUNT = 4;
	private final int MAX_BLINK_RATE = 6;
	private final int MAX_FUEL_LEVEL = 150;
	
	/**
	 * Variables are initialized to their default values
	 */
	public void init() {
		mapWidth = 1024;
		mapHeight = 768;
		numLives = 3;
		playerScore = 0;
		numPSMissiles = 0;
		soundOn = true;
		clock = 0;
		hasPS = false;
		sGameOver = new Sound();
		System.out.println("");
	}
	
	/**
	 * Returns the iterator
	 */
	public IIterator getIterator() {
		return store.getIterator();
	}
	
	/**
	 * Returns the number of lives remaining
	 */
	public int getNumLives() {
		return numLives;
	}
	
	/**
	 * Returns the current player score
	 */
	public int getPlayerScore() {
		return playerScore;
	}
	
	/**
	 * Returns the number of PS missiles remaining
	 */
	public int getPSMissiles() {
		return numPSMissiles;
	}
	
	/**
	 * Returns true if soundOn is true, else false
	 */
	public boolean getSoundOn() {
		return soundOn;
	}
	
	/**
	 * Returns "On" if soundOn is true, else "Off"
	 */ 
	public String isSoundOn(boolean soundOn) {
		if (soundOn)
			return "On";
		else
			return "Off";
	}
	
	/**
	 * Returns the elapsed time
	 */
	public int getClock() {
		return clock;
	}
	
	/**
	 * Returns whether a PS is alive
	 */
	public boolean getHasPS() {
		return hasPS;
	}
	
	/**
	 * Sets soundOn to the accepted boolean parameter
	 */ 
	public void setSound() {
		soundOn = (!soundOn);
		
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}
	
	/**
	 * Sets the width to the size of the MapView
	 */
	public void setWidth(int width) {
		this.mapWidth = width;
	}
	
	/**
	 * Sets the height to the size of the MapView
	 */
	public void setHeight(int height) {
		this.mapHeight = height;
	}
	
	/**
	 * Sets the max speed relative to the size of the MapView
	 */
	public void setMaxSpeed(int speed) {
		this.maxSpeed = speed;
	}
	
	/**
	 * An asteroid is created and added to the world. When the asteroid is created,
	 * the location, size, speed and direction of the asteroid are to be randomly 
	 * generated and the color is set to light gray. 
	 */
	public void addAsteroid() {
		Asteroid asteroid = new Asteroid(((double)random.nextInt(mapWidth)+random.nextDouble()), ((double)random.nextInt(mapHeight)+random.nextDouble()), ColorUtil.LTGRAY, random.nextInt(maxSpeed)+1, random.nextInt(MAX_DEGREES), mapWidth/20 + random.nextInt(mapWidth/15));
		store.add(asteroid);
		System.out.println("A new ASTEROID has been created...");
		
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}
	
	/**
	 * An NPS is created and added to the world. It has a fixed attribute size of
	 * either 15 or 25. Location, speed and direction of the NPS are randomly
	 * generated and the color is set to magenta.
	 */
	public void addNonPlayerShip() {
		int size;
		if (random.nextInt(2) == 1)
			size = mapWidth/25;
		else
			size = mapWidth/20;
		NonPlayerShip nonPlayerShip = new NonPlayerShip(((double)random.nextInt(mapWidth)+random.nextDouble()), ((double)random.nextInt(mapHeight)+random.nextDouble()), ColorUtil.MAGENTA, random.nextInt(maxSpeed)+1, random.nextInt(MAX_DEGREES), MAX_NPS_Missile_COUNT, size);
		store.add(nonPlayerShip);
		System.out.println("A new NON-PLAYER SHIP has been created...");
		
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}
	
	/**
	 * A SpaceStation is created and added to the world with a randomly generated
	 * location and blink rate. The color is set to yellow.
	 */
	public void addBlinkingSpaceStation() {
		SpaceStation spaceStation = new SpaceStation(((double)random.nextInt(mapWidth)+random.nextDouble()), ((double)random.nextInt(mapHeight)+random.nextDouble()), ColorUtil.YELLOW, MAX_BLINK_RATE, mapWidth/35);
		store.add(spaceStation);
		System.out.println("A new BLINKING SPACE STATION has been created...");
		
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}
	
	/**
	 * A for loop implementation checks if there is already a PlayerShip object.
	 * If there is, an error message is printed. Otherwise, a PS is created and 
	 * added to the world in the center of the map with a speed of 0, the max
	 * number of missiles, and a white color. 
	 */
	public void addPlayerShip() {
		if (hasPS) { //Checks if a PS exists
			System.out.println("Error: No more than one PLAYER SHIP is allowed...\n");
			return;
		}
		PlayerShip playerShip = new PlayerShip((double)mapWidth/2, (double)mapHeight/2, ColorUtil.WHITE, GLOBAL_MIN, GLOBAL_MIN, MAX_PS_Missile_COUNT, mapWidth/28);
		store.add(playerShip);
		numPSMissiles = 10;
		hasPS = true;
		System.out.println("A new PLAYER SHIP has been created...");
		
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}
	
	/**
	 * A for loop implementation checks if there is a PlayerShip object in the vector.
	 * If there is and the PlayerShip is out of missiles, an error message is printed.
	 * Otherwise, a missile is created and add to the world with a location, speed,
	 * and lancher's heading determined by the PlayerShip's current attributes.
	 */
	public void firePSMissile() {
		IIterator gameIterator = getIterator();
		GameObject obj;
		
		if (hasPS) { // Checks if a PS exists
			if (numPSMissiles == 0) { // Checks the missile count
				System.out.println("The PLAYER SHIP is out of missiles...");
				return;
			}
			
			while (gameIterator.hasNext()) { // Checks to see if there's another element in the vector
				obj = gameIterator.getNext();
				if (obj instanceof PlayerShip) { // Checks if the current element is a PlayerShip object
					Missile missile = new Missile(obj.getLocationX(), obj.getLocationY(), ColorUtil.BLACK, ((PlayerShip)obj).getSpeed() + maxSpeed, ((PlayerShip)obj).getMLDirection(), MAX_FUEL_LEVEL, "Player Ship", mapWidth/100);
					store.add(missile);
					((PlayerShip)obj).setMissileCount(((PlayerShip)obj).getMissileCount()-1); // Decrements the missile count by 1
					numPSMissiles--;
					System.out.println("The PLAYER SHIP has fired a missile...");
					
					setChanged();
					notifyObservers(new GameWorldProxy(this));
					return;
				}
			}
			
		}
		System.out.println("Error: No PLAYER SHIP exists...\n");
	}
	
	/**
	 * If there is a NonPlayerShip, its missile count is retrieved. If out of missiles,
	 * an error message is printed. Otherwise, a missile is created and add to the world
	 * with a location, speed, and lancher's heading determined by the NonPlayerShip's current attributes.
	 */
	public void launchNPSMissile() {
		IIterator gameIterator = getIterator();
		GameObject obj;
		
		while (gameIterator.hasNext()) { // Checks to see if there's another element in the vector
			obj = gameIterator.getNext();
			if (obj instanceof NonPlayerShip) { // Checks if the current element is a Non-PlayerShip object
				if (((NonPlayerShip)obj).getMissileCount() == 0) { // Checks the missile count
					System.out.println("The NON-PLAYER SHIP is out of missiles...");
					return;
				}
				Missile missile = new Missile(obj.getLocationX() + ((NonPlayerShip)obj).getSize()/2, obj.getLocationY() + ((NonPlayerShip)obj).getSize()/4, ColorUtil.BLACK, ((NonPlayerShip)obj).getSpeed() + maxSpeed, ((NonPlayerShip)obj).getDirection(), MAX_FUEL_LEVEL, "Non-Player Ship", mapWidth/100);
				store.add(missile);
				((NonPlayerShip)obj).setMissileCount(((NonPlayerShip)obj).getMissileCount()-1); // Decrements the missile count by 1
				System.out.println("A NON-PLAYER SHIP has fired a missile...");
				
				setChanged();
				notifyObservers(new GameWorldProxy(this));
				return;
			}
		}
		System.out.println("Error: No NON-PLAYER SHIP exists...\n");
	}
	
	/**
	 * If there are no objects on the map, then that is printed.
	 * If there are, then the current state of the world is printed. 
	 */
	public void printMap() {
		IIterator gameIterator = getIterator();
		
		if (!gameIterator.hasNext()) { // Checks to see if there are any elements in the vector
			System.out.println("There are currently no objects on the map.");
			return;
		}
		System.out.println("\n   World Map\n---------------");
		while (gameIterator.hasNext()) { // Checks to see if there's another element in the vector
			System.out.println(gameIterator.getNext()); // Calls the toString() method of each element
		}
		System.out.println();
	}
	
	/**
	 * If there is PlayerShip, then revolveML() is called to revolve the MissileLauncher angle.
	 */
	public void turnPSMissileLauncherLeft() {
		IIterator gameIterator = getIterator();
		GameObject obj;
		
		while (gameIterator.hasNext()) { // Checks to see if there's another element in the vector
			obj = gameIterator.getNext();
			if (obj instanceof PlayerShip) { // Checks if the current element is a PlayerShip object
				((PlayerShip)obj).turnMLLeft();
				System.out.println("The PS Missile Launcher has turned left...");
				
				setChanged();
				notifyObservers(new GameWorldProxy(this));
				return;
			}
		}
		System.out.println("Error: There is no PLAYER SHIP.\n");
	}
	
	/**
	 * If there is PlayerShip, then revolveML() is called to revolve the MissileLauncher angle.
	 */
	public void turnPSMissileLauncherRight() {
		IIterator gameIterator = getIterator();
		GameObject obj;
		
		while (gameIterator.hasNext()) { // Checks to see if there's another element in the vector
			obj = gameIterator.getNext();
			if (obj instanceof PlayerShip) { // Checks if the current element is a PlayerShip object
				((PlayerShip)obj).turnMLRight();
				System.out.println("The PS Missile LAUNCHER has turned right...");
				
				setChanged();
				notifyObservers(new GameWorldProxy(this));
				return;
			}
		}
		System.out.println("Error: There is no PLAYER SHIP.\n");
	}
	
	/**
	 * If there is PlayerShip, then reload() is called to reset the PlayerShip missile count to max. 
	 */
	public void reloadNewPSMissiles() {
		IIterator gameIterator = getIterator();
		GameObject obj;
		
		boolean ssAlive = false;
		
		if (hasPS) { // Checks if a PS exists
			if (numPSMissiles != 10) {
				while (gameIterator.hasNext()) { // Checks to see if there's another element in the vector
					obj = gameIterator.getNext();
					if (obj instanceof SpaceStation) // Checks if the current element is a SpaceStation object
						ssAlive = true;
				}
				if (ssAlive) {
					gameIterator = getIterator();
					while (gameIterator.hasNext()) { // Checks to see if there's another element in the vector
						obj = gameIterator.getNext();
						if (obj instanceof PlayerShip) { // Checks if the current element is a PlayerShip object
							((PlayerShip)obj).reload();
							System.out.println("A new supply of missiles has been loaded into the PS...");
							numPSMissiles = 10;
							
							setChanged();
							notifyObservers(new GameWorldProxy(this));
							return;
						}
					}
				}
				else {
					System.out.println("Error: There is no SPACE STATION.\n");
					return;
				}
			}
			System.out.println("Error: The PLAYER SHIP is already maxed out.\n");
			return;
		}
		System.out.println("Error: There is no PLAYER SHIP.\n");
	}
	
	/**
	 * If a PlayerShip, its missile, and an asteroid exist, then one missile and an
	 * asteroid are removed from the vector. The player score is incremented.
	 */
	public void psMissileKillsAsteroid() {
		IIterator gameIterator = getIterator();
		GameObject obj;
		
		// Variables to save object indexes for future use
		int missileAt = 0;
		int asteroidAt = 0;
		int indexCount = 0;
		
		boolean missileAlive = false;
		boolean asteroidAlive = false;
		
		if (hasPS) { // Checks if a PS exists
			while (gameIterator.hasNext()) { // Checks to see if there's another element in the vector
				obj = gameIterator.getNext();
				if (obj instanceof Missile) { // Checks if the current element is a Missile object
					if (((Missile)obj).getOwner() == "Player Ship") {
						missileAlive = true;
						missileAt = indexCount;
					}
				}
				if (obj instanceof Asteroid) { // Checks if the current element is a Asteroid object
					asteroidAlive = true;
					asteroidAt = indexCount;
				}
				indexCount++;
			}
	
			// Removes the highest index first, and then the lowest index
			if (missileAlive && asteroidAlive) {
				if (missileAt > asteroidAt) {
					store.remove(missileAt);
					store.remove(asteroidAt);
				}
				else {
					store.remove(asteroidAt);
					store.remove(missileAt);
				}
				playerScore += 2; // The player score is incremented by 2
				System.out.println("A PS Missile has killed an ASTEROID...");
				
				setChanged();
				notifyObservers(new GameWorldProxy(this));
				return;	
			}
			else {
				System.out.println("Error: There is either no PS MISSILE or no ASTEROID.\n");
				return;
			}
		}
		System.out.println("Error: There is no PLAYER SHIP.\n");
	}
	
	/**
	 * If a PlayerShip, its missile, and a NonPlayerShip exist, then one missile and one
	 * NonPlayerShip are removed from the vector. The player score is incremented.
	 */
	public void psMissileEliminatesNPS() {
		IIterator gameIterator = getIterator();
		GameObject obj;
		
		// Variables to save object indexes for future use
		int missileAt = 0;
		int npsAt = 0;
		int indexCount = 0;
		
		boolean npsAlive = false;
		boolean missileAlive = false;
		
		if (hasPS) { // Checks if a PS exists
			while (gameIterator.hasNext()) { // Checks to see if there's another element in the vector
				obj = gameIterator.getNext();
				if (obj instanceof NonPlayerShip) { // Checks if the current element is a NonPlayerShip object
					npsAlive = true;
					npsAt = indexCount;
				}
				if (obj instanceof Missile) { // Checks if the current element is a Missile object
					if (((Missile)obj).getOwner() == "Player Ship") {
						missileAlive = true;
						missileAt = indexCount;
					}
				}
				indexCount++;
			}
			
			// Removes the highest index first, and then the lowest index
			if (missileAlive && npsAlive) {
				if (missileAt > npsAt) {
					store.remove(missileAt);
					store.remove(npsAt);
				}
				else {
					store.remove(npsAt);
					store.remove(missileAt);
				}
				playerScore += 5; // The player score is incremented by 5
				System.out.println("A PS Missile has eliminated an NPS...");
				
				setChanged();
				notifyObservers(new GameWorldProxy(this));
				return;
			}
			else {
				System.out.println("Error: There is either no PS MISSILE or no NON-PLAYER SHIP.\n");
				return;
			}
		}
		System.out.println("Error: There is no PLAYER SHIP.\n");
	}
	
	/**
	 * If a PlayerShip, a NonPlayerShip, and its missile exists, then one missile and the
	 * PlayerShip are removed from the vector. The number of lives remaining is decremented.
	 * The user is asked if he/she would like to play again.
	 */
	public void npsMissileExplodesPS() {
		IIterator gameIterator = getIterator();
	    GameObject obj;
		
		int psAt = 0;
		int missileAt = 0;
		int indexCount = 0;
		
		boolean npsAlive = false;
		boolean missileAlive = false;
		
		if (hasPS) { // Checks if a PS exists
			while (gameIterator.hasNext()) { // Checks to see if there's another element in the vector
	            obj = gameIterator.getNext();
				if (obj instanceof PlayerShip) { // Checks if the current element is a PlayerShip object
					psAt = indexCount;
				}
				if (obj instanceof NonPlayerShip) // Checks if the current element is a NonPlayerShip object
					npsAlive = true;
				if (obj instanceof Missile) { // Checks if the current element is a Missile object
					if (((Missile)obj).getOwner() == "Non-Player Ship") {
						missileAlive = true;
						missileAt = indexCount;
					}
				}
				indexCount++;
			}
			
			// Removes the highest index first, and then the lowest index
			if (missileAlive && npsAlive) {
				if (psAt > missileAt) {
					store.remove(psAt);
					store.remove(missileAt);
				}
				else {
					store.remove(missileAt);
					store.remove(psAt);
				}
				numLives -= 1; // Number of lives decremented by 1
				hasPS = false;
				System.out.println("An NPS Missile has exploded the PS...");
				// A dialog box pops up asking if the user would like to play again
				if (numLives == 0) {
					if (soundOn)
						sGameOver.playSound("Game Over.wav");
					Boolean bOk = Dialog.show( "Game Over",  "You scored " + this.playerScore + "\n\nPlay again?", "No", "Yes");
					System.out.println("Game Over"); 
					
					if (!bOk) {
						this.init(); // call GameWorld's init()
						
						setChanged();
						notifyObservers(new GameWorldProxy(this));
						return;
					}
					else {
						Display.getInstance().exitApplication();
					}
				}
				setChanged();
				notifyObservers(new GameWorldProxy(this));
				return;
			}
			else {
				System.out.println("Error: There is either no MISSILE or no NON-PLAYER SHIP.\n");
				return;
			}
		}
		System.out.println("Error: There is no PLAYER SHIP.\n");
	}
	
	/**
	 * If a PlayerShip and an asteroid exists, then the PlayerShip and an asteroid
	 * are removed from the vector. The number of lives remaining is decremented.
	 * The user is asked if he/she would like to play again.
	 */
	public void psCrashesIntoAsteroid() {
		IIterator gameIterator = getIterator();
        GameObject obj;
		
		int psAt = 0;
		int aseteroidAt = 0;
		int indexCount = 0;
		
		boolean asteroidAlive = false;
	
		if (hasPS) { // Checks if a PS exists
			while (gameIterator.hasNext()) { // Checks to see if there's another element in the vector
				obj = gameIterator.getNext();
				if (obj instanceof PlayerShip) { // Checks if the current element is a PlayerShip object
					psAt = indexCount;
				}
				if (obj instanceof Asteroid) { // Checks if the current element is an Asteroid object
					asteroidAlive = true;
					aseteroidAt = indexCount;
				}
				indexCount++;
			}
			 
			// Removes the highest index first, and then the lowest index
			if (asteroidAlive) {
				if (psAt > aseteroidAt) {
					store.remove(psAt);
					store.remove(aseteroidAt);
				}
				else {
					store.remove(aseteroidAt);
					store.remove(psAt);
				}
				numLives -= 1; // Number of lives decremented by 1
				hasPS = false;
				System.out.println("The PS has crashed into an ASTEROID...");
				// A dialog box pops up asking if the user would like to play again
				if (numLives == 0) {
					if (soundOn)
						sGameOver.playSound("Game Over.wav");
					Boolean bOk = Dialog.show( "Game Over",  "You scored " + this.playerScore + "\n\nPlay again?", "No", "Yes");
	
					if (!bOk) {
						this.init(); // call GameWorld's init()
						
						setChanged();
						notifyObservers(new GameWorldProxy(this));
						return;
					}
					else {
						Display.getInstance().exitApplication();
					}
				}
				setChanged();
				notifyObservers(new GameWorldProxy(this));
				return;
			}
			else { 
				System.out.println("Error: There is no ASTEROID.\n");
				return;
			}
		}
		System.out.println("Error: There is no PLAYER SHIP.\n");
	}
	
	/**
	 * If a PlayerShip and a NonPlayerShip exists, then the PlayerShip and a NonPlayerShip
	 * are removed from the vector. The number of lives remaining is decremented. The user
	 * is asked if he/she would like to play again.
	 */
	public void psHitsNPS( ) {
		IIterator gameIterator = getIterator();
        GameObject obj;
		
		int psAt = 0;
		int npsAt = 0;
		int indexCount = 0; 
		
		boolean npsAlive = false;
		
		if (hasPS) { // Checks if a PS exists
			 while (gameIterator.hasNext()) { // Checks to see if there's another element in the vector
                obj = gameIterator.getNext();
				if (obj instanceof PlayerShip) { // Checks if the current element is a PlayerShip object
					psAt = indexCount;
				}
				if (obj instanceof NonPlayerShip) { // Checks if the current element is a NonPlayerShip object
					npsAlive = true;
					npsAt = indexCount;
				}
				indexCount++;
			}
			 
			// Removes the highest index first, and then the lowest index
			if (npsAlive) {
				if (psAt > npsAt) {
					store.remove(psAt);
					store.remove(npsAt);
				}
				else {
					store.remove(npsAt);
					store.remove(psAt);
				}
				numLives -= 1; // Number of lives decremented by 1
				hasPS = false;
				System.out.println("The PS has hit an NPS...");
				// A dialog box pops up asking if the user would like to play again
				if (numLives == 0) {
					if (soundOn)
						sGameOver.playSound("Game Over.wav");
					Boolean bOk = Dialog.show( "Game Over",  "You scored " + this.playerScore + "\n\nPlay again?", "No", "Yes");
	
					if (!bOk) {
						this.init(); // call GameWorld's init()
						
						setChanged();
						notifyObservers(new GameWorldProxy(this));
						return;
					}
					else {
						Display.getInstance().exitApplication();
					}
				}
				setChanged();
				notifyObservers(new GameWorldProxy(this));
				return;
			}
			else { 
				System.out.println("Error: There is no NON-PLAYER SHIP.\n");
				return;
			}
		}
		System.out.println("Error: There is no PLAYER SHIP.\n");
	}
	
	/**
	 * The first for loop counts the number of asteroids in the vector.
	 * If there are 2 asteroids, then another for loop will loop through the vector
	 * again and start removing asteroids until the number of asteroids removed
	 * becomes 2.
	 */
	public void asteroidExtermination() {
		IIterator gameIterator = getIterator();
		GameObject obj;
	    
		int asteroidCount = 0;
		int indexCount = 0;
		int a1 = -1;
		int a2 = -1;
		
		while(gameIterator.hasNext()) {  // Checks to see if there's another element in the vector
			obj = gameIterator.getNext();
			if (obj instanceof Asteroid) { // Checks if the current element is an Asteroid object
				asteroidCount++;
				if(a1 < 0) {
					a1 = indexCount;
				}
				if(a1 >= 0 && a2 < 0) {
					a2 = indexCount;
				}
			}
			indexCount++;	
		}
		
		if (asteroidCount >= 2) { // Loops only if there are 2+ asteroids in the vector
			store.remove(a2);
			store.remove(a1);
		
			System.out.println("Two ASTEROIDS have crashed into and exterminated eachother...");
				
			setChanged();
			notifyObservers(new GameWorldProxy(this));
			return;
		}
		System.out.println("Error: There are less than two ASTEROIDS...\n");
	}
		
	
	
	/**
	 * If a NonPlayerShip and an asteroid exists, then a NonPlayerShip and an asteroid
	 * are removed from the vector.
	 */
	public void asteroidImpactsNPS() {
		IIterator gameIterator = getIterator();
		GameObject obj;
		
		int npsAt = 0;
		int asteroidAt = 0;
		int indexCount = 0; 
		
		boolean npsAlive = false;
		boolean asteroidAlive = false;
	
		
		while(gameIterator.hasNext()) {  // Checks to see if there's another element in the vector
			obj = gameIterator.getNext();
			if (obj instanceof NonPlayerShip) { // Checks if the current element is a NonPlayerShip object
				npsAlive = true;
				npsAt = indexCount;
			}
			if (obj instanceof Asteroid) { // Checks if the current element is an Asteroid object
				asteroidAlive = true;
				asteroidAt = indexCount;
			}
			indexCount++;
		}
		// Removes the highest index first, and then the lowest index
		if (asteroidAlive && npsAlive) {
			if (asteroidAt > npsAt) {
				store.remove(asteroidAt);
				store.remove(npsAt);
			}
			else {
				store.remove(npsAt);
				store.remove(asteroidAt);
			}
			System.out.println("An ASTEROID has impacted an NPS...");
			
			setChanged();
			notifyObservers(new GameWorldProxy(this));
			return;
		}
		System.out.println("Error: There is either no NON-PLAYER SHIP or no ASTEROID.\n");
	}
	
	/**
	 * If a PlayerShip exists, then its speed is increased
	 */
	public void increasePSSpeed() {
		IIterator gameIterator = getIterator();
		GameObject obj;
		
		while(gameIterator.hasNext()) {  // Checks to see if there's another element in the vector
			obj = gameIterator.getNext();
			if (obj instanceof PlayerShip) { // Checks if the current element is a PlayerShip object
				((PlayerShip)obj).increaseSpeed(mapWidth);
				System.out.println("The PS speed has increased...");
				
				setChanged();
				notifyObservers(new GameWorldProxy(this));
				return;
			}
		}
		System.out.println("Error: There is no PLAYER SHIP.\n");
	}
	
	/**
	 * If a PlayerShip exists, then its speed is decreased
	 */
	public void decreasePSSpeed() {
		IIterator gameIterator = getIterator();
		GameObject obj;
		
		while(gameIterator.hasNext()) { // Checks to see if there's another element in the vector
			obj = gameIterator.getNext();
			if (obj instanceof PlayerShip) { // Checks if the current element is a PlayerShip object
				((PlayerShip)obj).decreaseSpeed(mapWidth);
				System.out.println("The PS speed has decreased...");
				
				setChanged();
				notifyObservers(new GameWorldProxy(this));
				return;
			}
		}
		System.out.println("Error: There is no PLAYER SHIP.\n");
	}
	
	/**
	 * If a PlayerShip exists, then turn the direction left
	 */
	public void turnPSLeft() {
		IIterator gameIterator = getIterator();
		GameObject obj;
		
		while(gameIterator.hasNext()) {  // Checks to see if there's another element in the vector
			obj = gameIterator.getNext();
			if (obj instanceof PlayerShip) { // Checks if the current element is a PlayerShip object
				((PlayerShip)obj).turnLeft();
				System.out.println("The PS has turned left...");
				
				setChanged();
				notifyObservers(new GameWorldProxy(this));
				return;
			}
		}
		System.out.println("Error: There is no PLAYER SHIP.\n");
	}
	
	/**
	 * If a PlayerShip exists, then turn the direction right
	 */
	public void turnPSRight() {
		IIterator gameIterator = getIterator();
		GameObject obj;
		
		while(gameIterator.hasNext()) {  // Checks to see if there's another element in the vector
			obj = gameIterator.getNext();
			if (obj instanceof PlayerShip) { // Checks if the current element is a PlayerShip object
				((PlayerShip)obj).turnRight();
				System.out.println("The PS has turned right...");
				
				setChanged();
				notifyObservers(new GameWorldProxy(this));
				return;
			}
		}
		System.out.println("Error: There is no PLAYER SHIP.\n");
	}
	
	/**
	 * Sets the location to the default position in the center of the screen
	 */
	public void jumpHyperspace() {
		IIterator gameIterator = getIterator();
		GameObject obj;
		
		while(gameIterator.hasNext()) { // Checks to see if there's another element in the vector
			obj = gameIterator.getNext();
			if (obj instanceof PlayerShip) { // Checks if the current element is a PlayerShip object
				((PlayerShip)obj).setLocation((double)mapWidth/2, (double)mapHeight/2);
				((PlayerShip)obj).setMLLocation((double)mapWidth/2, (double)mapHeight/2);
				System.out.println("The PS has returned to its default position...");
				
				setChanged();
				notifyObservers(new GameWorldProxy(this));
				return;
			}
		}
		System.out.println("Error: There is no PLAYER SHIP.\n");
	}
	
	/**
	 * Movable objects have their position updated based on their current direction and speed.
	 * Each missile's fuel level is decremented and missiles with 0 fuel are removed.
	 * The SpaceStation toggles its light based on the blinkRate and the clock is incremented.
	 */
	public void tick(int time) {
		IIterator gameIterator = getIterator();
		GameObject obj;
		int indexCount = 0;
		clock += 20;
		
		while(gameIterator.hasNext()) { // Checks to see if there's another element in the vector
			obj = gameIterator.getNext();
			if (obj instanceof IMovable) {  // Checks if the current element is an instance of IMovable
				((IMovable)obj).setWidth(mapWidth);
				((IMovable)obj).setHeight(mapHeight);
				((IMovable)obj).move(time);
				if (obj instanceof PlayerShip) {
					((PlayerShip)obj).setMLWidth(mapWidth);
					((PlayerShip)obj).setMLHeight(mapHeight);
					((PlayerShip)obj).setMLLocation(((PlayerShip)obj).getLocationX(), ((PlayerShip)obj).getLocationY());
					((PlayerShip)obj).moveML(time);
				}
				if (obj instanceof NonPlayerShip)
					((NonPlayerShip)obj).moveML(time);
				if (obj instanceof Missile) {  // Checks if the current element is a Missile object
					((Missile)obj).decrementFuelLevel();
					if (((Missile)obj).getFuelLevel() == 0) { // If the Missile fuel level reaches 0
						store.remove(indexCount);
						gameIterator.decrementIndex();
						indexCount--;
					}
				}
			}
			if (obj instanceof SpaceStation) { // Checks if the current element is a SpaceStation object
				if (clock % (((SpaceStation)obj).getBlinkRate() * 20) == 0) { // If the clock % blinkRate == 0
					((SpaceStation)obj).toggleLight();
				}
			}
			indexCount++;
		}
		System.out.println("Time waits for no one...");
		
		setChanged();
		notifyObservers(new GameWorldProxy(this));
	}
	
	/**
	 * Asks the user if they'd like to quit
	 */
	public void quit() {
		Boolean bOk = Dialog.show("Confirm quit", "Are you sure you want to quit?", "Cancel", "Yes");
	     if (!bOk) {
	          Display.getInstance().exitApplication();
	    }
	}
}

