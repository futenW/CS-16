package ranking;

import java.util.Arrays;

/**
 * This class models a competition between Vaulters. Given an initial ranking,
 * it returns the final ranking after adding an additional Vaulter.
 */
public class Ranking {
	
	private static Vaulter[] _currentRankings;
	
	/* Input: none
	 * Output: array of Vaulter objects
	 * Purpose: create hard-coded array of Vaulters to show initial ranking of players
	 */
	public static Vaulter[] setUpVaulters() {
		// Vaulters stored from best to worst rank.
		String[] vaulterNames = new String[] {"Amy", "Lisa", "Lucy", "Lionel"};
		String[] vaulterNations = new String[] {"USA", "USA", "India", "USA"};
		double[] bestVaultHeights = new double[] {5.6, 5.2,  5.1, 3.8};
		double[] worstVaultHeights = new double[] {5.4, 4.9, 4.6, 2.1};
		
		Vaulter[] vaulters = createVaulters(vaulterNames, vaulterNations);
		for (int i = 0; i < vaulters.length; i++) {
			vaulters[i].setBestHeight(bestVaultHeights[i]);
			vaulters[i].setWorstHeight(worstVaultHeights[i]);
			vaulters[i].setRank(i);
		}
		return vaulters;
	}
	
	
	/* Input: String array of vaulter names, String array of vaulter nations 
	 * Output: array of Vaulter objects
	 * Purpose: create array of Vaulter objects without their best and worst heights set
	 */
	public static Vaulter[] createVaulters(String[] vaulterNames, String[] vaulterNations) {
		int numVaulters = vaulterNames.length;
		Vaulter[] vaulters = new Vaulter[numVaulters];
		for (int i = 0; i < numVaulters; i++) {
			Vaulter v = new Vaulter(vaulterNames[i], vaulterNations[i]);
			vaulters[i] = v;
		}
		return vaulters;
	}
	
	/* Input: existing ranking of Vaulter objects, Vaulter object to add to ranking
	 * Output: array of Vaulter objects in order of their best height vaulted
	 * Purpose: add a new player to the ranking 
	 */
	public static Vaulter[] addVaulterToRankings(Vaulter[] oldRankings, Vaulter newVaulter) {
		int newNumVaulters = oldRankings.length + 1;
		Vaulter[] newRankings = new Vaulter[newNumVaulters];
		
		// Place old Vaulters into new ranking until the newVaulter's height is greater than another Vaulter's.
		int curVaulterIndex = 0;
		while ((curVaulterIndex < oldRankings.length) && 
				(newVaulter.getBestHeight() < oldRankings[curVaulterIndex].getBestHeight()) ) {
			newRankings[curVaulterIndex] = oldRankings[curVaulterIndex];
			curVaulterIndex++;
		}
		
		// Place newVaulter into the appropriate ranking.
		newVaulter.setRank(curVaulterIndex);
		newRankings[curVaulterIndex] = newVaulter;
		
		// Add the remaining players into the ranking.
		for (int i = curVaulterIndex + 1; i < newNumVaulters; i++) {
			newRankings[i] = oldRankings[i - 1];
			newRankings[i].setRank(i);
		}
		
		return newRankings;
	}
	
	public static void main(String[] args) {
		// Create existing rankings.
		
		System.out.println("Running competition...");
		
		_currentRankings = setUpVaulters();

		
		// Initialize new Vaulters.
		Vaulter seny = new Vaulter("Doug", "USA");
		seny.setBestHeight(5.2);
		seny.setWorstHeight(5.1);
		
		Vaulter rando = new Vaulter("Rando", "USA");
		rando.setBestHeight(2.2);
		rando.setWorstHeight(1.9);
		
		System.out.println("Adding vaulter to rankings...");
		
		// Add seny and rando to the rankings.
		_currentRankings = addVaulterToRankings(_currentRankings, seny);
		_currentRankings = addVaulterToRankings(_currentRankings, rando);
		
		System.out.println("Vaulter Rankings:");
		
		/* Print results. Expect:
		 * Amy
		 * Doug
		 * Lisa
		 * Lucy
		 * Lionel
		 */
		for (Vaulter i: _currentRankings) {
			// we add 1 to the rank set for each Vaulter because otherwise, the ranking would start at 0
			System.out.println("Rank " + (i.getRank() + 1) + ": " + i.getName()); 
		}	
	}
}
