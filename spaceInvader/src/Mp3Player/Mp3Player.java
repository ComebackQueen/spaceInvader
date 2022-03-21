package Mp3Player;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javazoom.jl.decoder.Bitstream;
import javazoom.jl.decoder.BitstreamException;
import javazoom.jl.decoder.Decoder;
import javazoom.jl.decoder.DecoderException;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.decoder.SampleBuffer;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.FactoryRegistry;

public class Mp3Player {

	static class Sample{
		private short[] buffer;
		private int size;
		
		
		
		public Sample(short[] buf, int s)
		{
			
			buffer= buf.clone();
			size=s;
		}
		public int getSize()
		{
			return size;
		}
		public short[] getBuffer()
		{
			return buffer;
		}
		
		
		
		
		
	}
	
	protected Decoder decoder;

	protected ArrayList<Sample> samples;
	protected int size;
	
	
	
	
public Mp3Player(String path)
{
	Open(path);
	
	
	
}
	public  boolean IsInvalid()
	{
		return (decoder==null||samples==null);
		
	
	
	}
	private boolean GetSamples(String path)
	{
		
		if(IsInvalid())
			return false;
		try {
		Header header;
		SampleBuffer sb;
		FileInputStream in=new FileInputStream(path);
		Bitstream bitstream=new Bitstream(in);
		if((header=bitstream.readFrame())==null)
			return false;
		while(header!=null)
		{sb=(SampleBuffer)decoder.decodeFrame(header,bitstream);
		samples.add(new Sample(sb.getBuffer(),sb.getBufferLength()));
		size++;
		bitstream.closeFrame();
        header=bitstream.readFrame();
			
		}
		}
		catch(FileNotFoundException e)
		{
			return false;
		}
		catch(BitstreamException e)
		{
			return false;
		}
		catch(DecoderException e)
		{
			return false;
		}
		return true;
	}
	
	public void Close()
	{
		
		size=0;
		samples=null;
		decoder=null;
	}
	
	private boolean Open(String path)
	{
	Close();
	decoder=new Decoder();
	
	samples=new ArrayList<Sample>();
	size=0;
	
	GetSamples(path);
		return true;
	}
	
	
	
	public  void Play()
	{
	}
	
	public void Stop()
	{
	
	}
	

	
	
	
	
}
