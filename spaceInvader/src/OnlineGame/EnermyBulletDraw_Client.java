package OnlineGame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GraphicObject.GraphicObject;
import Manager.SoundManager;

public class EnermyBulletDraw_Client extends GraphicObject {

	/* ��������� */

	//�̹������� ����
	public BufferedImage emptyImg;
	public BufferedImage bulletImg;
	/* ������ �ι� */

	// ������ �̹����� �ʱ� x,y ��ǥ�� �޴´�.
	public EnermyBulletDraw_Client(String name, int x, int y) {
		super(name);
		this.x = x;
		this.y = y;
		super.img = null;
		//�̹��� ���ۿ� �̹����� �޾ƿ´�.
		try {
				emptyImg = ImageIO.read(new File("singleGameImage/Empty.png"));
			} catch (IOException e) {
		}
		try {
			  bulletImg = ImageIO.read(new File("singleGameImage/Bullet.png"));
			} catch (IOException e) {
		}
	}

	/* Get, Set �޼ҵ� �����ι� */

	// Set�Լ�
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

	

	/* �޼ҵ�ι� */
	
	// �̹����� �����ϴ� �Լ�
	public void getPicture(BufferedImage tmpImg) {

		super.img = tmpImg;

	}
}
