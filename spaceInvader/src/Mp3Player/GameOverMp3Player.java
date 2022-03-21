package Mp3Player;

import Manager.SceneManager;
import Manager.SoundManager;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;

public class GameOverMp3Player extends Mp3Player{

	public GameOverMp3Player(String path) {
		super(path);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void Play()
	{
		
		
		if(SoundManager.GetEffectMusicFlag())
		{

			final Thread t = new Thread(new Runnable() {
	            public void run() {
	            	PlayInternal();
	            }});
	        t.setDaemon(true);
	        t.start();
		}
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
		
		if(IsInvalid())
		{
			
			return;
		}	
		try {
		
			
			
		for(int i=0;i<size;i++)
		{	
			
				
				
			if(SceneManager.GetCurSceneIndex()!=2)
			{out.flush();
			out.close();
			out=null;
			
				return;
			}
			out.write(samples.get(i).getBuffer(),0,samples.get(i).getSize());
		}
		out.flush();
		
			
			}
		catch(JavaLayerException e)
		{
			
		}
		
		
		
	}
	
}
