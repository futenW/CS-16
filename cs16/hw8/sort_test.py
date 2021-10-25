#!/usr/bin/python3

# DO NOT DELETE THESE STATEMENTS
import sort
from importlib import reload
reload(sort)
from sort import *
import pytest

# Write your testing functions here! Each testing function should have an
# informative name and test a specific aspect of your program's functionality.
# It is fine to have multiple assert statements in each function to test for
# various related conditions.

# DO NOT write your tests within the example test functions we provide!
# Our scripts will skip the test functions we provide, so write your own
# functions to test your code thoroughly.

# Here are some example test functions. Feel free to delete example_test_1
# and remove it from the list in get_tests(), and feel free to add to simple_test.

def simple_test():
    # This loops through all of your sort algorithms, and
    # runs any asserts in the for loop on each one.
    sort_algorithms = [(merge_sort, "Merge sort"), (insertion_sort, "Insertion sort"), (selection_sort, "Selection sort")]
    for sort_algorithm, name in sort_algorithms:
        assert sort_algorithm([4,5,1,3,2]) == [5,4,3,2,1], "%s failed." % name
        # Add many, many more asserts here to test your sorts!




























def first_test():
    # This loops through all of your sort algorithms, and runs any asserts in the for loop on each one.
    sort_algorithms = [(merge_sort, "Merge sort"), (insertion_sort, "Insertion sort"), (selection_sort, "Selection sort")]
    for sort_algorithm, name in sort_algorithms:
        assert sort_algorithm([4,5,1,3,2]) == [5,4,3,2,1], "%s failed." % name

def simple_insertion_test():  # ensure insertion sort works as expected
    assert insertion_sort([1,2,3]) == [3,2,1], "test failed"    
    assert insertion_sort([]) == [], "test failed"

def simple_selection_test(): # ensure selection sort works as expected
    assert selection_sort([1,2,3]) == [3,2,1], "test failed"
    assert selection_sort([]) == [], "test failed"

def simple_merge_test(): # ensure merge sort works as expected
    assert merge_sort([1,2,3]) == [3,2,1], "test failed" 

def test_exceptions(): # should throw invalid input exception when input is None
    with pytest.raises(InvalidInputException):
        merge_sort(None)
    with pytest.raises(InvalidInputException):
        insertion_sort(None)
    with pytest.raises(InvalidInputException):
        selection_sort(None)

def get_tests():
    return [simple_test, first_test, simple_insertion_test, simple_selection_test, simple_merge_test,
    test_exceptions]



























# DO NOT EDIT BELOW THIS LINE ==================================================

# The mainline runs all of the test functions in the list returned by get_tests
if __name__ == '__main__' :
    print('Running tests...')
    for test in get_tests():
        test()
    print('All tests passed!')
