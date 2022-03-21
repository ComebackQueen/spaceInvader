package OnlineGame;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GraphicObject.GraphicObject;

public class PlayerDraw_Client extends GraphicObject {

	long setTime = System.currentTimeMillis();
	long maxTime = System.currentTimeMillis();
	long itemCoolTime = System.currentTimeMillis();
	public boolean isDead = false;
	public boolean keyLeftOn = false;
	public boolean keyRightOn = false;
	public int exploedEnime = 0;
	public boolean isRemove = false;
	public BufferedImage playerImg;
	BufferedImage userExploed1;
	BufferedImage userExploed2;
	BufferedImage helperImg;
	BufferedImage empty;
	public boolean delay = false;
	public int getItem = 0;
	public boolean exploedON = false;
	boolean coolTimeFlag = false;
	boolean helperOnFlag = false;
	public int playerLife; // 체력
	public int index = -1;
	public int playerBulletIndex;
	public boolean lifeM = false;

	
	// 생성자
	public void setPicture(String name){
		if(name.equals("userExploed1")){
			getPicture(userExploed1);
			if(!lifeM)
			{
				System.out.println("exploead1 이미지 변경");
				lifeM = true;
				isDead = true;
			}
		}
		else if(name.equals("userExploed2")){
			getPicture(userExploed2);
			if(lifeM)
			{
				
				this.playerLife--;
				lifeM = false;
			}
		}
		else if(name.equals("Empty")) {
			
			getPicture(empty);
		}
		else{
			
			getPicture(playerImg);
			isDead = false;
		}
	}

	public PlayerDraw_Client(String name, int x, int y, int index) {
		super(name);
		playerImg = super.img;
		this.x = x;
		this.y = y;
		this.index = index;
		playerLife = 2;
		playerBulletIndex = 0;
		
		try {

			userExploed1 = ImageIO.read(new File("SingleGameImage/PlayerExploed1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			userExploed2 = ImageIO.read(new File("SingleGameImage/PlayerExploed2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			empty = ImageIO.read(new File("singleGameImage/Empty.png"));
		} catch (IOException e) {
		}

	}

	public String keyPressed(KeyEvent event) // user 움직임을 관여하는 키입력시 반응하는 메소드
	{
		String keyData = null;
		if (!isDead) {

			if (event.getKeyCode() == KeyEvent.VK_LEFT) {
				keyData = "#%01%#"+"#%41%#"+ index + "COP" +"LEFT PRESSED";
				
			}
			if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
				keyData = "#%01%#"+"#%42%#"+ index +"COP"+"RIGHT PRESSED";
			}	
		}
		return keyData;
	}

	public String keyReleased(KeyEvent event) {
		
		String keyData = null;
		if (!isDead) {

			if (event.getKeyCode() == KeyEvent.VK_LEFT) {
				keyData = "#%01%#"+"#%43%#"+ index + "COP" +"LEFT RELESSASED";
				
			}
			if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
				keyData = "#%01%#"+"#%44%#"+ index + "COP" +"RIGHT RELESSASED";
			}	
		}
		return keyData;

	}

	public void getPicture(BufferedImage tmpImg) {

		super.img = tmpImg;

	}

}
