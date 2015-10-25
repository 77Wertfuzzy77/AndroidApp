package userInterface;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import main.Assignment;
import main.AssignmentList;
import support.AssignmentComparator;
import support.ChatManager;
import support.InputMapper;
import support.InvalidInputException;

public class MainCommandLine {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		String fileBegin = "C:\\Users\\Nikolai\\workspace\\HackUMass\\bin\\";
		String mainHelp = 
						  "Print: Prints the Current Assignment List\n"
						+ "Today: Prints what needs to be done Today"
						+ "Add: Adds a new Assignment\n"
						+ "Help: Displays this Menu\n"
						+ "Close: Closes the App\n"
						+ "Database: Allows you to Update your Database";

		AssignmentList AList =  new AssignmentList(new AssignmentComparator());
		FileReader reader = new FileReader(fileBegin + "RealAssignments");
		Scanner input = new Scanner(reader);
		ChatManager chat = new ChatManager("Assignment Planner");
		InputMapper map = new InputMapper(chat);

		OpenApp(map, chat);

		String inputString;
		boolean keepGoing = true;
		while(keepGoing){
			inputString = input.nextLine().toLowerCase();
			chat.print(inputString);
			switch(inputString){
			case "help": chat.help(mainHelp); break;
			case "print": chat.printList(AList.getList()); break;
			case "today": chat.todayAssignmentList(AList.getPriorityList(map)); break;
			case "close": keepGoing = false; break;
			case "add": { //EDITED
				Assignment e = add(input);
				if(e != null){
					AList.add(e); 
				}
				break;
			}
			case "database": map.additionalQuestions(5);
			default: break;
			}
		}
		input.close();
		System.out.close();
	}

	public static Assignment add(Scanner addInput){
		System.out.println("Please input the Assignment Name: ");
		String Name = addInput.nextLine();
		System.out.println(Name);

		System.out.println("Type (1 = Extra Credit, 2 = Homework, 3 = Major Homework, 4 = Project, 5 = Exam: ");
		int Type = addInput.nextInt();
		System.out.println(Type);

		System.out.println("Days (0 = Today, 1 = Tomorrow, 2 = Two days from now, etc. : ");
		int Days = addInput.nextInt();
		System.out.println(Days);

		System.out.println("Percent Completed: ");
		int Percent = addInput.nextInt();
		System.out.println(Percent);
		try { //EDITED
			if(Days < 0 || Type < 1 || Type > 5 || Percent > 100 || Percent < 0) throw new InvalidInputException("Invalid Inputs to Creation of Assignment");
		}
		catch(InvalidInputException e){
			System.err.println("Invalid Inputs to Creation of Assignment");
			return null;
		}
		return new Assignment(Name, Days, Type, Percent);
	}

	public static void OpenApp(InputMapper map, ChatManager chat){
		map.firstInitialize();
		chat.firstOpen();
		chat.mainPage();
	}

}
