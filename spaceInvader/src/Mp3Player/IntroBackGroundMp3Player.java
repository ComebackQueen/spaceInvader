package Mp3Player;

import Manager.SceneManager;
import Manager.SoundManager;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;

public class IntroBackGroundMp3Player extends RepeatMp3Player {
	public static boolean soundEnd=false;
	public IntroBackGroundMp3Player(String path) {
		super(path);
		
		

	}

	@Override
	public void Play()
	{
		
		//System.out.println("메인화면 음악 시작");
		final Thread th = new Thread(new Runnable() {
            public void run() {
            	PlayInternal();
            }});
        th.setDaemon(true);
        th.start();
		
	}
	
	public void PlayInternal()
	{
	AudioDevice out=null;
	try {
		//System.out.println("메소드 트라이 실행"); 한번들어감
		out = FactoryRegistry.systemRegistry().createAudioDevice();
		out.open(decoder);
	} catch (JavaLayerException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	// System.out.println("메소드 트라이 다음"); 한번들어감
	if(IsInvalid()||out==null||!out.isOpen())
	{
		// System.out.println("메소드 아웃"); 안들어감
	    out.close();
		out=null;
	
		return;
	}
	try 
	{
		while(true)
		{
	
	if(SoundManager.GetBackGroundMusicFlag()&&SceneManager.GetCurSceneIndex()==0)	
		
	{	
		//System.out.println("메소드 사운드1"); 한번들어감
		for(int i=0;i<size;i++)
	
	{	
		if(!(SoundManager.GetBackGroundMusicFlag()&&SceneManager.GetCurSceneIndex()==0))
		{
			i=0;
			out.flush();
			//System.out.println("메소드 사운드2"); 안들어감
		}
		out.write(samples.get(i).getBuffer(),0,samples.get(i).getSize());
		//System.out.println("메소드 사운드3"); 계속 나옴
		if(soundEnd)
			return;

		
	}
	out.flush();
	// System.out.println("메소드 사운드4"); 안나옴
	}
	// System.out.println("메인화면 사운드 출력중"); 안나옴	

		}
		
	}
	catch(JavaLayerException e)
	{
		
		out.close();
		out=null;
	
	}
	
	}
	public static void mystop(){
		soundEnd=true;
	}
	public static void reSoundEnd(){
		soundEnd =false;
	}
	
}
