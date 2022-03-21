package Button;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Button extends JButton {
	String name;
	int x, y, w, h;
	
	ImageIcon img;
	public Button(String name, int x, int y, int w, int h) {
		this.name=name; //����
		this.x=x;  
		this.y=y;
		this.w=w; //���α��� 
		this.h=h; //���α��� 
		
		img=new ImageIcon(name);
		
		this.setIcon(img);
		
		this.setBounds(x, y, w, h);
		this.setBorderPainted(false);
		this.setContentAreaFilled(false);
		this.setFocusPainted(false);
		this.setOpaque(false);
	}
}