#!/usr/bin/python3

# DO NOT DELETE THESE STATEMENTS
import increment
from importlib import reload
reload(increment)
from increment import *
import pytest

def strToList(string):
    """
    This function takes in a string of numbers (example: '111') and turns
    it into a list of integers. The purpose of this function is to make
    it easier to call increment. For example, instead of calling
        increment([1,1,1])
    you could write
        increment(strToList('111'))
    You are not required to use this function, but it might be helpful!
    """
    return [int(x) for x in string]

def example_test_1():
    assert 1 == 1, 'Error: 1 does not equal 1!'

def example_test_2():
    my_list = []
    assert len(my_list) == 0, 'Error: The list is not empty...'








































def basic(): # basic test to make sure everything works
    assert increment([1,1,0,0]) == [1,1,0,1], 'test failed'
    assert increment([1,0,0,0]) == [1,0,0,1], 'test failed'

def one_digit():
    assert increment([1]) == [1,0], 'test failed'
    assert increment([0]) == [1], 'test failed'

def right_all_1(): # right most digit is 1
    assert increment([0,0,1]) == [0,1,0], 'test failed'
    assert increment([1,1,0,1]) == [1,1,1,0], 'test failed'
    assert increment([0,1,1,1]) == [1,0,0,0], 'test failed'
    assert increment([1,0,1,1]) == [1,1,0,0], 'test failed'
    assert increment([0,0,1,1]) == [0,1,0,0], 'test failed'

def all_are_0(): # entire list is 0's
    assert increment([0,0,0,0]) == [0,0,0,1], 'test failed'
    assert increment([0,0]) == [0,1], 'test failed'
    assert increment([0]) == [1], 'test failed'

def all_are_1(): # entire list is 1's
    assert increment([1,1,1]) == [1,0,0,0], 'test failed'
    assert increment([1]) == [1,0], 'test failed'
    assert increment([1,1,1]) == [1,0,0,0], 'test failed'

def middle_is_1(): # middle digit is 1, but ends vary
    assert increment([0,1,0]) == [0,1,1], 'test failed'
    assert increment([0,1,1]) == [1,0,0], 'test failed'
    assert increment([1,1,0]) == [1,1,1], 'test failed'
    assert increment([1,1,1]) == [1,0,0,0], 'test failed'

def middle_is_0(): # middle digit is 0, but ends vary
    assert increment([0,0,0]) == [0,0,1], 'test failed'
    assert increment([0,0,1]) == [0,1,0], 'test failed'
    assert increment([1,0,0]) == [1,0,1], 'test failed'
    assert increment([1,0,1]) == [1,1,0], 'test failed'

def exceptions(): # these will raise InvalidInputException
    with pytest.raises(InvalidInputException):
        increment([])
    with pytest.raises(InvalidInputException):
        increment(None)

def get_tests():
    return [example_test_1, example_test_2, basic, exceptions, all_are_1, middle_is_1, 
    middle_is_0, all_are_0, right_all_1, one_digit]




































# DO NOT EDIT BELOW THIS LINE ==================================================

# The mainline runs all of the test functions in the list returned by get_tests
if __name__ == '__main__' :
    print('Running tests...')
    for test in get_tests():
        test()
    print('All tests passed!')
