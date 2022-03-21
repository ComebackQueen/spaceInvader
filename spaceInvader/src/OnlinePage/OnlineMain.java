package OnlinePage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Button.Button;
import Manager.SoundManager;

public class OnlineMain extends JPanel{
	BufferedImage img = null; // �� �� 
	public Button pvp = new Button("OnlineImage/pvp.png", 0, 20, 290, 170);
	public Button teamPlay = new Button("OnlineImage/teamPlay.png", 0, 180, 290, 170);
	public Button ranking = new Button("OnlineImage/ranking.png", 0, 350, 290, 170);
	public Button exit = new Button("OnlineImage/exit.png", 0, 520, 290, 170);
	public JTextArea area = new JTextArea();   //ä�ø��
	public JTextField t2 = new JTextField(30); //ä���Է±���
	public JScrollPane scrollPane=new JScrollPane(area);
	public JTextArea User=new JTextArea();  //������ ���
	public JScrollPane UserScroll=new JScrollPane(User);
	public boolean PanelOnOff=false;
	public JTextArea countDown=new JTextArea();
	public OnlineMain(){
		setLayout(null);
		try {
			img=ImageIO.read(new File("OnlineImage/BattlenetImg.jpg")); // ����̹�
			
		} catch (IOException e) {
			System.out.println("����ȭ�� ��� �̹����� �ҷ��� �� �����ϴ�. "); //����̹����� �� �ҷ���
			System.exit(0);
		}

		countDown.setBounds(1120, 40, 200, 100);
		countDown.setOpaque(false);
		countDown.setBorder(null);
		countDown.setForeground(Color.red);
		countDown.setFont(new Font("���", Font.BOLD, 100));
		countDown.setText(" ");

		
		scrollPane.setBounds(300, 200, 675, 400);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(null);
		scrollPane.setForeground(Color.WHITE);
		
		area.setOpaque(false);
		area.setEditable(false);
		area.setForeground(Color.WHITE);
		
		UserScroll.setBounds(1007, 200, 250, 460);
		UserScroll.setOpaque(false);
		UserScroll.getViewport().setOpaque(false);
		UserScroll.setBorder(null);
		UserScroll.setForeground(Color.WHITE);
		
		
		User.setOpaque(false);
		User.setBorder(null);
		User.setFocusable(false);
		User.setFont(new Font("Courier", Font.PLAIN, 16));
		User.setForeground(Color.WHITE);
		User.setCaretColor(Color.WHITE);
		
		
		
		t2.setBounds(300, 620, 675, 45);
		t2.setOpaque(false);
		t2.setBorder(null);
		t2.setFocusable(true);
		t2.setFont(new Font("Courier", Font.PLAIN, 16));
		t2.setForeground(Color.WHITE);
		t2.setCaretColor(Color.WHITE);
		
		add(countDown);
		add(UserScroll);
		add(pvp);
		add(exit);
		add(ranking);
		add(teamPlay);
		add(scrollPane);
		add(t2);
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	
		g.drawImage(img, -5, -40, null);
		

		setOpaque(false);
	}


}