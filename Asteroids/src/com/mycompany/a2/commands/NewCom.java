
package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class NewCom extends Command {
	public NewCom() {
		super("New");
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
	    System.out.println("New command is invoked...");
	}
}
