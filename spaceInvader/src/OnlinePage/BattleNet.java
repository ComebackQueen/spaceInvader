package OnlinePage;

public class BattleNet {
	String ID, NikName;
	int index;
	
	public void setId(String id){ this.ID=id; }
	public String getID() { return ID; }
	
	public void setNikName(String NikName) { this.NikName=NikName; }
	public String getNikName(){ return NikName; }
	
	public void setIndex(int index){ this.index+=index; }
	public int getIndex(){ return index; }
	
	public String toString(){ return String.format("ID : "+ID+"\n NIKNAME : "+NikName+"\n Index : "+index); } //확인하고 싶을때
}