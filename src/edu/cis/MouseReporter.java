/*
 * File: MouseReporter.java
 * -----------------------------
 * Output the location of the mouse to a label on the
 * screen. Change the color of the label to red when
 * the mouse touches it.
 */
package edu.cis;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.*;
import acm.graphics.*;
import acm.program.*;

public class MouseReporter extends GraphicsProgram {

	// A constant for the x value of the label
	private static final int INDENT = 20;

	// This variable is visible to the entire program
	// It is called an "instance" variable
	double x;
	double y;
	private GLabel label = new GLabel("");
	public void mouseMoved(MouseEvent e)
	{
		Point location = MouseInfo.getPointerInfo().getLocation();
		x = location.getX();
		y = location.getY();

		label.setFont("Courier-24");
		label.setColor(Color.BLUE);

		// this setLabel method takes in a "String"
		// you can concatenate integers and commas as such:
		label.setLabel(x + "," + y);

		// add the label to the screen!
		add(label, INDENT, getHeight()/2);
	}
	public void run()
	{
		// this code already adds the label to the screen!
		// run it to see what it does.
		Point location = MouseInfo.getPointerInfo().getLocation();
		x = location.getX();
		y = location.getY();
		label.setFont("Courier-24");
		label.setColor(Color.BLUE);

		// this setLabel method takes in a "String"
		// you can concatenate integers and commas as such:
		label.setLabel(x + "," + y);

		// add the label to the screen!
		add(label, INDENT, getHeight()/2);

	}

	public static void main(String[] args)
	{
		new MouseReporter().start();
	}

}
