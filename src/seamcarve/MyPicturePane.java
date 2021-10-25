package seamcarve;

import javafx.scene.layout.BorderPane;
import support.seamcarve.*;
import javafx.scene.paint.Color;


/**
 * This class is your seam carving picture pane.  It is a subclass of PicturePane,
 * an abstract class that takes care of all the drawing, displaying, carving, and
 * updating of seams and images for you.  Your job is to override the abstract
 * method of PicturePane that actually finds the lowest cost seam through
 * the image.
 *
 * See method comments and handouts for specifics on the steps of the seam carving algorithm.
 *
 *
 * @version 01/17/2019
 */

public class MyPicturePane extends PicturePane {



	/**
	 * The constructor accepts an image filename as a String and passes
	 * it to the superclass for displaying and manipulation.
	 *
	 * @param pane
	 * @param filename
	 */
	public MyPicturePane(BorderPane pane, String filename) {
		super(pane, filename);

	}


	/**
	 * In this method, you'll implement the dynamic programming algorithm
	 * that you learned on the first day of class to find the lowest cost seam from the top
	 * of the image to the bottom. BEFORE YOU START make sure you fully understand how the algorithm works
	 * and what it's doing.
	 * See the handout for some helpful resources and use hours/piazza to clarify conceptual blocks
	 * before you attempt to write code.
	 *
	 * This method returns an array of ints that represents a seam.  This size of this array
	 * is the height of the image.  Each entry of the seam array corresponds to one row of the
	 * image.  The data in each entry should be the x coordinate of the seam in this row.
	 * For example, given the below "image" where s is a seam pixel and - is a non-seam pixel
	 *
	 * - s - -
	 * s - - -
	 * - s - -
	 * - - s -
	 *
	 *
	 * the following code will properly return a seam:
	 *
	 * int[] currSeam = new int[4];
	 * currSeam[0] = 1;
	 * currSeam[1] = 0;
	 * currSeam[2] = 1;
	 * currSeam[3] = 2;
	 * return currSeam;
	 *
	 *
	 * This method is protected so it is accessible to the class MyPicturePane and is not
	 * accessible to other classes. PLEASE DO NOT CHANGE THIS!
	 *
	 * @return the lowest cost seam of the current image
 	 */
	protected int[] findLowestCostSeam() {
		int[][] vals = new int[this.getPicHeight()][this.getPicWidth()]; // energy-importance array
		int[][] cost = new int[this.getPicHeight()][this.getPicWidth()]; // cost of each path array
		int[][] dirs = new int[this.getPicHeight()][this.getPicWidth()]; // array of directions
		int[] lowestSeam = new int[this.getPicHeight()]; // indexes of lowest-cost seam

		// calculate importance array
		for (int i = 0; i < this.getPicHeight(); i++) {
			for (int j = 0; j < this.getPicWidth(); j++) {
				int importance = calculateImportance(i, j);
				vals[i][j] = importance;
			}
		}

		// calculate cost and dirs arrays
		for (int j = 0; j < this.getPicWidth(); j++) { // fill in cost bottom row
			cost[this.getPicHeight() - 1][j] = vals[this.getPicHeight() - 1][j];
		}
		for (int i = this.getPicHeight() - 2; i >= 0; i--) { // fill in rest of cost and dirs (dirs w/out bottom)
			for (int j = 0; j < this.getPicWidth(); j++) {
				this.fillCostDirs(i, j, cost, dirs, vals);
			}
		}

		// find least-cost seam
		int indexSmallest = 0; // index of smallest-value cell in specified row of cost array
		for (int j = 0; j < this.getPicWidth();j++) { // find the smallest-value cell in top row
			if (cost[0][j] < cost[0][indexSmallest]) {
				indexSmallest = j;
			}
		}
		lowestSeam[0] = indexSmallest;

		for (int i = 1; i < this.getPicHeight(); i++) { // find smallest-value cell of neighboring 3 cells below
			if (dirs[i-1][lowestSeam[i-1]] == -1) {
				lowestSeam[i] = lowestSeam[i-1] - 1;
			} else if (dirs[i-1][lowestSeam[i-1]] == 1) {
				lowestSeam[i] = lowestSeam[i-1] + 1;
			} else {
				lowestSeam[i] = lowestSeam[i-1];
			}
		}

		return lowestSeam;
	}

	// calculate importance value of a particular pixel
	private int calculateImportance(int i, int j) {
		Color pixel = this.getPixelColor(i, j);
		int importance = 0;

		if (i != 0) { // not the top row
			Color above = this.getPixelColor(i - 1, j);
			importance += Math.abs(this.getColorRed(pixel) - this.getColorRed(above));
			importance += Math.abs(this.getColorGreen(pixel) - this.getColorGreen(above));
			importance += Math.abs(this.getColorBlue(pixel) - this.getColorBlue(above));
		}
		if (j != 0) { // not the first column
			Color left = this.getPixelColor(i, j - 1);
			importance += Math.abs(this.getColorRed(pixel) - this.getColorRed(left));
			importance += Math.abs(this.getColorGreen(pixel) - this.getColorGreen(left));
			importance += Math.abs(this.getColorBlue(pixel) - this.getColorBlue(left));
		}
		if (i != this.getPicHeight() - 1) { // not the bottom row
			Color below = this.getPixelColor(i + 1, j);
			importance += Math.abs(this.getColorRed(pixel) - this.getColorRed(below));
			importance += Math.abs(this.getColorGreen(pixel) - this.getColorGreen(below));
			importance += Math.abs(this.getColorBlue(pixel) - this.getColorBlue(below));
		}
		if (j != this.getPicWidth() - 1) { // not the last column
			Color right = this.getPixelColor(i, j + 1);
			importance += Math.abs(this.getColorRed(pixel) - this.getColorRed(right));
			importance += Math.abs(this.getColorGreen(pixel) - this.getColorGreen(right));
			importance += Math.abs(this.getColorBlue(pixel) - this.getColorBlue(right));
		}

		return importance;
	}

	// fill in the cost and dirs arrays
	private void fillCostDirs(int i, int j, int[][] cost, int[][] dirs, int[][] vals) {
		if (j == 0) { // if first column
			cost[i][j] = vals[i][j] + Math.min(cost[i+1][j], cost[i+1][j+1]);
			if (cost[i+1][j] < cost[i+1][j+1]) {
				dirs[i][j] = 0;
			} else {
				dirs[i][j] = 1;
			}
		} else if (j == this.getPicWidth() - 1) { // if last column
			cost[i][j] = vals[i][j] + Math.min(cost[i+1][j], cost[i+1][j-1]);
			if (cost[i+1][j] < cost[i+1][j-1]) {
				dirs[i][j] = 0;
			} else {
				dirs[i][j] = -1;
			}
		} else { // everything else
			int min = Math.min(cost[i+1][j+1], cost[i+1][j-1]);
			min = Math.min(min, cost[i+1][j]);
			cost[i][j] = vals[i][j] + min;
			if (min == cost[i+1][j-1]) {
				dirs[i][j] = -1;
			} else if (min == cost[i+1][j+1]) {
				dirs[i][j] = 1;
			} else {
				dirs[i][j] = 0;
			}
		}
	}
}
