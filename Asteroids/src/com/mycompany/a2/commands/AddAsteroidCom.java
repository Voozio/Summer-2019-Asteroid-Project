package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class AddAsteroidCom extends Command {
	private GameWorld gw;
	
	public AddAsteroidCom(GameWorld gw) {
		super("+ Asteroid");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		if (ev.getKeyEvent() != -1) {
			System.out.println("Add Asteroid command has been invoked...");
			gw.addAsteroid();
		}
	}
}
