package OnlineGame;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

import javax.imageio.ImageIO;
import javax.swing.JPanel;



public class PVPGame extends JPanel implements KeyListener{
	 BufferedImage img = null;
	
	 public BufferedReader reader;  //서버에서 데이터를 받을 스트림을 담을 그릇
	 public PrintWriter writer; //서버에게 데이터를 보낼 스트림을 담을 그릇
	 public Socket socket;
	 public PVPPlayer player1;
	 public PVPPlayer player2;
	 
	 public String player;
	 public boolean pvpon=false;
	 public boolean gameEnd=false;
	 public RepaintThread repaint=new RepaintThread();
	 public boolean MoveKeyPressed=false;
	 
	 public ClientPlayerBullet[] player1Bullet=new ClientPlayerBullet[5];
	 public ClientPlayerBullet[] player2Bullet=new ClientPlayerBullet[5];
	
	 Queue<keySave> keyOrder=new LinkedList<keySave>();
	 long startTime, endTime;
	public PVPGame(Socket socket) throws IOException{
		this.socket=socket;
		this.addKeyListener(this);
		this.setFocusable(true);
		this.setLayout(null);
		/**************************************************
		 플레이어 이미지 크기
		 user1 = 50x38
		 user2 = 50x50
		 user3 = 50x28
		 user4 = 50x41
		 **************************************************/
		player1=new PVPPlayer("PVPImage/user2.png", 300, 680, 1);
		player2=new PVPPlayer("PVPImage/user2change.png", 300, 10, 2);
		for(int i=0; i<player1Bullet.length; i++){
			player1Bullet[i]=new ClientPlayerBullet("SingleGameImage/Empty.png");
			player2Bullet[i]=new ClientPlayerBullet("SingleGameImage/Empty.png");
		}
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));// 메시지를
		writer = new PrintWriter(socket.getOutputStream(), true);
		
		repaint.start();
		try {
			img = ImageIO.read(new File("PVPImage/PVP사진.jpg"));
		} catch (IOException e) {
			System.out.println("인게임 배경을 불러올 수 없습니다.");
			System.exit(0);
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) { // 눌럿을때
		// TODO Auto-generated method stub
		if(!MoveKeyPressed){
			if (e.getKeyCode() == KeyEvent.VK_LEFT) { //왼쪽 눌렀을때
				keyOrder.offer(new keySave("LEFT_UP"));
	
				
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) { //오른쪽 눌렀을때
				keyOrder.offer(new keySave("RIGHT_UP"));
	
			}	
			MoveKeyPressed=true;
		}
			if(e.getKeyCode()==KeyEvent.VK_SPACE){	
				startTime=System.currentTimeMillis();
				if(startTime>=endTime){
					endTime=System.currentTimeMillis()+300;
					keyOrder.offer(new keySave("SPACE"));
				}
				
			}
			
			if(e.getKeyCode()==KeyEvent.VK_SPACE && e.getKeyCode() == KeyEvent.VK_LEFT){	
				startTime=System.currentTimeMillis();
				if(startTime>=endTime){
				endTime=System.currentTimeMillis()+150;
				keyOrder.offer(new keySave("LEFT_SPACE_UP"));
				}
				
			}
			if(e.getKeyCode()==KeyEvent.VK_SPACE && e.getKeyCode() == KeyEvent.VK_RIGHT){	
				startTime=System.currentTimeMillis();
				if(startTime>=endTime){
				endTime=System.currentTimeMillis()+150;
				keyOrder.offer(new keySave("RIGHT_SPACE_UP"));
				}
				
			}
			
		
	}

	@Override
	public void keyReleased(KeyEvent e) { //뗏을때
		// TODO Auto-generated method stub
		
		if(MoveKeyPressed){
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				keyOrder.offer(new keySave("LEFT_DOWN"));
		
			
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				keyOrder.offer(new keySave("RIGHT_DOWN"));
	
			
			}	
		
			MoveKeyPressed=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_SPACE && e.getKeyCode() == KeyEvent.VK_LEFT){	
		
			keyOrder.offer(new keySave("LEFT_SPACE_DOWN"));
			
			
		}
		if(e.getKeyCode()==KeyEvent.VK_SPACE && e.getKeyCode() == KeyEvent.VK_RIGHT){	
		
			keyOrder.offer(new keySave("RIGHT_SPACE_DOWN"));
			
			
		}

	}
	public void paint(Graphics g){
		super.paint(g);
		g.drawImage(img, 0, 0, null);
		player1.draw(g);
		player2.draw(g);
		for(int i=0; i<player1Bullet.length; i++){
			if(player1Bullet[i].launched==true){
				player1Bullet[i].draw(g);
				if(player1Bullet[i].y >= 30)
				{
					if(player.compareTo("1") == 0)
						player1Bullet[i].setName("SingleGameImage/Bullet.png");	
					else
						player1Bullet[i].setName("SingleGameImage/HelperBullet.png");
						
				}
			}
			if(player2Bullet[i].launched==true){
				player2Bullet[i].draw(g);
				if(player2Bullet[i].y >= 50)
				{
					if(player.compareTo("1") == 0)
						player2Bullet[i].setName("SingleGameImage/HelperBullet.png");
					else
						player2Bullet[i].setName("SingleGameImage/Bullet.png");
				}
			}
		}
	}

	class RepaintThread extends Thread {
		public void run() {
			while (true) {
				if (!gameEnd) {
					try{
						repaint();
						if(keyOrder.size() >0)
							keyOrder.poll().startKey();
						if(player1.isDaed)
						{
							player1.x =-50;
							player1.y = -50;
							player1.setUser1(null);
						} else if(player2.isDaed)
						{
							player2.x =-50;
							player2.y = -50;
							player2.setUser1(null);
						}
						this.sleep(2);
					}catch(Exception e){
						System.out.println("Repaint Thread 부분 문제발생! : "+e);
					}
				}
			}

		}
	}
	class keySave{
		String keyCode;
		public keySave(String keyCode){
			this.keyCode=keyCode;
		}
		public void startKey(){
			if(keyCode.compareTo("LEFT_UP")==0)
			writer.println("#%01%#"+"#%41%#"+player+"PVP"+"LEFT PRESSED");
			else if(keyCode.compareTo("RIGHT_UP")==0)
			writer.println("#%01%#"+"#%42%#"+player+"PVP"+"RIGHT PRESSED");
			else if(keyCode.compareTo("LEFT_DOWN")==0)
				writer.println("#%01%#"+"#%41%#"+player+"PVP"+"LEFT RELESSASED");
			else if(keyCode.compareTo("RIGHT_DOWN")==0)
				writer.println("#%01%#"+"#%42%#"+player+"PVP"+"RIGHT RELESSASED");
			else if(keyCode.compareTo("SPACE")==0)
				writer.println("#%01%#"+"#%45%#"+player+"PVP"+"SPACE PRESSED");
			else if(keyCode.compareTo("LEFT_SPACE_UP")==0)
			{
				writer.println("#%01%#"+"#%45%#"+player+"PVP"+"SPACE PRESSED");
				writer.println("#%01%#"+"#%41%#"+player+"PVP"+"LEFT PRESSED");
			}
			else if(keyCode.compareTo("RIGHT_SPACE_UP")==0)
			{
				writer.println("#%01%#"+"#%45%#"+player+"PVP"+"SPACE PRESSED");
				writer.println("#%01%#"+"#%42%#"+player+"PVP"+"RIGHT PRESSED");
			}	
			else if(keyCode.compareTo("LEFT_SPACE_DOWN")==0)
			{
				writer.println("#%01%#"+"#%41%#"+player+"PVP"+"LEFT RELESSASED");
			}
			else if(keyCode.compareTo("RIGHT_SPACE_DOWN")==0)
			{
				writer.println("#%01%#"+"#%42%#"+player+"PVP"+"RIGHT RELESSASED");
			}	
		
		}
	}
}
