package Crash;

import Manager.SoundManager;
import Bullet.PlayerBullet;
import Enermy.Enermy;

public class ItemBulletColision {
   int x;
   public int ibx;
   public int iby;
   public void enermyColision(PlayerBullet PlayBullet, Enermy enermy, int index) {
      x=-1;
      PlayerBullet pb1;
      
      if (PlayBullet.getCnt() != 0) { 
         if ((PlayBullet.x >= enermy.x && PlayBullet.x <= enermy.x + 50)
               && (PlayBullet.y >= enermy.y && PlayBullet.y <= enermy.y + 38) // �������𼭸� �浹 ����
               || (PlayBullet.x >= enermy.x && PlayBullet.x <= enermy.x + 50)
                     && (PlayBullet.y + 4 >= enermy.y && PlayBullet.y + 4 <= enermy.y + 38) // ���ʾƷ��𼭸� �浹 ����
               || (PlayBullet.x + 4 >= enermy.x && PlayBullet.x + 4 <= enermy.x + 50)
                     && (PlayBullet.y >= enermy.y && PlayBullet.y <= enermy.y + 38) // ���������𼭸� �浹 ����
               || (PlayBullet.x + 4 >= enermy.x && PlayBullet.x + 4 <= enermy.x + 50)
                     && (PlayBullet.y + 4 >= enermy.y && PlayBullet.y + 4 <= enermy.y + 38)) // �����ʾƷ��𼭸� �浹 ����
         {
            
            if (!enermy.isDead) {
               enermy.isDead = true;
               enermy.maxTime = System.currentTimeMillis() + 100;
               PlayBullet.setCnt(1); // �̰� ���� �߿��ѵ� �̷��� �غ��� ������ �Ű����� ���� ���������� �̻��ϰ�ü�� ī���͸� �پ��°Ŷ� �̻��� �迭�� ���� ī���͸� ���ϼ�
               PlayBullet.launched=false;
               SoundManager.EnemyHitEffectMp3Player.Play();
               
               PlayBullet.x = 0;
               PlayBullet.y = 0;
               PlayBullet.img = null;
               // �־���Ѵ�.
               x=index;
            }

         }
      }
   }
   public int remove(){
      return x;
   }
}