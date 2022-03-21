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

public class Sign extends JPanel {
	BufferedImage img = null; // 배 경
	public JTextField f1; //아이디
	public JTextField f2; //비밀번호
	public JTextField f3; //닉네임
	public JTextField f4; //이름
	
	public Button signup = new Button("OnlineImage/Sign1.png", 538, 574, 206, 40);
	public Button Quit = new Button("OnlineImage/Quit1.png", 107, 605, 185, 46);
	
	boolean complete=false;
	public JLabel Status;
	public Sign() {
		setLayout(null);
		try {
			img = ImageIO.read(new File("OnlineImage/SignBack.jpg"));
		} catch (IOException e) {
			System.out.println("회원가입 배경 이미지를 불러올 수 없습니다.");
			System.exit(0);
		}

		
		f1 = new JTextField(20); //아이디
		f2 = new JPasswordField(20); //비밀번호
		f3 = new JTextField(20); //닉네임
		f4 = new JTextField(20); //이름
		
		Status=new JLabel("SignUp Click Please");
		
		signup.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
				signup.setIcon(new ImageIcon("OnlineImage/Sign2.png")); //
			}

			public void mouseExited(MouseEvent e) {
				signup.setIcon(new ImageIcon("OnlineImage/Sign1.png")); // �̱۸��
																		// ���콺
																		// ������
																		// ����
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
		f1.setOpaque(false);
		f1.setBorder(null);
		f1.setFont(new Font("Courier", Font.PLAIN, 16));
		f1.setForeground(Color.WHITE);
		f1.setBounds(538, 315, 205, 32);
		f2.setOpaque(false);
		f2.setBorder(null);
		f2.setFont(new Font("Courier", Font.PLAIN, 16));
		f2.setForeground(Color.WHITE);
		f2.setBounds(538, 384, 205, 32);
		f3.setOpaque(false);
		f3.setBorder(null);
		f3.setFont(new Font("Courier", Font.PLAIN, 16));
		f3.setForeground(Color.WHITE);
		f3.setBounds(538, 455, 205, 32);
		f4.setOpaque(false);
		f4.setBorder(null);
		f4.setFont(new Font("Courier", Font.PLAIN, 16));
		f4.setForeground(Color.WHITE);
		f4.setBounds(538, 523, 205, 32);

		Status.setForeground(Color.red);
		Status.setBorder(null);
		Status.setOpaque(false);
		Status.setBounds(538, 615, 205, 32);
		Status.setFont(new Font("굴림", Font.BOLD, 16));
		Status.setHorizontalAlignment(Status.CENTER);
		
		add(f1);
		add(f2);
		add(f3);
		add(f4);
		add(signup);
		add(Quit);
		add(Status);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(img, 0, 0, null);
		
	
		setOpaque(false);
	}
}