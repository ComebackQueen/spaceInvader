package Enermy;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GraphicObject.*;
import SingleGamePanel.SingleGame;

public class Enermy extends GraphicObject {

	/* ��������� */
	
	// ���� �¿� �����̴� ��, ���� ���� 5��� 5���� �¿�� �̵��Ѵ�.
	public static final int ENERMY_MAX_MOVE = 24;
	// �ѹ��� �̵��ϴ� �̵���(��½�)
	public static final int ENERMY_MOVE_SIZE = 15;
	   // �� �� ��ü�� ��
	   public static final int ENERMY_MAX = 55;
	
	//�̹������� ���� ����.
	BufferedImage enermy11 = null;
	BufferedImage enermy12 = null;
	BufferedImage enermy13 = null;
	BufferedImage enermy14 = null;
	BufferedImage enermy21 = null;
	BufferedImage enermy22 = null;
	BufferedImage enermy31 = null;
	BufferedImage enermy32 = null;
	BufferedImage exploed = null;

   // ��ü��ǥ(������ǥ�� ȭ�鳡�� �浹�� �����ϱ� ������� ��ü��ǥ x�� ����.)
   public int forceX = 1;
   // x��ǥ�� �̵��� �ѹ� �޽��Ѵ�.(y��ǥ���� ���� �ɶ��� �밢�� �������� ���ڿ� ������ ������ y��ǥ �̵��� �Ϸ� �ɶ����� x��ǥ �̵���
   // ����.)
   public int breakTime = 1;
   // ������ �����ϱ� ���� �Ϸù�ȣ
   int number = 0;
   // �� ��ü�� x�� �����̴� ũ��(enemyMoveSize�� �޴´�.)
   public int directionX = 10;
   // �� ��ü�� �����̴� ����(1:������ 0:����)
   public int direction = 1;
   // ����ü�� �����̴� �ӵ�
   public long speed;
   // ������ü�ΰ�?(0:������ü 1:����ִ°�ü)
   public boolean isDead = false;
   // �ִϸ��̼� ����(true: �׸�1 | false: �׸�2)
   boolean enime = false;
   // ����(false: ������ | true: ����)
   public boolean isRemove = false;
   //�ð������ ���� �ð����� ����
   long setTime = System.currentTimeMillis();
   public long maxTime = System.currentTimeMillis();
   
   /* ������ */

   // ������
   public Enermy(String name, int sX, int sY, int sNumber) {

      super(name);

      super.x = sX;
      super.y = sY;
      // ������ ������ ���� �Ϸù�ȣ
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

   /* Get, Set �޼ҵ� �ι� */

   // Set�Լ�
   public void setX(int sx) {
      this.x = sx;
   }

   public void setY(int sy) {
      this.y = sy;
   }

   public void setSpeed(int sS) {
      this.speed = sS;
   }

   // Get�Լ�
   public int getX() {
      return this.x;
   }

   public int getY() {
      return this.y;
   }

   public long getSpeed() {
      return this.speed;
   }

   // ������ ���� ���� �ѹ�
   public int getNumber() {
      return this.number;
   }

   /* �޼ҵ� ����� */

   // ȭ�鿡 ��µ� ������
   public void update() {
      setTime = System.currentTimeMillis();
		if (!isDead) {
			if (setTime >= maxTime) {

				maxTime = System.currentTimeMillis() + speed;
				// �������� ����ϴ� �ι�
				if (breakTime == 1) {
					// ������ �¿��� ���� �ٴٶ�����
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
					// �¿� �̵� ������ �ι�
					//��
					if (direction == 0 && breakTime == 1) {

						directionX = -ENERMY_MOVE_SIZE;
						forceX--;
						breakTime = 1;
					}
					//��
					if (direction == 1 && breakTime == 1) {
						directionX = +ENERMY_MOVE_SIZE;
						forceX++;
						breakTime = 1;
					}
				}
				// ���� ���� �������� x��ǥ�� ����
				super.x = super.x + directionX;
				//���� �ٴٶ������� x��ǥ�� �̵���Ű�� �ʴ´�.
				breakTime = 1;

				/*�ִϸ��̼� ��ȯ�ι�*/

				// ���� ����ִٸ�
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
		/*�׾����� �ִϸ��̼�*/
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

   // �̹����� �����ϴ� �Լ�(�ִϸ��̼��� ����)
   public void getPicture(BufferedImage tmpImg) {
     
         super.img = tmpImg;

     
   }
}