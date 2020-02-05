package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.GameWorld;

public class QuitCom extends Command {
	private GameWorld gw; 
	
	public QuitCom(GameWorld gw) {
		super("Quit");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
	    System.out.println("Quit command is invoked...");
		gw.quit();
	}
}
