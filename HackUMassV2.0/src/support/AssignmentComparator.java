package support;

import java.util.Comparator;
import main.Assignment;


public class AssignmentComparator implements Comparator<Assignment> {

	@Override
	public int compare(Assignment o1, Assignment o2) {
		// TODO Auto-generated method stub
		if(o1.getPriority() - o2.getPriority() > 0) return 1; else return 0;
	}

}
