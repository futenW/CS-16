#!/usr/bin/python3

import minsteps
from importlib import reload
reload(minsteps)
from minsteps import *
import pytest

def example_test():
    assert minsteps([3, 1, 2, 0, 8]) == 2, "Test Failed"
    assert minsteps([1, 3, 6, 1, 0, 9]) == 3, "Test Failed"

def exception_test_example():
    with pytest.raises(InvalidInputException):
        minsteps(None)















# basic test to make sure it works as expected
def basic_test():
    assert minsteps([1, 1, 1]) == 2, "Test Failed"
    assert minsteps([1, 1, 1, 1]) == 3, "Test Failed"
    assert minsteps([2, 1, 1, 1]) == 2, "Test Failed"
    assert minsteps([9, 1, 1, 1, 1, 1, 1, 1, 1, 9, 9]) == 2, "Test Failed"
    assert minsteps([9, 1, 1, 1, 1, 1, 1, 1, 1, 9, 9, 1, 1, 1, 1, 1, 1, 1, 1, 2]) == 3, "Test Failed"

    assert minsteps([3, 1, 2, 0, 8]) == 2, "Test Failed"
    assert minsteps([1, 3, 6, 1, 0, 9]) == 3, "Test Failed"

def negatives(): # array is all negative
    assert minsteps([-1, -2, -3]) == None, "Test Failed"

def cant_reach_end(): # oof
    assert minsteps([1, 1, 1, 0, 1]) == None, "Test Failed"

def length_one():
    assert minsteps([1]) == 0, "Test Failed"
    assert minsteps([0]) == 0, "Test Failed"

def zeros(): # array is all zeros
    assert minsteps([0,0,0,0]) == None, "Test Failed"

def exception_test():
    # In order to test for an exception, you write something like this:
    with pytest.raises(InvalidInputException):
        minsteps(None) # This test will pass, because the exception will be raised
    with pytest.raises(InvalidInputException):
        minsteps([]) # This test will pass, because the exception will be raised

def get_tests():
    return [cant_reach_end, example_test, exception_test_example, basic_test, negatives, exception_test, zeros, length_one]



















# DO NOT EDIT BELOW THIS LINE ==================================================

# The mainline runs all of the test functions in the list returned by get_tests
if __name__ == '__main__' :
    print('Running tests...')
    for test in get_tests():
        test()
    print('All tests passed!')
