package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class NPSMissileExplodesPSCom extends Command {
	private GameWorld gw;
	
	public NPSMissileExplodesPSCom(GameWorld gw) {
		super("NPS Missile Explodes PS");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		System.out.println("NPS Missile Explodes PS command has been invoked...");
		gw.npsMissileExplodesPS();
	}
}
