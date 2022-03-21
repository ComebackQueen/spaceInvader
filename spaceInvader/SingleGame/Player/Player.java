package Player;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GraphicObject.GraphicObject;
import Helper.Helper;

public class Player extends GraphicObject {

	long setTime = System.currentTimeMillis();
	long maxTime = System.currentTimeMillis();
	long itemCoolTime = System.currentTimeMillis();
	public boolean isDead = false;
	public boolean keyLeftOn = false;
	public boolean keyRightOn = false;
	public int exploedEnime = 0;
	public boolean isRemove = false;
	public BufferedImage playerImg;
	BufferedImage userExploed1;
	BufferedImage userExploed2;
	BufferedImage helperImg;
	public boolean delay = false;
	public int getItem = 0;
	public boolean exploedON = false;
	public boolean coolTimeFlag = false;
	boolean helperOnFlag = false;
	
	int LeftValue=0, RightValue=0;
	
	public int playerLife; // 체력

	public int playerBulletIndex;

	public Helper helper[] = new Helper[2];
	// 생성자

	public Player(String name, int x, int y) {
		super(name);
		this.x = x;
		this.y = y;
		playerLife = 2;
		helper[0] = null;
		helper[1] = null;
		try {
			playerImg = ImageIO.read(new File("singleGameImage/Player1.png"));
		} catch (IOException e) {
		}
		try {

			userExploed1 = ImageIO.read(new File("SingleGameImage/PlayerExploed1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			userExploed2 = ImageIO.read(new File("SingleGameImage/PlayerExploed2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public void update() {
		setTime = System.currentTimeMillis();
		if (!isDead) {
			if (getItem != 0 && !coolTimeFlag) {
				itemCoolTime = System.currentTimeMillis() + 10000;
				coolTimeFlag = true;
			}
			if (getItem == 3 && !helperOnFlag) {
				helper[0] = new Helper("SingleGameImage/ItemHellperImageBlack.png", x - 50, y, 1);
				helper[1] = new Helper("SingleGameImage/ItemHellperImageBlack.png", x + 50, y, 2);
				helperOnFlag = true;
			}

			if (setTime >= maxTime) {

				maxTime = System.currentTimeMillis() + 2;
				if (x > 45) {
					if (keyLeftOn == true)
						x -= 1;
				}
				if (x < 1280 - 85) {
					if (keyRightOn == true)
						x += 1;
				}
			}
			if (setTime >= itemCoolTime) {
				if (getItem == 2 || getItem == 3) {
					getItem = 0;
					helper[0] = null;
					helper[1] = null;
					coolTimeFlag = false;
					helperOnFlag = false;
				}
			}
		}
		if (isDead) {
			if (exploedEnime == 0) {
				maxTime = System.currentTimeMillis() + 1000;
				getPicture(userExploed1);
				exploedEnime = 1;
			} else {
				if (setTime >= maxTime) {
					maxTime = System.currentTimeMillis() + 100;
					getPicture(userExploed2);
					isRemove = true;
					exploedEnime = 0;
					delay = true;
				}
			}
		}
	}

	public void keyPressed(KeyEvent event) // user 움직임을 관여하는 키입력시 반응하는 메소드
	{
		if (!isDead) {

			if (event.getKeyCode() == KeyEvent.VK_LEFT) {
				keyLeftOn = true;
				LeftValue=1;
			}
			if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
				keyRightOn = true;
				RightValue=1;
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (!isDead) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				keyLeftOn = false;
				LeftValue=2;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				keyRightOn = false;
				RightValue=2;
			}
		}

	}

	public void getPicture(BufferedImage tmpImg) {

		super.img = tmpImg;

	}

}
