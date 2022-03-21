package Bullet;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

//import ETC.Play_Sound;
import GraphicObject.GraphicObject;
import Manager.SoundManager;
import OnlineGame.PVPPlayer;
import Player.Player;


public class PlayerBullet extends GraphicObject
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
	public int count=1; //총알 생
	// 가져올 이미지와 초기 x,y 좌표를 받는다.
	public PlayerBullet(String name, PVPPlayer player) {
		super(name);
		super.img = null;
		
	}
	public PlayerBullet(String name) {
		super(name);
		super.img = null;
		
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
		this.x = sx;
	}

	public void setY(int sy) {
		this.y = sy;
	}

	public void setSpeed(int sS) {
		this.speed = sS;
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

	// 화면에 출력될 움직임
	public void update(Player py) {
		
		setTime = System.currentTimeMillis();
		
		// 속도를 조절하기 위한 시간 비교 구문
		if (setTime >= maxTime) {
			if(py.getItem == 2)
				maxTime = System.currentTimeMillis() + speed-5;
			else
				maxTime = System.currentTimeMillis() + speed;
			// 만약 발사되었다면
			if (launched) {
				if(py.getItem == 0 || py.getItem == 3)
					getPicture("SingleGameImage/Bullet.png");
				if(py.getItem == 2)
					getPicture("SingleGameImage/PlusBullet.png");
				
				
				y -= 5;
			}
			// 총알의 y좌표가 700을 넘어섰다면
			if(py.getItem == 0)
			{
				if (y < 0) {
					launched = false;
					count = 1;
				}
			}
			if(py.getItem == 2)
			{
				if (y < -30) {
					launched = false;
					count = 1;
				}
			}
			if(py.getItem == 1)
			{
				if (y < -60) {
					launched = false;
					count = 1;
				}
			}
			if(py.getItem == 3)
			{
				if (y < 0) {
					launched = false;
					count = 1;
				}
			}
		}
		
		
		if(count==0) {
			img=null;
			x= 0;
			y= 0;
		}
		
		// 속도 조절을 위한 시간추가 구문
	}

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

	public void keyPressed(KeyEvent event, int x, int y, boolean isDead, Player py) // user 움직임을 관여하는 키입력시 반응하는 메소드
	{
		if (!isDead) {
			if (event.getKeyCode() == KeyEvent.VK_SPACE) {
				SoundManager.PlayerBulletMp3Player.Play();
				// Play_Sound.playSound("bullet_sound.wav");
				launched = true;
				keySpaceOn = true;
				this.x = x;
				this.y = y;
				if(py.getItem == 1) {
				 this.getPicture("SingleGameImage/Bomb.png");
				
				}

			}
		}
	}

}
