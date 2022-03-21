package Helper;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Bullet.HelperBullet;
import GraphicObject.GraphicObject;
import Player.Player;

public class Helper extends GraphicObject {

	public HelperBullet helperBullet = new HelperBullet("SingleGameImage/Bullet.png");
	long setTime = System.currentTimeMillis();
	long maxTime = System.currentTimeMillis();
	public boolean keyLeftOn = false;
	public boolean keyRightOn = false;
	public boolean isRemove = false;
	public BufferedImage HelperImg;
	public int index = 0;
	
	

	public int playerLife; // 端径
	
	public int playerBulletIndex;
	// 持失切

	public Helper(String name, int x, int y, int index) {
		super(name);
		this.x = x;
		this.y = y;
		this.index = index;
		try {
			HelperImg = ImageIO.read(new File("singleGameImage/Player1.png"));
		} catch (IOException e) {
		}
		
	}

	public int getUser1_x() {
		return x;
	}

	public void setUser1_x(int ux) {
		this.x = ux;
	}

	public int getUser1_y() {
		return y;
	}

	public void setUser1_y(int uy) {
		this.y = uy;
	}

	public void update(Player py) {
		
			if(this.index == 1)
			{
				this.x = py.x + 50;
			}
			else
			{
				this.x = py.x -50;
			}
		
		
	}


	public void getPicture(BufferedImage tmpImg) {

		super.img = tmpImg;

	}

}