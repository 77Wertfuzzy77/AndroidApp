package support;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class InputMapper {
	private int Type = 0;
	private int Days = 0;
	private int Percent = 0;
	Map<String, Boolean> DataBase = new HashMap<String, Boolean>();
	private int inputs = 0;
	private Scanner databaseInput = new Scanner(System.in);
	private ChatManager chat;
	
	public InputMapper(ChatManager chat){
		this.chat = chat;
	}
	
	private String askQuestion(){
		Type = (int)(Math.random() * 4) + 1;
		Days = (int)(Math.random() * 7);
		Percent = (int)(Math.random() * 100);
		return ("If you had a " + getType(Type) +
				" due in " + Days +  " days, that you did about " +
				Percent + " percent, would you do it Today?");
	}
	
	public void firstInitialize(){
		chat.print("Would you like to make a customizable Database, or would you like to use the dafault?\nCustom/Default: ");
		String choice = databaseInput.next().toLowerCase();
		if(choice.equals("custom") || choice.equals("default"))
		switch(choice){
		case "custom": chat.firstOpen();
						while(inputs < 10) MakeDatabase();
						chat.afterCustomInitilaize();
						break;
		case "default": initilaizeDefault();
						chat.afterDefaultInitilaize();
		}
		else throw new InvalidInputException("You Must Pick Custom or Default");
	}
	
	public void additionalQuestions(int questions){
		for(int i = 0; i < questions; i++)
			MakeDatabase();
		
	}
	
	private Map<String, Boolean> makeDefaultDatabase(){
		Map<String, Boolean> def = new HashMap<String, Boolean>();
		//Days, Type, Percent
		def.put(toCode(0,1,30), true);
		def.put(toCode(0,2,0), true);
		
		def.put(toCode(1,2,70), true);
		def.put(toCode(2,2,25), true);
		def.put(toCode(3,2,0), true);
		
		def.put(toCode(3,3,50), true);
		def.put(toCode(1,3,0), true);
		
		def.put(toCode(1,4,60), true);
		def.put(toCode(2,4,45), true);
		def.put(toCode(3,4,20), true);
		def.put(toCode(4,4,0), true);
		
		def.put(toCode(1,5,85), true);
		def.put(toCode(2,5,70), true);
		def.put(toCode(3,5,45), true);
		def.put(toCode(4,5,30), true);
		def.put(toCode(5,5,10), true);
		def.put(toCode(6,5,0), true);
		
		return def;
	}
	
	private String toCode(int a, int b, int c){
		String X = "X";
		return a+X+b+X+c;
	}
	
	public void initilaizeDefault(){
		addOtherDatabase(makeDefaultDatabase());
	}
	
	public void addOtherDatabase(Map<String, Boolean> other){
		Set<String> set = other.keySet();
		Iterator<String> iter = set.iterator();
		while (iter.hasNext()){
			String curString = iter.next();
			String[] array = curString.split("X");
			addToDatabase(Integer.parseInt(array[0]), Integer.parseInt(array[1]), Integer.parseInt(array[2]));
		}
		
	}
	
	public void MakeDatabase(){
		System.out.println(askQuestion());
		switch(databaseInput.next().toLowerCase()){
		case "y": addToDatabase(Days, Type, Percent); inputs++; break;
		}
	}
	
	public void addToDatabase(int d, int t, int p){
		for(int a = 0; a <= d; a++){ //For every Day from today to that Day
			for(int b = t; b <= 5; b++){ //For Every Type that has a bigger Priority
				for(int c = 0; c <= p; c++){ // For every Assignment that is less done
					//System.out.println("Adding: " + "X"+a+"X"+b+"X"+c);
					DataBase.put(toCode(a,b,c), true);
				}
			}
		}
		}
	
	public Boolean query(int a, int b, int c){
		//System.out.println("Looking for: " + "X"+a+"X"+b+"X"+c);
		if(DataBase.get(toCode(a,b,c)) != null)
			return DataBase.get(toCode(a,b,c));
		else
			return false;
			
	}
	
	private String getType(int num){
		switch(num){
		case 1: return "Extra Credit";
		case 2: return "Homework";
		case 3: return "Major Homework";
		case 4: return "Project";
		case 5: return "Exam";
		}
		return "";
	}
}
