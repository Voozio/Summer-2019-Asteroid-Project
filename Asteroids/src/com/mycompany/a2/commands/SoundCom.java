package com.mycompany.a2.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a2.BGSound;
import com.mycompany.a2.GameWorld;

public class SoundCom extends Command {
	private GameWorld gw;
	private BGSound bgSound;
	private boolean bPause;
	
	public SoundCom(GameWorld gw) {
		super("Sound");
		this.gw = gw;
		bgSound = new BGSound("Interplanetary Odyssey.wav");
		bgSound.playMusic();
		bPause = false;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		bPause = !bPause;
	    System.out.println("Sound command is invoked...");
		gw.setSound();
		if (bPause)
			bgSound.pause();
		else
			bgSound.playMusic();
	}
}
