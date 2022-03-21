package MainPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import EnviromentSetting.EnviromentSetting;
import Manager.SceneManager;
import Manager.SoundManager;
import OnlineGame.CooEnd;
import OnlineGame.PVPEnd;
import OnlineGame.PVPGame;
import OnlineGame.TogetherGame;
import OnlinePage.BattleNet;
import OnlinePage.OnlineBack;
import OnlinePage.OnlineMain;
import OnlinePage.Ranking;
import OnlinePage.Sign;
import Score.Score;
import SingleGamePanel.SingleGame;
import optionBackground.GameSetting;

public class MainPage extends JFrame {
	Background Main = new Background();
	GameSetting singleOption = new GameSetting();
	SingleGame singleGame;
	OnlineBack onlineback = new OnlineBack();
	Sign sign = new Sign();
	EnviromentSetting enviromentSetting = new EnviromentSetting();
	public static JButton button = new JButton(new ImageIcon("SingleGameImage/GameOver.png"));
	public static JButton Clear = new JButton(new ImageIcon("SingleGameImage/GameClear.png"));
	OnlineMain onlinemain = new OnlineMain();
	Ranking ranking = new Ranking();
	public boolean pvpchk = false;
	public boolean teamPlaychk = false;
	public boolean matchchk = false;
	public boolean exitOn = false;
	String name = null;
	String message;
	// 아래는 네트워크에 관련된
	// 아래는 네트워크에 관련된 객체부분들

	in input;
	BattleNet Save;
	TogetherGame togetherGame;
	PVPGame pvpGame;

	String player;
	PVPEnd pvpEnd = new PVPEnd();
	CooEnd cooEnd = new CooEnd();
	
