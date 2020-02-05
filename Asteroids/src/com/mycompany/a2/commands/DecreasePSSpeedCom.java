package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class DecreasePSSpeedCom extends Command {
	private GameWorld gw;
	
	public DecreasePSSpeedCom(GameWorld gw) {
		super("Decrease PS Speed");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
	    System.out.println("Decrease PS Speed command is invoked...");
		gw.decreasePSSpeed();
	}
}
