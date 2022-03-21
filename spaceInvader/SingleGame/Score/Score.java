package Score;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Score {
	public static int round=1;
	int score;
	public Score(){
		score=0;
		
		Font font=new Font("SansSerif", Font.PLAIN, 10);
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.black);
		g.drawString("ROUND : "+ Integer.toString(round), 5, 20);
		g.drawString("SCORE : "+Integer.toString(score), 5, 40);
	}
	public void setScore(int num){
		this.score+=num;
	}
	public void update(){
		
	}
}
