#!/usr/bin/python3

# DO NOT DELETE THESE STATEMENTS
import functional
from importlib import reload
reload(functional)
from functional import *
import pytest

# Write your testing functions here! Each testing function should have an
# informative name and test a specific aspect of your program's functionality.
# It is fine to have multiple assert statements in each function to test for
# various related conditions.


# DO NOT write your tests within the example test functions we provide!
# Our scripts will skip the test functions we provide, so write your own
# functions to test your code thoroughly.

# Here are some example test functions. Feel free to delete example_test_1 and
# remove it from the list in get_tests().

def example_test_1():
    assert 1 == 1, 'Error: 1 does not equal 1!'

def simple_test():
    assert apply_all([lambda x: x+1, lambda x: x+2, lambda x: x+3], 4) == [5,6,7], "apply_all is not correct"

    assert compose([lambda x: x+1, lambda x: x+2, lambda x: x+3], 4) == 10, "compose is not correct"

    assert list_compose_steps([lambda x: x+1, lambda x: x+2, lambda x: x+3], 4) == [4, 5, 7, 10], "list_compose_steps is not correct"

































def basic_test(): # basic test to ensure each method works as intended
    assert apply_all([lambda x: x+1, lambda x: x+2, lambda x: x+3], 4) == [5,6,7], "apply_all is not correct"
    assert compose([lambda x: x+1, lambda x: x+2, lambda x: x+3], 4) == 10, "compose is not correct"
    assert list_compose_steps([lambda x: x+1, lambda x: x+2, lambda x: x+3], 4) == [4, 5, 7, 10], "list_compose_steps is not correct"

def list_length_zero(): # when list is empty
    assert apply_all([], 4) == [], "apply_all is not correct"
    assert compose([], 4) == 4, "compose is not correct"
    assert list_compose_steps([], 4) == [4], "list_compose_steps is not correct"

def none_exceptions(): # None input raises InvalidInputException
    with pytest.raises(InvalidInputException):
        apply_all(None, 4), "apply_all is not correct"
    with pytest.raises(InvalidInputException):
        apply_all([lambda x: x+1, lambda x: x+2, lambda x: x+3], None), "apply_all is not correct"
    with pytest.raises(InvalidInputException):
        compose(None, 4), "compose is not correct"
    with pytest.raises(InvalidInputException):
        compose([lambda x: x+1, lambda x: x+2, lambda x: x+3], None), "compose is not correct"
    with pytest.raises(InvalidInputException):
        list_compose_steps(None, 4), "list_compose_steps is not correct"
    with pytest.raises(InvalidInputException):
        list_compose_steps([lambda x: x+1, lambda x: x+2, lambda x: x+3], None), "list_compose_steps is not correct"

def get_tests():
    return [example_test_1, simple_test,
    basic_test, none_exceptions, list_length_zero]





































# DO NOT EDIT BELOW THIS LINE ==================================================

# The mainline runs all of the test functions in the list returned by get_tests
if __name__ == '__main__' :
    print('Running tests...')
    for test in get_tests():
        test()
    print('All tests passed!')
