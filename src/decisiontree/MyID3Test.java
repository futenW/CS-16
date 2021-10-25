package decisiontree;

import org.junit.Test;
import support.decisiontree.DataReader;
import support.decisiontree.DecisionTreeData;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import java.util.Arrays;

/**
 * This class can be used to test the functionality of your MyID3 implementation.
 * Use the Heap stencil and your heap tests as a guide!
 * 
 */

public class MyID3Test {

	/**
	 * test findBestAttribute() helper method
	 * find the best attribute of short-data-training.csv
	 */
	@Test
	public void findBestAttributeTest() {
		MyID3 id3 = new MyID3();
		DecisionTreeData shortData = DataReader.readFile("src/decisiontree/decisiontree-data/short-data-training.csv");

		assertThat(id3.findBestAttribute(shortData).getName(), is(" Pat"));
	}

	/**
	 * test calculateInfoGainTest() helper method
	 * calculate info gain of first attribute of short-data-training.csv
	 */
	@Test
	public void calculateInfoGainTest() {
		MyID3 id3 = new MyID3();
		DecisionTreeData shortData = DataReader.readFile("src/decisiontree/decisiontree-data/short-data-training.csv");

		assertThat(id3.calculateInfoGain(shortData.getAttributeList().get(0), shortData), is(0.0032289436203634114));
	}

	/**
	 * test calculateEntropy() helper method
	 * calculate entropy of short-data-training.csv set
	 */
	@Test
	public void calculateEntropyTest() {
		MyID3 id3 = new MyID3();
		DecisionTreeData shortData = DataReader.readFile("src/decisiontree/decisiontree-data/short-data-training.csv");

		assertThat(id3.calculateEntropy(shortData.getExamples(), shortData.getClassifications()), is(0.9544340029249649));
	}

	/**
	 * test log2() helper method
	 * calculate log2() of 1, 2, 3, and 1024
	 */
	@Test
	public void log2Ntest() {
		MyID3 id3 = new MyID3();
		assertThat(id3.log2(1), is(0.0));
		assertThat(id3.log2(2), is(1.0));
		assertThat(id3.log2(3), is(1.5849625007211563));
		assertThat(id3.log2(1024), is(10.0));
	}

	/**
	 * test mostFrequentClassification() helper method
	 * first more true's than false's
	 * then more false's than true's
	 * then more true's than false's
	 */
	@Test
	public void mostFrequentClassificationTest() {
		MyID3 id3 = new MyID3();
		DecisionTreeData shortData = DataReader.readFile("src/decisiontree/decisiontree-data/short-data-training.csv");

		assertThat(id3.mostFrequentClassification(shortData).getElement(), is(" true"));

		shortData.getExamples()[0][shortData.getExamples()[0].length - 1] = " false"; // now more false's
		assertThat(id3.mostFrequentClassification(shortData).getElement(), is(" false"));

		shortData.getExamples()[0][shortData.getExamples()[0].length - 1] = " true";
		shortData.getExamples()[shortData.getExamples().length - 2][shortData.getExamples()[0].length - 1] = " true";

		assertThat(id3.mostFrequentClassification(shortData).getElement(), is(" true")); // now more true's
	}

	/**
	 * test allSameClassification() helper method
	 * first not all same classification
	 * then all same classification
	 */
	@Test
	public void allSameClassificationTest() {
		MyID3 id3 = new MyID3();
		DecisionTreeData shortData = DataReader.readFile("src/decisiontree/decisiontree-data/short-data-training.csv");

		assertThat(id3.allSameClassification(shortData), is(false));

		// make new subset of examples with the same classification
		int count = 0; // count number of examples in subset to initiate correct size array
		for (int i = 0; i < shortData.getExamples().length; i++) {
			if (shortData.getExamples()[i][shortData.getExamples()[0].length - 1].equals(" true")) {
				count++;
			}
		}
		String[][] newExamples = new String[count][shortData.getExamples()[0].length];
		int nextIndex = 0;
		for (int i = 0; i < shortData.getExamples().length; i++) {
			if (shortData.getExamples()[i][shortData.getExamples()[0].length - 1].equals(" true")) {
				for (int j = 0; j < shortData.getExamples()[0].length; j++) {
					newExamples[nextIndex][j] = shortData.getExamples()[i][j];
				}
				nextIndex++;
			}
		}

		DecisionTreeData newData = new DecisionTreeData(newExamples, shortData.getAttributeList(), shortData.getClassifications());
		assertThat(id3.allSameClassification(newData), is(true));
	}

	/**
	 * test makeNewSubset() helper method
	 * create new subset of short-data-training.csv using the first attribute and its last value
	 */
	@Test
	public void makeNewSubsetTest() {
		MyID3 id3 = new MyID3();
		DecisionTreeData shortData = DataReader.readFile("src/decisiontree/decisiontree-data/short-data-training.csv");

		String str = "";
		for (String s : shortData.getAttributeList().get(0).getValues()) { // get the last string value of the first attribute
			str = s;
		}

		String[][] expectedResult = {{"Yes", " No", " No", " Yes", " Some", " $$$", " No", " Yes", " French", " 0-10", " true"},
				{"Yes", " No", " No", " Yes", " Full", " $", " No", " No", " Thai", " 30-60", " false"},
				{"Yes", " No", " Yes", " Yes", " Full", " $", " Yes", " No", " Thai", " 10-30", " true"},
				{"Yes", " No", " Yes", " No", " Full", " $$$", " No", " Yes", " French", " >60", " false"},
				{"Yes", " Yes", " Yes", " Yes", " Full", " $", " No", " No", " Burger", " 30-60", " true"}}; // expected return of assert statement

		assertThat(id3.makeNewSubset(shortData, shortData.getAttributeList().get(0), str), is(expectedResult)); // should return expectedResult
	}
}