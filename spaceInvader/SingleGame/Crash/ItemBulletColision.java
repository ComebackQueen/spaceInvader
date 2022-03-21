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
               && (PlayBullet.y >= enermy.y && PlayBullet.y <= enermy.y + 38) // 왼쪽위모서리 충돌 감지
               || (PlayBullet.x >= enermy.x && PlayBullet.x <= enermy.x + 50)
                     && (PlayBullet.y + 4 >= enermy.y && PlayBullet.y + 4 <= enermy.y + 38) // 왼쪽아래모서리 충돌 감지
               || (PlayBullet.x + 4 >= enermy.x && PlayBullet.x + 4 <= enermy.x + 50)
                     && (PlayBullet.y >= enermy.y && PlayBullet.y <= enermy.y + 38) // 오른쪽위모서리 충돌 감지
               || (PlayBullet.x + 4 >= enermy.x && PlayBullet.x + 4 <= enermy.x + 50)
                     && (PlayBullet.y + 4 >= enermy.y && PlayBullet.y + 4 <= enermy.y + 38)) // 오른쪽아래모서리 충돌 감지
         {
            
            if (!enermy.isDead) {
               enermy.isDead = true;
               enermy.maxTime = System.currentTimeMillis() + 100;
               PlayBullet.setCnt(1); // 이게 제일 중요한데 이렇게 해봤자 어차피 매개변수 안의 지역변수의 미사일객체의 카운터만 줄어드는거라서 미사일 배열의 실제 카운터를 줄일수
               PlayBullet.launched=false;
               SoundManager.EnemyHitEffectMp3Player.Play();
               
               PlayBullet.x = 0;
               PlayBullet.y = 0;
               PlayBullet.img = null;
               // 있어야한다.
               x=index;
            }

         }
      }
   }
   public int remove(){
      return x;
   }
}