package com.state;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.main.Resources;
/**
 * º”‘ÿ◊¥Ã¨¿‡
 * @author Lenovo
 *
 */
public class LoadState extends State {

	@Override
	public void init() {
		Resources.load();
		System.out.println("Loaded Successfully");
	}

	@Override
	public void update() {
		setCurrentState(new MenuState());
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onkeyRelease(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
