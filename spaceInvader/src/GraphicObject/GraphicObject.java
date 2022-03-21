package GraphicObject;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;


public class GraphicObject {
	//불러올 이미지
	public BufferedImage img = null;
	//이미지 출력 좌표
	public int x =0;
	public int y = 0;
	public String name;
	//생성자
	public GraphicObject(String name) {
		try {
			//지정한 이름의 파일을 불러온다
			img = ImageIO.read(new File(name));
			
		}catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}
	//출력을 업데이트
	public void update() { }
	//화면에 그림을 출력하는 메소드
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
	}
	
	//Get, Set 메소드 정의 부문
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

