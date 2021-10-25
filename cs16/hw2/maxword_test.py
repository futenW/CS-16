#!/usr/bin/python3

# DO NOT DELETE THESE STATEMENT
import maxword
from importlib import reload
reload(maxword)
from maxword import *
import pytest

# Write your testing functions here! Each testing function should have an
# informative name and test a specific aspect of your program's functionality.
# It is fine to have multiple assert statements in each function to test for
# various related conditions.

# Here are some example test functions. Feel free to delete example_test
# and remove it from the list in get_tests().

def example_test():
    assert max_word("hello world") == 1, "Test Failed"
    assert max_word("the quick brown fox jumped over the lazy dog") == 2, "Test Failed"


def exception_test_example():
    # In order to test for an exception you expect to be raised, you write something like this:
    with pytest.raises(InvalidInputException): # This test will pass, because the exception will be raised
        max_word("")
    with pytest.raises(InvalidInputException): # This test will pass, because the exception will be raised
        max_word(None)

# Make sure the program baseline works as expected
def basic_test():
    assert max_word("goodbye earth") == 1, "Test Failed"
    assert max_word("the slow red rabbit leaped under the hyperactive cat") == 2, "Test Failed"


def exception_test(): # if passed in "None" or empty string
    with pytest.raises(InvalidInputException): # This test will pass, because the exception will be raised
        max_word("")

    with pytest.raises(InvalidInputException): # This test will pass, because the exception will be raised
        max_word(None)

def single_word(): # only one type of word
    assert max_word("goodbye") == 1, "Test Failed"
    assert max_word("the the the the") == 4, "Test Failed"

def multiple_maxes(): # multiple words tied for the max
    assert max_word("sing to sing to") == 2, "Test Failed"

def double_space(): # double space between words
    assert max_word("i had an  oopsie") == 1, "Test Failed"
    assert max_word("oopsie i had an  oopsie") == 2, "Test Failed"

def unusual_spaces(): # u need halp
    assert max_word("sing to sing to ") == 2, "Test Failed"

def single_letter(): # single letter word
    assert max_word("i") == 1, "Test Failed"
    assert max_word("i i i i") == 4, "Test Failed"

def punctuation():
    assert max_word("sing, i like to sing.") == 1, "Test Failed"

def capitalization():
    assert max_word("That is a that fox") == 1, "test failed"

def only_spaces():
    assert max_word(" ") == 0, "test failed"
    assert max_word("            ") == 0, "test failed"

def get_tests():
    # !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    # IMPORTANT
    # !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    # Add the names of each of your test functions to this list. It is very
    # important that you do this, or the TAs will not run your tests properly
    # and you will not receive full credit.
    return [example_test, exception_test_example, basic_test, exception_test, single_word, double_space, 
    unusual_spaces, multiple_maxes, single_letter, punctuation, capitalization, only_spaces]

# DO NOT EDIT BELOW THIS LINE ==================================================

# The mainline runs all of the test functions in the list returned by get_tests
if __name__ == '__main__' :
    print("Testing...")
    for test in get_tests():
        test()
    print("All tests passed!")
