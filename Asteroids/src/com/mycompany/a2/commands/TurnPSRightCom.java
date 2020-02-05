package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class TurnPSRightCom extends Command {
	private GameWorld gw; 
	
	public TurnPSRightCom(GameWorld gw) {
		super("Turn PS Right");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
	    System.out.println("Turn PS Right command is invoked...");
		gw.turnPSRight();
	}
}
