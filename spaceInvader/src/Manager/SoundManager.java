package Manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import Mp3Player.*;
import javazoom.jl.decoder.*;
import javazoom.jl.player.*;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

//�̱��� ���� �Ŵ���
public class SoundManager {
	
	private static SoundManager instance=null;
 
	private static boolean BackGroundMusicFlag=true;
	private static boolean EffectMusicFlag=true;
	
	
	public static IntroBackGroundMp3Player IntroBackGroundMp3Player=null;
	public static InGameBackGroundMp3Player IngameBackGroundMp3Player=null;
	public static BattleNetMp3Player BattleNetMp3Player =null;
	public static EffectMp3Player ButtonEffectMp3Player=null;
	public static EffectMp3Player EnemyHitEffectMp3Player=null;
	public static EffectMp3Player EnemyBulletEffectMp3Player=null;
	public static PVPInGameMp3Player PVPInGameMp3Player=null;
	public static COPInGameMp3Player COPInGameMp3Player =null;
	
	public static GameOverMp3Player GameOverMp3Player=null;
	public static PlayerBulletMp3Player PlayerBulletMp3Player=null;
	
	public static SoundManager getInstance()
	{
		if(instance==null)
		{
			instance=new SoundManager();// ���� �Ŵ��� ��ü�� ���ϼ� ����
			
		
		}
		return instance;
	}
	
	public static void InitializeSoundResource()
	{
		IntroBackGroundMp3Player=new IntroBackGroundMp3Player("LoginScreenLoop.mp3");
		
		IngameBackGroundMp3Player=new InGameBackGroundMp3Player("2.mp3");
		BattleNetMp3Player = new BattleNetMp3Player("Wing.mp3");
		ButtonEffectMp3Player=new EffectMp3Player("002 - Extend ��ư��.mp3");
		EnemyHitEffectMp3Player=new EffectMp3Player("006 Hit on Zako - ����ü ������.mp3");
		EnemyBulletEffectMp3Player=new EffectMp3Player("005 Shot - �Ѿ� �߻���.mp3");
		GameOverMp3Player=new GameOverMp3Player("014 Captive is shot - ���� ����.mp3");
		PlayerBulletMp3Player=new PlayerBulletMp3Player("002-bullet_sound-�Ѿ�-�߻���.mp3");
		PVPInGameMp3Player = new PVPInGameMp3Player("����2.mp3");
		COPInGameMp3Player = new COPInGameMp3Player("���.mp3");
	}
	
	
	
	
	public static boolean GetBackGroundMusicFlag()
	{
		return BackGroundMusicFlag;
	}
	public static void SetBackGroundMusicFlag(boolean val)
	{
		BackGroundMusicFlag=val;
	}
	

	public static boolean GetEffectMusicFlag()
	{
		return EffectMusicFlag;
	}
	public static void SetEffectMusicFlag(boolean val)
	{
		EffectMusicFlag=val;
	}


}


