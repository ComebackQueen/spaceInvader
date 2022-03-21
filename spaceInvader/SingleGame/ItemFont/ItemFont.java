package ItemFont;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Player.Player;


public class ItemFont {
	
	String itemName;
	public ItemFont(){
		
		
		Font font=new Font("SansSerif", Font.BOLD, 30);
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.black);
		g.drawString("ITEM", 15, 90);
		g.drawString(itemName, 15, 200);
	}
	
	public void update(Player py){
		switch (py.getItem) {
		
		case 1:
			itemName = "BOMB";
			break;
		case 2:
			itemName = "BULLET+";
			break;
		case 3:
			itemName = "HELPER";
			break;
		case 0:
			itemName = "NONE";
			break;
		}
	}

}
