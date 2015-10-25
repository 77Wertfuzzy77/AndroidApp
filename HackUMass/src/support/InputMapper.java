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
	private Map<String, Boolean> DataBase = new HashMap<String, Boolean>();
	private int inputs = 0;
	private Scanner databaseInput = new Scanner(System.in);
	private ChatManager chat;
	
	public InputMapper(ChatManager chat){
		this.chat = chat;
	}
	
	private String askQuestion(){ // EDITED
		Type = (int)(Math.random() * 5) + 1; // 1-5
		Days = (int)(Math.random() * 8); // 0-7
		Percent = (int)(Math.random() * 101); // 0-100
		return ("If you had a " + getType(Type) +
				" due in " + Days +  " days, that you did about " +
				Percent + " percent, would you do it Today?");
	}
	
	public void firstInitialize(){ //EDITED
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
		else {
			chat.error("Invalid Choice, ReInitilaizing...");
			firstInitialize();
		}
	}
	
	public void additionalQuestions(int questions){
		for(int i = 0; i < questions; i++)
			MakeDatabase();
		
	}
	
	private Map<String, Boolean> makeDefaultDatabase(){ //EDITED
		Map<String, Boolean> def = new HashMap<String, Boolean>();
		//Days, Type, Percent
		
		//Extra Credit
		def.put(toCode(0,1,100), true);
		def.put(toCode(1,1,0), true);
		
		//Homework
		def.put(toCode(0,2,100), true);
		def.put(toCode(1,2,66), true);
		def.put(toCode(2,2,33), true);
		def.put(toCode(3,2,0), true);
		
		//Major Homework
		def.put(toCode(0,3,100), true);
		def.put(toCode(1,3,85), true);
		def.put(toCode(2,3,60), true);
		def.put(toCode(3,3,35), true);
		def.put(toCode(4,3,0), true);
		
		
		//Projects
		def.put(toCode(0,4,100), true);
		def.put(toCode(1,4,75), true);
		def.put(toCode(2,4,50), true);
		def.put(toCode(3,4,25), true);
		def.put(toCode(4,4,0), true);
		
		
		//Exams
		def.put(toCode(0,5,100), true);
		def.put(toCode(1,5,83), true);
		def.put(toCode(2,5,66), true);
		def.put(toCode(3,5,50), true);
		def.put(toCode(4,5,33), true);
		def.put(toCode(5,5,17), true);
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
	
	public void MakeDatabase(){ //EDITED
		System.out.println(askQuestion());
		switch(databaseInput.next().toLowerCase()){
		case "y": addToDatabase(Days, Type, Percent); inputs++; break;
		case "n": removeFromDatabase(Days, Type, Percent); break;
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
	
	private void removeFromDatabase(int d, int t, int p){ //NEW
		for(int a = d; a <= 7; a++){ //For today to day 7
			for(int b = t; b >= 0; b--){ //For Every Type that has a Lesser Priority
				for(int c = p; c <= 100; c++){ // For every Assignment that is more done
					DataBase.put(toCode(d,b,c), false);
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
