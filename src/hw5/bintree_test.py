#! /usr/bin/python3

# DO NOT DELETE THESE STATEMENTS
import bintree
from importlib import reload
reload(bintree)
from bintree import *
import pytest

# Write your testing functions here! Each testing function should have an
# informative name and test a specific aspect of your program's functionality.
# It is fine to have multiple assert statements in each function to test for
# various related conditions.

# DO NOT write your tests within the example test functions we provide!
# Our scripts will skip the test functions we provide, so write your own
# functions to test your code thoroughly.

def simple_test_1():
    # Setup simple graph
    bt = BinTree()
    bt.addRoot("A")
    bt.addLeft(bt.root(), "B")
    bt.addRight(bt.root(), "C")

    # Check graph properties using assert
    assert bt.root().value() == "A", "ERROR: Incorrect root"





















def basic_test(): # basic test to make sure everything works as expected
    bt = BinTree()
    assert bt.isEmpty() == True, "test failed" # first check when tree is empty
    assert bt.size() == 0, "test failed" # first check when size is zero

    assert bt.addRoot("a") == bt.root(), "test failed" # added root
    assert bt.height() == 0, "test failed" # first check of height when zero
    assert bt.addRoot("z") == bt.root(), "test failed" # tried to overwrite root

    assert bt.addLeft(bt.root(), "b") == bt.root().left(), "test failed" # added left
    assert bt.addRight(bt.root(), "c") == bt.root().right() # added right
    assert bt.addLeft(bt.root(), "z") == bt.root().left(), "test failed" # tried to overwrite left
    assert bt.addRight(bt.root(), "z") == bt.root().right(), "test failed" # tried to overwrite right

    assert bt.root().value() == "a", "ERROR: Incorrect root" # confirm root value
    assert bt.parent(bt.root()) == None, "ERROR: Incorrect left node" # confirm parent node of root doesn't exist
    assert bt.parent(bt.root().left()) == bt.root(), "test failed" # find parent from left node

    assert bt.isEmpty() == False, "test failed" # first check when tree is not empty
    assert bt.size() == 3, "test failed" # first check of when size != 0
    assert bt.isRoot(bt.root().left()) == False, "test failed" # imposter root accessed from root() node

def is_empty(): # test isEmpty() function when tree is empty and then not
    bt = BinTree()
    assert bt.isEmpty() == True, "test failed" # first check when tree is empty

    assert bt.addRoot("a") == bt.root(), "test failed" # added root
    assert bt.addLeft(bt.root(), "b") == bt.root().left(), "test failed" # added left
    assert bt.addRight(bt.root(), "c") == bt.root().right() # added right

    assert bt.isEmpty() == False, "test failed" # first check when tree is not empty

def size(): # test size() function when tree is empty and then not
    bt = BinTree()
    assert bt.size() == 0, "test failed" # first check when size is zero

    assert bt.addRoot("a") == bt.root(), "test failed" # added root
    assert bt.addLeft(bt.root(), "b") == bt.root().left(), "test failed" # added left
    assert bt.addRight(bt.root(), "c") == bt.root().right() # added right

    assert bt.size() == 3, "test failed" # first check of when size != 0

def external_internal(): # test isExternal() and isInternal() nodes
    bt = BinTree()
    assert bt.addRoot("a") == bt.root(), "test failed" # added root
    assert bt.isExternal(bt.root()) == True, "test failed" # check root when it still is external
    assert bt.addLeft(bt.root(), "b") == bt.root().left(), "test failed" # added left
    assert bt.addRight(bt.root(), "c") == bt.root().right() # added right

    assert bt.isInternal(bt.root()) == True, "test failed"
    assert bt.isExternal(bt.root()) == False, "test failed"
    assert bt.isInternal(bt.root().left()) == False, "test failed"
    assert bt.isExternal(bt.root().left()) == True, "test failed"

def get_children(): # test getChildren() function
    bt = BinTree()
    assert bt.addRoot("a") == bt.root(), "test failed" # added root
    assert bt.addLeft(bt.root(), "b") == bt.root().left(), "test failed" # added left
    assert bt.addRight(bt.root(), "c") == bt.root().right() # added right

    _children = bt.children(bt.root())
    assert _children[0].value() == "b", "test failed" # first child
    assert _children[1].value() == "c", "test failed" # second child

def attempt_overwrite(): # try to overwite existing root, left, and right nodes
    bt = BinTree()

    assert bt.addRoot("a") == bt.root(), "test failed" # added root
    assert bt.addRoot("z") == bt.root(), "test failed" # tried to overwrite root
    assert bt.addLeft(bt.root(), "b") == bt.root().left(), "test failed" # added left
    assert bt.addRight(bt.root(), "c") == bt.root().right() # added right

    assert bt.addLeft(bt.root(), "z") == bt.root().left(), "test failed" # tried to overwrite left
    assert bt.addRight(bt.root(), "z") == bt.root().right(), "test failed" # tried to overwrite right

def empty_tree_exceptions(): # these will raise emptyBinTreeExceptions
    bt = BinTree()
    with pytest.raises(EmptyBinTreeException):
        bt.height()
    with pytest.raises(EmptyBinTreeException):
        bt.root()

def invalid_input_exceptions(): # these will raise InvalidInputExceptions
    bt = BinTree()
    with pytest.raises(InvalidInputException):
        bt.parent(None)
    with pytest.raises(InvalidInputException):
        bt.children(None)
    with pytest.raises(InvalidInputException):
        bt.isInternal(None)
    with pytest.raises(InvalidInputException):
        bt.isExternal(None)
    with pytest.raises(InvalidInputException):
        bt.isRoot(None)
    with pytest.raises(InvalidInputException):
        bt.left(None)
    with pytest.raises(InvalidInputException):
        bt.right(None)
    with pytest.raises(InvalidInputException):
        bt.hasLeft(None)
    with pytest.raises(InvalidInputException):
        bt.hasRight(None)
    with pytest.raises(InvalidInputException):
        bt.addLeft(None, "left")
    with pytest.raises(InvalidInputException):
        bt.addRight(None, "right")

def get_tests():
    return [simple_test_1, basic_test, is_empty, size, external_internal, get_children, attempt_overwrite,
    empty_tree_exceptions, invalid_input_exceptions]

























# DO NOT EDIT BELOW THIS LINE ==================================================

# The mainline runs all of the test functions in the list returned by get_tests
if __name__ == '__main__' :
    print('Running tests...')
    for test in get_tests():
        test()
    print('All tests passed!')