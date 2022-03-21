package Wall;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GraphicObject.GraphicObject;

public class Wall extends GraphicObject{
	int w, h;
	public int WallLife;
	public boolean WallChange =true;

	public Wall(String name) {
		super(name);
		w=30;
		h=30;
		 WallLife=2;
	}
	public void draw(Graphics g) {
		
		if(WallLife==2) {
			getPicture("SingleGameImage/Wall.jpg");
		}
		if(WallLife==1) {
			getPicture("SingleGameImage/BreakWall.png");
		}
		g.drawImage(img, x, y, w, h, null);
	}
	public void setLife(int num) {
		this.WallLife-=num;
	}
	public int changeX(int x){ //�浹�������� �̹���ũ�⸸ŭ ��ǥ���� +�ϰ� �ε�Ľ���ϴµ�(���� ������ǥ�� �ǵ��� ���� �񱳿��� ���ؾ��ϴϱ�) �׳� ���⼭ �ٷ� �ذ��� �޼ҵ�
		return this.x+x;
	}
	public int changeY(int y){ 
		return this.y+y;
	}
	public void getPicture(String name) {
		try {
			// ������ �̸��� ������ �ҷ��´�
			super.img = ImageIO.read(new File(name));

		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
}
