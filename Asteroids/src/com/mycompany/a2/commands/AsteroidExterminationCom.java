package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class AsteroidExterminationCom extends Command {
	private GameWorld gw;
	
	public AsteroidExterminationCom(GameWorld gw) {
		super("Asteroid Extermination");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
	    System.out.println("Asteroid Extermination command is invoked...");
		gw.asteroidExtermination();
	}
}
