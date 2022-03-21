package OnlineGame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GraphicObject.GraphicObject;

public class PVPPlayer extends GraphicObject{
	int x;
	int y;
	int index;
	BufferedImage user1;
	int playerLife;
	String name;
	public boolean isDaed;
	public PVPPlayer(String name, int x, int y, int index) { //파일이름, x좌표, y좌표, 1p 인지 2p인지 구별하는 인덱스
		super(name);
		this.name=name;
		this.x=x;
		this.y=y;
		this.index = index;
		playerLife = 2;
		
		try {
			user1 = ImageIO.read(new File(name));
		} catch (IOException e) {
		}
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y 
		= y;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public BufferedImage getUser1() {
		return user1;
	}
	public void setUser1(BufferedImage user1) {
		this.user1 = user1;
	}
	public int getPlayerLife() {
		return playerLife;
	}
	public void setPlayerLife(int playerLife) {
		this.playerLife = playerLife;
	}
	public void setName(String s) 
	{
		this.name = s;
		try {
			user1 = ImageIO.read(new File(s));
		} catch (IOException e) {
		}
	}
	/**************************************************
	 플레이어 이미지 크기
	 user1 = 50x38
	 user2 = 50x50
	 user3 = 50x28
	 user4 = 50x41
	 **************************************************/
	public void draw(Graphics g){
			g.drawImage(user1, x, y, 50, 50, null);

	}
	
	
}
