package Crash;


import Bullet.EnermyBullet;
import Bullet.PlayerBullet;
import Player.Player;

public class BulletColision {
   int x;
   public void bulletColision(PlayerBullet PlayBullet, EnermyBullet eb, int index, Player player) {
      x=-1;
      if (PlayBullet.getCnt() != 0 && eb.launched == true) { 
         if ((PlayBullet.x >= eb.x && PlayBullet.x <= eb.x + 4)
               && (PlayBullet.y >= eb.y && PlayBullet.y <= eb.y + 4) // 왼쪽위모서리 충돌 감지
               || (PlayBullet.x >= eb.x && PlayBullet.x <= eb.x + 4)
                     && (PlayBullet.y + 4 >= eb.y && PlayBullet.y + 4 <= eb.y + 4) // 왼쪽아래모서리 충돌 감지
               || (PlayBullet.x + 4 >= eb.x && PlayBullet.x + 4 <= eb.x + 4)
                     && (PlayBullet.y >= eb.y && PlayBullet.y <= eb.y + 4) // 오른쪽위모서리 충돌 감지
               || (PlayBullet.x + 4 >= eb.x && PlayBullet.x + 4 <= eb.x + 4)
                     && (PlayBullet.y + 4 >= eb.y && PlayBullet.y + 4 <= eb.y + 4)) // 오른쪽아래모서리 충돌 감지
         {
            
            
               eb.launched = false;
               eb.x = -10;
               eb.y = -10;
               if(player.getItem !=1) {
                  PlayBullet.setCnt(1); // 이게 제일 중요한데 이렇게 해봤자 어차피 매개변수 안의 지역변수의 미사일객체의 카운터만 줄어드는거라서 미사일 배열의 실제 카운터를 줄일수
                   PlayBullet.launched=false;


                   PlayBullet.x = 0;
                   PlayBullet.y = 0;
                   PlayBullet.img = null;
               }
              
               // 있어야한다.

   

         }
      }
   }


}