#!/usr/bin/python3

# DO NOT DELETE THESE STATEMENTS
import arraysearch
from importlib import reload
reload(arraysearch)
from arraysearch import *
import pytest

# Write your testing functions here! Each testing function should have an
# informative name and test a specific aspect of your program's functionality.
# It is fine to have multiple assert statements in each function to test for
# various related conditions.

def example_test():
	assert array_search(2, [1, 2, 3]) == True, "Test Failed"
	assert array_search(6, [1, 7, 9, 2, 3]) == False, "Test Failed"

def exception_test_example():
	# In order to test for an exception you expect to be raised, you write something like this:
	# This test will pass, because the exception will be raised
	with pytest.raises(InvalidInputException):
		array_search(None, [9,3,5])

# Make sure the program baseline works as expected
def basic_test():
	assert array_search(3, [3, 2, 1]) == True, "Test failed finding number"
	assert array_search(1, [3, 2, 1]) == True, "Test failed finding number"
	assert array_search(3, [4, 8, 2, 5, 1]) == False, "Test failed at finding no number"

def exception_test(): # if one, other, or both are "None"
	with pytest.raises(InvalidInputException):
		array_search(None, [9,3,5])
	with pytest.raises(InvalidInputException):
		array_search(3, None)
	with pytest.raises(InvalidInputException):
		array_search(None, None)

def multiple_occurences(): # array repeats numbers
	assert array_search(3, [3, 3, 2, 1]) == True, "Test failed finding number in multiple occurences"
	assert array_search(3, [3, 3, 3, 3]) == True, "Test failed finding number in multiple occurences"
	assert array_search(3, [4, 8, 5, 2, 5, 1]) == False, "Test failed at finding no number in multiple occurences"

def single_int_array():
	assert array_search(3,[3]) == True, "test failed"
	assert array_search(3,[1]) == False, "test failed"

def empty_array():
	assert array_search(3, []) == False, "test failed"

def zero(): # good one zero!
	assert array_search(0, [0]) == True, "test failed"
	assert array_search(0, [0, 0, 0]) == True, "test failed"
	assert array_search(0, [1, 2, 3, 4]) == False, "test failed"
	assert array_search(0, [1, 2, 0, 4, 5]) == True, "test failed"
	assert array_search(1, [0]) == False, "test failed"
	assert array_search(1, [0, 0, 0]) == False, "test failed"
	assert array_search(1, [0, 1, 0]) == True, "test failed"

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
	return [example_test, exception_test_example, basic_test, exception_test, multiple_occurences, 
	single_int_array, empty_array, zero]

# DO NOT EDIT BELOW THIS LINE ==================================================

# The mainline runs all of the test functions in the list returned by get_tests
if __name__ == '__main__' :
	print('Running tests...')
	for test in get_tests():
		test()
	print('All tests passed!')
