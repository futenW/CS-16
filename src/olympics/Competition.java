package olympics;

/**
 * This class models a competition between Athletes. Given a qualifying time,
 * it returns the names of all Athletes whose race times are less than the qualifying time.
 */
public class Competition {
	// Default qualifying time.
	private static double _qualifyingTime = 10.00;
	
	/* Input: array of athlete names, array of athlete times
	 * Output: array of Athletes
	 * Purpose: convert array of names and times into an array of Athlete objects
	 */
	public static Athlete[] createAthletes(String[] athleteNames, double[] athleteTimes) {
		int numAthletes = athleteNames.length;
		Athlete[] athletes = new Athlete[numAthletes];
		
		for (int i = 0; i < numAthletes; i++) {
			Athlete a = new Athlete(athleteNames[i], athleteTimes[i]);
			athletes[i] = a;
		}
		return athletes;
	}
	
	
	/* Input: array of Athletes
	 * Output: array of Athlete names
	 * Purpose: return list of qualifying Athlete names
	 */
	public static Athlete[] findQualifyingAthletes(Athlete[] athletes) {
		int numAthletes = athletes.length;
		int numQualified = 0;
		for (int i = 0; i < numAthletes; i++) {
			if (athletes[i].getTime() < _qualifyingTime) {
				numQualified++;
			}
		}
		
		Athlete[] qualifiedAthletes = new Athlete[numQualified];
		int qualifiedCounter = 0;
		for (int i = 0; i < numAthletes; i++) {
			if (athletes[i].getTime() < _qualifyingTime) {
				qualifiedAthletes[qualifiedCounter] = athletes[i];
				qualifiedCounter++;
			}
		}
		
		return qualifiedAthletes;
	}
	
	public static void main(String[] args) {
		// If specified, set _qualifyingTime to command-line argument.
		if (args.length > 0) {
			_qualifyingTime = Double.parseDouble(args[0]);
		}
		
		// Initialize array of Athletes.
		String[] athleteNames = new String[] {"Amy", "Lisa", "Lucy", "Lionel"};
		double[] athleteTimes = new double[] {9.89, 10.96, 10.01, 9.92};
		Athlete[] athletes = createAthletes(athleteNames, athleteTimes);
		
		// Find qualifying Athletes and print out their names.
		Athlete[] qualifyingAthletes = findQualifyingAthletes(athletes);
		for (int i = 0; i < qualifyingAthletes.length; i++) {
			System.out.println(qualifyingAthletes[i].getName());
		}
	}
}
