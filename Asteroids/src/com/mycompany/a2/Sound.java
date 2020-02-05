package com.mycompany.a2;

import java.io.InputStream;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class Sound {
	//The sound volume is adjusted and an audio file is played
	public void playSound(String fileName) {
		try {
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);
			Media m = MediaManager.createMedia(is, "audio/wav");
			m.setVolume(50);
			m.play();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
