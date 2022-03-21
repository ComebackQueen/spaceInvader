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
	public int changeX(int x){ //충돌범위에서 이미지크기만큼 좌표에서 +하고 부등식써야하는데(물론 원래좌표는 건들지 말고 비교에만 더해야하니까) 그냥 여기서 바로 해결한 메소드
		return this.x+x;
	}
	public int changeY(int y){ 
		return this.y+y;
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
