package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class ReloadPSMissileCom extends Command {
	private GameWorld gw; 
	
	public ReloadPSMissileCom(GameWorld gw) {
		super("Reload PS Missile");
		this.gw = gw;
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Reload PS Missile Command has been invoked...");
		gw.reloadNewPSMissiles();
	}
}
