package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class JumpHyperspaceCom extends Command {
	private GameWorld gw;
	
	public JumpHyperspaceCom(GameWorld gw) {
		super("Jump");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		if (ev.getKeyEvent() != -1) {
			System.out.println("Jump Hyperspace command is invoked...");
			gw.jumpHyperspace();
		}
	}
}
