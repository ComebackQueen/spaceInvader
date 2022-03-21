package Item;

import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import GraphicObject.GraphicObject;

public class HiddenMonster extends GraphicObject{

   public int hx = 1245;
   int hy = 0;
   Point p;
   Point vec;
   Image hidden_img;
   public int random;
   public boolean airplaneOn = false;
   
   long stTime =System.currentTimeMillis();
   long edTime =System.currentTimeMillis()+5;
   public HiddenMonster(String name) {
      super(name);
      this.x = hx;
      this.y = hy;
      
      vec = new Point();
       p = new Point(hx, hy);
       vec.x =-1;
      // TODO Auto-generated constructor stub
   }
   public void setHX(int x) {
	   this.x = x;
	   hx =x;
	   p.x = x;
   }
   public int getHX() {
	   return  this.x;
   }
   public void update(){
 stTime =System.currentTimeMillis();
      if(stTime > edTime){
         p.x += vec.x;
      this.x = this.x +vec.x;
      hx = this.x;
      edTime = stTime+5;
      }

      hx = p.x;
      if(hx == 0)
         this.x =p.x=hx=0;
      
      if(getHX() <10){
			airplaneOn =false;
			setHX(1250);

		}
      
      }
   
   public void getPicture(String name) {
      try {
         // 지정한 이름의 파일을 불러온다
         super.img = ImageIO.read(new File(name));

      } catch (IOException e) {
         System.out.println(e.getMessage());
         System.exit(0);
      }
   }
   
}