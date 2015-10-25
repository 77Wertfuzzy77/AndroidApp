package support;

import main.Assignment;

public class ChatManager {
	private final String Name;
	public ChatManager(String Name){
		this.Name = Name;
	}
	
	public void firstOpen(){
		print(
		"Welcome to " + Name + ".\n"
		);
	}
	
	public void mainPage(){
		print("=-=-=-=-=-="+Name+"=-=-=-=-=-=\n"
				+ "The Database has been created, please add your current assignments using the add button,\n"
				+ "or press Help for more Options");
	}
	
	public void afterCustomInitilaize(){
		print("Thank you for Answering, your personal Database is being created.");
	}
	
	public void afterDefaultInitilaize(){
		print("Default Database being created.");
	}
	
	public void help(String helpMessage){
		print(helpMessage);
	}
	
	public void error(String errorMessage){
		System.err.print(errorMessage);
	}
	
	public void todayAssignmentList(Tuple<Assignment[],Integer> tup){
		if(tup.second == 0){print("There are No Assignments to Do Today.");}
		else {
			print("Assignments that Should we worked on today.");
			for(int i = 0; i < tup.second; i++) {
				print(tup.first[i].toString() + "\n");
			}
		}
		
	}
	
	public void printList(Assignment[] list){
		print("Current Assignments: ");
		for(int i = 0; i < list.length; i++) {
			print(list[i].toString() + "\n");
		}
	}
	
	public void print(String toPrint){
		System.out.println(toPrint);
	}
	
	
}
