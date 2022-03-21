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
	// �߻�Ǿ��°�?(true: O | false: X)
	public boolean launched = false;
	// �Ѿ��� �ӵ� ����
	int speed = 10;
	// �ӵ��� �����ϱ� ���� Ÿ�̸� ����

	
	boolean keySpaceOn = false;
	BufferedImage bullet;
	/* ������ �ι� */
	public int count=1; //�Ѿ� ��
	// ������ �̹����� �ʱ� x,y ��ǥ�� �޴´�.
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
	/* Get, Set �޼ҵ� �����ι� */
	public int getCnt() {
		return count;
	}
	public void setCnt(int x) {
		this.count=x;
	}
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
	
	// �̹����� �����ϴ� �Լ�
	public void getPicture(String name) {
		try {
			// ������ �̸��� ������ �ҷ��´�
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
