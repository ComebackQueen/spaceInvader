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

//����� ������ �г��� ����
public class SingleGame extends JPanel implements KeyListener {

	// ����̹���
	BufferedImage img = null;

	/* @@ ��ü���� �ι� @@ */

	/* @ �÷��̾� ��ü ���� �ι� @ */

	// �÷��̾� ��ü ����
	Player player1;
	// �÷��̾� �Ѿ� ��ü ����
	PlayerBullet playerBullet[] = new PlayerBullet[5];

	// �÷��̾� �������� ����
	LifeBar lifeBar[] = new LifeBar[5];

	/* @ �� ��ü ���� �ι� @ */

	// �� ��ü ����(��ũ�� ����ũ)
	LinkedList<Enermy> enermy = new LinkedList<Enermy>();
	// �� �Ѿ˰�ü ����
	EnermyBullet enermyBullet[] = new EnermyBullet[EnermyBullet.bulletCount];

	/* @ �浹�˻�� ��ü ���� �ι� @ */

	// �Ѿ� �浹 �˻�� ����
	BulletColision bulletColision;
	// �� �浹 �˻�� ��ü ����
	EnermyColision enermyColision;
	// �÷��̾� �浹 �˻�� ��ü ����
	PlayerColision playerColision;
	// ������ �浹 �˻�� ��ü ����
	ItemColision itemColision;

	/* @ ����üũ�� ��ü ���� �ι� @ */

	// ���� üũ�� ��ü ����
	Score score;

	// ������ �� ��ü ����
	ItemBar itemBar;

	ItemFont itemFont;

	/* @ ������ ��ü ���� �ι� @ */

	// ������ ��ü ����
	HiddenMonster hiddenMonster;
	// ������ ��ü ����
	Item item;

	/* @ �� ��ü ���� �ι� @ */

	// �� ��ü ����
	LinkedList<WallPiece> wall = new LinkedList<WallPiece>();

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

	// ����ü ������ ���� ��ǥ����
	int wallX;
	int wallY;

	// ���ӿ��� �˻� ����
	public boolean gameOver;
	public boolean gameClear;
	public boolean gameOn;
	// �����ݸ����� �Ϲ� �� �ݸ����� �������ִ� �Ҹ�
	// ������ ��԰�
	public boolean wallFirst = true;
	// �Ѿ��� �߻� �ӵ� ������ ���� �ð�����
	long playerBulletSetTime;
	long playerBulletMaxTime;

	/* @@ ������ ���� �ι� @@ */

	/* @ ������ @ */

	public SingleGame() {
		// �г� �⺻ ����
		super();
		this.setBackground(Color.WHITE);
		this.addKeyListener(this);
		this.requestFocus();
		setFocusable(true);
		setLayout(null);

		// �ʱ�ȭ �޼ҵ�
		init();

		try {
			img = ImageIO.read(new File("singleGameImage/BackGround.png"));
		} catch (IOException e) {
			System.out.println("�ΰ��� ����� �ҷ��� �� �����ϴ�.");
			System.exit(0);
		}

		updateThread.start();
		repaintThread.start();

	}

	/* ��ºι� */

