package Crash;

import Bullet.HelperBullet;
import Bullet.PlayerBullet;
import Enermy.Enermy;
import Manager.SoundManager;
import Player.Player;

public class EnermyColision {
   int x;
   int ex;
   int ey;
   public void enermyColision(PlayerBullet PlayBullet, Enermy enermy, int index, Player player1) {
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
                if(player1.getItem == 1) {
                    ex = enermy.x;
                   ey = enermy.y;
                   player1.exploedON =true;
                   player1.getItem = 0;
                   player1.coolTimeFlag = false;
                 }
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
   public void helpedBulletEnermyColision(HelperBullet helperBullet, Enermy enermy) {
          
         if (helperBullet.getCnt() != 0) { 
            if ((helperBullet.x >= enermy.x && helperBullet.x <= enermy.x + 50)
                  && (helperBullet.y >= enermy.y && helperBullet.y <= enermy.y + 38) // 왼쪽위모서리 충돌 감지
                  || (helperBullet.x >= enermy.x && helperBullet.x <= enermy.x + 50)
                        && (helperBullet.y + 4 >= enermy.y && helperBullet.y + 4 <= enermy.y + 38) // 왼쪽아래모서리 충돌 감지
                  || (helperBullet.x + 4 >= enermy.x && helperBullet.x + 4 <= enermy.x + 50)
                        && (helperBullet.y >= enermy.y && helperBullet.y <= enermy.y + 38) // 오른쪽위모서리 충돌 감지
                  || (helperBullet.x + 4 >= enermy.x && helperBullet.x + 4 <= enermy.x + 50)
                        && (helperBullet.y + 4 >= enermy.y && helperBullet.y + 4 <= enermy.y + 38)) // 오른쪽아래모서리 충돌 감지
            {
               
                     enermy.isDead = true;
                       enermy.maxTime = System.currentTimeMillis() + 100;
                       helperBullet.setCnt(1); // 이게 제일 중요한데 이렇게 해봤자 어차피 매개변수 안의 지역변수의 미사일객체의 카운터만 줄어드는거라서 미사일 배열의 실제 카운터를 줄일수
                       helperBullet.launched=false;
                       SoundManager.EnemyHitEffectMp3Player.Play();
                       
                       helperBullet.x = 0;
                       helperBullet.y = 0;
                       helperBullet.img = null;
                       // 있어야한다.  
                       
                      
                     
                  
                  
               }

            }
         }
         
         
      
   
   
   
   public void bombColision(Enermy enermy, int index) {
         x=-1;
           
               if (!enermy.isDead) {
                  if((ex-150 <= enermy.x && enermy.x+50 <=ex+150) && (ey-120 <= enermy.y && enermy.y+38 <= ey+120)) {         //2번 적
                     
                     enermy.isDead = true;
                       enermy.maxTime = System.currentTimeMillis() + 100;
                       x=index;
                  }
                  // SoundManager.EnemyHitEffectMp3Player.Play(); 나중에 아이템 효과음 구현 수기형이 하신답니다.
               
               }

              
      }
   
   
   
   public int remove(){
      return x;
   }
}