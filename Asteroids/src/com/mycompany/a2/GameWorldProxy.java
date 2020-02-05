package com.mycompany.a2;

import java.util.Observable;

public class GameWorldProxy extends Observable implements IGameWorld {
	private GameWorld gw;
	
	public GameWorldProxy(GameWorld gw) {
		this.gw = gw;
	}
	
	/**
	 * Returns the iterator
	 */
	public IIterator getIterator() {
		return gw.getIterator();
	}
	
	/**
	 * Returns the number of lives remaining
	 */
	public int getNumLives() {
		return gw.getNumLives();
	}
	
	/**
	 * Returns the current player score
	 */
	public int getPlayerScore() {
		return gw.getPlayerScore();
	}
	
	/**
	 * Returns the number of PS missiles remaining
	 */
	public int getPSMissiles() {
		return gw.getPSMissiles();
	}
	
	/**
	 * Returns true if soundOn is true, else false
	 */
	public boolean getSoundOn() {
		return gw.getSoundOn();
	}
	
	/**
	 * Returns true if soundOn is true, else false
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
		return gw.getClock();
	}
}
