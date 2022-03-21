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
	// �߻�Ǿ��°�?(true: O | false: X)
	public boolean launched = false;
	// �Ѿ��� �ӵ� ����
	int speed = 10;
	// �ӵ��� �����ϱ� ���� Ÿ�̸� ����
	long setTime = System.currentTimeMillis();
	long maxTime = System.currentTimeMillis();
	public long bulletSetTime = System.currentTimeMillis();
	public long bulletMaxTime = System.currentTimeMillis();
	
	boolean keySpaceOn = false;

	/* ������ �ι� */
	public int count=1; //�Ѿ� ��
	// ������ �̹����� �ʱ� x,y ��ǥ�� �޴´�.
	public PlayerBullet(String name, PVPPlayer player) {
		super(name);
		super.img = null;
		
	}
	public PlayerBullet(String name) {
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
	public void update(Player py) {
		
		setTime = System.currentTimeMillis();
		
		// �ӵ��� �����ϱ� ���� �ð� �� ����
		if (setTime >= maxTime) {
			if(py.getItem == 2)
				maxTime = System.currentTimeMillis() + speed-5;
			else
				maxTime = System.currentTimeMillis() + speed;
			// ���� �߻�Ǿ��ٸ�
			if (launched) {
				if(py.getItem == 0 || py.getItem == 3)
					getPicture("SingleGameImage/Bullet.png");
				if(py.getItem == 2)
					getPicture("SingleGameImage/PlusBullet.png");
				
				
				y -= 5;
			}
			// �Ѿ��� y��ǥ�� 700�� �Ѿ�ٸ�
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

	public void keyPressed(KeyEvent event, int x, int y, boolean isDead, Player py) // user �������� �����ϴ� Ű�Է½� �����ϴ� �޼ҵ�
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
