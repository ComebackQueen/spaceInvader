package Item;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GraphicObject.GraphicObject;

public class Item extends GraphicObject {
	public int ix = 0;
	public int iy = 0;
	public Point p;
	Point vec;
	Image item_img;
	public int random;
	public boolean itemOn = false;		// ������ ��������
	public boolean save = false; 		// ������� ��ǥ ���ʷ� �޴� ��Ȱ�� ���ִ� �Ҹ�
	long edTime = System.currentTimeMillis() +1000;			// �ð� ����
	long stTime = System.currentTimeMillis();				
	public int itemNum = 0; // ������������ ǥ�����ִ� ���� 0=>���»���, 1=> �ӻ�, 2=>����, 3=> ��ź
	HiddenMonster tempHm;


	public Item(String name, HiddenMonster hm) {
		super(name);
		ix = hm.hx;
		iy = 0;
		this.x = ix;
		this.y = 0;
		vec = new Point();
		p = new Point(hm.hx, 0);
		vec.y = 1;
		tempHm = hm;
		
		// TODO Auto-generated constructor stub
	}

	public void update() {
		
			
			if(edTime <= stTime){
				if(itemOn){
					super.x = ix;
					p.y += vec.y;
					this.y += 1;
					iy = p.y;	
					edTime = System.currentTimeMillis()+20;
				}
				
			}
			stTime = System.currentTimeMillis();
			
			if(p.y >= 710){
				itemOn = false;
				p.y = this.y = iy =0;
				save = false;
				p.x = this.x = ix = 1280;
			}
				
			
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