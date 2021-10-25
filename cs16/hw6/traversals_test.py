#! /usr/bin/python3

# DO NOT DELETE THESE STATEMENTS
try:
    from bintree import BinTree
except ImportError:
    import autograder.source.autograder.resources.bintree as bintree
import traversals
from importlib import reload
reload(traversals)
from traversals import *
import pytest

def show_values(l):
    """
    This function takes in a list of nodes and produces a list of just the nodes' values.
    The purpose of this function is to make the output of your traversals more readable.
    You are not required to use this function, but it might be helpful!
    """
    if l != None:
        return [x.value() for x in l]
    else:
        return []

def example_test_1():
    assert 1 == 1, 'Error: 1 does not equal 1!'

def simple_test():
    # Setup the tree
    bt = BinTree()
    root = bt.addRoot("Root")
    left = bt.addLeft(root, "Left")
    right = bt.addRight(root, "Right")

    # Run the algorithm
    pre = preorder(bt)

    # Test its output
    print("Preorder is in this order: " + str(show_values(pre)))
    assert pre == [root, left, right], "Preorder missed assertion!"










































def simple_preorder_test(): # test preorder
    # Setup the tree
    bt = BinTree()
    root = bt.addRoot("Root")
    left = bt.addLeft(root, "Left")
    right = bt.addRight(root, "Right")

    # Run the algorithm
    pre = preorder(bt)

    # Test its output
    print("PREORDER is in this order: " + str(show_values(pre)))
    assert pre == [root, left, right], "Preorder missed assertion!"

def simple_postorder_test(): # test postorder
    # Setup the tree
    bt = BinTree()
    root = bt.addRoot("Root")
    left = bt.addLeft(root, "Left")
    right = bt.addRight(root, "Right")

    # Run the algorithm
    post = postorder(bt)

    # Test its output
    print("POSTORDER is in this order: " + str(show_values(post)))
    assert post == [left, right, root], "Postorder missed assertion!"

def simple_inorder_test(): # test inorder
    # Setup the tree
    bt = BinTree()
    root = bt.addRoot("Root")
    left = bt.addLeft(root, "Left")
    right = bt.addRight(root, "Right")

    # Run the algorithm
    inOrd = inorder(bt)

    # Test its output
    print("INORDER is in this order: " + str(show_values(inOrd)))
    assert inOrd == [left, root, right], "In order missed assertion!"

def simple_BFSorder_test(): # test BFS
    # Setup the tree
    bt = BinTree()
    root = bt.addRoot("Root")
    left = bt.addLeft(root, "Left")
    right = bt.addRight(root, "Right")

    # Run the algorithm
    bfs = breadthfirst(bt)

    # Test its output
    print("BFS is in this order: " + str(show_values(bfs)))
    assert (bfs == [root, left, right] or bfs == [root, right, left]), "BFS order missed assertion!"
    assert bfs[0] == root
    assert bfs[1] == left or bfs[1] == right
    assert bfs[2] == left or bfs[2] == right

    left_left = bt.addLeft(left, "Left-Left")
    left_right = bt.addRight(left, "Left-Right")
    right_left = bt.addLeft(right, "Right-Left")
    right_right = bt.addRight(right, "Right-Right")

    # Run the algorithm
    bfs = breadthfirst(bt)

    # Test its output
    print("BFS is in this order: " + str(show_values(bfs)))
    assert bfs[0] == root
    assert bfs[1] == left or bfs[1] == right
    assert bfs[2] == left or bfs[2] == right
    assert bfs[3] == right_right or bfs[3] == left_left or bfs[3] == right_left or bfs[3] == left_right
    assert bfs[4] == right_right or bfs[4] == left_left or bfs[4] == right_left or bfs[4] == left_right
    assert bfs[5] == right_right or bfs[5] == left_left or bfs[5] == right_left or bfs[5] == left_right
    assert bfs[6] == right_right or bfs[6] == left_left or bfs[6] == right_left or bfs[6] == left_right

def raise_exceptions(): # these will raise InvalidInputExceptionv from null input
    with pytest.raises(InvalidInputException):
        breadthfirst(None)
    with pytest.raises(InvalidInputException):
        preorder(None)
    with pytest.raises(InvalidInputException):
        postorder(None)
    with pytest.raises(InvalidInputException):
        inorder(None)

def return_empty_list(): # these will return an empty list
    bt = BinTree() # Setup empty tree
    assert breadthfirst(bt) == [], "test failed"
    assert preorder(bt) == [], "test failed"
    assert postorder(bt) == [], "test failed"
    assert inorder(bt) == [], "test failed"

def get_tests():
    return [example_test_1, simple_test, simple_preorder_test, simple_postorder_test, simple_inorder_test,
    return_empty_list, raise_exceptions, simple_BFSorder_test]









































# DO NOT EDIT BELOW THIS LINE ==================================================

# The mainline runs all of the test functions in the list returned by get_tests
if __name__ == '__main__' :
    print('Running tests...')
    for test in get_tests():
        test()
    print('All tests passed!')
