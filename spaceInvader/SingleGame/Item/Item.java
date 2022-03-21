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
	public boolean itemOn = false;		// 아이템 생성유무
	public boolean save = false; 		// 히든몬스터 좌표 최초로 받는 역활을 해주는 불린
	long edTime = System.currentTimeMillis() +1000;			// 시간 제한
	long stTime = System.currentTimeMillis();				
	public int itemNum = 0; // 아이템종류를 표시해주는 변수 0=>없는상태, 1=> 속사, 2=>헬퍼, 3=> 폭탄
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
			// 지정한 이름의 파일을 불러온다
			super.img = ImageIO.read(new File(name));

		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}

}