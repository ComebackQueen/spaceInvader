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
	public int count=0; //�Ѿ� ��
	// ������ �̹����� �ʱ� x,y ��ǥ�� �޴´�.
	
	public BufferedImage emptyImg;
	public BufferedImage bulletImg;
	
	public PlayerBulletDraw_Cilent(String name) {
		super(name);
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
	public int getCnt() {
		return count;
	}
	public void setCnt(int x) {
		this.count=x;
	}
	// Set�Լ�
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

	public String keyPressed(KeyEvent event, int x, int y, boolean isDead, PlayerDraw_Client py, int count) // user �������� �����ϴ� Ű�Է½� �����ϴ� �޼ҵ�
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
	
	// �̹����� �����ϴ� �Լ�
		public void getPicture(BufferedImage tmpImg) {

			super.img = tmpImg;

		}

}
