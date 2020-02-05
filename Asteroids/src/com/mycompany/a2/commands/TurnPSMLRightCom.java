package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;
import com.mycompany.a2.Sound;

public class TurnPSMLRightCom extends Command {
	private GameWorld gw;
	private Sound sRotate;
	
	public TurnPSMLRightCom(GameWorld gw){
		super("Turn PS ML Right");
		this.gw = gw;
		sRotate = new Sound();
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Turn PS ML Right command has been invoked...");
		if (gw.getSoundOn())
			if (gw.getHasPS())
				sRotate.playSound("Rotating Machine.wav");
		gw.turnPSMissileLauncherRight();
	}
}
