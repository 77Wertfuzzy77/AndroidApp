package main;

import support.InvalidInputException;

public class Assignment implements Comparable<Assignment> {
	private String Name;
	private int Days, Type, Percent;
	public Assignment(String Name, int Days, int Type, int Percent){
		if(Days < 0 || Type < 1 || Type > 5 || Percent > 100 || Percent < 0) throw new InvalidInputException("Invalid Inputs to Creation of Assignment");
		this.Name = Name;
		this.Days = Days;
		this.Type = Type;
		this.Percent = Percent;
	}
	
	public int getDays(){
		return Days;
	}
	
	public int getType(){
		return Type;
	}
	
	public int getPercent(){
		return Percent;
	}
	
	public void setDays(int input){
		Days = input;
	}
	
	public void setType(int input){
		Type = input;
	}
	
	public void setPercent(int input){
		Percent = input;
	}
	
	public int getPriority(){
		return Type * 11 + 
		-Days * 11 + 
		-Percent / 8;
	}
	
	public String toActualType(){
		switch(Type){
		case 1: return "Extra Credit";
		case 2: return "Homework";
		case 3: return "Major Homework";
		case 4: return "Project";
		case 5: return "Exam";
		}
		return "";
		
	}
	
	public String toString(){
		return ("Name: " + Name + "\nDays: " + Days + "\nType: " + toActualType() + "\nPercent: " + Percent);
	}

	@Override
	public int compareTo(Assignment o) {
		// TODO Auto-generated method stub
		if(this.getPriority() - o.getPriority() > 0) return 1; else return 0;
	}
}
