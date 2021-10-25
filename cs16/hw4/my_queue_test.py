#!/usr/bin/python3

import my_queue
from importlib import reload
reload(my_queue)
from my_queue import *
import pytest

def test_1(): # this enqueues a number, then dequeues it
    q = Queue(4)
    assert q.is_empty() == True, "Test 1 failed: Should return true"
    q.enqueue(3)
    assert q.dequeue() == 3, "Test 2 failed: Should return 3"

















def basic_test(): # basic test to make sure it works
    q = Queue(4)
    q.enqueue(3)
    assert q.dequeue() == 3, "Test failed: Should return 3"
    q.enqueue(1)
    assert q.dequeue() == 1, "test failed"
    q.enqueue(1)
    q.enqueue(1)
    q.enqueue(1)
    q.enqueue(1)
    assert q.dequeue() == 1, "test failed"
    assert q.dequeue() == 1, "test failed"
    assert q.dequeue() == 1, "test failed"
    assert q.dequeue() == 1, "test failed"

def insert_float(): # enqueue/dequeue decimal
    q = Queue(1)
    q.enqueue(-5.7)
    assert q.dequeue() == -5.7, "test failed"
    q.enqueue(1.1)
    assert q.dequeue() == 1.1, "test failed"

def insert_boolean(): # enqueue/dequeue a boolean
    q = Queue(1)
    q.enqueue(True)
    q.dequeue() == True, "test failed"
    q.enqueue(False)
    q.dequeue() == False, "test failed"

def insert_negative(): # enqueue/dequeue negative number
    q = Queue(1)
    q.enqueue(-1)
    assert q.dequeue() == -1, "Test failed: Should return -1"
    q.enqueue(-4.6)
    assert q.dequeue() == -4.6, "Test failed: Should return -1"

def insert_string(): # enqueue/dequeue string
    q = Queue(1)
    q.enqueue('hi')
    assert q.dequeue() == 'hi', "test failed: should return 'hi'"
    q.enqueue("hello world!")
    assert q.dequeue() == "hello world!", "test failed: should return 'hello world!'"

def shrinking_test(): # tests if array shrinks
    q = Queue(4)
    assert q.capacity() == 4, "Capacity test failed: Should return 4"
    q.enqueue(3)
    assert q.dequeue() == 3, "Test failed: Should return 3"
    assert q.capacity() == 2, "Shrinking test failed: Should return 2"

def queue_dequeue(): # enqueues a number, then dequeues it
    q = Queue(4)
    q.enqueue(3)
    assert q.dequeue() == 3, "Test failed: Should return 3"

def test_empty(): # tests an empty queue, then a not empty one
    q = Queue(4)
    assert q.is_empty() == True, "Test failed: Should return True"
    q.enqueue(3)
    assert q.is_empty() == False, "Test failed: Should return False"

def test_size(): # asks for size of empty queue, then a not empty one
    q = Queue(4)
    assert q.size() == 0, "Test failed: Should return 0"
    q.enqueue(3)
    assert q.size() == 1, "test failed: Should return 1"

def wrap_around(): # wraps tail around to beginning
    q = Queue(4)
    q.enqueue(None)
    q.enqueue(None)
    q.enqueue(None)

    q.front() == None, "test failed"
    q.dequeue() == None, "test failed"
    q.dequeue() == None, "test failed"

    q.enqueue(None)
    q.enqueue(None)

    assert q.size() == 3, "Test failed: Should return 3"

    q.dequeue() == None, "test failed"
    q.dequeue() == None, "test failed"
    q.dequeue() == None, "test failed"
    with pytest.raises(EmptyQueueException):
        q.dequeue()

def expand(): # repeatedly expands queue and then de-expand it
    q = Queue(1)
    assert q.capacity() == 1, "Test failed: Should return 1"
    assert q.size() == 0, "test failed"
    assert q.is_empty() == True, "test failed"
    with pytest.raises(EmptyQueueException):
        q.front()
    with pytest.raises(EmptyQueueException):
        q.dequeue()
    q.enqueue(None) # enqueue
    assert q.capacity() == 1, "Test failed: Should return 1"
    assert q.size() == 1, "test failed"
    assert q.is_empty() == False, "test failed"
    assert q.front() == None, "test failed"
    q.enqueue(None) # enqueue
    assert q.capacity() == 2, "test failed"
    assert q.size() == 2, "test failed"
    assert q.is_empty() == False, "test failed"
    q.enqueue(None) # enqueue
    assert q.size() == 3, "test failed"
    assert q.capacity() == 4, "test failed"
    assert q.is_empty() == False, "test failed"
    q.enqueue(None) # enqueue
    assert q.size() == 4, "test failed"
    assert q.capacity() == 4, "test failed"
    assert q.is_empty() == False, "test failed"
    q. enqueue(None) # enqueue
    assert q.size() == 5, "test failed"
    assert q.capacity() == 8, "test failed"
    assert q.is_empty() == False, "test failed"

    assert q.dequeue() == None, "test failed" # dequeue
    assert q.size() == 4, "test failed"
    assert q.capacity() == 8, "test failed"
    assert q.is_empty() == False, "test failed"
    assert q.dequeue() == None, "test failed" # dequeue
    assert q.size() == 3, "test failed"
    assert q.capacity() == 8, "test failed"
    assert q.is_empty() == False, "test failed"
    assert q.dequeue() == None, "test failed" # dequeue
    assert q.size() == 2, "test failed"
    assert q.capacity() == 4, "test failed"
    assert q.is_empty() == False, "test failed"
    assert q.dequeue() == None, "test failed" # dequeue
    assert q.size() == 1, "test failed"
    assert q.capacity() == 2, "test failed"
    assert q.is_empty() == False, "test failed"
    assert q.dequeue() == None, "test failed" # dequeue
    assert q.size() == 0, "test failed"
    assert q.capacity() == 2, "test failed"
    assert q.is_empty() == True, "test failed"

def enqueue_none(): # enqueue null
    q = Queue(1)
    assert q.capacity() == 1, "Test failed: Should return 1"
    assert q.size() == 0, "test failed"
    assert q.is_empty() == True, "test failed"
    q.enqueue(None)
    assert q.size() == 1, "test failed"
    assert q.capacity() == 1, "test failed"
    assert q.is_empty() == False, "test failed"

def initialize_exceptions(): # invalid size inputs
    with pytest.raises(InvalidInputException):
        Queue(-1)
    with pytest.raises(InvalidInputException):
        Queue(0)
    with pytest.raises(InvalidInputException):
        Queue(None)

def empty_array_exceptions(): # dequeue() or front() when empty
    q = Queue(1)
    with pytest.raises(EmptyQueueException):
        q.dequeue()
    with pytest.raises(EmptyQueueException):
        q.front()

def get_tests():
    return [test_1, basic_test, queue_dequeue, test_empty, test_size, initialize_exceptions,
    empty_array_exceptions, shrinking_test, expand, enqueue_none, wrap_around, insert_negative, 
    insert_string, insert_boolean, insert_float]




















# DO NOT EDIT BELOW THIS LINE ==================================================

# The mainline runs all of the test functions in the list returned by get_tests
if __name__ == '__main__' :
    print('Running tests...')
    for test in get_tests():
        test()
    print('All tests passed!')
