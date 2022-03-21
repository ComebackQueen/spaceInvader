package OnlinePage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Button.Button;

public class OnlineBack extends JPanel {
	BufferedImage img = null; // 배 경
	public JTextField f1;
	public JTextField f2;
	public Button Login = new Button("OnlineImage/LoginButton1.png", 538, 461, 205, 34);
	public Button Quit = new Button("OnlineImage/Quit1.png", 107, 605, 185, 46);
	public Button Sign = new Button("OnlineImage/Sign1.png", 992, 605, 185, 46);
	public JLabel status;
	
	public OnlineBack() {
		setLayout(null);
		try {
			img = ImageIO.read(new File("OnlineImage/OnlineBack.jpg"));
		} catch (IOException e) {
			System.out.println("온라인 배경 이미지를 불러올 수 없습니다.");
			System.exit(0);
		}
		
		

		f1 = new JTextField(20);
		f2 = new JPasswordField(20);
		status = new JLabel("로그인 필요");
		
		f1.setOpaque(false);
		f1.setBorder(null);
		f1.setFont(new Font("Courier", Font.PLAIN, 16));
		f1.setForeground(Color.WHITE);
		f1.setBounds(538, 343, 205, 32);
		f2.setOpaque(false);
		f2.setBorder(null);
		f2.setFont(new Font("Courier", Font.PLAIN, 16));
		f2.setForeground(Color.WHITE);
		f2.setBounds(538, 406, 205, 32);
		status.setForeground(Color.red);
		status.setBorder(null);
		status.setOpaque(false);
		status.setBounds(538, 530, 205, 32);
		status.setFont(new Font("굴림", Font.BOLD, 16));
		status.setHorizontalAlignment(status.CENTER);
		
		Login.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
				Login.setIcon(new ImageIcon("OnlineImage/LoginButton2.png"));
			}

			public void mouseExited(MouseEvent e) {
				Login.setIcon(new ImageIcon("OnlineImage/LoginButton1.png"));
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}
		});
		Quit.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
				Quit.setIcon(new ImageIcon("OnlineImage/Quit2.png"));
			}

			public void mouseExited(MouseEvent e) {
				Quit.setIcon(new ImageIcon("OnlineImage/Quit1.png"));
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}
		});
		Sign.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
				Sign.setIcon(new ImageIcon("OnlineImage/Sign2.png"));
			}

			public void mouseExited(MouseEvent e) {
				Sign.setIcon(new ImageIcon("OnlineImage/Sign1.png"));
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}
		});

		add(f1);
		add(f2);
		add(Login);
		add(Quit);
		add(Sign);
		add(status);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(img, 0, 0, null);

		setOpaque(false);
	}
}