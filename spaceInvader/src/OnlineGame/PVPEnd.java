package OnlineGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Button.Button;

public class PVPEnd extends JPanel {
	public BufferedImage image = null; // πË ∞Ê
	public JLabel player1;
	public JLabel player2;
	public JLabel vs;
	public JLabel smallplayer1;
	public JLabel smallplayer2;
	public JLabel ratingName1;
	public JLabel ratingName2;
	public JLabel rating1;
	public JLabel rating2;
	public Button out = new Button("PVPImage/outbutton.png", 186, 640, 260, 81);
	public boolean winLose = true;
	public boolean PanelOnOff = false;
	public String name = null;
	public void setImage(String name){
		try{
			image=ImageIO.read(new File(name));
		}catch(Exception e){
			System.out.println();
		}
	}
	public PVPEnd() {
		this.name=name;
		setLayout(null);
		player1 = new JLabel("11");
		player2 = new JLabel("12");
		vs = new JLabel("VS");
		smallplayer1 = new JLabel("11");
		smallplayer2 = new JLabel("12");
		ratingName1 = new JLabel("∑π¿Ã∆√ : ");
		ratingName2 = new JLabel("∑π¿Ã∆√ : ");
		rating1 = new JLabel("1000");
		rating2 = new JLabel("1000");

		player1.setForeground(Color.WHITE);
		player1.setFont(new Font("±º∏≤", Font.BOLD, 48));
		player1.setOpaque(false);
		player1.setBorder(null);
		player1.setBounds(81, 167, 204, 70);
		player1.setHorizontalAlignment(player1.CENTER);

		vs.setForeground(Color.WHITE);
		vs.setFont(new Font("±º∏≤", Font.BOLD, 48));
		vs.setOpaque(false);
		vs.setBorder(null);
		vs.setBounds(281, 167, 93, 70);
		vs.setHorizontalAlignment(vs.CENTER);

		player2.setForeground(Color.WHITE);
		player2.setFont(new Font("±º∏≤", Font.BOLD, 48));
		player2.setOpaque(false);
		player2.setBorder(null);
		player2.setBounds(367, 167, 204, 70);
		player2.setHorizontalAlignment(player2.CENTER);

		smallplayer1.setForeground(Color.WHITE);
		smallplayer1.setFont(new Font("±º∏≤", Font.BOLD, 32));
		smallplayer1.setOpaque(false);
		smallplayer1.setBorder(null);
		smallplayer1.setBounds(57, 293, 122, 42);
		smallplayer1.setHorizontalAlignment(smallplayer1.CENTER);

		ratingName1.setForeground(Color.WHITE);
		ratingName1.setFont(new Font("±º∏≤", Font.BOLD, 32));
		ratingName1.setOpaque(false);
		ratingName1.setBorder(null);
		ratingName1.setBounds(169, 293, 160, 42);
		ratingName1.setHorizontalAlignment(ratingName1.CENTER);

		rating1.setForeground(Color.WHITE);
		rating1.setFont(new Font("±º∏≤", Font.BOLD, 32));
		rating1.setOpaque(false);
		rating1.setBounds(411, 293, 92, 42);

		smallplayer2.setForeground(Color.WHITE);
		smallplayer2.setFont(new Font("±º∏≤", Font.BOLD, 32));
		smallplayer2.setOpaque(false);
		smallplayer2.setBorder(null);
		smallplayer2.setBounds(57, 360, 122, 42);
		smallplayer2.setHorizontalAlignment(smallplayer2.CENTER);

		ratingName2.setForeground(Color.WHITE);
		ratingName2.setFont(new Font("±º∏≤", Font.BOLD, 32));
		ratingName2.setOpaque(false);
		ratingName2.setBorder(null);
		ratingName2.setBounds(169, 360, 160, 42);
		ratingName2.setHorizontalAlignment(ratingName2.CENTER);
		
		rating2.setForeground(Color.WHITE);
		rating2.setFont(new Font("±º∏≤", Font.BOLD, 32));
		rating2.setOpaque(false);
		rating2.setBorder(null);
		rating2.setBounds(411, 360, 92, 42);
		
		add(player1); add(vs); add(player2);
		add(smallplayer1); add(ratingName1); add(rating1);
		add(smallplayer2); add(ratingName2); add(rating2);
		add(out);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//if (winLose)
		g.drawImage(image, 0, 0, null);
		/*else
			g.drawImage(lose, 0, 0, null);
*/
		setOpaque(false);
	}
}
