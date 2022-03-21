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
		
		//System.out.println("����ȭ�� ���� ����");
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
		//System.out.println("�޼ҵ� Ʈ���� ����"); �ѹ���
		out = FactoryRegistry.systemRegistry().createAudioDevice();
		out.open(decoder);
	} catch (JavaLayerException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	// System.out.println("�޼ҵ� Ʈ���� ����"); �ѹ���
	if(IsInvalid()||out==null||!out.isOpen())
	{
		// System.out.println("�޼ҵ� �ƿ�"); �ȵ�
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
		//System.out.println("�޼ҵ� ����1"); �ѹ���
		for(int i=0;i<size;i++)
	
	{	
		if(!(SoundManager.GetBackGroundMusicFlag()&&SceneManager.GetCurSceneIndex()==0))
		{
			i=0;
			out.flush();
			//System.out.println("�޼ҵ� ����2"); �ȵ�
		}
		out.write(samples.get(i).getBuffer(),0,samples.get(i).getSize());
		//System.out.println("�޼ҵ� ����3"); ��� ����
		if(soundEnd)
			return;

		
	}
	out.flush();
	// System.out.println("�޼ҵ� ����4"); �ȳ���
	}
	// System.out.println("����ȭ�� ���� �����"); �ȳ���	

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
