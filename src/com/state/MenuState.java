package com.state;

import java.awt.Graphics;
import com.main.Resources;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public class MenuState extends State {

	@Override
	public void init() {
		System.out.println("Entered MenuState");
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		//System.out.println("currentState");	
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(Resources.welcome,0,0,null);
		

	}

	@Override
	public void onClick(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("onclick");
		setCurrentState(new PlayState());
		
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("onkeypress");
		
	}

	@Override
	public void onkeyRelease(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("onkeyrelease");
		
	}
	
	
	

}
