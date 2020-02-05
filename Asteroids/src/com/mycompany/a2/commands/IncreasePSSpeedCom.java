package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class IncreasePSSpeedCom extends Command {
	private GameWorld gw;
	
	public IncreasePSSpeedCom(GameWorld gw) {
		super("Increase PS Speed");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
	    System.out.println("Increase PS Speed command is invoked...");
		gw.increasePSSpeed();
	}
}
