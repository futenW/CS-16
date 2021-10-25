#!/usr/bin/python3

import maxseq
from importlib import reload
reload(maxseq)
from maxseq import *
import pytest

def example_test():
    assert maxseq([-1, 2, 7, -8, 13, -2]) == 194, "Test Failed"
    assert maxseq([-7, -9, -10]) == 180, "Test Failed"
    assert maxseq([10, -5, 200]) == 385, "Test Failed"

def exception_test_example():
    with pytest.raises(InvalidInputException):
        maxseq(None)














# basic test to make sure it works as expected
def basic_test():
    assert maxseq([-2, -3, 4, -1, -2, 1, 5, -3]) == 187, "Test Failed"
    assert maxseq([-1, 2, 7, -8, 13, -2]) == 194, "Test Failed"
    assert maxseq([10, -5, 200]) == 385, "Test Failed"

def all_negative():
    assert maxseq([-7, -9, -10]) == 180, "Test Failed"

def empty_array():
    assert maxseq([]) == 180, "Test Failed"

def dont_buy_at_all():
    assert maxseq([-7, 0, -10]) == 180, "Test Failed"

def hold_one_day(): # only one number is positive
    assert maxseq([-7, 1, -10]) == 181, "Test Failed"

def hold_entire_duration(): # hold for every int in array
    assert maxseq([1, -5, 200]) == 380, "Test Failed"

def exception_test():
    with pytest.raises(InvalidInputException): # In order to test for an exception, you write something like this
        maxseq(None) # This test will pass, because the exception will be raised

def get_tests():
    return [example_test, exception_test_example, basic_test, all_negative, dont_buy_at_all,
    empty_array, hold_one_day, hold_entire_duration, exception_test]















# DO NOT EDIT BELOW THIS LINE ==================================================

# The mainline runs all of the test functions in the list returned by get_tests
if __name__ == '__main__' :
    print('Running tests...')
    for test in get_tests():
        test()
    print('All tests passed!')
