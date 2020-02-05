package com.mycompany.a2;

public interface IGameWorld {
	/**
	 * Returns the iterator
	 */
	public IIterator getIterator();
	
	/**
	 * Returns the number of lives remaining
	 */
	public abstract int getNumLives();
	
	/**
	 * Returns the current player score
	 */
	public abstract int getPlayerScore();
	
	/**
	 * Returns the number of PS missiles remaining
	 */
	public abstract int getPSMissiles();
	
	/**
	 * Returns true if soundOn is true, else false
	 */
	public boolean getSoundOn();
	
	/**
	 * Returns "On" if soundOn is true, else "Off"
	 */
	public abstract String isSoundOn(boolean soundOn);
	
	/**
	 * Returns the elapsed time
	 */
	public abstract int getClock();
}
