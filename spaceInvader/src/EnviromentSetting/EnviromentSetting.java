package EnviromentSetting;

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

public class EnviromentSetting extends JPanel{

	
	BufferedImage img = null; // �ɼ� �� ��
	
	public Button back = new Button("OptionImage/back1.png", 0, 310, 100, 100); //�ڷΰ��� ��ư

	
	
	
	
	public Button backGroundSound = new Button("EnviromentOptionImage/backGroundMusicOn.png", 200, 500, 300	, 150); 
	public Button effectSound = new Button("EnviromentOptionImage/effectSoundOn.png", 800, 500, 300, 150);
	
	
	
	public EnviromentSetting(){
		setLayout(null);
		try {
			img=ImageIO.read(new File("EnviromentOptionImage/Option.png"));
		} catch (IOException e) {
			System.out.println("�¶��� ��� �̹����� �ҷ��� �� �����ϴ�.");
			System.exit(0);
		}
	
		backGroundSound.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) { 
				
				
				
			}
			public void mousePressed(MouseEvent e) {
				SoundManager.ButtonEffectMp3Player.Play();
				SoundManager.SetBackGroundMusicFlag(!SoundManager.GetBackGroundMusicFlag());
				
			}
			public void mouseReleased(MouseEvent e) {  }
			public void mouseEntered(MouseEvent e) { }
			public void mouseExited(MouseEvent e) { }
		});
		
		
		effectSound.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) { 
			
				
			}
			public void mousePressed(MouseEvent e) {
				SoundManager.ButtonEffectMp3Player.Play();
				SoundManager.SetEffectMusicFlag(!SoundManager.GetEffectMusicFlag());
				
			}
			public void mouseReleased(MouseEvent e) {  }
			public void mouseEntered(MouseEvent e) { }
			public void mouseExited(MouseEvent e) { }
		});
		
		
		
		back.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) { }
			public void mousePressed(MouseEvent e) {  }
			public void mouseReleased(MouseEvent e) {  }
			public void mouseEntered(MouseEvent e) { back.setIcon(new ImageIcon("OptionImage/back2.png")); }
			public void mouseExited(MouseEvent e) { back.setIcon(new ImageIcon("OptionImage/back1.png")); }
		});

		add(back); 
		add(backGroundSound);
		add(effectSound);
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(img, 0, 0, null);
		
		setOpaque(false);
	}
}
