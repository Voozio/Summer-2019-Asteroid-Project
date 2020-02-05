package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class AddNonPlayerShipCom extends Command {
	private GameWorld gw;
	
	public AddNonPlayerShipCom(GameWorld gw) {
		super("+ NPS");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		if (ev.getKeyEvent() != -1) {
			System.out.println("Add Non-Player Ship command has been invoked...");
			gw.addNonPlayerShip();
		}
	}
}