	public MainPage() {

		setTitle("Spaceinvader");
		this.setSize(1280, 720);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

				int q1 = JOptionPane.showConfirmDialog(null, "프로그램을 종료?", "프래그램 종료", JOptionPane.OK_CANCEL_OPTION);
				System.err.println(e);
				if (q1 == 0) {
					System.err.println("프로그램종료");
					System.out.println("");
					System.out.print("드디어 종료 완성");
					if (exitOn){
						input.writer.println("#%02%#" + "#%15%#" + "data");
						System.out.println("서버전송완료");
					}
						
					System.exit(0);
				} else {
					System.err.println("프로그램 종료 취소");
				}
			}
		});
		this.setResizable(false);
		Save = new BattleNet();
		add(Main);

		Main.Mulity.addActionListener(new ActionListener() { // 멀티플레이를 눌렀을때 전환되는
			// // 효과
			public void actionPerformed(ActionEvent e) {
				SoundManager.ButtonEffectMp3Player.Play();
				if (e.getSource() == Main.Mulity) {
					setVisible(false);
					remove(Main);
					add(onlineback);
					try {
						input = new in();
						System.out.println("접속 성공!");
						input.start();
						SoundManager.IntroBackGroundMp3Player.Play();
					} catch (Exception o) {
						System.out.println("접속 실패!");
					}
					setVisible(true);
				}
			}
		});

		Main.Setting.addActionListener(new ActionListener() { // 환경설정 누를시 화면전환

			public void actionPerformed(ActionEvent e) {
				SoundManager.ButtonEffectMp3Player.Play();
				if (e.getSource() == Main.Setting) {
					setVisible(false);
					remove(Main);
					add(enviromentSetting);
					setVisible(true);
				}
			}
		});

		Main.Single.addActionListener(new ActionListener() { // 환경설정 누를시 화면전환

			public void actionPerformed(ActionEvent e) {
				SoundManager.ButtonEffectMp3Player.Play();
				if (e.getSource() == Main.Single) {
					setVisible(false);
					remove(Main);
					add(singleOption);

					setVisible(true);
				}
			}
		});
		singleOption.back.addActionListener(new ActionListener() { // 옵션페이지의
			// 뒤로가기
			// 버튼(메인으로
			// 다시 돌아옴)
			public void actionPerformed(ActionEvent e) {
				SoundManager.ButtonEffectMp3Player.Play();
				if (e.getSource() == singleOption.back) {
					setVisible(false);
					remove(singleOption);
					add(Main);
					setVisible(true);
				}
			}
		});
		enviromentSetting.back.addActionListener(new ActionListener() { // 옵션페이지의
			// 뒤로가기
			// 버튼(메인으로
			// 다시
			// 돌아옴)
			public void actionPerformed(ActionEvent e) {
				SoundManager.ButtonEffectMp3Player.Play();
				if (e.getSource() == enviromentSetting.back) {
					setVisible(false);
					remove(enviromentSetting);
					add(Main);
					setVisible(true);
				}
			}
		});

		singleOption.start.addActionListener(new ActionListener() { // 옵션페이지의
			// 게임시작 버튼
			public void actionPerformed(ActionEvent e) {
				SoundManager.ButtonEffectMp3Player.Play();
				if (e.getSource() == singleOption.start) {
					singleGame = new SingleGame();
					setVisible(false);
					singleGame.optionLevel = singleOption.level;
					singleGame.optionSpeed = singleOption.speed;
					singleGame.wallFirst = singleOption.wallOnOff;
					remove(singleOption);
					if (singleGame.gameOver == true) { // 게임오버가 아니면
						
						singleGame = new SingleGame();
						singleGame.optionLevel = singleOption.level;
						singleGame.optionSpeed = singleOption.speed;
						singleGame.wallFirst = singleOption.wallOnOff;
					} else {
		
						SoundManager.IngameBackGroundMp3Player.Play();
					}

					SceneManager.SetCurSeneIndex(1);
					add(singleGame);

					setVisible(true);
				}
			}
		});
		button.addActionListener(new ActionListener() { // 게임 오버시 버튼눌리면 적용되는 액션
			public void actionPerformed(ActionEvent e) {

				SoundManager.ButtonEffectMp3Player.Play();
				if (e.getSource() == button) {
					setVisible(false);
					remove(singleGame);

					if (singleGame.gameOver == false) { // 게임오버가 아니면

						add(singleGame);
					} else {

						SceneManager.SetCurSeneIndex(0);
						add(Main);

					}

					setVisible(true);
				}
			}
		});
		Clear.addActionListener(new ActionListener() {// 게임 클리어시 다음 스테이지로 이동되는
			// 액션
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				singleOption.level = singleGame.optionLevel;
				singleOption.speed = singleGame.optionSpeed;
				singleGame.wallFirst = singleOption.wallOnOff;
				remove(singleGame);

				singleGame = new SingleGame();
				singleGame.optionLevel = singleOption.level;
				singleGame.optionSpeed = singleOption.speed;
				singleGame.wallFirst = singleOption.wallOnOff;
				singleGame.optionLevel -= 1;
				singleGame.optionSpeed -= 100;
				Score.round += 1;

				add(singleGame);

				setVisible(true);
			}
		});
		onlineback.Quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SoundManager.ButtonEffectMp3Player.Play();

				setVisible(false);
				remove(onlineback);
				add(Main);

				System.out.println("연결종료");
				setVisible(true);

			}
		});
		onlineback.Sign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SoundManager.ButtonEffectMp3Player.Play();

				setVisible(false);
				remove(onlineback);
				add(sign);

				setVisible(true);
			}
		});
		sign.Quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SoundManager.ButtonEffectMp3Player.Play();

				setVisible(false);
				remove(sign);
				add(onlineback);
				setVisible(true);
			}
		});
		sign.signup.addActionListener(new ActionListener() { // 회원가입
			public void actionPerformed(ActionEvent e) {
				SoundManager.ButtonEffectMp3Player.Play();
				String ID = sign.f1.getText();
				String PW = sign.f2.getText();
				String NikName = sign.f3.getText();
				String Name = sign.f4.getText();
				try {
					input.writer.println("#%02%#" + "#%12%#" + ID + " " + NikName + " " + PW + " " + Name);
					sign.f1.setText("");
					sign.f2.setText("");
					sign.f3.setText("");
					sign.f4.setText("");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		onlineback.Login.addActionListener(new ActionListener() { // 로그인
			public void actionPerformed(ActionEvent e) {
				SoundManager.ButtonEffectMp3Player.Play();
				String id = onlineback.f1.getText();
				String pw = onlineback.f2.getText();
				try {
					input.writer.println("#%02%#" + "#%11%#" + id + " " + pw + " ");
					
				} catch (Exception e2) {
					try {
						onlineback.status.setText("로그인 필요"); // <- 해결 - 문제 :
																// 로그인창 아무것도
																// 입력되지 않은상태에서
																// 에러발생
						
						e2.printStackTrace();
					} catch (Exception a) {
						System.out.println(a);

					}
				}
			}
		});

		onlinemain.t2.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == e.VK_ENTER) {
					try {
						input.writer.println(
								"#%02%#" + "#%21%#" + Save.getNikName() + " : " + onlinemain.t2.getText().toString());

						onlinemain.area.setCaretPosition(onlinemain.area.getDocument().getLength());
						onlinemain.t2.setText("");
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

				// 협동게임 키입력
				/*
				 * if (e.getKeyCode() == e.VK_LEFT) {
				 * input.writer.println("#%01%#" + "#%40%#" + "LEFT"); } if
				 * (e.getKeyCode() == e.VK_RIGHT) {
				 * input.writer.println("#%01%#" + "#%41%#" + "RIGHT"); } if
				 * (e.getKeyCode() == e.VK_SPACE) {
				 * input.writer.println("#%01%#" + "#%4
				 * 4%#" + "SPACE"); }
				 */
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// 협동게임 키입력
				/*
				 * if (e.getKeyCode() == e.VK_LEFT) {
				 * input.writer.println("#%01%#" + "#%42%#" + "LEFT"); } if
				 * (e.getKeyCode() == e.VK_RIGHT) {
				 * input.writer.println("#%01%#" + "#%43%#" + "RIGHT"); }
				 */
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

		});

		onlinemain.exit.addActionListener(new ActionListener() { // 멀티플레이를 눌렀을때
																	// 전환되는
			// 효과
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == onlinemain.exit) {
					SoundManager.BattleNetMp3Player.mystop();
					SoundManager.IntroBackGroundMp3Player.reSoundEnd();
					SoundManager.IntroBackGroundMp3Player.Play();
					input.writer.println("#%02%#" + "#%16%#" + Save.getID());
					setVisible(false);
					remove(onlinemain);
					add(Main);
					
					setVisible(true);
					onlineback.status.setText("로그인 필요");
					onlineback.f1.setText("");
					onlineback.f2.setText("");
					exitOn = false;
				}
			}
		});

		onlinemain.pvp.addActionListener(new ActionListener() { 
			// 효과
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == onlinemain.pvp) {
					input.writer.println("#%01%#" + "#%25%#" + " ");
					if (!matchchk) {
						if (!pvpchk) {
							onlinemain.pvp.setIcon(new ImageIcon("OnlineImage/match.png"));
							pvpchk = true;
							ranking.pvp.setIcon(new ImageIcon("OnlineImage/match.png"));
						} else {
							onlinemain.pvp.setIcon(new ImageIcon("OnlineImage/pvp.png"));
							pvpchk = false;
							ranking.pvp.setIcon(new ImageIcon("OnlineImage/pvp.png"));
						}
					}
				}
			}
		});
		onlinemain.teamPlay.addActionListener(new ActionListener() { // 멀티플레이를
																		// 눌렀을때
																		// 전환되는
			// 효과
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == onlinemain.teamPlay) {
					input.writer.println("#%01%#" + "#%24%#" + " ");
					if (pvpchk == false)
						matchchk = true;
					if (matchchk) {
						if (!teamPlaychk) {
							onlinemain.teamPlay.setIcon(new ImageIcon("OnlineImage/match.png"));
							teamPlaychk = true;
							ranking.teamPlay.setIcon(new ImageIcon("OnlineImage/match.png"));

						} else {
							onlinemain.teamPlay.setIcon(new ImageIcon("OnlineImage/teamPlay.png"));
							teamPlaychk = false;
							ranking.teamPlay.setIcon(new ImageIcon("OnlineImage/teamPlay.png"));
							matchchk = false;
						}
					}
				}
			}
		});

		onlinemain.ranking.addActionListener(new ActionListener() { // 멀티플레이를
																	// 눌렀을때 전환되는
			// 효과
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == onlinemain.ranking) {
					setVisible(false);
					input.writer.println("#%02%#" + "#%26%#" + " "); // 협동랭킹 목록좀
																		// 보여주소
					remove(onlinemain);
					add(ranking);
					onlinemain.PanelOnOff = false;
					ranking.PanelOnOff = true;
					setVisible(true);
				}
			}
		});
		ranking.pvp.addActionListener(new ActionListener() { // 멀티플레이를 눌렀을때 전환되는
																// 효과
			// 17.11.22 PM 9:22 김희규 수정요청 버튼
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == ranking.pvp) {
					if (!matchchk) {
						if (!pvpchk) {
							ranking.pvp.setIcon(new ImageIcon("OnlineImage/match.png"));
							pvpchk = true;
							onlinemain.pvp.setIcon(new ImageIcon("OnlineImage/match.png"));
						} else {
							ranking.pvp.setIcon(new ImageIcon("OnlineImage/pvp.png"));
							pvpchk = false;
							onlinemain.pvp.setIcon(new ImageIcon("OnlineImage/pvp.png"));
						}
					}
				}
			}
		});
		ranking.teamPlay.addActionListener(new ActionListener() { // 멀티플레이를 눌렀을때
																	// 전환되는
			// 17.11.22 PM 9:22 김희규 수정요청 버튼
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == ranking.teamPlay) {
					if (pvpchk == false)
						matchchk = true;
					if (matchchk) {
						if (!teamPlaychk) {
							ranking.teamPlay.setIcon(new ImageIcon("OnlineImage/match.png"));
							teamPlaychk = true;
							onlinemain.teamPlay.setIcon(new ImageIcon("OnlineImage/match.png"));

						} else {
							ranking.teamPlay.setIcon(new ImageIcon("OnlineImage/teamPlay.png"));
							teamPlaychk = false;
							onlinemain.teamPlay.setIcon(new ImageIcon("OnlineImage/teamPlay.png"));
							matchchk = false;
						}
					}
				}
			}
		});

		ranking.exit.addActionListener(new ActionListener() { // 멀티플레이를 눌렀을때
																// 전환되는
			// 17.11.22 PM 9:22 김희규 수정요청 버튼
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == ranking.exit) {
					setVisible(false);
					ranking.ta.setText("");
					onlinemain.area.setText("");
					remove(ranking);
					add(onlinemain);
					onlinemain.PanelOnOff = true;
					ranking.PanelOnOff = false;
					setVisible(true);
				}
			}
		});

		ranking.pvpRanking.addActionListener(new ActionListener() { // pvp 랭킹
																	// 버튼눌럿을때
			// 전환되는
			// 효과
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == ranking.pvpRanking) {
					ranking.ta.setText("");
					input.writer.println("#%02%#" + "#%27%#" + " ");

				}
			}
		});

		ranking.coopRanking.addActionListener(new ActionListener() { // 협동랭킹버튼
																		// 눌럿을때
			// 전환되는
			// 효과
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == ranking.coopRanking) {

					ranking.ta.setText("");
					input.writer.println("#%02%#" + "#%26%#" + " ");
				}
			}
		});
		pvpEnd.out.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				onlinemain.countDown.setText(" ");
				onlinemain.pvp.setIcon(new ImageIcon("OnlineImage/pvp.png"));
				SoundManager.PVPInGameMp3Player.mystop();
				SoundManager.BattleNetMp3Player.reSoundEnd();
				SoundManager.BattleNetMp3Player.Play();
				pvpchk=false;
				setVisible(false);
				remove(pvpEnd);
				add(onlinemain);
				setSize(1280, 720);
				setVisible(true);
			}
		});
		cooEnd.button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				onlinemain.countDown.setText(" ");
				onlinemain.teamPlay.setIcon(new ImageIcon("OnlineImage/teamPlay.png"));
				SoundManager.COPInGameMp3Player.mystop();
				SoundManager.BattleNetMp3Player.reSoundEnd();
				SoundManager.BattleNetMp3Player.Play();
				teamPlaychk=false;
				setVisible(false);
				remove(cooEnd);
				add(onlinemain);
				setVisible(true);
			}
		});
		this.setVisible(true);
	}

	class in extends Thread {
		public Socket socket;
		public BufferedReader in;
		public PrintWriter writer;
		public String tag1;
		public String tag2;
		public String tag3;
		public String data;
		public String[] readData;
		public String[] enermyData = new String[5];
		public String[] playerData = new String[3];
		public String indexData;
		public String[] playerData1 = new String[4];
		public String[] playerData2 = new String[4];
		public String[] enermyBulletData = new String[4];
		int count =0;
		int enermyIndex;
		int enermyBulletIndex;
		public String[] playerBulletData = new String[6];
		public in() {
			try {
				socket = new Socket("localhost", 8080);
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer = new PrintWriter(socket.getOutputStream(), true);
			} catch (Exception e) {
				System.out.println("in 클래스 오류");
			}
		}

		public void run() {
			// TODO Auto-generated method stub
			System.out.println("변경전 인덱스 :" + Save.getIndex());

			try {
				int time = 0;
				// 읽는
				while (((message = in.readLine()) != null)) {
					//System.out.println("카운터: "+count);
					//count++;
					// System.out.println(message);
					tag1 = message.substring(0, 6);
					tag2 = message.substring(6, 12);
					/*-------------------------------------------------------------*/
					// 게임에 관한정보일때만 분리
					// 에너미 정보라면
					if ((tag2.compareTo("#%60%#")) == 0) {
						data = message.substring(12, message.length());
						enermyData = data.split("/");

					}
					// 유저 정보라면
					else if ((tag2.compareTo("#%40%#")) == 0 || (tag2.compareTo("#%49%#")) == 0
							|| (tag2.compareTo("#%50%#")) == 0 || (tag2.compareTo("#%51%#")) == 0
							|| (tag2.compareTo("#%52%#")) == 0 ||  (tag2.compareTo("#%48%#")) == 0){

						data = message.substring(12, message.length());

						playerData = data.split("/");

					}
					else if((tag2.compareTo("#%41%#")) == 0)
					{
						
						data = message.substring(12, message.length());
						playerData1 = data.split("/");
						

					}
					else if((tag2.compareTo("#%42%#")) == 0)
					{
						data = message.substring(12, message.length());
						playerData2 = data.split("/");
						
					}
					//유저 총알 정보라면
					else if((tag2.compareTo("#%70%#")) == 0)
					{
						data = message.substring(12, message.length());
						
						playerBulletData = data.split("/");
					}
					// 나머지
					else {
						data = message.substring(12, message.length());
					}

					// 게임일 때
					if ((tag1.compareTo("#%01%#")) == 0) {

						if ((tag2.compareTo("#%60%#")) == 0) {
							// 적 출력 부문

							togetherGame.enermy[Integer.parseInt(enermyData[0])]
									.setX(Integer.parseInt(enermyData[1]));

							togetherGame.enermy[Integer.parseInt(enermyData[0])]
									.setY(Integer.parseInt(enermyData[2]));

							togetherGame.enermy[Integer.parseInt(enermyData[0])]
									.setIsDead(Boolean.valueOf(enermyData[3]));
							
							togetherGame.enermy[Integer.parseInt(enermyData[0])].isRemove = Boolean.valueOf(enermyData[4]);
							
							togetherGame.enermy[Integer.parseInt(enermyData[0])].setPicture(enermyData[5]);

						}
						else if ((tag2.compareTo("#%41%#")) == 0) {
							togetherGame.player1.setX(Integer.parseInt(playerData1[1]));
							togetherGame.player1.setY(Integer.parseInt(playerData1[2]));
							togetherGame.player1.setPicture(playerData1[3]);

						}
						else if ((tag2.compareTo("#%42%#")) == 0) {
							togetherGame.player2.setX(Integer.parseInt(playerData2[1]));
							togetherGame.player2.setY(Integer.parseInt(playerData2[2]));
							togetherGame.player2.setPicture(playerData2[3]);

						}
						 else if ((tag2.compareTo("#%48%#")) == 0) { //1p 또는 2p 죽으면 승리, 패배 판정 부분
							if ((playerData[1].compareTo("1")) == 0){ //1p이면
								pvpGame.player1.isDaed =true;
								if((pvpGame.player.compareTo("1"))==0){
									System.out.println("패배");
									pvpEnd.setImage("PVPImage/패배.png");
								}
								else if((pvpGame.player.compareTo("2"))==0){
									System.out.println("승리");
									pvpEnd.setImage("PVPImage/승리.png");
								}
							}
							if ((playerData[1].compareTo("2")) == 0){ //2p이면
								pvpGame.player2.isDaed =true;
								if((pvpGame.player.compareTo("1"))==0){
									System.out.println("승리");
									pvpEnd.setImage("PVPImage/승리.png");
								}
								else if((pvpGame.player.compareTo("2"))==0){
									System.out.println("패배");
									pvpEnd.setImage("PVPImage/패배.png");
								}
							}
							setVisible(false);
							remove(pvpGame);
							add(pvpEnd);
							setVisible(true);
							pvpGame=new PVPGame(socket);
						}else if ((tag2.compareTo("#%55%#"))==0){ //게임 끝낫을 때 라벨 적용 하는 부분 닉네임
							
							StringTokenizer token=new StringTokenizer(data);
							String[] nikname=new String[2];
							int cnt=0;
							while(token.hasMoreTokens()){
								nikname[cnt]=token.nextToken();
								cnt++;
							}
							pvpEnd.player1.setText(nikname[0]);
							pvpEnd.player2.setText(nikname[1]);
							pvpEnd.smallplayer1.setText(nikname[0]);
							pvpEnd.smallplayer2.setText(nikname[1]);
							
						}else if ((tag2.compareTo("#%56%#"))==0){ //게임 끝낫을 때 라벨 적용 하는 부분 레이팅
							
							StringTokenizer token=new StringTokenizer(data);
							String[] rating=new String[2];
							int cnt=0;
							while(token.hasMoreTokens()){
								rating[cnt]=token.nextToken();
								cnt++;
							}
							pvpEnd.rating1.setText(rating[0]);
							pvpEnd.rating2.setText(rating[1]);
						}else if ((tag2.compareTo("#%49%#")) == 0) { // 1p 좌표
																		// 이동값
																		// 적용
							// 유저 출력 부문

							if (pvpGame.pvpon == true) // PVP게임이 온이면
							{
								pvpGame.player1.setX(Integer.parseInt(playerData[1]));
								pvpGame.player1.setY(Integer.parseInt(playerData[2]));
							}

						} else if ((tag2.compareTo("#%50%#")) == 0) { // 2p 좌표
																		// 이동값
																		// 적용
							// 유저 출력 부문
							if (pvpGame.pvpon == true) {
								pvpGame.player2.setX(Integer.parseInt(playerData[1]));
								pvpGame.player2.setY(Integer.parseInt(playerData[2]));
							}
						} else if ((tag2.compareTo("#%51%#")) == 0) { // 1p 총알
																		// 이동값
																	// 적용
							if (pvpGame.pvpon == true) {
					
								pvpGame.player1Bullet[Integer.parseInt(playerData[0])]
										.setX(Integer.parseInt(playerData[1])); // 수정
								pvpGame.player1Bullet[Integer.parseInt(playerData[0])]
										.setY(Integer.parseInt(playerData[2])); // 수정
								pvpGame.player1Bullet[Integer.parseInt(playerData[0])].launched=true;
							}
						} else if ((tag2.compareTo("#%52%#")) == 0) { // 2p 총알
																		// 이동값
																		// 적용
							if (pvpGame.pvpon == true) {
								
								pvpGame.player2Bullet[Integer.parseInt(playerData[0])]
										.setX(Integer.parseInt(playerData[1])); // 수정
								pvpGame.player2Bullet[Integer.parseInt(playerData[0])]
										.setY(Integer.parseInt(playerData[2])); // 수정
								pvpGame.player2Bullet[Integer.parseInt(playerData[0])].launched=true;
							}// 총알 출력 부문
						}  else if ((tag2.compareTo("#%53%#")) == 0) { // 1p 총알 라이프
			
							if (pvpGame.pvpon == true) {
							
								pvpGame.player1Bullet[Integer.parseInt(data)].launched = false;
								pvpGame.player1Bullet[Integer.parseInt(data)].setName("SingleGameImage/Empty.png");
							}
						} else if ((tag2.compareTo("#%54%#")) == 0) { // 2p 총알 라이프 그냥 런치 바꾸는 부분
				
							if (pvpGame.pvpon == true) {
							
								pvpGame.player2Bullet[Integer.parseInt(data)].launched = false;
								pvpGame.player1Bullet[Integer.parseInt(data)].setName("SingleGameImage/Empty.png");
							} 
						}
						 else if ((tag2.compareTo("#%70%#")) == 0) {
							 if(playerBulletData[0].compareTo("1") == 0)
							 {
								 togetherGame.player1Bullet[Integer.parseInt(playerBulletData[1])].setX(Integer.parseInt(playerBulletData[2]));
								 togetherGame.player1Bullet[Integer.parseInt(playerBulletData[1])].setY(Integer.parseInt(playerBulletData[3]));
								 togetherGame.player1Bullet[Integer.parseInt(playerBulletData[1])].setPicture(playerBulletData[4]);
								 togetherGame.player1Bullet[Integer.parseInt(playerBulletData[1])].launched = Boolean.valueOf(playerBulletData[5]);
							 }
							 if(playerBulletData[0].compareTo("2") == 0)
							 {
								 togetherGame.player2Bullet[Integer.parseInt(playerBulletData[1])].setX(Integer.parseInt(playerBulletData[2]));
								 togetherGame.player2Bullet[Integer.parseInt(playerBulletData[1])].setY(Integer.parseInt(playerBulletData[3]));
								 togetherGame.player2Bullet[Integer.parseInt(playerBulletData[1])].setPicture(playerBulletData[4]);
								 togetherGame.player2Bullet[Integer.parseInt(playerBulletData[1])].launched = Boolean.valueOf(playerBulletData[5]);
							 }
						 }

						else if ((tag2.compareTo("#%75%#")) == 0) { // 총알 index
							enermyBulletIndex = Integer.parseInt(data);
						} else if ((tag2.compareTo("#%71%#")) == 0) { // 총알 좌표 x
							togetherGame.enermyBulletDraw[enermyBulletIndex].setX(Integer.parseInt(data));
						} else if ((tag2.compareTo("#%72%#")) == 0) { // 총알 좌표 y
							togetherGame.enermyBulletDraw[enermyBulletIndex].setY(Integer.parseInt(data));
						} else if ((tag2.compareTo("#%73%#")) == 0) { // 총알
																		// luanched

						}
						if ((tag2.compareTo("#%74%#")) == 0) { // 총알 그림
							togetherGame.enermyBulletDraw[enermyBulletIndex].setPicture(data);
						}

						/*-----------------------------------------------------------*/

						else if ((tag2.compareTo("#%24%#")) == 0) { // 협동매칭이 되었을때
							SoundManager.BattleNetMp3Player.mystop();
							SoundManager.COPInGameMp3Player.reSoundEnd();
							SoundManager.COPInGameMp3Player.Play();
							 togetherGame = new TogetherGame(socket, data);
							 setVisible(false);
                           remove(onlinemain);
                           add(togetherGame);
                           setVisible(true);
							
	                            if(onlinemain.PanelOnOff=true){            //위에서 1p,2p구분해서 생성한 패널을 추가한다.
	                               setVisible(false);
	                               remove(onlinemain);
	                               add(togetherGame);
	                               setVisible(true);
	                            }
	                            else{
	                               setVisible(false);
	                               remove(ranking);
	                               add(togetherGame);
	                               setVisible(true);
	                            }
	                          
	                }
						if ((tag2.compareTo("#%25%#")) == 0) { // PVP게임이 매칭 되었을
																// 때
							setVisible(false);
							remove(onlinemain);
							setSize(640, 750);
							SoundManager.BattleNetMp3Player.mystop();
							SoundManager.PVPInGameMp3Player.reSoundEnd();
							SoundManager.PVPInGameMp3Player.Play();
							add(pvpGame);
							
							setVisible(true);
							pvpGame.pvpon = true;

							if (player.compareTo("2") == 0) {
								pvpGame.player1.setName("PVPImage/user2change.png");
								pvpGame.player2.setName("PVPImage/user2.png");
								for(int i =0; i<5; i++){
									
									pvpGame.player1Bullet[i].setName("SingleGameImage/HelperBullet.png");
									pvpGame.player2Bullet[i].setName("SingleGameImage/Bullet.png");
								}
								
								
								// pvpGame.player2Bullet.setImage(");
							}

						}

						if ((tag2.compareTo("#%28%#")) == 0) { // 카운트 다운
							onlinemain.countDown.setText("");
							onlinemain.countDown.append(data);

						}
						if ((tag2.compareTo("#%46%#")) == 0) { // 1p 2p 식별 예: 내가
																// 1p인가? 2p 인가?
							player = data;
						
							pvpGame = new PVPGame(socket);
							
							if (pvpGame.pvpon = true) // PVP게임이 온이면
								pvpGame.player = data;

							// else
							// 협동 넣어줘야함
						}
						else if((tag2.compareTo("#%91%#")) == 0){ //게임 오버
							cooEnd.setImage("CooImage/임무 실패.png");
							cooEnd.end.setText("분발하세요!!");
		                        setVisible(false);
		                        remove(togetherGame);
		                        add(cooEnd);
		                        setVisible(true);
		                     
		                 } 
						else if((tag2.compareTo("#%92%#")) == 0){ //게임 클리어
							cooEnd.setImage("CooImage/임무 성공.png");
		                    setVisible(false);
	                        remove(togetherGame);
	                        add(cooEnd);
	                        setVisible(true);
						}
						else if((tag2.compareTo("#%93%#")) == 0){ //점수
							
							if(Integer.parseInt(data)!=togetherGame.playerScore){
								
								togetherGame.playerScore = Integer.parseInt(data);
								
							}
							
						}
						else if((tag2.compareTo("#%94%#"))==0){ //닉네임 라벨 적용부분
							StringTokenizer token=new StringTokenizer(data);
							String[] name=new String[2];
							int cnt=0;
							while(token.hasMoreTokens()){
								name[cnt]=token.nextToken();
								cnt++;
							}
							cooEnd.p1.setText(name[0]);
							cooEnd.p2.setText(name[1]);
						}
						else if((tag2.compareTo("#%95%#"))==0){ //점수 라벨 적용부분
							StringTokenizer token=new StringTokenizer(data);
							String[] score=new String[2];
							int cnt=0;
							while(token.hasMoreTokens()){
								score[cnt]=token.nextToken();
								cnt++;
							}
							int p1=Integer.parseInt(score[0]);
							int p2=Integer.parseInt(score[1]);
							cooEnd.p1Score.setText(score[0]);
							cooEnd.p2Score.setText(score[1]);
							cooEnd.Score.setText(Integer.toString(p1+p2));
						}
					}

					if ((tag1.compareTo("#%02%#")) == 0) {// 인증 일때

						if ((tag2.compareTo("#%11%#")) == 0) { // 로그인

						}
						if ((tag2.compareTo("#%12%#")) == 0) { // 회원가입

						}
						if ((tag2.compareTo("#%13%#")) == 0) { // 로그인 상태메시지
							onlineback.status.setText(data);

							if (onlineback.status.getText().compareTo("success") == 0) { // 로그인
																							// //
								SoundManager.IntroBackGroundMp3Player.mystop();
								SoundManager.BattleNetMp3Player.reSoundEnd();
								SoundManager.BattleNetMp3Player.Play();														// 성공시
								setVisible(false);
								remove(onlineback);
								add(onlinemain);
								onlinemain.PanelOnOff = true;
								setVisible(true);
								input.writer.println("#%02%#" + "#%11%#" + onlineback.f1.getText());
								exitOn =true;
							}

						}
						if ((tag2.compareTo("#%14%#")) == 0) {
							sign.Status.setText(data);
						} // 회원가입 상태메시지
						if ((tag2.compareTo("#%15%#")) == 0) { // 강제종료
							StringTokenizer str = new StringTokenizer(data);
							int i = 0;
							String[] st = new String[2];
							while (str.hasMoreTokens()) {
								st[i] = str.nextToken();
								i++;
							}
							if (Save.getIndex() > (Integer.parseInt(st[0])))
								Save.setIndex(-1);
							input.writer.println("#%03%#" + "#%19%#" + Save.getID() + " " + Save.getIndex());

						}
						if ((tag2.compareTo("#%16%#")) == 0) { // 로그아웃
							StringTokenizer str = new StringTokenizer(data);
							int i = 0;
							String[] st = new String[2];
							while (str.hasMoreTokens()) {
								st[i] = str.nextToken();
								i++;
							}
							if (Save.getIndex() > (Integer.parseInt(st[0])))
								Save.setIndex(-1);
							input.writer.println("#%03%#" + "#%19%#" + Save.getID() + " " + Save.getIndex());

							onlinemain.User.setText("");
						}
						if ((tag2.compareTo("#%21%#")) == 0) { // 인덱스
							onlinemain.area.append(data + "\n");

						} else if ((tag2.compareTo("#%26%#")) == 0) { // 협동모드 랭킹

							ranking.ta.append(data + "\n");
						} else if ((tag2.compareTo("#%27%#")) == 0) { // pvp 랭킹
							ranking.ta.append(data + "\n");
						}

					}
					if ((tag1.compareTo("#%03%#")) == 0) {// 세션일 때 (아이디,
						if ((tag2.compareTo("#%17%#")) == 0) { // 아이디
							Save.setId(data);
						}
						if ((tag2.compareTo("#%18%#")) == 0) { // 닉네임
							Save.setNikName(data);
						}
						if ((tag2.compareTo("#%19%#")) == 0) { // 인덱스
							Save.setIndex(Integer.parseInt(data));

						}
						if ((tag2.compareTo("#%20%#")) == 0) { // 방송용 브로드캐스

						}
						if ((tag2.compareTo("#%22%#")) == 0) { // 방송용 브로드캐스
							onlinemain.User.append(data + "\n");
						}
						if ((tag2.compareTo("#%23%#")) == 0) { // 방송용 브로드캐스
							onlinemain.User.setText("");
						}
					}

				}
			} catch (Exception e) {
				System.err.println(e);
			}
		}

	}

	public static void main(String[] args) {
		SceneManager.SetCurSeneIndex(0);
		SoundManager.InitializeSoundResource();
		MainPage main = new MainPage();
		SoundManager.IntroBackGroundMp3Player.reSoundEnd();
		SoundManager.IntroBackGroundMp3Player.Play();
		
	}

}