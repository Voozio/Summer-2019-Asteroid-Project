package com.mycompany.a2.commands;

import com.mycompany.a2.*;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class LaunchNPSMissileCom extends Command {
	private GameWorld gw;
	
	public LaunchNPSMissileCom(GameWorld gw) {
		super("Launch NPS Missile");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Launch NPS Missile command has been invoked...");
		gw.launchNPSMissile();
	}
}
