package SingleGamePanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Bullet.EnermyBullet;
import Bullet.PlayerBullet;
import Crash.BulletColision;
import Crash.EnermyColision;
import Crash.ItemColision;
import Crash.PlayerColision;
//import ETC.Play_Sound;
import Enermy.Enermy;
import Item.HiddenMonster;
import Item.Item;
import ItemBar.ItemBar;
import ItemFont.ItemFont;
import LifeBar.LifeBar;
import MainPage.MainPage;
import Player.Player;
import Score.Score;
import Wall.WallPiece;

//출력할 공간인 패널을 생성
public class SingleGame extends JPanel implements KeyListener {

	// 배경이미지
	BufferedImage img = null;

	/* @@ 객체선언 부문 @@ */

	/* @ 플레이어 객체 선언 부문 @ */

	// 플레이어 객체 선언
	Player player1;
	// 플레이어 총알 객체 선언
	PlayerBullet playerBullet[] = new PlayerBullet[5];

	// 플레이어 라이프바 선언
	LifeBar lifeBar[] = new LifeBar[5];

	/* @ 적 객체 선언 부문 @ */

	// 적 객체 선언(링크드 리스크)
	LinkedList<Enermy> enermy = new LinkedList<Enermy>();
	// 적 총알객체 선언
	EnermyBullet enermyBullet[] = new EnermyBullet[EnermyBullet.bulletCount];

	/* @ 충돌검사기 객체 선언 부문 @ */

	// 총알 충돌 검사기 선언
	BulletColision bulletColision;
	// 적 충돌 검사기 객체 선언
	EnermyColision enermyColision;
	// 플레이어 충돌 검사기 객체 선언
	PlayerColision playerColision;
	// 아이템 충돌 검사기 객체 선언
	ItemColision itemColision;

	/* @ 점수체크기 객체 선언 부문 @ */

	// 점수 체크기 객체 선언
	Score score;

	// 아이템 바 객체 선언
	ItemBar itemBar;

	ItemFont itemFont;

	/* @ 공수기 객체 선언 부문 @ */

	// 공수기 객체 선언
	HiddenMonster hiddenMonster;
	// 아이템 객체 선언
	Item item;

	/* @ 방어막 객체 선언 부문 @ */

	// 방어막 객체 선언
	LinkedList<WallPiece> wall = new LinkedList<WallPiece>();

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

	// 벽객체 생성을 위한 좌표변수
	int wallX;
	int wallY;

	// 게임오버 검사 변수
	public boolean gameOver;
	public boolean gameClear;
	public boolean gameOn;
	// 폴팔콜리젼과 일반 적 콜리젼을 구분해주는 불린
	// 개발중 희규가
	public boolean wallFirst = true;
	// 총알의 발사 속도 조절을 위한 시간변수
	long playerBulletSetTime;
	long playerBulletMaxTime;

	/* @@ 생성자 정의 부문 @@ */

	/* @ 생성자 @ */

	public SingleGame() {
		// 패널 기본 설정
		super();
		this.setBackground(Color.WHITE);
		this.addKeyListener(this);
		this.requestFocus();
		setFocusable(true);
		setLayout(null);

		// 초기화 메소드
		init();

		try {
			img = ImageIO.read(new File("singleGameImage/BackGround.png"));
		} catch (IOException e) {
			System.out.println("인게임 배경을 불러올 수 없습니다.");
			System.exit(0);
		}

		updateThread.start();
		repaintThread.start();

	}

	/* 출력부문 */

