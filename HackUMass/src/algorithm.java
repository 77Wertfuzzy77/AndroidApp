
public class algorithm {
	public boolean testNumber(int i, int d, int p){
		printData(i,d,p);
		if(getPriority(i,d,p) > 0) 
			return true;
		else 
			return false;
			
	}
	
	private void printData(int Importance, int Days, int Percent){
		System.out.println("New Task\nDays: " + Days + "\nImportance: " + Importance + "\nPercent: " + Percent + "\nPriority: " + getPriority(Importance, Days, Percent));
	}
	
	private int getPriority(int i, int d, int p){
		return i * 11 + 
		-d * 11 + 
		-p / 8;
	}
}
