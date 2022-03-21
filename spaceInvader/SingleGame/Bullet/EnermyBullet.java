package Bullet;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GraphicObject.GraphicObject;
import Manager.SoundManager;

public class EnermyBullet extends GraphicObject {

	/* ��������� */

	//�Ѿ��� ����
	public static int bulletCount = 5;
	// �߻�Ǿ��°�?(true: O | false: X)
	public boolean launched = false;
	// �Ѿ��� �ӵ� ����
	int speed;
	// �ӵ��� �����ϱ� ���� Ÿ�̸� ����
	long setTime = System.currentTimeMillis();
	long maxTime = System.currentTimeMillis();
	//�̹������� ����
	public BufferedImage emptyImg;
	public BufferedImage bulletImg;
	/* ������ �ι� */

	// ������ �̹����� �ʱ� x,y ��ǥ�� �޴´�.
	public EnermyBullet(String name, int x, int y) {
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
		this.x = sx;
	}

	public void setY(int sy) {
		this.y = sy;
	}

	public void setSpeed(int sS) {
		this.speed = sS;
	}

	// Get�Լ�
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public long getSpeed() {
		return this.speed;
	}

	/* �޼ҵ�ι� */

	// ȭ�鿡 ��µ� ������
	public void update() {
		setTime = System.currentTimeMillis();
		// �ӵ��� �����ϱ� ���� �ð� �� ����
		if (setTime >= maxTime) {
			
			maxTime = System.currentTimeMillis() + speed;
			// ���� �߻�Ǿ��ٸ�
			if (launched) {
				
				getPicture(bulletImg);
				y += 1;
			}
			// �Ѿ��� y��ǥ�� 700�� �Ѿ�ٸ�
			if (y > 700) {
				launched = false;
			}
		}

	}
	
	// �̹����� �����ϴ� �Լ�
	public void getPicture(BufferedImage tmpImg) {

		super.img = tmpImg;

	}
}
