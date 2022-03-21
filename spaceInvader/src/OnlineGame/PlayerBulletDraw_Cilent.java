package OnlineGame;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GraphicObject.GraphicObject;
import Manager.SoundManager;
import Player.Player;

public class PlayerBulletDraw_Cilent extends GraphicObject
{
	public static int bulletCount = 5;
	// 발사되었는가?(true: O | false: X)
	public boolean launched = false;
	// 총알의 속도 변수
	int speed = 10;
	// 속도를 조절하기 위한 타이머 변수
	long setTime = System.currentTimeMillis();
	long maxTime = System.currentTimeMillis();
	public long bulletSetTime = System.currentTimeMillis();
	public long bulletMaxTime = System.currentTimeMillis();
	
	boolean keySpaceOn = false;

	/* 생성자 부문 */
	public int count=0; //총알 생
	// 가져올 이미지와 초기 x,y 좌표를 받는다.
	
	public BufferedImage emptyImg;
	public BufferedImage bulletImg;
	
	public PlayerBulletDraw_Cilent(String name) {
		super(name);
		super.img = null;
		
		//이미지 버퍼에 이미지를 받아온다.
				try {
						emptyImg = ImageIO.read(new File("singleGameImage/Empty.png"));
					} catch (IOException e) {
				}
				try {
					  bulletImg = ImageIO.read(new File("singleGameImage/Bullet.png"));
					} catch (IOException e) {
				}
		
	}
	/* Get, Set 메소드 생성부문 */
	public int getCnt() {
		return count;
	}
	public void setCnt(int x) {
		this.count=x;
	}
	// Set함수
	public void setX(int sx) {
		super.x = sx;
	}

	public void setY(int sy) {
		super.y = sy;
	}

	public void setSpeed(int sS) {
		this.speed = sS;
	}
	public void setPicture(String image) {
		if (image.equals("Empty"))
			getPicture(emptyImg);
		if (image.equals("Bullet"))
			getPicture(bulletImg);
	}

	// Get함수
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public long getSpeed() {
		return this.speed;
	}

	/* 메소드부문 */

	// 이미지를 변경하는 함수
	public void getPicture(String name) {
		try {
			// 지정한 이름의 파일을 불러온다
			super.img = ImageIO.read(new File(name));

		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}

	public String keyPressed(KeyEvent event, int x, int y, boolean isDead, PlayerDraw_Client py, int count) // user 움직임을 관여하는 키입력시 반응하는 메소드
	{
		String keyData = null;
		this.count = count;
		if (!isDead) {
			
				SoundManager.PlayerBulletMp3Player.Play();
				// Play_Sound.playSound("bullet_sound.wav");
				launched = true;
				keySpaceOn = true;
				this.x = x;
				this.y = y;
		}
		keyData = "#%01%#"+ "#%70%#" + py.index + "COP" + this.x + "/" + this.y + "/" + this.count;
		
		return keyData;
		
	}
	
	// 이미지를 변경하는 함수
		public void getPicture(BufferedImage tmpImg) {

			super.img = tmpImg;

		}

}
