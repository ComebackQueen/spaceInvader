package OnlineGame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Bullet.PlayerBullet;
import ItemBar.ItemBar;
import LifeBar.LifeBar;

public class TogetherGame extends JPanel implements KeyListener{

	// ����̹���
	BufferedImage img = null;

	/* @@ ��ü���� �ι� @@ */
	 Socket serverSocket; //Ŭ��� ������ ������ 1:1 ����̹Ƿ� Ŭ�� ���� ������ ������ �ϳ��� �ȴ�.
	 public BufferedReader in;  //�������� �����͸� ���� ��Ʈ���� ���� �׸�
	 public PrintWriter writer; //�������� �����͸� ���� ��Ʈ���� ���� �׸�

	/* @ �÷��̾� ��ü ���� �ι� @ */
	public PlayerDraw_Client player1;
	public PlayerDraw_Client player2;
	// �� ��ü ����(��ũ�� ����ũ)
	public EnermyDraw_Client enermy[] = new EnermyDraw_Client[55];
	// ���Ѿ� ��ü ����
	public EnermyBulletDraw_Client enermyBulletDraw[] = new EnermyBulletDraw_Client[12];
	
	public PlayerBulletDraw_Cilent player1Bullet[] = new PlayerBulletDraw_Cilent[3];
	public PlayerBulletDraw_Cilent player2Bullet[] = new PlayerBulletDraw_Cilent[3];
	
	//�������� ����
	LifeBar lifeBar[] = new LifeBar[2];
	//���ھ�
	public Score score;
	/* @@ ���� ����� @@ */

	// �Ѿ� ��ü �⺻�ӵ� �������� ���̵��� ����
	public int optionLevel = 9;
	// �� ��ü �̵��ӵ� �������� ���̵��� ����
	public int optionSpeed = 1000;
	// �Ѿ� �߻� �˻�⿡ ���� �������� ���� ����
	int random;
	// ����ü ������ �ε��� ��ȣ
	int enermyIndex;
	// ����ü�� �ʱ� ��ġ�� ������ ���� ��
	int setEnermyLocation;
	// �� ��ü ��ǥ
	public int enermyX = 0;
	public int enermyY = 0;

	// ���ӿ��� �˻� ����
	public boolean gameOver =false;
	public boolean gameClear = false;
	public boolean gameOn;
	// �����ݸ����� �Ϲ� �� �ݸ����� �������ִ� �Ҹ�
	// ������ ��԰�
	public boolean wallFirst = true;
	// �Ѿ��� �߻� �ӵ� ������ ���� �ð�����
	long playerBulletSetTime;
	long playerBulletMaxTime;

	boolean soketDataEnermyMove = false;
	boolean keyPressedFlag = false;
	public boolean lock = false;
	public String playerIndex = null;
	public int playerScore = 0;
	
	MyThread2 repaintThread = new MyThread2();
	/* @@ ������ ���� �ι� @@ */

	/* @ ������ @ */

	public TogetherGame(Socket socket, String playerIndex) {
		// �г� �⺻ ����
		super();
		this.setBackground(Color.WHITE);
		this.addKeyListener(this);
		this.requestFocus();
		setFocusable(true);
		setLayout(null);
		serverSocket = socket;
		this.playerIndex = playerIndex;
		try {
	         in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	         writer = new PrintWriter(socket.getOutputStream(), true);
	        
	      }catch (Exception e){
	         System.out.println("�������� ��Ʈ�� ���� ����");
	      }
		try {
			img = ImageIO.read(new File("singleGameImage/BackGround.png"));
		} catch (IOException e) {
			System.out.println("�ΰ��� ����� �ҷ��� �� �����ϴ�.");
			System.exit(0);
		}
		
		System.out.println("����");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~SFSDASDASCQWEQWDQWADSADASDASDASDASDASDADZXFDWRFSXCBDSRFZXZVXDFGDX");
		
		
		

		// �ʱ�ȭ �޼ҵ�
		init();
		repaintThread.start();
		// repaintThread.start();

	}

