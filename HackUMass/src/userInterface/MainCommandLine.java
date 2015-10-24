package userInterface;

import java.util.Scanner;

import main.Assignment;
import main.AssignmentList;
import support.AssignmentComparator;
import support.ChatManager;
import support.InputMapper;

public class MainCommandLine {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String mainHelp = 
				"Print: Prints the Current Assignment List\n"
				+ "Today: Prints what needs to be done Today"
				+ "Add: Adds a new Assignment\n"
				+ "Help: Displays this Menu\n"
				+ "Close: Closes the App\n"
				+ "Database: Allows you to Update your Database";
		
		String toHelp = 
				"Invalid input. Please use the Help command.";
		
		
		AssignmentList AList =  new AssignmentList(new AssignmentComparator());
		Scanner input = new Scanner(System.in);
		ChatManager chat = new ChatManager("Assignment Planner");
		InputMapper map = new InputMapper(chat);
		
		OpenApp(map, chat);
		
		String inputString;
		boolean keepGoing = true;
		while(keepGoing){
			inputString = input.nextLine().toLowerCase();
			switch(inputString){
			case "help": chat.help(mainHelp); break;
			case "print": chat.printList(AList.getList()); break;
			case "today": chat.todayAssignmentList(AList.getPriorityList(map)); break;
			case "close": keepGoing = false; break;
			case "add": AList.add(add(input)); break;
			case "database": map.additionalQuestions(5);
			default: chat.error(toHelp); break;
			}
		}
		input.close();
		System.out.close();
	}
	
	public static Assignment add(Scanner addInput){
		System.out.println("Please input the Assignment Name: ");
		String Name = addInput.nextLine();
		
		System.out.println("Type (1 = Extra Credit, 2 = Homework, 3 = Major Homework, 4 = Project, 5 = Exam: ");
		int Type = addInput.nextInt();
		
		System.out.println("Days (0 = Today, 1 = Tomorrow, 2 = Two days from now, etc. : ");
		int Days = addInput.nextInt();
		
		System.out.println("Percent Completed: ");
		int Percent = addInput.nextInt();
		return new Assignment(Name, Days, Type, Percent);
	}
	
	public static void OpenApp(InputMapper map, ChatManager chat){
		map.firstInitialize();
		chat.firstOpen();
		chat.mainPage();
	}

}
