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
               && (PlayBullet.y >= enermy.y && PlayBullet.y <= enermy.y + 38) // �������𼭸� �浹 ����
               || (PlayBullet.x >= enermy.x && PlayBullet.x <= enermy.x + 50)
                     && (PlayBullet.y + 4 >= enermy.y && PlayBullet.y + 4 <= enermy.y + 38) // ���ʾƷ��𼭸� �浹 ����
               || (PlayBullet.x + 4 >= enermy.x && PlayBullet.x + 4 <= enermy.x + 50)
                     && (PlayBullet.y >= enermy.y && PlayBullet.y <= enermy.y + 38) // ���������𼭸� �浹 ����
               || (PlayBullet.x + 4 >= enermy.x && PlayBullet.x + 4 <= enermy.x + 50)
                     && (PlayBullet.y + 4 >= enermy.y && PlayBullet.y + 4 <= enermy.y + 38)) // �����ʾƷ��𼭸� �浹 ����
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
   public void helpedBulletEnermyColision(HelperBullet helperBullet, Enermy enermy) {
          
         if (helperBullet.getCnt() != 0) { 
            if ((helperBullet.x >= enermy.x && helperBullet.x <= enermy.x + 50)
                  && (helperBullet.y >= enermy.y && helperBullet.y <= enermy.y + 38) // �������𼭸� �浹 ����
                  || (helperBullet.x >= enermy.x && helperBullet.x <= enermy.x + 50)
                        && (helperBullet.y + 4 >= enermy.y && helperBullet.y + 4 <= enermy.y + 38) // ���ʾƷ��𼭸� �浹 ����
                  || (helperBullet.x + 4 >= enermy.x && helperBullet.x + 4 <= enermy.x + 50)
                        && (helperBullet.y >= enermy.y && helperBullet.y <= enermy.y + 38) // ���������𼭸� �浹 ����
                  || (helperBullet.x + 4 >= enermy.x && helperBullet.x + 4 <= enermy.x + 50)
                        && (helperBullet.y + 4 >= enermy.y && helperBullet.y + 4 <= enermy.y + 38)) // �����ʾƷ��𼭸� �浹 ����
            {
               
                     enermy.isDead = true;
                       enermy.maxTime = System.currentTimeMillis() + 100;
                       helperBullet.setCnt(1); // �̰� ���� �߿��ѵ� �̷��� �غ��� ������ �Ű����� ���� ���������� �̻��ϰ�ü�� ī���͸� �پ��°Ŷ� �̻��� �迭�� ���� ī���͸� ���ϼ�
                       helperBullet.launched=false;
                       SoundManager.EnemyHitEffectMp3Player.Play();
                       
                       helperBullet.x = 0;
                       helperBullet.y = 0;
                       helperBullet.img = null;
                       // �־���Ѵ�.  
                       
                      
                     
                  
                  
               }

            }
         }
         
         
      
   
   
   
   public void bombColision(Enermy enermy, int index) {
         x=-1;
           
               if (!enermy.isDead) {
                  if((ex-150 <= enermy.x && enermy.x+50 <=ex+150) && (ey-120 <= enermy.y && enermy.y+38 <= ey+120)) {         //2�� ��
                     
                     enermy.isDead = true;
                       enermy.maxTime = System.currentTimeMillis() + 100;
                       x=index;
                  }
                  // SoundManager.EnemyHitEffectMp3Player.Play(); ���߿� ������ ȿ���� ���� �������� �ϽŴ�ϴ�.
               
               }

              
      }
   
   
   
   public int remove(){
      return x;
   }
}