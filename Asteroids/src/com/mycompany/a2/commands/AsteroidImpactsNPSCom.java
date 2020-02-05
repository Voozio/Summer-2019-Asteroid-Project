package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class AsteroidImpactsNPSCom extends Command {
	private GameWorld gw;
	
	public AsteroidImpactsNPSCom(GameWorld gw) {
		super("Asteroid Impacts NPS");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
	    System.out.println("Asteroid Impacts NPS command is invoked...");
		gw.asteroidImpactsNPS();
	}
}
