package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class PSHitsNPSCom extends Command {
	private GameWorld gw;
	
	public PSHitsNPSCom(GameWorld gw) {
		super("PS Hits NPS");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		System.out.println("PS Hits NPS command invoked...");
		gw.psHitsNPS();
	}
}
