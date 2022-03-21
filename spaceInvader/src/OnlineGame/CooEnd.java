package OnlineGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CooEnd extends JPanel{
	BufferedImage image;
	JLabel ScoreName=new JLabel("TOTAL SCORE");
	public JLabel Score=new JLabel("555점");
	public JLabel end=new JLabel("고생하셨습니다!");
	public JLabel p1=new JLabel("1P"); //p1 닉네임
	public JLabel p2=new JLabel("2P"); //p2 닉네임
	public JLabel p1Score=new JLabel("222"); //p1 점수
	public JLabel p2Score=new JLabel("333"); //p2 점수
	public JButton button=new JButton(new ImageIcon("CooImage/나가기버튼.png"));
	public void setImage(String name){
		try {
			image=ImageIO.read(new File(name));
		} catch (IOException e) {
			System.out.println(name+" 파일을 불러올 수 없습니다."+e);
		}
		
	}
	public CooEnd(){
		this.setLayout(null);

		ScoreName.setForeground(Color.WHITE);
		Score.setForeground(Color.WHITE);
		end.setForeground(Color.white);
		p1.setForeground(Color.white);
		p2.setForeground(Color.white);
		p1Score.setForeground(Color.WHITE);
		p2Score.setForeground(Color.WHITE);
		
		ScoreName.setFont(new Font("고딕", Font.BOLD, 70));
		Score.setFont(new Font("고딕", Font.PLAIN, 100));
		end.setFont(new Font("굴림", Font.PLAIN, 60));
		p1.setFont(new Font("굴림", Font.PLAIN, 60));
		p2.setFont(new Font("굴림", Font.PLAIN, 60));
		p1Score.setFont(new Font("굴림", Font.PLAIN, 60));
		p2Score.setFont(new Font("굴림", Font.PLAIN, 60));
		
		ScoreName.setHorizontalAlignment(ScoreName.CENTER);
		Score.setHorizontalAlignment(Score.CENTER);
		end.setHorizontalAlignment(end.CENTER);
		p1.setHorizontalAlignment(p1.LEFT);
		p2.setHorizontalAlignment(p2.RIGHT);
		p1Score.setHorizontalAlignment(p1.LEFT);
		p2Score.setHorizontalAlignment(p2.RIGHT);
		
		button.setBorder(null);
		button.setOpaque(false);
		button.setBorderPainted(false);
		button.setContentAreaFilled(false);
		button.setFocusPainted(false);
		
		ScoreName.setBounds(0, 100, 1280, 150);
		Score.setBounds(0, 250, 1280, 200);
		end.setBounds(0, 450, 1280, 200);
		button.setBounds(510, 590, 260, 81);
		p1.setBounds(60, 100, 1280, 150);
		p2.setBounds(0, 100, 1200, 150);
		p1Score.setBounds(60, 250, 1280, 200);
		p2Score.setBounds(0, 250, 1200, 200);
		
		add(p1);
		add(p2);
		add(p1Score);
		add(p2Score);
		add(ScoreName);
		add(Score);
		add(end);
		add(button);
	}
	public void paintComponent(Graphics g){
		g.drawImage(image, 0, 0, null);
	}
}
