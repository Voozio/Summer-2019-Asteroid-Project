package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;
import com.mycompany.a2.Sound;

public class TurnPSMLLeftCom extends Command {
	private GameWorld gw;
	private Sound sRotate;
	
	public TurnPSMLLeftCom(GameWorld gw) {
		super("Turn PS ML Left");
		this.gw = gw;
		sRotate = new Sound();
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		System.out.println("Turn PS ML Left command has been invoked...");
		if (gw.getSoundOn())
			if (gw.getHasPS())
				sRotate.playSound("Rotating Machine.wav");
		gw.turnPSMissileLauncherLeft();
	}
}
