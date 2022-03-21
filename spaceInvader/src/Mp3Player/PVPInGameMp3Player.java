
package Mp3Player;

import Manager.SoundManager;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;

public class PVPInGameMp3Player extends RepeatMp3Player {
	public static boolean soundEnd=false;
	public PVPInGameMp3Player(String path) {
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
		
		if(IsInvalid()||out==null||!out.isOpen())
		{
			out.close();
			out=null;
		
			return;

		}	
		try {
		
			
			
		for(int i=0;i<size;i++)
		{	
			
				
				
			
			out.write(samples.get(i).getBuffer(),0,samples.get(i).getSize());
			if(soundEnd)
				return;
			
		}
		
		out.flush();
		
			
			}
		catch(JavaLayerException e)
		{
			
		}
		
		
		
	}
	public static void mystop(){

		soundEnd=true;
		
	}
	public static void reSoundEnd(){
		soundEnd =false;
	}
}
