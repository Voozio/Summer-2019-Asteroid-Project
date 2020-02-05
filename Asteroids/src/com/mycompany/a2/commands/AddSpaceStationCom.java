package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class AddSpaceStationCom extends Command {
	private GameWorld gw;
	
	public AddSpaceStationCom(GameWorld gw) {
		super("+ Space Station");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		if (ev.getKeyEvent() != -1) {
			System.out.println("Add Space Station command has been invoked...");
			gw.addBlinkingSpaceStation();
		}
	}
}