	public void paint(Graphics g) {
		super.paint(g);

		// ���߿� ���� ���� firstPaint�� �ؼ� ���� ó���� �׳� �ٱ׸��� ��ǥ �̵��Ͻ� or ������ ����� repaint
		// �ϵ��� ���� �����̰�
		if (!gameOver) {
			// ����ü�� �׸�
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

						score.update(); // ����
						if (hiddenMonster.airplaneOn)
							hiddenMonster.update();
						if (hiddenMonster.hx == 40) {
							hiddenMonster.airplaneOn = false;
							hiddenMonster.hx = 1280;// �������
						}

						if (item.itemOn)
							item.update();

						itemFont.update(player1);
						player1.update();

						for (int i = 0; i < playerBullet.length; i++) { // �浹�˻�κ�
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
													score.setScore(30); // ���� �κ� ���������� 10����
													// ����

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
												score.setScore(30); // ���� �κ� ���������� 10����
												// ����

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
							// ������ �浹 �˻�ι�
							if (item.itemOn)
								itemColision.item_colision(item, player1);
							// ����Ŭ���� �˻�
							if (enermy.size() == 0) {
								gameClear = true;
								this.suspend();
								break;
							}
						}

						// �Ѿ� �˻�ι�
						// ���� �Ѿ��� �߻� ������ ���� ���¶��
						for (int i = 0; i < enermyBullet.length; i++) {
							if (enermyBullet[i].launched == false) {
								// �������� �߻���Ų��.(50000/1�� Ȯ��)
								random = (int) (Math.random() * 100 + 1);
								// ���� ��ġ�ϰԵǸ�
								if (random == 1) {
									// �̹����� ����ü�߿� ����ִ� ��ü�� ���������� ���غ���.
									random = (int) (Math.random() * enermy.size() - 1);
									// ���� ���� ���� �ε����� �ش��ϴ� ��ü�� ����ִٸ�
									if (enermy.size() != 0) {
										if (enermy.get(random).isDead == false) {
											// �Ѿ� ��ü�� �����Ѵ�.
											enermyBullet[i] = new EnermyBullet("SingleGameImage\\Bullet.png",
													enermy.get(random).x + 23, enermy.get(random).y + 40);

											// �� �Ѿ� �ӵ� ����
											enermyBullet[i].setSpeed(optionLevel);

											// ������ ���ÿ� �߻�
											enermyBullet[i].launched = true;

										} else {
											enermy.remove(random);
										}

									}
								}

							}
							playerColision.userColision(player1, enermyBullet[i]); // ��
							// �Ѿ���
							// ������
							// �浹
							// ��������
							// �˻�
							for (int i1 = 0; i1 < playerBullet.length; i1++)
								bulletColision.bulletColision(playerBullet[i1], enermyBullet[i], i1, player1);
						}

						// ���ӿ��� �˻�
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

	/* @@ �޼ҵ� ���� �ι� @@ */

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
		wallX = 64;
		wallY = 510;
		// ���ӿ��� �˻� ����
		gameOver = false;
		gameClear = false;
		gameOn = true;
		// ������ ��԰�
		wallFirst = true;
		// �Ѿ��� �߻� �ӵ� ������ ���� �ð�����
		playerBulletSetTime = System.currentTimeMillis();
		playerBulletMaxTime = System.currentTimeMillis();

		/* @@ ��ü���� �ι� @@ */

		/* @ �÷��̾� ��ü ���� �ι� @ */

		// �÷��̾� ���� �ι�
		player1 = new Player("SingleGameImage/Player1.png", 640, 650);

		// �÷��̾� �Ѿ� ��ü ����
		for (int i = 0; i < playerBullet.length; i++) {
			playerBullet[i] = new PlayerBullet("SingleGameImage/Bullet.png");
		}

		// �÷��̾� �������� ��ü ����
		for (int i = 0; i < lifeBar.length; i++) {
			lifeBar[i] = new LifeBar("SingleGameImage/Player1Life.png", 10 + (25 * i), 50);
		}

		// �÷��̾� �����۹� ��ü ����
		itemBar = new ItemBar("SingleGameImage/Empty.png", 10, 100);

		/* @ �� ��ü ���� �ι� @ */

		// ����ü ����
		for (int j = 0; j < 5; j++) {
			for (int i = 0; i < 11; i++) {
				enermy.add(new Enermy("SingleGameImage/Empty.png", setEnermyLocation + 80 * (i + 1), 60 * (j + 1),
						enermyIndex));
				enermyIndex++;
			}
		}
		// �� �Ѿ� ����
		for (int i = 0; i < enermyBullet.length; i++) {
			enermyBullet[i] = new EnermyBullet("SingleGameImage/Empty.png", 50, 50);
		}

		/* @ �浹�˻�� ��ü ���� @ */

		// �Ѿ� �浹 �˻�� ����
		bulletColision = new BulletColision();
		// �� �浹 �˻�� ��ü ����(���� �Ѿ˰� ����ü �浹 ����)
		enermyColision = new EnermyColision();
		// �÷��̾� �浹 �˻�� ��ü ����
		playerColision = new PlayerColision();
		// ������ �浹 �˻�� ��ü ����
		itemColision = new ItemColision();

		/* @ ����üũ�� ��ü ���� �ι� @ */

		// ���� üũ�� ��ü ����
		score = new Score();
		// ������ ��Ʈ ��ü ����
		itemFont = new ItemFont();

		/* @ ������ ��ü ���� �ι� @ */

		// ������ ��ü ����
		hiddenMonster = new HiddenMonster("SingleGameImage/AirPlane.png");
		// ������ ��ü ����
		item = new Item("SingleGameImage/ITEM.png", hiddenMonster);

		/* @ �� ��ü ���� �ι� @ */

		// �� ��ü ����
		for (int i = 0; i < 7; i++) {
			wall.add(new WallPiece(wallX, wallY));
			wallX += 172;
		}

	}

}

