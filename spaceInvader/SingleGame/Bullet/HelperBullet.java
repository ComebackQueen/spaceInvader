package Bullet;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GraphicObject.GraphicObject;
import Helper.Helper;
import Manager.SoundManager;
import Player.Player;

public class HelperBullet extends GraphicObject
{
	public static int bulletCount = 5;
	// �߻�Ǿ��°�?(true: O | false: X)
	public boolean launched = false;
	// �Ѿ��� �ӵ� ����
	int speed = 10;
	int index = 0;
	// �ӵ��� �����ϱ� ���� Ÿ�̸� ����
	long setTime = System.currentTimeMillis();
	long maxTime = System.currentTimeMillis();
	public long bulletSetTime = System.currentTimeMillis();
	public long bulletMaxTime = System.currentTimeMillis();
	
	boolean keySpaceOn = false;

	/* ������ �ι� */
	public int count=1; //�Ѿ� ��
	// ������ �̹����� �ʱ� x,y ��ǥ�� �޴´�.
	public HelperBullet(String name) {
		super(name);
		super.img = null;
		
		
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

	/* �޼ҵ�ι� */

	// ȭ�鿡 ��µ� ������
	public void update(Helper hp) {
		
		setTime = System.currentTimeMillis();
		
		// �ӵ��� �����ϱ� ���� �ð� �� ����
		if (setTime >= maxTime) {
			
			maxTime = System.currentTimeMillis() + speed;
			// ���� �߻�Ǿ��ٸ�
			if (launched) {
				getPicture("SingleGameImage/HelperBullet.png");
				y -= 5;
			}
			// �Ѿ��� y��ǥ�� 700�� �Ѿ�ٸ�
			if (y < 0) {
				launched = false;
				count = 1;
			}
			if(!launched) {
				
				autoPressed(hp.x, hp.y, hp);
					
			}
			
		}
		if (count == 0) {
			img = null;
			x = 0;
			y = 0;
		}
		if (setTime >= bulletMaxTime) {
			keySpaceOn = false;
			bulletMaxTime = System.currentTimeMillis() + 1000;
		}
		

		// �ӵ� ������ ���� �ð��߰� ����
		
		
		
		
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

	public void autoPressed(int x, int y, Helper hp) // user �������� �����ϴ� Ű�Է½� �����ϴ� �޼ҵ�
	{

		SoundManager.PlayerBulletMp3Player.Play();
		// Play_Sound.playSound("bullet_sound.wav");
		launched = true;
	
		this.x = x + 20;
		
		
		this.y = y;

	}

}