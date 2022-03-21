package Manager;

import Mp3Player.Mp3Player;

public class SceneManager {
	private static SceneManager instance=null;
	 
	private static int CurSceneIndex;
			
	public static SceneManager getInstance()
	{
		if(instance==null)
		{
			instance=new SceneManager();// ���� �Ŵ��� ��ü�� ���ϼ� ����
			
		
		}
		return instance;
	}
	
	public static int GetCurSceneIndex()
	{
		return CurSceneIndex;
	}
	public static void SetCurSeneIndex(int val)
	{
		CurSceneIndex=val;
	}
}
