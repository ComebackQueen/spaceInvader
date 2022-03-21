package Wall;
import java.awt.Graphics;
import java.util.LinkedList;

import Bullet.EnermyBullet;
import Bullet.PlayerBullet;

public class WallPiece {
	long stTime = System.currentTimeMillis();
	long edTime = System.currentTimeMillis()+400;
	public LinkedList<Wall> wall=new LinkedList<Wall>();
	int x, y;
	boolean pbWallDlay =true;
	boolean ebWallDlay =true;
	public WallPiece(int x, int y) {
		this.x=x;
		this.y=y;
		
		for(int i=0; i<6; i++) {
			wall.add(new Wall("SingleGameImage/Wall.jpg"));
		}
		wall.get(0).setX(x);     //���� ��ĭ�Ʒ� �� 
		wall.get(0).setY(y+31);
		
		wall.get(1).setX(x); 	// ���� �� 
		wall.get(1).setY(y);
		
		x+=31;
		wall.get(2).setX(x);		// 2��° �� 
		wall.get(2).setY(y);
		
		x+=31;
		wall.get(3).setX(x); // 3��° �� 
		wall.get(3).setY(y);
		
		x+=31;
		wall.get(4).setX(x); // 4��° �� ��
		wall.get(4).setY(y); 
		
		wall.get(5).setX(x); // 5��° �Ʒ� ��
		wall.get(5).setY(y+31);
	}
	public void update() {
		
	}
	public void draw(Graphics g) {
		for(int i=0; i<wall.size(); i++) {
			wall.get(i).draw(g);
		}
	}
	public void pb_colision(PlayerBullet PlayBullet){
		
		for(int i =0; i<wall.size(); i++){
			
			      if (PlayBullet.getCnt() != 0) { 
			    	 
			    	  if ((PlayBullet.x > wall.get(i).getX() && PlayBullet.x < wall.get(i).changeX(30))
			                  && (PlayBullet.y > wall.get(i).getY() && PlayBullet.y < wall.get(i).changeY(30)) // �������𼭸� �浹 ����
			                  || (PlayBullet.x > wall.get(i).getX() && PlayBullet.x < wall.get(i).changeX(30))
			                        && (PlayBullet.y + 4 > wall.get(i).getY() && PlayBullet.y + 4 < wall.get(i).changeY(30)) // ���ʾƷ��𼭸� �浹 ����
			                  || (PlayBullet.x + 4 > wall.get(i).getX() && PlayBullet.x + 4 < wall.get(i).changeX(30))
			                        && (PlayBullet.y > wall.get(i).getY() && PlayBullet.y < wall.get(i).changeY(30)) // ���������𼭸� �浹 ����
			                  || (PlayBullet.x + 4 > wall.get(i).getX() && PlayBullet.x + 4 < wall.get(i).changeX(30))
			                        && (PlayBullet.y + 4 > wall.get(i).getY() && PlayBullet.y + 4 < wall.get(i).changeY(30))) // �����ʾƷ��𼭸� �浹 ����
			         {
			            
			            
			        	 
			    		  PlayBullet.setCnt(1); // �̰� ���� �߿��ѵ� �̷��� �غ��� ������ �Ű����� ���� ���������� �̻��ϰ�ü�� ī���͸� �پ��°Ŷ� �̻��� �迭�� ���� ī���͸� ���ϼ�
			               PlayBullet.launched=false;
			    
			               PlayBullet.x = 0;
			               PlayBullet.y = 0;
			               PlayBullet.img = null;
			             if(pbWallDlay)
			               if(wall.get(i).WallLife == 2){
			            	   wall.get(i).WallLife =1;
			            	   pbWallDlay =false;
			               }
			             if(pbWallDlay)     
			               if(wall.get(i).WallLife == 1){
			            	   wall.remove(i);
			               }
			             pbWallDlay =true;

			   

			         
			      }
			   }
		}
	}
	public void eb_colision(EnermyBullet eb){
		
		 
		for(int i =0; i<wall.size(); i++){
			
		      
		    	 
		    	  if ((eb.x > wall.get(i).getX() && eb.x < wall.get(i).changeX(30))
		                  && (eb.y > wall.get(i).getY() && eb.y < wall.get(i).changeY(30)) // �������𼭸� �浹 ����
		                  || (eb.x > wall.get(i).getX() && eb.x < wall.get(i).changeX(30))
		                        && (eb.y + 4 > wall.get(i).getY() && eb.y + 4 < wall.get(i).changeY(30)) // ���ʾƷ��𼭸� �浹 ����
		                  || (eb.x + 4 > wall.get(i).getX() && eb.x + 4 < wall.get(i).changeX(30))
		                        && (eb.y > wall.get(i).getY() && eb.y < wall.get(i).changeY(30)) // ���������𼭸� �浹 ����
		                  || (eb.x + 4 > wall.get(i).getX() && eb.x + 4 < wall.get(i).changeX(30))
		                        && (eb.y + 4 > wall.get(i).getY() && eb.y + 4 < wall.get(i).changeY(30))) // �����ʾƷ��𼭸� �浹 ����
		         {
		    		  eb.x = 0;
		               eb.y = 0;
		               eb.launched=false;
		               eb.img = null;
		        
		               if(ebWallDlay)
			               if(wall.get(i).WallLife == 2){
			            	   wall.get(i).WallLife =1;
			            	   ebWallDlay =false;
			               }
			             if(ebWallDlay)     
			               if(wall.get(i).WallLife == 1){
			            	   wall.remove(i);
			               }
			            
		              
		         
		      
		   }
	}
		ebWallDlay =true;
	}

}
