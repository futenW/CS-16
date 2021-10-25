package decisiontree;

import support.decisiontree.Attribute;
import support.decisiontree.DecisionTreeData;
import support.decisiontree.DecisionTreeNode;
import support.decisiontree.ID3;
import java.util.ArrayList;

/**
  * this is the class where the ID3 algorithm is implemented.
  */
public class MyID3 implements ID3 {

    /**
     * Constructor - not edited
     */
    public MyID3() {
        
    }

    /**
     * This is the trigger method that actually runs the algorithm.
     * This will be called by the visualizer when you click 'train'.
     */
    @Override
    public DecisionTreeNode id3Trigger(DecisionTreeData data) {
        // runs the algorithm, return the root of the tree
        return this.myID3Algorithm(data, data); // second data input is meaningless initially, no parent data available
    }

    /**
     * implements the algorithm - recursively calls itself
     */
    private DecisionTreeNode myID3Algorithm(DecisionTreeData data, DecisionTreeData parentData) {
        if (data.getExamples().length == 0) { // if data examples is empty,
            return this.mostFrequentClassification(parentData); // return new node w/ most frequent classification in parentData
        } else if (this.allSameClassification(data) == true) { // if all examples in data have same classification,
            DecisionTreeNode newNode = new DecisionTreeNode();
            newNode.setElement(data.getExamples()[0][data.getExamples()[0].length - 1]);
            return newNode; // return a new node with that classification
        } else if (data.getAttributeList().isEmpty()) { // if attributes is empty,
            return this.mostFrequentClassification(data); // return a new node with most frequent classification in data
        } else { // not a base case
            DecisionTreeNode root = new DecisionTreeNode();
            Attribute bestAttr = this.findBestAttribute(data); // finds best attribute and sets to var
            root.setElement(bestAttr.getName());

            // create new Attribute list without best attribute
            ArrayList<Attribute> newAttributes = new ArrayList<Attribute>();
            for (int i = 0; i < data.getAttributeList().size(); i++) {
                newAttributes.add(data.getAttributeList().get(i));
            }
            newAttributes.remove(bestAttr); // remove the best attribute that was found to make list smaller

            for (String s : bestAttr.getValues()) { // for every value of the best attribute, make new subset of examples and use as parameter in--
            // --the new data set, pass in the new data set into the recursive call, append subtree result of recursive call to the root
                String[][] subset = this.makeNewSubset(data, bestAttr, s); // new subset partitioned by value s
                DecisionTreeData newData = new DecisionTreeData(subset, newAttributes, data.getClassifications());
                DecisionTreeNode subtree = this.myID3Algorithm(newData, data); // subtree made from recursive call
                root.addChild(s, subtree); // append subtree
            }
            return root;
        }
    }

    /**
     * finds the best attribute given the data set
     * Input: a DecisiontreeData object
     * @return best attribute to split with of the given data
     */
    public Attribute findBestAttribute(DecisionTreeData data) {
        double greatestGain = 0.0; // the greatest gain of the best attribute so far
        Attribute bestAttr = data.getAttributeList().get(0); // current best attribute
        for (int i = 0; i < data.getAttributeList().size(); i++) {
            double thisGain = this.calculateInfoGain(data.getAttributeList().get(i), data);
            if (thisGain > greatestGain) { // if this attribute is a better attribute to use
                greatestGain = thisGain;
                bestAttr = data.getAttributeList().get(i);
            }
        }
        return bestAttr;
    }

    /**
     * calculate the amount of info gained from a particular attribute
     * @param attr the attribute to calculate the info gain from
     * @param data the data set from which to calculate
     * @return the amount of info gained from that particula attribute
     */
    public double calculateInfoGain(Attribute attr, DecisionTreeData data) {
        double remainder = 0.0;
        for (String s : attr.getValues()) { // for every possible value of the given attribute
            // make new subset of examples
            int count = 0; // count number of examples in subset to initiate correct size array
            for (int i = 0; i < data.getExamples().length; i++) { // for every examples
                if (data.getExamples()[i][attr.getColumn()].equals(s)) { // if the example matches the value of the attribute
                    count++;
                }
            }
            String[][] newExamples = new String[count][data.getExamples()[0].length]; // the new data structure holding subset
            int nextIndex = 0; // counter for the next row to copy to in the new data structure
            for (int i = 0; i < data.getExamples().length; i++) {
                if (data.getExamples()[i][attr.getColumn()].equals(s)) {
                    for (int j = 0; j < data.getExamples()[0].length; j++) { // copy every column
                        newExamples[nextIndex][j] = data.getExamples()[i][j];
                    }
                    nextIndex++;
                }
            }
            // update remainder
            remainder += ((this.calculateEntropy(newExamples, data.getClassifications()) * newExamples.length) / data.getExamples().length);
        }
        double infoGain = this.calculateEntropy(data.getExamples(), data.getClassifications()) - remainder; // info gain
        return infoGain;
    }

