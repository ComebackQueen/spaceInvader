package LifeBar;

import java.awt.Image;
import java.awt.event.KeyEvent;

import GraphicObject.GraphicObject;

public class LifeBar extends GraphicObject{
	long setTime = System.currentTimeMillis();
	long maxTime = System.currentTimeMillis();
	
	// »ý¼ºÀÚ
	public LifeBar(String name, int x , int y) {
		super(name);
		this.x = x;
		this.y = y;
	}
	
	public void update() {
		
	}
}
