package edu.cis;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Breakout extends GraphicsProgram {

	private RandomGenerator rgen = RandomGenerator.getInstance();

	double vx;
	double vy;

	// Dimensions of the canvas, in pixels
	// These should be used when setting up the initial size of the game,
	// but in later calculations you should use getWidth() and getHeight()
	// rather than these constants for accurate size information.
	public static final double CANVAS_WIDTH = 420;
	public static final double CANVAS_HEIGHT = 600;

	public static final int NBRICK_COLUMNS = 10; // Number of bricks in each row
	public static final int NBRICK_ROWS = 10; // How many rows of bricks
	public static final double BRICK_SEP = 2.4; 	// Distance between each brick, in pixels

	public static final double BRICK_WIDTH = Math.floor(
			(CANVAS_WIDTH - (NBRICK_COLUMNS + 1.0) * BRICK_SEP) / NBRICK_COLUMNS); 	// Width of each brick
	public static final double BRICK_HEIGHT = 10; 	// Height of the brick
	public static final double BRICK_Y_OFFSET = 40; // Distance between the brick row from the top
	public static final double PADDLE_WIDTH = 60; // The width of the paddle
	public static final double PADDLE_HEIGHT = 15; // The length of the paddle

	public static final double PADDLE_Y_OFFSET = 10; 	// Distance of the paddle up from the bottom
	public static final double BALL_RADIUS = 10; 	// The radius of the ball in pixels

	public static final double VELOCITY_Y = 0.5; // The ball's vertical velocity.

	public static final double VELOCITY_X_MIN = 0.1; 	// The ball's minimum horizontal velocity
	public static final double VELOCITY_X_MAX = 0.5;  //The ball's maximum horizontal velocity
	public static final double DELAY = 1000.0 / 200.0; // The delay between whenever the ball moves
	public static final int NTURNS = 3; // Number of turns
	GRect MyPaddle;
	GRect MyBrick;
	GOval myBall;

	int userlives = 3;

	public static void main(String[] args)
	{
		new Breakout().start();
	}

	public String instructions(){
		return("Welcome to breakout! In this game your goal is to not let the ball touch the bottom of the screen, and to remove all the blocks at the top by hitting the ball with a paddle!");
	}

	public void run() {
		setTitle("Breakout game"); // sets the title of the game
		Color clr [] = {Color.RED,Color.RED,Color.ORANGE,Color.ORANGE,Color.YELLOW,Color.YELLOW,Color.GREEN,Color.GREEN,Color.CYAN,Color.CYAN};
		setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT); //sets up the color format of the bricks
		for(int i = 1; i <= 10; i++)
		{
			for(int x = 0; x < 10;x++)
			{
				setUpBrick(clr[i-1],x*42+BRICK_SEP,i*BRICK_HEIGHT);
			}
		}

		setUpPaddle(); // calling methods
		setUpBall();
		play();
	}
	public void setUpBall()
	{
		myBall = new GOval(BALL_RADIUS,BALL_RADIUS);  // creating the ball's size with already set radius
		myBall.setFillColor(Color.GRAY);
		myBall.setFilled(true);
		myBall.setLocation(180,360);
		add(myBall);
	}
	public GObject hitObject()
	{
		GObject elem = getElementAt(myBall.getX()+10,myBall.getY()+10);  // when an object gets hit
		return(elem);
	}
	public void setUpBrick(Color cl, double xpos, double ypos)
	{
		GRect gr = new GRect( 40, BRICK_HEIGHT);
		gr.setFillColor(cl);
		gr.setFilled(true);
		gr.setLocation(xpos,ypos+40);
		add(gr);
	}
	public void play()
	{
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) vx = -vx; // determines where the ball goes in terms of left and right

		vy = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) vy = -vy; // up or down


		while(true) {
			pause(DELAY);
			myBall.move(vx, vy);
			if (myBall.getX() < 1 || myBall.getX() > 410) { // hits top
				vx = -vx;
			}
			if (myBall.getY() < 1) {
				vy = -vy;
			}
			if (myBall.getY() > 610) { // reaches bottom
				// userlives--;
			}
			GObject object = hitObject();
			if(object == null) {
			}
			else if (object == MyPaddle)
				vy=-vy;

			else
			{
				remove(object); // removed block
				vy=-vy;
			}
		}
	}
	public void setUpPaddle()
	{
		MyPaddle = new GRect(PADDLE_WIDTH,PADDLE_HEIGHT);  // paddle features decided at the top
		MyPaddle.setFillColor(Color.BLACK);
		MyPaddle.setFilled(true);
		MyPaddle.setLocation(160,575);  // same as ball
		add(MyPaddle);
	}
	public void mouseMoved(MouseEvent x)  // where the mouse moves
	{
		if(x.getX()<1) {
			MyPaddle.setLocation(1,500);
		}
		else if(x.getX()>360) {
			MyPaddle.setLocation(360,500);
		}
		else {
			MyPaddle.setLocation(x.getX(),500);
		}
	}
}
