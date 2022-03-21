package GraphicObject;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;


public class GraphicObject {
	//�ҷ��� �̹���
	public BufferedImage img = null;
	//�̹��� ��� ��ǥ
	public int x =0;
	public int y = 0;
	public String name;
	//������
	public GraphicObject(String name) {
		try {
			//������ �̸��� ������ �ҷ��´�
			img = ImageIO.read(new File(name));
			
		}catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
	//����� ������Ʈ
	public void update() { }
	//ȭ�鿡 �׸��� ����ϴ� �޼ҵ�
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
	}
	
	//Get, Set �޼ҵ� ���� �ι�
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	
}

