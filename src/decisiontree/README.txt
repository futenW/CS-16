--DecisionTree README--

Handin: might resubmit later

Design Choices:
    DecisionTree has one main class: the MyID3 class. This class implements the ID3 algorithm itself, calling itself
    recursively to determine subtrees for which to append. The id3Trigger() method calls the actual algorithm method,
    acting as the trigger method that actually runs the algorithm. Seven helper methods were included: findBestAttribute(),
    calculateInfoGain(), calculateEntropy(), log2(), mostFrequentClassification(), makeNewSubset(), and
    allSameClassification(). findBestAttribute() takes in a DecisionTreeData object and finds the best attribute for which
    to split the data. It is used within the ID3 algorithm method itself, finding the bestAttribute at every recursive call.
    calculateInfoGain() takes in an Attribute object and a DecisionTreeData object and returns the amount of info gained
    from that particular attribute. It is used within the findBestAttribute() method to calculate the info gain for each
    attribute within the current recursive call. calculateEntropy() takes in a set of examples represented by a 2D array
    of Strings and a set of possible classifications, returning the entropy of the particular set of examples.
    calculateEntropy() is used within the calculateInfoGain() method to find the entropy of subsets divided by a particular
    attribute. The log2(N) method takes in a numerical input N and calculates and returns the log-base-2 of N. This method
    is used within the calculateEntropy() method calculations. mostFrequentClassification() takes in a DecisiontreeData
    object, finds the most frequent classification for a the particular dataset, and returns it. It is used within the ID3
    method for the base case in which the data set of examples is empty to find the most frequent classification of the
    parent and also in the base case for which the attribute list is empty to find the most frequent classification of
    the current given example set. allSameClassification() takes in a DecisionTreeData object and determines whether a
    given set of examples has all the same classification, returning a boolean to represent the result. If is used to
    determine the base-case in which all the examples in a given data set have the same classification. makeNewSubset()
    takes in a DecsionTreeData object, a particular attribute, and one of its possible values and creates a subset of the
    DecisionTreeData examples partitioned by the attribute's value. It is used within the ID3 algorithm method to create
    subsets of every value of the best attribute.

Known Bugs: n/a

Test Cases:
    - First test findBestAttribute() to find the best attribute to divide upon using short-training-data.csv, correctly
    returns Patrons as best attribute with most info gain
    - Then tests calculateInfoGain() of the first attribute using the same data set, correctly returns the hand-calculated value
    - Test findEntropy() using the examples of the same data set, correctly returns the hand-calculate value
    - Then test log2() using inputs of 1, 2, 3, and 1024, correctly returning 0.0, 1.0, 1.5849625007211563, and 10.0, respectively
    - Then tests mostFrequentClassification() using the same data set, correctly returning true at first
    - Then the data set is altered to have more false's than true's, for which the method correctly returns false as the
    most frequent classification. The data set is then altered back to more true classifications once more, for which the
    method passes once again
    - Test allSameClassification() by using the same original data set of short-training-data.csv
    - Test correctly returns false at first because short-training-data.csv includes differing classifications
    - A subset is then created with all of the same classification, for which the method correctly returns true this time
    - Finally, tests makeNewSubset() using the same short-training-data.csv, its first attribute, and that attribute's
    last value, correctly returns the expected 2D array of Strings representing the subset