	public void paint(Graphics g) {
		super.paint(g);

		// 나중에 전부 통일 firstPaint로 해서 전부 처음은 그냥 다그리고 좌표 이동일시 or 라이프 변경시 repaint
		// 하도록 통일 렉줄이게
		if (!gameOver) {
			// 적객체를 그림
			g.drawImage(img, 0, 0, null);
			player1.draw(g);
			score.paintComponent(g);
			itemFont.paintComponent(g);
			itemBar.draw(g);
			for (int i = 0; i < enermy.size(); i++) {
				enermy.get(i).draw(g);
			}
			for (int i = 0; i < enermyBullet.length; i++) {
				enermyBullet[i].draw(g);
			}

			for (int i = 0; i < playerBullet.length; i++) {
				playerBullet[i].draw(g);
			}

			for (int i = 0; i < player1.playerLife; i++) {
				lifeBar[i].draw(g);
			}
			if (wallFirst)
				for (int i = 0; i < wall.size(); i++) {
					wall.get(i).draw(g);
					for (int j = 0; j < enermyBullet.length; j++) {
						if (j < playerBullet.length)
							wall.get(i).pb_colision(playerBullet[j]);

						wall.get(i).eb_colision(enermyBullet[j]);
					}
				}

			if (player1.helper[0] != null && player1.helper[1] != null) {
				for (int i = 0; i < 2/*player1.helper.length*/; i++) {
					player1.helper[i].draw(g);
					player1.helper[i].helperBullet.draw(g);
				}

			}

			hiddenMonster.random = (int) (Math.random() * 90 + 1);
			if (hiddenMonster.random == 1 && item.itemOn == false) {
				hiddenMonster.airplaneOn = true;
				hiddenMonster.random = 0;
			}
			if (hiddenMonster.airplaneOn) {
				hiddenMonster.draw(g);
				if (!item.itemOn)
					item.random = (int) (Math.random() * 35 + 1);

			}
			if(hiddenMonster.x>150 && hiddenMonster.x<1150 )
			if (item.random == 1) {
				if (player1.getItem == 0) {
					item.itemOn = true;
					item.random = 0;
					if (!item.save) {
						item.ix = hiddenMonster.hx;

						item.save = true;
					}
				}
			}

			if (item.itemOn) {
				item.draw(g);
			}
			if (!itemColision.itemDrop  && player1.getItem == 0) {
	            itemColision.itemDrop = true;
	            item.itemOn = false;
	         }

		}
	}

