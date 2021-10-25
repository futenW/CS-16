#!/usr/bin/python3

# DO NOT DELETE THESE STATEMENTS
import arraylessthan
from importlib import reload
reload(arraylessthan)
from arraylessthan import *
import pytest

# Write your testing functions here! Each testing function should have an
# informative name and test a specific aspect of your program's functionality.
# It is fine to have multiple assert statements in each function to test for
# various related conditions.

def example_test():
    assert array_less_than([4, 0, -4, 1], [5, 2, 5, 23]) == True, "Test Failed"
    assert array_less_than([4, 0, -4, 24], [5, 2, 5, 23]) == False, "Test Failed"
    assert array_less_than([27, 0], [5]) == False, "Test Failed"

def exception_test_example():
    # In order to test for an exception, you write something like this:
    # This test will pass, because the exception will be raised
    with pytest.raises(InvalidInputException):
        array_less_than(None, [9,3,5])

# Make sure the program baseline works as expected
def basic_test():
    assert array_less_than([5, 1, -3, 2], [6, 3, 6, 24]) == True, "Test Failed"
    assert array_less_than([5, 1, -3, 25], [6, 3, 6, 24]) == False, "Test Failed"
    assert array_less_than([1], [1, 2]) == False, "Test Failed"
    assert array_less_than([1,2,3], [4,5,6]) == True, "Test Failed"
    assert array_less_than([1,2,3], [4,5,1]) == False, "Test Failed"
    assert array_less_than([-1], [-1]) == False, "Test Failed"
    assert array_less_than([5, 9, 3], [2]) == False, "Test Failed"
    assert array_less_than([-1,-2,-3], [-4,-5,-6]) == False, "Test Failed"

def exception_test(): # if one, other, or both are "None"
    with pytest.raises(InvalidInputException):
        array_less_than(None, [5, 7, 3])
    with pytest.raises(InvalidInputException):
        array_less_than([4, 7, 3], None)
    with pytest.raises(InvalidInputException):
        array_less_than(None, None)

def empty_arrays():
    assert array_less_than([5, 1, -3, 2], []) == False, "Test Failed"
    assert array_less_than([], [6, 3, 6, 24]) == False, "Test Failed"
    assert array_less_than([], []) == False, "Test Failed"

def single_int_arrays(): # also compares single ints to empty arrays
    assert array_less_than([1], [2]) == True, "Test Failed"
    assert array_less_than([2], [1]) == False, "Test Failed"
    assert array_less_than([1], [1]) == False, "Test Failed"
    assert array_less_than([1], []) == False, "Test Failed"
    assert array_less_than([], [1]) == False, "Test Failed"

def exactly_equal():
    assert array_less_than([1, 2, 3, 4], [1, 2, 3, 4]) == False, "Test Failed"
    assert array_less_than([1, 1, 1, 1], [1, 1, 1, 1]) == False, "Test Failed"
    assert array_less_than([1, 1, 2], [1, 1, 2]) == False, "Test Failed"
    assert array_less_than([2], [2]) == False, "Test Failed"

def zero(): # u wont get me this time!
    assert array_less_than([0], [0]) == False, "Test Failed"
    assert array_less_than([0], [0, 0, 0]) == False, "Test Failed"
    assert array_less_than([0,0,0,0], [0,0,0,0]) == False, "Test Failed"
    assert array_less_than([0,0,0,0], [1,1,1,1]) == True, "Test Failed"
    assert array_less_than([1,1,1,1], [0,0,0,0]) == False, "Test Failed"
    assert array_less_than([0,1,2,3], [1,2,3,4]) == True, "Test Failed"
    assert array_less_than([1,2,3,4], [0,1,2,3]) == False, "Test Failed"

def negatives():
    assert array_less_than([-1], [1]) == True, "Test Failed"
    assert array_less_than([-1], [-1]) == False, "Test Failed"
    assert array_less_than([-5], [-4, -3, -2]) == False, "Test Failed"
    assert array_less_than([-5,-4,-3,-2], [-4,-3,-2,-1]) == True, "Test Failed"
    assert array_less_than([-4,-4,-3,-2], [-5,-3,-2,-1]) == False, "Test Failed"

def doubles():
    assert array_less_than([1.7, 2.0], [2.0, 3.0]) == True, "Test Failed"
    assert array_less_than([2.0, 3.0], [1.7, 2.0]) == False, "Test Failed"

def get_tests():
    # !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    # IMPORTANT
    # !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    # Add the names of each of your test functions to this list. It is very
    # important that you do this, or the TAs will not run your tests properly
    # and you will not receive full credit.
    #
    # DO NOT remove either example test from this list, just add new tests like so:
    #       [example, example, new test,...]
    # We will not be able to properly grade your coal tests if you do not follow
    # these instructions! You will lose points on your submission for failing
    # to follow these instructions.
    return [example_test, exception_test_example, basic_test, exception_test, empty_arrays, 
    single_int_arrays, exactly_equal, zero, negatives, doubles]

# DO NOT EDIT BELOW THIS LINE ==================================================

# The mainline runs all of the test functions in the list returned by get_tests
if __name__ == '__main__' :
    print('Running tests...')
    for test in get_tests():
        test()
    print('All tests passed!')
