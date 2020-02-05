package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class PSCrashesAsteroidCom extends Command {
	private GameWorld gw;
	
	public PSCrashesAsteroidCom(GameWorld gw) {
		super("PS Crashes Asteroid"); 
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		System.out.println("PS Crashes Asteroid Command invoked...");
		gw.psCrashesIntoAsteroid();
	}
}
