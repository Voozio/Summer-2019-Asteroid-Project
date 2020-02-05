package com.mycompany.a2;

import java.util.Observer;
import java.util.Observable;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;

public class PointsView extends Container implements Observer {
	private Label livesValueLabel;
	private Label scoreValueLabel;
	private Label missilesValueLabel;
	private Label soundValueLabel;
	private Label timeValueLabel;
	
	public PointsView(GameWorld gw) {
		Container nContainer = new Container();
		nContainer.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		
		Label livesTextLabel = new Label("Lives: ");
		Label scoreTextLabel = new Label("Score: ");
		Label missilesTextLabel = new Label("Missiles: ");
		Label soundTextLabel = new Label("Sound: ");
		Label timeTextLabel = new Label("Elapsed Time: ");
		
		// The labels are given their default values
		livesValueLabel = new Label("3"); 
		scoreValueLabel = new Label("0");
		missilesValueLabel = new Label("0");
		soundValueLabel = new Label("On");
		timeValueLabel = new Label("0");
		
		livesValueLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		scoreValueLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		missilesValueLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		soundValueLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		timeValueLabel.getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		
		nContainer.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.rgb(50, 50, 50)));
		
		// The labels are added to the container
		nContainer.add(livesTextLabel);
		nContainer.add(livesValueLabel);
		nContainer.add(scoreTextLabel);
		nContainer.add(scoreValueLabel);
		nContainer.add(missilesTextLabel);
		nContainer.add(missilesValueLabel);
		nContainer.add(soundTextLabel);
		nContainer.add(soundValueLabel);
		nContainer.add(timeTextLabel);
		nContainer.add(timeValueLabel);
		
		this.add(nContainer);			
	}
	
	/**
	 * Whenever the Observable has changes, the current number of lives, player
	 * score, Player Ship missiles, sound boolean, and time are updated and 
	 * then repainted.
	 */
	@Override
	public void update(Observable o, Object arg) {
		IGameWorld gw = (IGameWorld) arg;
		int elapsedSec = gw.getClock()/1000;
		int elapsedMin = elapsedSec/60;
		String realTime;
		
		//The time is converted into what looks like an actual clock
		if (elapsedSec % 60 < 10)
			realTime = elapsedMin + ":0" + (elapsedSec % 60);
		else
			realTime = elapsedMin + ":" + (elapsedSec % 60);
		
		this.livesValueLabel.setText("" + gw.getNumLives());
		this.scoreValueLabel.setText("" + gw.getPlayerScore());
		this.missilesValueLabel.setText("" + gw.getPSMissiles());
		this.soundValueLabel.setText("" + gw.isSoundOn(gw.getSoundOn()));
		this.timeValueLabel.setText(realTime);
		
		this.repaint();
	}
}