    /**
     * calculate the entropy from a given set of examples
     * @param examples the set of examples from which to calculate entropy
     * @param classifications the possible classifications of the examples
     * @return the entropy from the inputs
     */
    public double calculateEntropy(String[][] examples, String[] classifications) {
        // count number of positives in subset
        int countPositives = 0;
        for (int i = 0; i < examples.length; i++) {
            if (examples[i][examples[0].length - 1].equals(classifications[0])) {
                countPositives++;
            }
        }
        double countPositive = countPositives; // turn into double
        double countNegative = examples.length - countPositive; // turn into double
        double q;
        if (examples.length == 0) { // because can't divide by zero
            q = 0.0;
        } else { // countPositive + countNegative != 0
            q = countPositive / (countPositive + countNegative);
        }
        double entropy = 0.00;
        if (q == 0.0) { // because log2(0) can't be undefined
            entropy =  -1 * (1 - q) * this.log2(1 - q);
        } else if (q == 1.0) { // because log2(0) can't be undefined
            entropy = -1 * q * this.log2(q);
        } else { // log2(0) won't be called
            entropy = -1 * (q * this.log2(q) + (1 - q) * this.log2(1 - q));
        }
        return entropy;
    }

    /**
     * function calculating the log-base-2 for a given numerical input
     * @param N a double of the numerical input for which to calculate the log-base-2
     * @return the double value of the log-base-2 of the input
     */
    public double log2(double N) {
        return Math.log(N) / Math.log(2); // divide log(input) by log(2)
    }

    /**
     * finds the most frequent classification of a given set of examples
     * @param data the data object containing the set of examples from which to find the most frequent classification
     * @return a new node with the most frequent classification
     */
    public DecisionTreeNode mostFrequentClassification(DecisionTreeData data) {
        DecisionTreeNode newNode = new DecisionTreeNode(); // the new node to return
        int countPositives = 0; // count the amount of the first classification
        for (int i = 0; i < data.getExamples().length; i++) {
            if (data.getExamples()[i][data.getExamples()[0].length - 1].equals(data.getClassifications()[0])) {
                countPositives++;
            }
        }
        if (countPositives > data.getExamples().length - countPositives) { // more positives than negatives
            newNode.setElement(data.getClassifications()[0]); // set to first classification
        } else {
            newNode.setElement(data.getClassifications()[1]); // set to second classification
        }
        return newNode;
    }

    /**
     * determines whether a given set of examples has all the same classification
     * @param data the dat object containing the set of examples from which to determine
     * @return a boolean representing whether the data set of examples has all the same classification
     */
    public boolean allSameClassification(DecisionTreeData data) {
        boolean allSame = true; // assume they are all the same classification
        int lastIndex = data.getExamples()[0].length - 1;
        for (int i = 0; i < data.getExamples().length; i++) {
            if (!(data.getExamples()[i][lastIndex].equals(data.getExamples()[0][lastIndex]))) { // if any don't match the first
                allSame = false; // violates assumption
            }
        }
        return allSame;
    }

    /**
     * make new subset of examples
     * @param data DecisionTreeData object
     * @param bestAttr best attribute object for which new subsets are being made from
     * @param s particular value of the best attribute for which to divide with
     * @return new subset in a 2D String array
     */
    public String[][] makeNewSubset(DecisionTreeData data, Attribute bestAttr, String s) {
        int count = 0; // count number of examples in subset to initiate correct size array
        for (int i = 0; i < data.getExamples().length; i++) {
            if (data.getExamples()[i][bestAttr.getColumn()].equals(s)) {
                count++;
            }
        }
        String[][] newExamples = new String[count][data.getExamples()[0].length]; // actual new data structure
        int nextIndex = 0; // count which row of new data structure to copy to
        for (int i = 0; i < data.getExamples().length; i++) { // for every row
            if (data.getExamples()[i][bestAttr.getColumn()].equals(s)) {
                for (int j = 0; j < data.getExamples()[0].length; j++) { // copy every column
                    newExamples[nextIndex][j] = data.getExamples()[i][j];
                }
                nextIndex++;
            }
        }

        return newExamples;
    }
}
