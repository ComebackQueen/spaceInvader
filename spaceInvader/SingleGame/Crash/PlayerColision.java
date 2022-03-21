package Crash;

import Bullet.EnermyBullet;
import Bullet.PlayerBullet;
import Player.Player;

public class PlayerColision {
   int x;
   long stTime = System.currentTimeMillis();
   long edTime = System.currentTimeMillis()+200;
   boolean userHit = false;
   public void userColision(Player player, EnermyBullet enerbullet) {
      x = -1;
      stTime = System.currentTimeMillis();
      if (player.playerLife != 0) {
         if (((enerbullet.getX() >= player.getUser1_x() && enerbullet.getX() <= player.getUser1_x() + 45)
               && (enerbullet.getY() >= player.getUser1_y() && enerbullet.getY() <= player.getUser1_y() + 35) // 왼쪽위모서리
                                                                                       // 충돌
                                                                                       // 감지
               || (enerbullet.getX() >= player.getUser1_x() && enerbullet.getX() <= player.getUser1_x() + 45)
                     && (enerbullet.getY() + 4 >= player.getUser1_y()
                           && enerbullet.getY() + 4 <= player.getUser1_y() + 35) // 왼쪽아래모서리
                                                                     // 충돌
                                                                     // 감지
               || (enerbullet.getX() + 4 >= player.getUser1_x() && enerbullet.getX() + 4 <= player.getUser1_x() + 45)
                     && (enerbullet.getY() >= player.getUser1_y() && enerbullet.getY() <= player.getUser1_y() + 35) // 오른쪽위모서리
                                                                                             // 충돌
                                                                                             // 감지
               || (enerbullet.getX() + 4 >= player.getUser1_x() && enerbullet.getX() + 4 <= player.getUser1_x() + 45)
                     && (enerbullet.getY() + 4 >= player.getUser1_y()
                           && enerbullet.getY() + 4 <= player.getUser1_y() + 35)) && enerbullet.launched == true) // 오른쪽아래모서리
        	 								userHit = true;
                                                                     // 충돌
			// 감지
			{
				if (userHit) {
					player.isDead = true;
					enerbullet.launched = false;
					enerbullet.setX(0);
					enerbullet.setY(0);
					enerbullet.getPicture(enerbullet.emptyImg);

				}
				if (player.isDead) {
					if (player.isRemove) {
						player.playerLife -= 1;
						
						edTime = System.currentTimeMillis() + 200;
						userHit = false;
						player.isRemove = false;
						player.isDead = false;
					}
				}
         }
      }
   }

}