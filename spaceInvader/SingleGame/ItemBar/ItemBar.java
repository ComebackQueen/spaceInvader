package ItemBar;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GraphicObject.GraphicObject;
import Player.Player;

public class ItemBar extends GraphicObject {
	long setTime = System.currentTimeMillis();
	long maxTime = System.currentTimeMillis();
	public int itemNumber = 0;
	BufferedImage ItemBombImg = null;
	BufferedImage ItemBulletImg = null;
	BufferedImage ItemHellperImg = null;
	BufferedImage EmptyImg = null;

	// »ý¼ºÀÚ
	public ItemBar(String name,int x, int y) {
		super(name);
		this.x = x;
		this.y = y;
		try {
			ItemBombImg = ImageIO.read(new File("SingleGameImage/ItemImageBomb.png"));
		} catch (IOException e) {
		}
		try {
			ItemBulletImg = ImageIO.read(new File("SingleGameImage/ItemBulletImage.png"));
		} catch (IOException e) {
		}
		try {
			ItemHellperImg = ImageIO.read(new File("SingleGameImage/ItemHellperImageBlack.png"));
		} catch (IOException e) {
		}
		try {
			EmptyImg = ImageIO.read(new File("SingleGameImage/Empty.png"));
		} catch (IOException e) {
		}

	}

	public void update(Player py) {
		
		switch (py.getItem) {
		case 1:
			getPicture(ItemBombImg);
			break;
		case 2:
			getPicture(ItemBulletImg);
			break;
		case 3:
			getPicture(ItemHellperImg);
			break;
		case 0:
			getPicture(EmptyImg);
			break;
		}

	}
	
	  public void getPicture(BufferedImage tmpImg) {
		     
	         super.img = tmpImg;

	     
	   }
}