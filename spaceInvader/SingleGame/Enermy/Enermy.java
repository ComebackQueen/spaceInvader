package Enermy;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GraphicObject.*;
import SingleGamePanel.SingleGame;

public class Enermy extends GraphicObject {

	/* 변수선언부 */
	
	// 적의 좌우 움직이는 값, 만약 값이 5라면 5번씩 좌우로 이동한다.
	public static final int ENERMY_MAX_MOVE = 24;
	// 한번에 이동하는 이동값(출력시)
	public static final int ENERMY_MOVE_SIZE = 15;
	   // 총 적 객체의 수
	   public static final int ENERMY_MAX = 55;
	
	//이미지버퍼 변수 선언.
	BufferedImage enermy11 = null;
	BufferedImage enermy12 = null;
	BufferedImage enermy13 = null;
	BufferedImage enermy14 = null;
	BufferedImage enermy21 = null;
	BufferedImage enermy22 = null;
	BufferedImage enermy31 = null;
	BufferedImage enermy32 = null;
	BufferedImage exploed = null;

   // 전체좌표(개별좌표로 화면끝의 충돌을 감별하기 어려워서 전체좌표 x를 만듬.)
   public int forceX = 1;
   // x좌표로 이동을 한번 휴식한다.(y좌표값이 변경 될때의 대각선 움직임이 부자연 스러워 보여서 y좌표 이동이 완료 될때까지 x좌표 이동을
   // 쉰다.)
   public int breakTime = 1;
   // 적들을 구분하기 위한 일련번호
   int number = 0;
   // 적 객체의 x가 움직이는 크기(enemyMoveSize를 받는다.)
   public int directionX = 10;
   // 적 객체가 움직이는 방향(1:오른쪽 0:왼쪽)
   public int direction = 1;
   // 적객체가 움직이는 속도
   public long speed;
   // 죽은객체인가?(0:죽은객체 1:살아있는객체)
   public boolean isDead = false;
   // 애니메이션 변수(true: 그림1 | false: 그림2)
   boolean enime = false;
   // 방향(false: 오른쪽 | true: 왼쪽)
   public boolean isRemove = false;
   //시간계산을 위한 시간변수 선언
   long setTime = System.currentTimeMillis();
   public long maxTime = System.currentTimeMillis();
   
   /* 생성자 */

   // 생성자
   public Enermy(String name, int sX, int sY, int sNumber) {

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
   }

   /* Get, Set 메소드 부문 */

   // Set함수
   public void setX(int sx) {
      this.x = sx;
   }

   public void setY(int sy) {
      this.y = sy;
   }

   public void setSpeed(int sS) {
      this.speed = sS;
   }

   // Get함수
   public int getX() {
      return this.x;
   }

   public int getY() {
      return this.y;
   }

   public long getSpeed() {
      return this.speed;
   }

   // 구별을 위한 개별 넘버
   public int getNumber() {
      return this.number;
   }

   /* 메소드 선언부 */

   // 화면에 출력될 움직임
   public void update() {
      setTime = System.currentTimeMillis();
		if (!isDead) {
			if (setTime >= maxTime) {

				maxTime = System.currentTimeMillis() + speed;
				// 움직임을 계산하는 부문
				if (breakTime == 1) {
					// 설정된 좌우의 끝에 다다랐을때
					if (forceX <= 0) {
						directionX = 0;
						y += 10;
						forceX++;
						direction = 1;
						breakTime = 0;
					}
					if (forceX >= ENERMY_MAX_MOVE) {
						directionX = 0;
						y += 10;
						direction = 0;
						breakTime = 0;
						forceX--;
					}
					// 좌우 이동 움직임 부문
					//좌
					if (direction == 0 && breakTime == 1) {

						directionX = -ENERMY_MOVE_SIZE;
						forceX--;
						breakTime = 1;
					}
					//우
					if (direction == 1 && breakTime == 1) {
						directionX = +ENERMY_MOVE_SIZE;
						forceX++;
						breakTime = 1;
					}
				}
				// 계산된 값을 실질적인 x좌표에 적용
				super.x = super.x + directionX;
				//끝에 다다랐을때는 x좌표를 이동시키지 않는다.
				breakTime = 1;

				/*애니메이션 변환부문*/

				// 만약 살아있다면
				if (number <= 21) {
					if (direction == 1) {
						if (enime == false) {
							getPicture(enermy11);
							enime = true;
						} else {
							getPicture(enermy12);
							enime = false;
						}
					} else {
						if (enime == false) {
							getPicture(enermy13);
							enime = true;
						} else {
							getPicture(enermy14);
							enime = false;
						}
					}
				} else if (number <= 43) {
					if (enime == false) {
						getPicture(enermy21);
						enime = true;
					} else {
						getPicture(enermy21);
						enime = false;
					}
				} else {
					if (enime == false) {
						getPicture(enermy31);
						enime = true;
					} else {
						getPicture(enermy32);
						enime = false;
					}
				}
			}
		}
		/*죽었을때 애니메이션*/
		else if(isDead)
		{
			
			getPicture(exploed);
			if (setTime >= maxTime) {
				isRemove = true;
			}		
			
		}
	}
   public void setImage() {
      super.img = null;
   }

   // 이미지를 변경하는 함수(애니메이션을 위해)
   public void getPicture(BufferedImage tmpImg) {
     
         super.img = tmpImg;

     
   }
}