package Mp3Player;

import Manager.SceneManager;
import Manager.SoundManager;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;

public class InGameBackGroundMp3Player extends RepeatMp3Player {
	public static Thread th=null;
	public InGameBackGroundMp3Player(String path) {
		super(path);
	
		
		
		
	}

	@Override
	public void Play()
	{
		
		
		th = new Thread(new Runnable() {
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
		out = FactoryRegistry.systemRegistry().createAudioDevice();
		out.open(decoder);
	} catch (JavaLayerException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	if(IsInvalid()||out==null||!out.isOpen())
	{
		
	    out.close();
		out=null;
	
		return;
	}
	try 
	{
		while(true)
		{
	
	if(SoundManager.GetBackGroundMusicFlag()&&SceneManager.GetCurSceneIndex()==1)	
		
	{
		for(int i=0;i<size;i++)
	
	{	
		if(!(SoundManager.GetBackGroundMusicFlag()&&SceneManager.GetCurSceneIndex()==1))
		{
			i=0;
			out.flush();
			
		}
		out.write(samples.get(i).getBuffer(),0,samples.get(i).getSize());
	}
	out.flush();
	}
		
		}
		
	}
	catch(JavaLayerException e)
	{
		
		out.close();
		out=null;
	
	}
	
	}
	public static void stop(){
		th.suspend();
	}
}
