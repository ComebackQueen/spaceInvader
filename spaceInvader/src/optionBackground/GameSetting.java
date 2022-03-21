package optionBackground;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Button.Button;
import Manager.SoundManager;
import SingleGamePanel.SingleGame;

public class GameSetting extends JPanel{
	BufferedImage img = null; // 옵션 배 경
	
	public Button back = new Button("OptionImage/back1.png", 0, 310, 100, 100); //뒤로가기 버튼
	public Button start = new Button("OptionImage/start1.png", 1180, 310, 100, 100); //게임시작 버튼
	//난이도 버튼
	public Button easy = new Button("OptionImage/easy2.png", 400, 180, 156, 136); 
	public Button normal = new Button("OptionImage/Normal1.png", 570, 180, 156, 136);
	public Button hard = new Button("OptionImage/hard1.png", 740, 180, 156, 136);
	// 배속 버튼
	public Button speed1 = new Button("OptionImage/slow2.png", 240, 360, 144, 128);
	public Button speed2 = new Button("OptionImage/betterfast1.png", 410, 360, 144, 128);
	public Button speed3 = new Button("OptionImage/normalfast1.png", 580, 360, 144, 128);
	public Button speed4 = new Button("OptionImage/fast1.png", 750, 360, 144, 128);
	public Button speed5 = new Button("OptionImage/veryfast1.png", 920, 360, 144, 128);
	// 벽 on/off버튼
	public Button wallOn = new Button("OptionImage/wallon2.png", 510, 540, 144, 128);
	public Button wallOff = new Button("OptionImage/walloff1.png", 680, 540, 144, 128);
	
	
	public int speed=1000; // 0 = 1배속, 1 = 1.5배속 , 2 = 2배속, 3 = 2.5배속, 4 = 3배속
	public int level=9; // 0 = 10, 1 = 20, 2 = 30, 3 = 40, 4 = 50, 5=60
	public boolean wallOnOff = true; // true = 벽켜짐, false = 벽꺼짐
	
