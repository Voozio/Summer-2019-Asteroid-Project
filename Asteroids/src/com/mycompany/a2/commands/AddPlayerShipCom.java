package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class AddPlayerShipCom extends Command {
	private GameWorld gw;
	
	public AddPlayerShipCom(GameWorld gw) {
		super("+ PS (1)");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		if (ev.getKeyEvent() != -1) {
			System.out.println("Add Player Ship command has been invoked...");
			gw.addPlayerShip();
		}
	}
}
