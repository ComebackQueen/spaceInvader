package OnlineGame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GraphicObject.GraphicObject;
import Manager.SoundManager;

public class EnermyBulletDraw_Client extends GraphicObject {

	/* 변수선언부 */

	//이미지버퍼 변수
	public BufferedImage emptyImg;
	public BufferedImage bulletImg;
	/* 생성자 부문 */

	// 가져올 이미지와 초기 x,y 좌표를 받는다.
	public EnermyBulletDraw_Client(String name, int x, int y) {
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
		super.x = sx;
	}

	public void setY(int sy) {
		super.y = sy;
	}
	
	public void setPicture(String image) {
		if (image.equals("emptyImg"))
			getPicture(emptyImg);
		if (image.equals("bulletImg"))
			getPicture(bulletImg);
	}

	

	/* 메소드부문 */
	
	// 이미지를 변경하는 함수
	public void getPicture(BufferedImage tmpImg) {

		super.img = tmpImg;

	}
}
