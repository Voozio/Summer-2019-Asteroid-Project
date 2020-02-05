package com.mycompany.a2.commands;

import com.mycompany.a2.*;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class FirePSMissileCom extends Command {
	private GameWorld gw;
	private Sound sFireMissile;
	
	public FirePSMissileCom(GameWorld gw) {
		super("+ PS Fire");
		this.gw = gw;
		sFireMissile = new Sound();
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		if (ev.getKeyEvent() != -1) {
			System.out.println("Fire PS Missile command has been invoked...");
			if (gw.getSoundOn())
				if (gw.getPSMissiles() != 0)
					sFireMissile.playSound("Fire Missile.wav");
			gw.firePSMissile();
		}
	}
}
