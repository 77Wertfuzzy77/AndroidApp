package main;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


import support.InputMapper;
import support.Tuple;


public class AssignmentList{
	Set<Assignment> main = new HashSet<Assignment>();
	
	public AssignmentList(Comparator<Assignment> comp){
		
	}
	
	public void add(Assignment toAdd){
		main.add(toAdd);
	}
	
	public Tuple<Assignment[],Integer> getPriorityList(InputMapper map){
		Assignment[] list = new Assignment[main.size()];
		Iterator<Assignment> iter = main.iterator();
		Assignment curAssign;
		int counter = 0;
		while (iter.hasNext()){
			curAssign =  iter.next();
			if(map.query(curAssign.getDays(), curAssign.getType(), curAssign.getPercent()) == true || curAssign.getDays() == 0) {
				list[counter] = curAssign;
				counter++;
			}
		}
				
		return new Tuple<Assignment[], Integer>(bubbleSort(list, counter), counter);
	}
	
	public Assignment[] getList(){
		Assignment[] ret = main.toArray(new Assignment[main.size()]);
		return bubbleSort(ret);
	}
	
	public Assignment[] bubbleSort(Assignment[] input){
		int num = input.length;
		Assignment temp;
		for(int i = 0; i < num; i++){
			for(int j = 1; j < (num - i); j++){
				if(input[j].compareTo(input[j-1]) > 0){
					temp = input[j-1];
					input[j-1] = input[j];
					input[j] = temp;
				}
			}
		}
		return input;
	}
	
	public Assignment[] bubbleSort(Assignment[] input, int length){
		int num = length;
		Assignment temp;
		for(int i = 0; i < num; i++){
			for(int j = 1; j < (num - i); j++){
				if(input[j].compareTo(input[j-1]) > 0){
					temp = input[j-1];
					input[j-1] = input[j];
					input[j] = temp;
				}	
			}
		}
		return input;
	}
}
