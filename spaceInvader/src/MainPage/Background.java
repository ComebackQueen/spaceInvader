package MainPage;

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


public class Background extends JPanel{
	BufferedImage img = null; // 諛� 寃� 
	
	public Button Single=new Button("ButtonImage/b1.png", 60, 450, 328, 220); // 踰꾪듉�씠誘몄� 
	public Button Mulity=new Button("ButtonImage/b3.png", 485, 450, 328, 220);
	public Button Setting=new Button("ButtonImage/b5.png", 910, 450, 328, 220);

	public Background(){
		try {
			img=ImageIO.read(new File("BackgroundImage/logo.png")); // 諛곌꼍�씠誘�
		} catch (IOException e) {
			System.out.println("硫붿씤�솕硫� 諛곌꼍 �씠誘몄�瑜� 遺덈윭�삱 �닔 �뾾�뒿�땲�떎. "); //諛곌꼍�씠誘몄�瑜� 紐� 遺덈윭�삱
			System.exit(0);
		}
		
		setLayout(null);
		
		Single.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent e){
			}
			public void mouseEntered(MouseEvent e) {
				Single.setIcon(new ImageIcon("ButtonImage/b2.png")); // 
			}
			public void mouseExited(MouseEvent e) {
				Single.setIcon(new ImageIcon("ButtonImage/b1.png")); //占싱글몌옙占� 占쏙옙占쎌스 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
			}
			public void mousePressed(MouseEvent e) {  }
			public void mouseReleased(MouseEvent e) {   }
		});
		
		Mulity.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent e){
			}
			public void mouseEntered(MouseEvent e) {
				Mulity.setIcon(new ImageIcon("ButtonImage/b4.png")); //占싱글몌옙占쏙옙占쏙옙
			}
			public void mouseExited(MouseEvent e) {
				Mulity.setIcon(new ImageIcon("ButtonImage/b3.png")); //占싱글몌옙占� 占쏙옙占쎌스 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
			}
			public void mousePressed(MouseEvent e) {  }
			public void mouseReleased(MouseEvent e) {   }
		});
		
		Setting.addMouseListener(new MouseListener() {
				public void mouseClicked(MouseEvent e){
			}
			public void mouseEntered(MouseEvent e) {
				Setting.setIcon(new ImageIcon("ButtonImage/b6.png")); //占싱글몌옙占쏙옙占쏙옙
			}
			public void mouseExited(MouseEvent e) {
				Setting.setIcon(new ImageIcon("ButtonImage/b5.png")); //占싱글몌옙占� 占쏙옙占쎌스 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙
			}
			public void mousePressed(MouseEvent e) {  }
			public void mouseReleased(MouseEvent e) {   }
		});
		
		add(Single); add(Mulity); add(Setting);
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	
		g.drawImage(img, 0, 0, null);
		setOpaque(false);
	}


}