	class MyThread1 extends Thread {
		public void run() {

			while (true) {
				if (!gameOver) {
					if (!player1.delay) {

						playerBulletSetTime = System.currentTimeMillis();

						for (int i = enermy.size(); i > 0; i--) {
							enermy.get(i - 1).setSpeed(optionSpeed);
							enermy.get(i - 1).update();
						}

						for (int i = 0; i < enermyBullet.length; i++)
						{
							enermyBullet[i].update();
						}

						for (int i = 0; i < playerBullet.length; i++)
							playerBullet[i].update(player1);
						if (wallFirst)
							for (int i = 0; i < wall.size(); i++)
								wall.get(i).update();

						if (player1.getItem == 3) {
							if (player1.helper[0] != null) {
								for (int i = 0; i < player1.helper.length; i++) {
									player1.helper[i].update(player1);
									player1.helper[i].helperBullet.update(player1.helper[i]);

								}
							}
						}

						itemBar.update(player1);

						score.update(); // 점수
						if (hiddenMonster.airplaneOn)
							hiddenMonster.update();
						if (hiddenMonster.hx == 40) {
							hiddenMonster.airplaneOn = false;
							hiddenMonster.hx = 1280;// 히든몬스터
						}

						if (item.itemOn)
							item.update();

						itemFont.update(player1);
						player1.update();

						for (int i = 0; i < playerBullet.length; i++) { // 충돌검사부분
							if (!player1.exploedON)
								for (int j = 0; j < enermy.size(); j++) {
									if (!player1.exploedON) {
										enermyColision.enermyColision(playerBullet[i], enermy.get(j), j, player1);

										if (player1.helper[0] != null)
											if (i < player1.helper.length)
												enermyColision.helpedBulletEnermyColision(
														player1.helper[i].helperBullet, enermy.get(j));

										if (enermy.get(j).isRemove)
											enermy.remove(j);

										if (enermyColision.remove() != -1) {

											if (j < enermy.size()) {
												if (enermy.get(j).getNumber() <= 21 && enermy.get(j).getNumber() >= 0) {
													score.setScore(30); // 점수 부분 잡을떄마다 10점씩
													// 증가

												} else if (enermy.get(j).getNumber() > 21
														&& enermy.get(j).getNumber() <= 43) {
													score.setScore(20);

												} else if (enermy.get(j).getNumber() > 43) {
													score.setScore(10);

												}
											}
										}
									}

								}

							else {
								for (int j = 0; j < enermy.size(); j++) {

									enermyColision.bombColision(enermy.get(j), j);

									if (enermy.get(j).isRemove)
										enermy.remove(j);

									if (enermyColision.remove() != -1) {

										if (j < enermy.size()) {
											if (enermy.get(j).getNumber() <= 21 && enermy.get(j).getNumber() >= 0) {
												score.setScore(30); // 점수 부분 잡을떄마다 10점씩
												// 증가

											} else if (enermy.get(j).getNumber() > 21
													&& enermy.get(j).getNumber() <= 43) {
												score.setScore(20);

											} else if (enermy.get(j).getNumber() > 43) {
												score.setScore(10);

											}
										}

									}
								}
								player1.exploedON = false;
								player1.getItem = 0;
							}
							// 아이템 충돌 검사부문
							if (item.itemOn)
								itemColision.item_colision(item, player1);
							// 게임클리어 검사
							if (enermy.size() == 0) {
								gameClear = true;
								this.suspend();
								break;
							}
						}

						// 총알 검사부문
						// 만약 총알이 발사 되있지 않은 상태라면
						for (int i = 0; i < enermyBullet.length; i++) {
							if (enermyBullet[i].launched == false) {
								// 랜덤값을 발생시킨다.(50000/1의 확률)
								random = (int) (Math.random() * 100 + 1);
								// 만약 일치하게되면
								if (random == 1) {
									// 이번에는 적객체중에 살아있는 객체를 렌덤값으로 구해본다.
									random = (int) (Math.random() * enermy.size() - 1);
									// 만약 렌덤 값의 인덱스에 해당하는 객체가 살아있다면
									if (enermy.size() != 0) {
										if (enermy.get(random).isDead == false) {
											// 총알 객체를 생성한다.
											enermyBullet[i] = new EnermyBullet("SingleGameImage\\Bullet.png",
													enermy.get(random).x + 23, enermy.get(random).y + 40);

											// 적 총알 속도 조정
											enermyBullet[i].setSpeed(optionLevel);

											// 생성과 동시에 발사
											enermyBullet[i].launched = true;

										} else {
											enermy.remove(random);
										}

									}
								}

							}
							playerColision.userColision(player1, enermyBullet[i]); // 적
							// 총알이
							// 유저와
							// 충돌
							// 했을때를
							// 검사
							for (int i1 = 0; i1 < playerBullet.length; i1++)
								bulletColision.bulletColision(playerBullet[i1], enermyBullet[i], i1, player1);
						}

						// 게임오버 검사
						for (int i = 0; i < enermy.size(); i++) {
							if (enermy.get(i).y > 500 || player1.playerLife <= 0) {
								gameOver = true;
								this.suspend();
								break;
							}
						}
					} else {
						try {
							updateThread.sleep(3000);
							player1.delay = false;
							player1.keyLeftOn = false;
							player1.keyRightOn = false;
							player1.getPicture(player1.playerImg);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
				try{
				this.sleep(2);
				}catch(Exception e){
					System.out.println(e);
				}
			}
		}
	}

	Thread updateThread = new MyThread1();

	class MyThread2 extends Thread {
		public void run() {
			while (true) {
				if (!gameOver || !gameClear) {
					repaint();
				}
				if (gameOver) {
					MainPage.button.setBounds(0, 0, 1280, 720);
					add(MainPage.button);
					break;
				} else if (gameClear) {
					MainPage.Clear.setBounds(0, 0, 1280, 720);
					add(MainPage.Clear);
					break;
				}
				try{
				this.sleep(10);
				}catch(Exception e){
					System.out.println(e);
				}
			}
			
		}
	}

	Thread repaintThread = new MyThread2();

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method
		if (!player1.isDead) {
			player1.keyPressed(e);

		}
		if (player1.getItem == 2) {
			if (playerBulletSetTime >= playerBulletMaxTime) {

				playerBulletMaxTime = System.currentTimeMillis();
				playerBulletMaxTime += 200;
				if (!playerBullet[player1.playerBulletIndex].launched) {
					playerBullet[player1.playerBulletIndex].keyPressed(e, player1.x + 20, player1.y, player1.isDead,
							player1);
					player1.playerBulletIndex++;
				}
				if (player1.playerBulletIndex >= playerBullet.length)
					player1.playerBulletIndex = 0;
			}
		} else if (player1.getItem == 1) {

			playerBulletMaxTime = System.currentTimeMillis();
			playerBulletMaxTime += 500;
			if (!playerBullet[0].launched) {
				playerBullet[0].keyPressed(e, player1.x + 20, player1.y, player1.isDead, player1);
			}

		} else {
			if (playerBulletSetTime >= playerBulletMaxTime) {
					playerBulletMaxTime = System.currentTimeMillis();
					playerBulletMaxTime += 500;
					if (!playerBullet[player1.playerBulletIndex].launched) {
						playerBullet[player1.playerBulletIndex].keyPressed(e, player1.x + 20, player1.y, player1.isDead,
								player1);
						player1.playerBulletIndex++;
					}
					if (player1.playerBulletIndex >= 3)
						player1.playerBulletIndex = 0;
				
			}

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (!player1.isDead)
			player1.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	/* @@ 메소드 정의 부문 @@ */

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
		wallX = 64;
		wallY = 510;
		// 게임오버 검사 변수
		gameOver = false;
		gameClear = false;
		gameOn = true;
		// 개발중 희규가
		wallFirst = true;
		// 총알의 발사 속도 조절을 위한 시간변수
		playerBulletSetTime = System.currentTimeMillis();
		playerBulletMaxTime = System.currentTimeMillis();

		/* @@ 객체생성 부문 @@ */

		/* @ 플레이어 객체 생성 부문 @ */

		// 플레이어 생성 부문
		player1 = new Player("SingleGameImage/Player1.png", 640, 650);

		// 플레이어 총알 객체 생성
		for (int i = 0; i < playerBullet.length; i++) {
			playerBullet[i] = new PlayerBullet("SingleGameImage/Bullet.png");
		}

		// 플레이어 라이프바 객체 생성
		for (int i = 0; i < lifeBar.length; i++) {
			lifeBar[i] = new LifeBar("SingleGameImage/Player1Life.png", 10 + (25 * i), 50);
		}

		// 플레이어 아이템바 객체 생성
		itemBar = new ItemBar("SingleGameImage/Empty.png", 10, 100);

		/* @ 적 객체 선언 부문 @ */

		// 적객체 생성
		for (int j = 0; j < 5; j++) {
			for (int i = 0; i < 11; i++) {
				enermy.add(new Enermy("SingleGameImage/Empty.png", setEnermyLocation + 80 * (i + 1), 60 * (j + 1),
						enermyIndex));
				enermyIndex++;
			}
		}
		// 적 총알 생성
		for (int i = 0; i < enermyBullet.length; i++) {
			enermyBullet[i] = new EnermyBullet("SingleGameImage/Empty.png", 50, 50);
		}

		/* @ 충돌검사기 객체 생성 @ */

		// 총알 충돌 검사기 생성
		bulletColision = new BulletColision();
		// 적 충돌 검사기 객체 생성(유저 총알과 적객체 충돌 판정)
		enermyColision = new EnermyColision();
		// 플레이어 충돌 검사기 객체 생성
		playerColision = new PlayerColision();
		// 아이템 충돌 검사기 객체 생성
		itemColision = new ItemColision();

		/* @ 점수체크기 객체 생성 부문 @ */

		// 점수 체크기 객체 생성
		score = new Score();
		// 아이템 폰트 객체 생성
		itemFont = new ItemFont();

		/* @ 공수기 객체 생성 부문 @ */

		// 공수기 객체 생성
		hiddenMonster = new HiddenMonster("SingleGameImage/AirPlane.png");
		// 아이템 객체 생성
		item = new Item("SingleGameImage/ITEM.png", hiddenMonster);

		/* @ 방어막 객체 생성 부문 @ */

		// 방어막 객체 생성
		for (int i = 0; i < 7; i++) {
			wall.add(new WallPiece(wallX, wallY));
			wallX += 172;
		}

	}

}

