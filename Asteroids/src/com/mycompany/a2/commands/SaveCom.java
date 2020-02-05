package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SaveCom extends Command {
	public SaveCom() {
		super("Save");
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
	    System.out.println("Save command is invoked...");
	}
}
