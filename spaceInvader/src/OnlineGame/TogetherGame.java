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

	// 배경이미지
	BufferedImage img = null;

	/* @@ 객체선언 부문 @@ */
	 Socket serverSocket; //클라는 무조건 서버랑 1:1 통신이므로 클라가 쓰는 소켓은 서버거 하나면 된다.
	 public BufferedReader in;  //서버에서 데이터를 받을 스트림을 담을 그릇
	 public PrintWriter writer; //서버에게 데이터를 보낼 스트림을 담을 그릇

	/* @ 플레이어 객체 선언 부문 @ */
	public PlayerDraw_Client player1;
	public PlayerDraw_Client player2;
	// 적 객체 선언(링크드 리스크)
	public EnermyDraw_Client enermy[] = new EnermyDraw_Client[55];
	// 적총알 객체 생성
	public EnermyBulletDraw_Client enermyBulletDraw[] = new EnermyBulletDraw_Client[12];
	
	public PlayerBulletDraw_Cilent player1Bullet[] = new PlayerBulletDraw_Cilent[3];
	public PlayerBulletDraw_Cilent player2Bullet[] = new PlayerBulletDraw_Cilent[3];
	
	//라이프바 선언
	LifeBar lifeBar[] = new LifeBar[2];
	//스코어
	public Score score;
	/* @@ 변수 선언부 @@ */

	// 총알 객체 기본속도 낮을수록 난이도가 높음
	public int optionLevel = 9;
	// 적 객체 이동속도 낮을수록 난이도가 높음
	public int optionSpeed = 1000;
	// 총알 발사 검사기에 사용될 랜덤값을 담을 변수
	int random;
	// 적객체 생성시 인덱스 번호
	int enermyIndex;
	// 적객체의 초기 위치값 설정을 위한 값
	int setEnermyLocation;
	// 적 객체 좌표
	public int enermyX = 0;
	public int enermyY = 0;

	// 게임오버 검사 변수
	public boolean gameOver =false;
	public boolean gameClear = false;
	public boolean gameOn;
	// 폴팔콜리젼과 일반 적 콜리젼을 구분해주는 불린
	// 개발중 희규가
	public boolean wallFirst = true;
	// 총알의 발사 속도 조절을 위한 시간변수
	long playerBulletSetTime;
	long playerBulletMaxTime;

	boolean soketDataEnermyMove = false;
	boolean keyPressedFlag = false;
	public boolean lock = false;
	public String playerIndex = null;
	public int playerScore = 0;
	
	MyThread2 repaintThread = new MyThread2();
	/* @@ 생성자 정의 부문 @@ */

	/* @ 생성자 @ */

	public TogetherGame(Socket socket, String playerIndex) {
		// 패널 기본 설정
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
	         System.out.println("협동게임 스트림 생성 오류");
	      }
		try {
			img = ImageIO.read(new File("singleGameImage/BackGround.png"));
		} catch (IOException e) {
			System.out.println("인게임 배경을 불러올 수 없습니다.");
			System.exit(0);
		}
		
		System.out.println("생성");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~SFSDASDASCQWEQWDQWADSADASDASDASDASDASDADZXFDWRFSXCBDSRFZXZVXDFGDX");
		
		
		

		// 초기화 메소드
		init();
		repaintThread.start();
		// repaintThread.start();

	}

	/* 출력부문 */

	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 0, 0, null);
		if (!gameOver) {
			
			// 적객체를 그림
			for (int i = 0; i < enermy.length; i++) {
				enermy[i].draw(g);
			}
			
			for (int j = 0; j < enermyBulletDraw.length; j++) {
				enermyBulletDraw[j].draw(g);
			}
			
			//플레이어를 그림
			
			player1.draw(g);
			player2.draw(g);
			
			
			//플레이어 총알 그림
			for (int i = 0; i < player1Bullet.length; i++) {
				player1Bullet[i].draw(g);
			}
			for (int i = 0; i < player2Bullet.length; i++) {
				player2Bullet[i].draw(g);
			}
			//플레이어 라이프 그림
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
			//스코어 바
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
	
	
	/* @ 초기화 메소드 @ */
	public void init() {
		/* @@ 변수 초기화 부문 @@ */

		// 총알 발사 검사기에 사용될 랜덤값을 담을 변수
		random = 0;
		// 적객체 생성시 인덱스 번호
		enermyIndex = 0;
		// 적객체의 초기 위치값 설정을 위한 값
		setEnermyLocation = -20;
		// 벽 객체 출력을 위한 좌표값

		// 게임오버 검사 변수
		gameOver = false;
		gameClear = false;
		gameOn = true;
		// 개발중 희규가
		wallFirst = true;
		// 총알의 발사 속도 조절을 위한 시간변수
		playerBulletSetTime = System.currentTimeMillis();
		playerBulletMaxTime = System.currentTimeMillis();

		//스코어 바 생성
		score = new Score();
		// 적객체 생성
		for (int j = 0; j < 5; j++) {
			for (int i = 0; i < 11; i++) {
				enermy[enermyIndex] = new EnermyDraw_Client("SingleGameImage/Empty.png", setEnermyLocation + 80 * (i + 1),
						60 * (j + 1), enermyIndex);
				enermyIndex++;
			}
		}

		// 플레이어 객체 생성
		
	
		player1 = new PlayerDraw_Client("SingleGameImage/Player1.png", 450, 650, 1);
		
		player2 = new PlayerDraw_Client("SingleGameImage/Player2.png", 730, 650, 2);
		
		//플레이어 라이프바 생성
		for (int i = 0; i < lifeBar.length; i++) {
			lifeBar[i] = new LifeBar("SingleGameImage/Player1Life.png", 10 + (25 * i), 50);
		}
		
		// 적 총알 객체 생성
		for (int j = 0; j < enermyBulletDraw.length; j++) {
			enermyBulletDraw[j] = new EnermyBulletDraw_Client("SingleGameImage/Empty.png", 0, 0);
		}
		
		//플레이어 총알 생성
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
	         // 총알 발사 부문
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
