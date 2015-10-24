package main;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import support.InputMapper;
import support.Tuple;


public class AssignmentList{
	
	public AssignmentList(Comparator<Assignment> comp){
		
	}
	Set<Assignment> main = new TreeSet<Assignment>();
	
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
			if(map.query(curAssign.getDays(), curAssign.getType(), curAssign.getPercent()) == true) {
				list[counter] = curAssign;
				counter++;
			}
		}
				
		return new Tuple<Assignment[], Integer>(list, counter);
	}
	
	public Assignment[] getList(){
		Assignment[] ret = new Assignment[main.size()];
		return main.toArray(ret);
	}
}
