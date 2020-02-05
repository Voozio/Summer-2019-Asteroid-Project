package com.mycompany.a2;

import com.mycompany.a2.commands.*;
import com.codename1.ui.Form;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Container;
import com.codename1.ui.Toolbar;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;

public class Game extends Form implements Runnable {
	private GameWorld gw;
	private MapView mv;
	private PointsView pv;
	UITimer timer = new UITimer(this);

	/**
	 * A new GameWorld is initialized, it's init() method is called, and then the play() method is called
	 */
	public Game() {
		gw = new GameWorld();
		mv = new MapView();
		pv = new PointsView(gw);
		gw.init();
		gw.addObserver(mv);
		gw.addObserver(pv);
		
		timer.schedule(20, true, this);

		Toolbar wToolbar = new Toolbar();
		Container wContainer = new Container();
		
		// The default layout is set and the containers are added to the Form
		this.setLayout(new BorderLayout());
		this.setToolbar(wToolbar);
		this.add(BorderLayout.NORTH, pv);
		this.add(BorderLayout.WEST, wContainer);
		this.add(BorderLayout.CENTER, mv);
		this.setTitle("Asteroids");
		
		wContainer.add(new Label("Commands"));
		wContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		
		mv.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.rgb(50, 50, 50)));
		
		// The command objects are initialized
		AddAsteroidCom addAsteroidCom = new AddAsteroidCom(gw);
		AddNonPlayerShipCom addNonPlayerShipCom = new AddNonPlayerShipCom(gw);
		AddPlayerShipCom addPlayerShipCom = new AddPlayerShipCom(gw);
		AddSpaceStationCom addSpaceStationCom = new AddSpaceStationCom(gw);
		AsteroidExterminationCom asteroidExterminationCom = new AsteroidExterminationCom(gw);
		AsteroidImpactsNPSCom asteroidImpactsNPSCom = new AsteroidImpactsNPSCom(gw);
		DecreasePSSpeedCom decreasePSSpeedCom = new DecreasePSSpeedCom(gw);
		FirePSMissileCom firePSMissileCom = new FirePSMissileCom(gw);
		IncreasePSSpeedCom increasePSSpeedCom = new IncreasePSSpeedCom(gw);
		JumpHyperspaceCom jumpHyperspaceCom = new JumpHyperspaceCom(gw);
		LaunchNPSMissileCom launchNPSMissileCom = new LaunchNPSMissileCom(gw);
		NPSMissileExplodesPSCom npsMissileExplodesPSCom = new NPSMissileExplodesPSCom(gw); 
		PSCrashesAsteroidCom psCrashesAsteroidCom = new PSCrashesAsteroidCom(gw);
		PSHitsNPSCom psHitsNPSCom = new PSHitsNPSCom(gw);
		PSMissileEliminatesNPSCom psMissileEliminatesNPSCom = new PSMissileEliminatesNPSCom(gw);
		PSMissileKillsAsteroidCom psMissileKillsAsteroidCom = new PSMissileKillsAsteroidCom(gw);
		ReloadPSMissileCom reloadPSMissileCom = new ReloadPSMissileCom(gw);
		TurnPSLeftCom turnPSLeftCom = new TurnPSLeftCom(gw);
		TurnPSRightCom turnPSRightCom = new TurnPSRightCom(gw);
		TurnPSMLLeftCom turnPSMLLeftCom = new TurnPSMLLeftCom(gw);
		TurnPSMLRightCom turnPSMLRightCom = new TurnPSMLRightCom(gw);
		TickCom tickCom = new TickCom(gw);
		PauseCom pauseCom = new PauseCom(gw);
		
		// The command objects for the side menu are initialized
		NewCom newCom = new NewCom();
		SaveCom saveCom = new SaveCom();
		UndoCom undoCom = new UndoCom();
		AboutCom aboutCom = new AboutCom();
		QuitCom quitCom = new QuitCom(gw);
		SoundCom soundCom = new SoundCom(gw);
		CheckBox cbox = new CheckBox("Sound");
		
		// The control panel buttons are initialized
		Button bAddAsteroid = new Button("Add Asteroid");
		Button bAddPS = new Button("Add Player Ship");
		Button bAddNPS = new Button("Add Non-Player Ship");
		Button bAddSpaceStation = new Button("Add Space Station");
		Button bFirePSMissile = new Button("Fire PS Missile");
		Button bJumpHyperspace = new Button("Jump Hyperspace");
		Button bPause = new Button("Pause/Resume");
		
		// The side menu buttons are initialized
		Button bNew = new Button("New");
		Button bSave = new Button ("Save");
		Button bUndo = new Button ("Undo");
		Button bAbout = new Button("About");
		Button bQuit = new Button("Quit");
		
		// The control panell buttons are customized
		bAddAsteroid.getAllStyles().setBgTransparency(200);
		bAddAsteroid.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 200, 200));
		bAddAsteroid.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		bAddAsteroid.getAllStyles().setPadding(TOP, 2);
		bAddAsteroid.getAllStyles().setPadding(BOTTOM, 2);
		bAddAsteroid.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.rgb(50, 50, 50)));

		bAddPS.getAllStyles().setBgTransparency(200);
		bAddPS.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 200, 200));
		bAddPS.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		bAddPS.getAllStyles().setPadding(TOP, 2);
		bAddPS.getAllStyles().setPadding(BOTTOM, 2);
		bAddPS.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.rgb(50, 50, 50)));
		
		bAddNPS.getAllStyles().setBgTransparency(200);
		bAddNPS.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 200, 200));
		bAddNPS.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		bAddNPS.getAllStyles().setPadding(TOP, 2);
		bAddNPS.getAllStyles().setPadding(BOTTOM, 2);
		bAddNPS.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.rgb(50, 50, 50)));
		
		bAddSpaceStation.getAllStyles().setBgTransparency(200);
		bAddSpaceStation.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 200, 200));
		bAddSpaceStation.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		bAddSpaceStation.getAllStyles().setPadding(TOP, 2);
		bAddSpaceStation.getAllStyles().setPadding(BOTTOM, 2);
		bAddSpaceStation.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.rgb(50, 50, 50)));
		
		bFirePSMissile.getAllStyles().setBgTransparency(200);
		bFirePSMissile.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 200, 200));
		bFirePSMissile.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		bFirePSMissile.getAllStyles().setPadding(TOP, 2);
		bFirePSMissile.getAllStyles().setPadding(BOTTOM, 2);
		bFirePSMissile.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.rgb(50, 50, 50)));
		
		bJumpHyperspace.getAllStyles().setBgTransparency(200);
		bJumpHyperspace.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 200, 200));
		bJumpHyperspace.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		bJumpHyperspace.getAllStyles().setPadding(TOP, 2);
		bJumpHyperspace.getAllStyles().setPadding(BOTTOM, 2);
		bJumpHyperspace.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.rgb(50, 50, 50)));
		
		bPause.getAllStyles().setBgTransparency(200);
		bPause.getUnselectedStyle().setBgColor(ColorUtil.rgb(0, 200, 200));
		bPause.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		bPause.getAllStyles().setPadding(TOP, 2);
		bPause.getAllStyles().setPadding(BOTTOM, 2);
		bPause.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.rgb(50, 50, 50)));

		// The check box is customized
		cbox.setSelected(true);
		cbox.getAllStyles().setBgTransparency(255);
		cbox.getAllStyles().setBgColor(ColorUtil.rgb(238, 29, 131));
		cbox.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		cbox.getAllStyles().setPadding(1, 1, 1, 1);
		cbox.getAllStyles().setMargin(5, 5, 5, 5);
		
		// The control panel buttons are given their corresponding commands
		bAddAsteroid.setCommand(addAsteroidCom);
		bAddPS.setCommand(addPlayerShipCom);
		bAddNPS.setCommand(addNonPlayerShipCom);
		bAddSpaceStation.setCommand(addSpaceStationCom);
		bFirePSMissile.setCommand(firePSMissileCom);
		bJumpHyperspace.setCommand(jumpHyperspaceCom);
		bPause.setCommand(pauseCom);
		
		// The side menu buttons are given their corresponding commands
		bNew.setCommand(newCom);
		bSave.setCommand(saveCom);
		bUndo.setCommand(undoCom);
		bAbout.setCommand(aboutCom);
		cbox.setCommand(soundCom);
		bQuit.setCommand(quitCom);
		
		// The control panel buttons are added to the west container
		wContainer.add(bAddAsteroid);
		wContainer.add(bAddPS);
		wContainer.add(bAddNPS);
		wContainer.add(bAddSpaceStation);
		wContainer.add(bFirePSMissile);
		wContainer.add(bJumpHyperspace);
		wContainer.add(bPause);

		// The side menu buttons are added to the toolbar container
		wToolbar.addCommandToLeftSideMenu(newCom);
		wToolbar.addCommandToLeftSideMenu(saveCom);
		wToolbar.addCommandToLeftSideMenu(undoCom);
		wToolbar.addCommandToLeftSideMenu(aboutCom);
		wToolbar.addCommandToLeftSideMenu(quitCom);
		wToolbar.addComponentToSideMenu(cbox);
		
		// The keys are binded to commands using KeyListener
		addKeyListener('a', addAsteroidCom);
		addKeyListener('y', addNonPlayerShipCom);
		addKeyListener('s', addPlayerShipCom);
		addKeyListener('b', addSpaceStationCom);
		addKeyListener('x', asteroidExterminationCom);
		addKeyListener('I', asteroidImpactsNPSCom);
		addKeyListener('d', decreasePSSpeedCom);
		addKeyListener(-92, decreasePSSpeedCom);
		addKeyListener(-90, firePSMissileCom);
		addKeyListener('i', increasePSSpeedCom);
		addKeyListener(-91, increasePSSpeedCom);
		addKeyListener('j', jumpHyperspaceCom);
		addKeyListener('L', launchNPSMissileCom);
		addKeyListener('E', npsMissileExplodesPSCom);
		addKeyListener('c', psCrashesAsteroidCom);
		addKeyListener('h', psHitsNPSCom);
		addKeyListener('e', psMissileEliminatesNPSCom);
		addKeyListener('k', psMissileKillsAsteroidCom);
		addKeyListener('n', reloadPSMissileCom);
		addKeyListener('l', turnPSLeftCom);
		addKeyListener(-93, turnPSLeftCom);
		addKeyListener('r', turnPSRightCom);
		addKeyListener(-94, turnPSRightCom);
		addKeyListener(44, turnPSMLLeftCom);
		addKeyListener(46, turnPSMLRightCom);
		addKeyListener('t', tickCom);
		addKeyListener('Q', quitCom);
		
		show();
		
		//Width, height, and max speed are set relative to the MapView container
		gw.setWidth(mv.getWidth());
		gw.setHeight(mv.getHeight());
		gw.setMaxSpeed(mv.getWidth() / 30);
	}
	
	/**
	 * A tick is invoked every 20 milliseconds
	 */
	public void run() {
		gw.tick(100);
		mv.repaint();
	}
}
