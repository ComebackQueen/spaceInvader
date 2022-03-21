package OnlineGame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GraphicObject.*;

public class EnermyDraw_Client extends GraphicObject {

	/* 변수선언부 */

	// 적의 좌우 움직이는 값, 만약 값이 5라면 5번씩 좌우로 이동한다.
	public static final int ENERMY_MAX_MOVE = 24;
	// 한번에 이동하는 이동값(출력시)
	public static final int ENERMY_MOVE_SIZE = 15;
	// 총 적 객체의 수
	public static final int ENERMY_MAX = 55;

	// 이미지버퍼 변수 선언.
	BufferedImage enermy11 = null;
	BufferedImage enermy12 = null;
	BufferedImage enermy13 = null;
	BufferedImage enermy14 = null;
	BufferedImage enermy21 = null;
	BufferedImage enermy22 = null;
	BufferedImage enermy31 = null;
	BufferedImage enermy32 = null;
	BufferedImage exploed = null;
	BufferedImage emptyImg = null;

	
	// 적들을 구분하기 위한 일련번호
	int number = 0;
	
	public boolean isDead = false;
	// 애니메이션 변수(true: 그림1 | false: 그림2)
	boolean enime = false;
	// 방향(false: 오른쪽 | true: 왼쪽)
	public boolean isRemove = false;


	/* 생성자 */

	// 생성자
	public EnermyDraw_Client(String name, int sX, int sY, int sNumber) {

		super(name);

		super.x = sX;
		super.y = sY;
		// 적들의 구분을 위한 일련번호
		this.number = sNumber;

		try {
			enermy11 = ImageIO.read(new File("SingleGameImage/Enermy11.png"));
		} catch (IOException e) {
		}
		try {
			enermy12 = ImageIO.read(new File("SingleGameImage/Enermy12.png"));
		} catch (IOException e) {
		}
		try {
			enermy13 = ImageIO.read(new File("SingleGameImage/Enermy13.png"));
		} catch (IOException e) {
		}
		try {
			enermy14 = ImageIO.read(new File("SingleGameImage/Enermy14.png"));
		} catch (IOException e) {
		}
		try {
			enermy21 = ImageIO.read(new File("SingleGameImage/Enermy21.png"));
		} catch (IOException e) {
		}
		try {
			enermy22 = ImageIO.read(new File("SingleGameImage/Enermy22.png"));
		} catch (IOException e) {
		}
		try {
			enermy31 = ImageIO.read(new File("SingleGameImage/Enermy31.png"));
		} catch (IOException e) {
		}
		try {
			enermy32 = ImageIO.read(new File("SingleGameImage/Enermy32.png"));
		} catch (IOException e) {
		}
		try {
			exploed = ImageIO.read(new File("SingleGameImage/EnermyExploed.png"));
		} catch (IOException e) {
		}
		try {
			emptyImg = ImageIO.read(new File("singleGameImage/Empty.png"));
		} catch (IOException e) {
	}
	}

	/* Get, Set 메소드 부문 */

	// Set함수
	public void setX(int sx) {
		super.x = sx;
	}

	public void setY(int sy) {
		super.y = sy;
	}
	public void setIsDead(boolean isDead)
	{
		this.isDead = isDead;
	}
	public void setPicture(String image)
	{
		if (!isDead) {
			/* 애니메이션 변환부문 */

			if (image.equals("enermy11"))
				getPicture(enermy11);
			if (image.equals("enermy12"))
				getPicture(enermy12);
			if (image.equals("enermy13"))
				getPicture(enermy13);
			if (image.equals("enermy14"))
				getPicture(enermy14);
			if (image.equals("enermy21"))
				getPicture(enermy21);
			if (image.equals("enermy31"))
				getPicture(enermy31);
			if (image.equals("enermy32"))
				getPicture(enermy32);
			

		}
		else if(isDead && !isRemove){
			if (image.equals("exploed"))
				getPicture(exploed);
			
		}
		else{
			if (image.equals("emptyImg"))
				getPicture(emptyImg);
		}


	
	}
	

	// 이미지를 변경하는 함수(애니메이션을 위해)
	public void getPicture(BufferedImage tmpImg) {

		super.img = tmpImg;

	}
}