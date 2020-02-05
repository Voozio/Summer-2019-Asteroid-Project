package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class PSMissileEliminatesNPSCom extends Command {
	private GameWorld gw;
	
	public PSMissileEliminatesNPSCom(GameWorld gw) {
		super("PS Missile Eliminates NPS");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		System.out.println("PS Missile Eliminates NPS command has been invoked...");
		gw.psMissileEliminatesNPS();
	}
}
