package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class PauseCom extends Command {
	private GameWorld gw;
	private boolean bPause = false;
	
	public PauseCom(GameWorld gw) {
		super("Pause/Resume");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		bPause = !bPause;
		if (ev.getKeyEvent() != -1) {
			System.out.println("Pause command has been invoked...");
			if (bPause)
				setCommandName("Resume");
			else
				setCommandName("Pause");
		}
	}
}
