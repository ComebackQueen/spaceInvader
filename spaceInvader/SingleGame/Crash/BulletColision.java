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
               && (PlayBullet.y >= eb.y && PlayBullet.y <= eb.y + 4) // �������𼭸� �浹 ����
               || (PlayBullet.x >= eb.x && PlayBullet.x <= eb.x + 4)
                     && (PlayBullet.y + 4 >= eb.y && PlayBullet.y + 4 <= eb.y + 4) // ���ʾƷ��𼭸� �浹 ����
               || (PlayBullet.x + 4 >= eb.x && PlayBullet.x + 4 <= eb.x + 4)
                     && (PlayBullet.y >= eb.y && PlayBullet.y <= eb.y + 4) // ���������𼭸� �浹 ����
               || (PlayBullet.x + 4 >= eb.x && PlayBullet.x + 4 <= eb.x + 4)
                     && (PlayBullet.y + 4 >= eb.y && PlayBullet.y + 4 <= eb.y + 4)) // �����ʾƷ��𼭸� �浹 ����
         {
            
            
               eb.launched = false;
               eb.x = -10;
               eb.y = -10;
               if(player.getItem !=1) {
                  PlayBullet.setCnt(1); // �̰� ���� �߿��ѵ� �̷��� �غ��� ������ �Ű����� ���� ���������� �̻��ϰ�ü�� ī���͸� �پ��°Ŷ� �̻��� �迭�� ���� ī���͸� ���ϼ�
                   PlayBullet.launched=false;


                   PlayBullet.x = 0;
                   PlayBullet.y = 0;
                   PlayBullet.img = null;
               }
              
               // �־���Ѵ�.

   

         }
      }
   }


}