	public GameSetting(){
		setLayout(null);
		try {
			img=ImageIO.read(new File("OptionImage/gamesetting.jpg"));
		} catch (IOException e) {
			System.out.println("온라인 배경 이미지를 불러올 수 없습니다.");
			System.exit(0);
		}
		
		// 난이도 조정
		easy.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) { 
				
			}
			public void mousePressed(MouseEvent e) { 
				SoundManager.ButtonEffectMp3Player.Play();
				level = 9;
			easy.setIcon(new ImageIcon("OptionImage/easy2.png"));
			normal.setIcon(new ImageIcon("OptionImage/normal1.png"));
			hard.setIcon(new ImageIcon("OptionImage/hard1.png")); 
			
			}
			public void mouseReleased(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) {  }
			public void mouseExited(MouseEvent e) {  }
		});
		normal.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) { 
			
			}
			public void mousePressed(MouseEvent e) { 
				
				SoundManager.ButtonEffectMp3Player.Play();
				level = 6;	
			normal.setIcon(new ImageIcon("OptionImage/normal2.png"));
			easy.setIcon(new ImageIcon("OptionImage/easy1.png"));
			hard.setIcon(new ImageIcon("OptionImage/hard1.png"));
			
			}
			public void mouseReleased(MouseEvent e) { }
			
			public void mouseEntered(MouseEvent e) { }
			public void mouseExited(MouseEvent e) {}
		});
		hard.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {
				
			}
			public void mousePressed(MouseEvent e) {
				SoundManager.ButtonEffectMp3Player.Play();
				level = 3;
			hard.setIcon(new ImageIcon("OptionImage/hard2.png"));
			easy.setIcon(new ImageIcon("OptionImage/easy1.png"));
			normal.setIcon(new ImageIcon("OptionImage/normal1.png")); 
			
			}
			public void mouseReleased(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { }
			public void mouseExited(MouseEvent e) {}
		});
		
		// 배속 조정
		speed1.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) { 
				
			
			}
			
			
			public void mousePressed(MouseEvent e) { 
				SoundManager.ButtonEffectMp3Player.Play();
				speed=1000;
			speed1.setIcon(new ImageIcon("OptionImage/slow2.png"));
			speed2.setIcon(new ImageIcon("OptionImage/betterfast1.png"));
			speed3.setIcon(new ImageIcon("OptionImage/normalfast1.png"));
			speed4.setIcon(new ImageIcon("OptionImage/fast1.png"));
			speed5.setIcon(new ImageIcon("OptionImage/veryfast1.png"));
			
			}
			public void mouseReleased(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { }
			public void mouseExited(MouseEvent e) {}
		});
		speed2.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) { 
			
			
			}
			public void mousePressed(MouseEvent e) { 	
				SoundManager.ButtonEffectMp3Player.Play();
				speed=900;
			speed1.setIcon(new ImageIcon("OptionImage/slow1.png"));
			speed2.setIcon(new ImageIcon("OptionImage/betterfast2.png"));
			speed3.setIcon(new ImageIcon("OptionImage/normalfast1.png"));
			speed4.setIcon(new ImageIcon("OptionImage/fast1.png"));
			speed5.setIcon(new ImageIcon("OptionImage/veryfast1.png"));
			
			}
			public void mouseReleased(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { }
			public void mouseExited(MouseEvent e) {}
		});
		speed3.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) { 
			
		
			}
			public void mousePressed(MouseEvent e) {	
				SoundManager.ButtonEffectMp3Player.Play();
				speed=800;
			speed1.setIcon(new ImageIcon("OptionImage/slow1.png"));
			speed2.setIcon(new ImageIcon("OptionImage/betterfast1.png"));
			speed3.setIcon(new ImageIcon("OptionImage/normalfast2.png"));
			speed4.setIcon(new ImageIcon("OptionImage/fast1.png"));
			speed5.setIcon(new ImageIcon("OptionImage/veryfast1.png"));
			
			}
			public void mouseReleased(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { }
			public void mouseExited(MouseEvent e) {}
		});
		speed4.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) { 
				
			}
			public void mousePressed(MouseEvent e) { 
				SoundManager.ButtonEffectMp3Player.Play();
				speed = 700; 
			speed1.setIcon(new ImageIcon("OptionImage/slow1.png"));
			speed2.setIcon(new ImageIcon("OptionImage/betterfast1.png"));
			speed3.setIcon(new ImageIcon("OptionImage/normalfast1.png"));
			speed4.setIcon(new ImageIcon("OptionImage/fast2.png"));
			speed5.setIcon(new ImageIcon("OptionImage/veryfast1.png"));
			
			}
			public void mouseReleased(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { }
			public void mouseExited(MouseEvent e) {}
		});
		speed5.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) { 
			
			
			}
			public void mousePressed(MouseEvent e) {
				
				SoundManager.ButtonEffectMp3Player.Play();
				speed = 600;	
			speed1.setIcon(new ImageIcon("OptionImage/slow1.png"));
			speed2.setIcon(new ImageIcon("OptionImage/betterfast1.png"));
			speed3.setIcon(new ImageIcon("OptionImage/normalfast1.png"));
			speed4.setIcon(new ImageIcon("OptionImage/fast1.png"));
			speed5.setIcon(new ImageIcon("OptionImage/veryfast2.png"));
			
			}
			public void mouseReleased(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { }
			public void mouseExited(MouseEvent e) {}
		});
		
		// 벽 On/Off
		wallOn.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) { wallOnOff = true;
			
		
			}
			public void mousePressed(MouseEvent e) { 	
				SoundManager.ButtonEffectMp3Player.Play();
				wallOn.setIcon(new ImageIcon("OptionImage/wallon2.png"));
			wallOff.setIcon(new ImageIcon("OptionImage/walloff1.png")); }
			public void mouseReleased(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { }
			public void mouseExited(MouseEvent e) {}
		});
		wallOff.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) { wallOnOff = false; 
				
			}
			public void mousePressed(MouseEvent e) {
				
				SoundManager.ButtonEffectMp3Player.Play();
				wallOn.setIcon(new ImageIcon("OptionImage/wallon1.png"));
			wallOff.setIcon(new ImageIcon("OptionImage/walloff2.png"));  }
			public void mouseReleased(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { }
			public void mouseExited(MouseEvent e) {}
		});
		back.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) { }
			public void mousePressed(MouseEvent e) {  }
			public void mouseReleased(MouseEvent e) {  }
			public void mouseEntered(MouseEvent e) { back.setIcon(new ImageIcon("OptionImage/back2.png")); }
			public void mouseExited(MouseEvent e) { back.setIcon(new ImageIcon("OptionImage/back1.png")); }
		});
		start.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) { 

			}
			public void mousePressed(MouseEvent e) {  }
			public void mouseReleased(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { start.setIcon(new ImageIcon("OptionImage/start2.png")); }
			public void mouseExited(MouseEvent e) { start.setIcon(new ImageIcon("OptionImage/start1.png")); }
		});
		add(back); add(start);
		add(easy); add(normal); add(hard);
		add(speed1); add(speed2); add(speed3); add(speed4); add(speed5);
		add(wallOn); add(wallOff);
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(img, 0, 0, null);
		
		setOpaque(false);
	}
}