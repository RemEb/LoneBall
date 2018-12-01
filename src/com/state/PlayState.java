package com.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.main.GameMain;
import com.main.Resources;
import com.model.Ball;
import com.model.Paddle;

public class PlayState extends State {
	
	private Paddle  paddleLeft,paddleRight;
	
	private static final int PADDLE_WIDTH = 15;
	private static final int PADDLE_HEIGHT = 60;
	
	private int playerScore = 0;
	private Font scoreFont;
	
	private Ball ball;
	private static final int BALL_DIAMETER = 20;
	

	@Override
	public void init() {
		// TODO Auto-generated method stub
		paddleLeft = new Paddle(0,195,PADDLE_WIDTH, PADDLE_HEIGHT);
		paddleRight = new Paddle(785,195,PADDLE_WIDTH,PADDLE_HEIGHT);
		scoreFont = new Font("SansSerif", Font.BOLD, 25);
		ball = new Ball(300,200,BALL_DIAMETER,BALL_DIAMETER);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		paddleLeft.update();
		paddleRight.update();
		ball.update();
		
		if(ballCollides(paddleLeft)) {
			playerScore++;
			ball.onCollideWith(paddleLeft);
			Resources.hit.play();
		} else if(ballCollides(paddleRight)) {
			playerScore++;
			ball.onCollideWith(paddleRight);
			Resources.hit.play();
		} else if(ball.isDead()) {
			playerScore -= 3;
			ball.reset();
		}
		
	}

	@Override
	public void render(Graphics g) {
		// Background
		g.setColor(Resources.darkBlue);
		g.fillRect(0, 0, GameMain.GAME_WIDTH, GameMain.GAME_HEIGHT);
		g.setColor(Resources.darkRed);
		g.fillRect(GameMain.GAME_WIDTH / 2, 0, GameMain.GAME_WIDTH / 2, GameMain.GAME_HEIGHT);
		
		// Separator Line
		g.drawImage(Resources.line, (GameMain.GAME_WIDTH / 2) - 2, 0, null);
		
		// Paddles
		g.setColor(Color.WHITE);
		g.fillRect(paddleLeft.getX(), paddleLeft.getY(), paddleLeft.getWidth(), paddleLeft.getHeight());
		g.fillRect(paddleRight.getX(), paddleRight.getY(), paddleRight.getWidth(), paddleRight.getHeight());
		
		// Ball
		g.drawRect(ball.getX(),ball.getY(),ball.getWidth(),ball.getHeight());
		
		//UI
		g.setFont(scoreFont);
		String playerScoreStr = "" + playerScore;
		g.drawString(playerScoreStr, 350, 40);
		
	}

	@Override
	public void onClick(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("On paly click!");
		
	}

	@Override
	public void onKeyPress(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("On paly keypress");
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			paddleLeft.accelUp();
			paddleRight.accelDown();
		} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			paddleLeft.accelDown();
			paddleRight.accelUp();
		}
	}

	@Override
	public void onkeyRelease(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("On paly keyrealse");
		if(e.getKeyCode() == KeyEvent.VK_UP ||
				e.getKeyCode() == KeyEvent.VK_DOWN) {
			paddleLeft.stop();
			paddleRight.stop();
		}
	}
	
	private boolean ballCollides(Paddle p) {
		return ball.getRect().intersects(p.getRect());
	}
	

}
