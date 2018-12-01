package com.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import com.state.LoadState;
import com.state.State;
import com.util.InputHandler;

/**
 * Game¿‡
 * 
 * @author Lenovo
 *
 */
public class Game extends JPanel implements Runnable {

	private int gameWidth;
	private int gameHeight;
	private Image gameImage;

	private Thread gameThread;
	private volatile boolean running;
	private volatile State currentState;
	
	private InputHandler inputHandler;

	public Game(int gameWidth, int gameHeight) {
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		setPreferredSize(new Dimension(gameWidth, gameHeight));
		setBackground(Color.BLACK);
		setFocusable(true);
		requestFocus();

	}
	
	public void setCurrentState(State newState) {
		System.gc();
		currentState = newState;
		newState.init();
		inputHandler.setCurrentState(currentState);
		
	}
	
	public void addNotify() {
		super.addNotify();
		initInput();
		setCurrentState(new LoadState());
		initGame();
	}
	
	private void initGame() {
		running = true;
		gameThread = new Thread(this,"Game Thread");
		gameThread.start();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(running) {
			currentState.update();
			prepareGameImage();
			currentState.render(gameImage.getGraphics());
			repaint();
			try {
				Thread.sleep(14);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.exit(0);
	}
	
	private void prepareGameImage() {
		//System.out.println("image");
		if (gameImage == null) {
			gameImage = createImage(gameWidth, gameHeight);
		}
		Graphics g = gameImage.getGraphics();
		g.clearRect(0, 0, gameWidth, gameHeight);
	}
	
	public void exit() {
		running = false;
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(gameImage == null) {
			return;
		}
		g.drawImage(gameImage, 0, 0, null);
	}
	
	private void initInput() {
		inputHandler = new InputHandler();
		addKeyListener(inputHandler);
		addMouseListener(inputHandler);
	}

}
