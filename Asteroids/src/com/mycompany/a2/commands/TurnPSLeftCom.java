package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class TurnPSLeftCom extends Command {
	private GameWorld gw; 
	
	public TurnPSLeftCom(GameWorld gw) {
		super("Turn PS Left");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
	    System.out.println("Turn PS Left command is invoked...");
		gw.turnPSLeft();
	}
}
