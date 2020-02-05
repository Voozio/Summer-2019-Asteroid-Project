package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class AboutCom extends Command {
	public AboutCom() {
		super("About");
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		Dialog.show("About", "Created By: Jamie Kim and Justin Voo", "Ok", "Ok");
	    System.out.println("Created By: Jamie Kim and Justin Voo");
	}
}
