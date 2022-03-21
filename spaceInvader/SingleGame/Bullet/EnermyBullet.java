package Bullet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GraphicObject.GraphicObject;
import Manager.SoundManager;

public class EnermyBullet extends GraphicObject {

	/* 변수선언부 */

	//총알의 갯수
	public static int bulletCount = 5;
	// 발사되었는가?(true: O | false: X)
	public boolean launched = false;
	// 총알의 속도 변수
	int speed;
	// 속도를 조절하기 위한 타이머 변수
	long setTime = System.currentTimeMillis();
	long maxTime = System.currentTimeMillis();
	//이미지버퍼 변수
	public BufferedImage emptyImg;
	public BufferedImage bulletImg;
	/* 생성자 부문 */

	// 가져올 이미지와 초기 x,y 좌표를 받는다.
	public EnermyBullet(String name, int x, int y) {
		super(name);
		this.x = x;
		this.y = y;
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
	public void update() {
		setTime = System.currentTimeMillis();
		// 속도를 조절하기 위한 시간 비교 구문
		if (setTime >= maxTime) {
			
			maxTime = System.currentTimeMillis() + speed;
			// 만약 발사되었다면
			if (launched) {
				
				getPicture(bulletImg);
				y += 1;
			}
			// 총알의 y좌표가 700을 넘어섰다면
			if (y > 700) {
				launched = false;
			}
		}

	}
	
	// 이미지를 변경하는 함수
	public void getPicture(BufferedImage tmpImg) {

		super.img = tmpImg;

	}
}