	/* ��ºι� */

	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 0, 0, null);
		if (!gameOver) {
			
			// ����ü�� �׸�
			for (int i = 0; i < enermy.length; i++) {
				enermy[i].draw(g);
			}
			
			for (int j = 0; j < enermyBulletDraw.length; j++) {
				enermyBulletDraw[j].draw(g);
			}
			
			//�÷��̾ �׸�
			
			player1.draw(g);
			player2.draw(g);
			
			
			//�÷��̾� �Ѿ� �׸�
			for (int i = 0; i < player1Bullet.length; i++) {
				player1Bullet[i].draw(g);
			}
			for (int i = 0; i < player2Bullet.length; i++) {
				player2Bullet[i].draw(g);
			}
			//�÷��̾� ������ �׸�
			if(playerIndex.compareTo("1")== 0)
			{
				for (int i = 0; i < player1.playerLife; i++) {
					lifeBar[i].draw(g);
				}
			}
			if(playerIndex.compareTo("2")== 0)
			{
				for (int i = 0; i < player2.playerLife; i++) {
					lifeBar[i].draw(g);
				}
			}
			//���ھ� ��
			score.paintComponent(g);
			

		}

	}

	class MyThread2 extends Thread {
		public void run() {
			while (true) {
				if (!gameOver || !gameClear) {
					playerBulletSetTime = System.currentTimeMillis();
					for (int j = 0; j < enermy.length; j++) {
						if(enermy[j].isRemove)
						{
							enermy[j].setPicture("emptyImg");
						}
							
					}
					score.setScore(playerScore);
					repaint();
					try {
						this.sleep(10);
					} catch (Exception e) {

					}

				} else{
					if(gameOver)
						this.suspend();
						if(gameClear)
							System.out.println("zmfldfj");
				}
			}

		}

	}
	
	
	/* @ �ʱ�ȭ �޼ҵ� @ */
	public void init() {
		/* @@ ���� �ʱ�ȭ �ι� @@ */

		// �Ѿ� �߻� �˻�⿡ ���� �������� ���� ����
		random = 0;
		// ����ü ������ �ε��� ��ȣ
		enermyIndex = 0;
		// ����ü�� �ʱ� ��ġ�� ������ ���� ��
		setEnermyLocation = -20;
		// �� ��ü ����� ���� ��ǥ��

		// ���ӿ��� �˻� ����
		gameOver = false;
		gameClear = false;
		gameOn = true;
		// ������ ��԰�
		wallFirst = true;
		// �Ѿ��� �߻� �ӵ� ������ ���� �ð�����
		playerBulletSetTime = System.currentTimeMillis();
		playerBulletMaxTime = System.currentTimeMillis();

		//���ھ� �� ����
		score = new Score();
		// ����ü ����
		for (int j = 0; j < 5; j++) {
			for (int i = 0; i < 11; i++) {
				enermy[enermyIndex] = new EnermyDraw_Client("SingleGameImage/Empty.png", setEnermyLocation + 80 * (i + 1),
						60 * (j + 1), enermyIndex);
				enermyIndex++;
			}
		}

		// �÷��̾� ��ü ����
		
	
		player1 = new PlayerDraw_Client("SingleGameImage/Player1.png", 450, 650, 1);
		
		player2 = new PlayerDraw_Client("SingleGameImage/Player2.png", 730, 650, 2);
		
		//�÷��̾� �������� ����
		for (int i = 0; i < lifeBar.length; i++) {
			lifeBar[i] = new LifeBar("SingleGameImage/Player1Life.png", 10 + (25 * i), 50);
		}
		
		// �� �Ѿ� ��ü ����
		for (int j = 0; j < enermyBulletDraw.length; j++) {
			enermyBulletDraw[j] = new EnermyBulletDraw_Client("SingleGameImage/Empty.png", 0, 0);
		}
		
		//�÷��̾� �Ѿ� ����
		for (int i = 0; i < player1Bullet.length; i++) {
			player1Bullet[i] = new PlayerBulletDraw_Cilent("SingleGameImage/Empty.png");
		}
		for (int i = 0; i < player2Bullet.length; i++) {
			player2Bullet[i] = new PlayerBulletDraw_Cilent("SingleGameImage/Empty.png");
		}
		
	}

	@Override
	   public void keyPressed(KeyEvent e) {
	      
	      if (!keyPressedFlag && (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT)) {

	         if (playerIndex.compareTo("1") == 0) {
	            if (!player1.isDead) {
	               writer.println(player1.keyPressed(e));
	            }

	         } else if (playerIndex.compareTo("2") == 0) {
	            if (!player2.isDead) {
	               writer.println(player2.keyPressed(e));
	            }

	         }
	         keyPressedFlag = true;
	      }
	      if (e.getKeyCode() == KeyEvent.VK_SPACE) {
	         // �Ѿ� �߻� �ι�
	         if (playerBulletSetTime >= playerBulletMaxTime) {
	            playerBulletMaxTime = System.currentTimeMillis();
	            playerBulletMaxTime += 500;

	            if (playerIndex.compareTo("1") == 0) {
	               if (!player1.isDead) {
	                  if (player1.playerLife > 0) {
	                     if (!player1Bullet[player1.playerBulletIndex].launched) {
	                        writer.println(player1Bullet[player1.playerBulletIndex].keyPressed(e, player1.x + 20,
	                              player1.y, player1.isDead, player1, player1.playerBulletIndex));
	                        player1.playerBulletIndex++;
	                     }

	                     if (player1.playerBulletIndex >= 3)
	                        player1.playerBulletIndex = 0;
	                  }
	               }
	            } else if (playerIndex.compareTo("2") == 0) {
	               if (!player2.isDead) {
	                  if (player2.playerLife > 0) {
	                     if (!player2Bullet[player2.playerBulletIndex].launched) {
	                        writer.println(player2Bullet[player2.playerBulletIndex].keyPressed(e, player2.x + 20,
	                              player2.y, player2.isDead, player2, player2.playerBulletIndex));
	                        player2.playerBulletIndex++;
	                     }
	                     if (player2.playerBulletIndex >= 3)
	                        player2.playerBulletIndex = 0;
	                  }
	               }
	            }

	         }
	      }

	   }

	   @Override
	   public void keyReleased(KeyEvent e) {
	      // TODO Auto-generated method stub
	      
	      if (keyPressedFlag  && (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT)) {

	         if (playerIndex.compareTo("1") == 0) {
	            if (!player1.isDead) {
	               writer.println(player1.keyReleased(e));
	            }
	         } else if (playerIndex.compareTo("2") == 0) {
	            if (!player2.isDead) {
	            writer.println(player2.keyReleased(e));
	            }
	         }
	         keyPressedFlag = false;

	      }

	   }
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
