package OnlineGame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

//import ETC.Play_Sound;
import GraphicObject.GraphicObject;


public class ClientPlayerBullet extends GraphicObject
{
	public static int bulletCount = 5;
	// 발사되었는가?(true: O | false: X)
	public boolean launched = false;
	// 총알의 속도 변수
	int speed = 10;
	// 속도를 조절하기 위한 타이머 변수

	
	boolean keySpaceOn = false;
	BufferedImage bullet;
	/* 생성자 부문 */
	public int count=1; //총알 생
	// 가져올 이미지와 초기 x,y 좌표를 받는다.
	public ClientPlayerBullet(String name) {
		super(name);
		super.name = name;
		try {
			this.bullet = ImageIO.read(new File(name));
		} catch (IOException e) {
		}
	}
	public void setName(String s) 
	{
		this.name = s;
		try {
			bullet = ImageIO.read(new File(s));
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
	public void draw(Graphics g){
		g.drawImage(bullet, x, y, 5, 5, null);

	}
}
