package OnlinePage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Button.Button;

public class Ranking extends JPanel{
   BufferedImage img = null; // 배 경 
   public Button pvp = new Button("OnlineImage/pvp.png", 0, 20, 290, 170);
   public Button teamPlay = new Button("OnlineImage/teamPlay.png", 0, 180, 290, 170);
   public Button ranking = new Button("OnlineImage/ranking.png", 0, 350, 290, 170);
   public Button exit = new Button("OnlineImage/exit.png", 0, 520, 290, 170);
   public JButton pvpRanking = new JButton("PVP랭킹");
   public JButton coopRanking = new JButton("협동모드랭킹");
   public JTextArea ta = new JTextArea();
   public JScrollPane scrollPane = new JScrollPane(ta);
   public boolean PanelOnOff=false;
   public Ranking(){
      try {
         img=ImageIO.read(new File("OnlineImage/ranking.png.jpg")); // 배경이미
      } catch (IOException e) {
         System.out.println("메인화면 배경 이미지를 불러올 수 없습니다. "); //배경이미지를 못 불러올
         System.exit(0);
      }
      setRanking();
      setLayout(null);
      
      add(pvp);
      add(teamPlay);
      add(ranking);
      add(exit);
      add(scrollPane);
      add(pvpRanking);
      add(coopRanking);
      pvpRanking.setBounds(305, 195, 100, 30);
      coopRanking.setBounds(405, 195, 120, 30);
   }
   public void setRanking(){
      scrollPane.setBounds(305, 235, 955, 450);
      scrollPane.setOpaque(false);
      scrollPane.getViewport().setOpaque(false);
      scrollPane.setBorder(null);
      scrollPane.setForeground(Color.WHITE);
      
      ta.setOpaque(false);
      ta.setEditable(false);
      ta.setForeground(Color.WHITE);
      ta.setFont(new Font("HY 견고딕", Font.ITALIC, 40));
   }

   public void paintComponent(Graphics g) {
      super.paintComponent(g);
   
      g.drawImage(img, 0, 0, null);
      

      setOpaque(false);
   }
}