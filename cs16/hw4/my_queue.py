#!/usr/bin/python3

"""
Queue Class: Implement a growable and shrinkable queue with an array as the underlying data structure
"""

class Queue:
    def __init__(self, initial_capacity):
        """
    initialize queue
    Consumes: a queue and a number |||| Produces: nothing
    init: queue * num -> |||| Example: Queue(9) -> An Empty Queue with space for 9 elements
    Raises: InvalidInputException:  initial_capacity is empty, negative, or null
    """
        if initial_capacity == None:
            raise InvalidInputException("initial_capacity is null")
        if initial_capacity < 1:
            raise InvalidInputException("initial_capacity is empty or negative")
        self._array = [None] * initial_capacity
        self._capacity = initial_capacity
        self._count = 0
        self._head = 0
        self._tail = 0

    def size(self):
        """
    returns number of items currently in queue
    Consumes: a queue |||| Produces: the number of items in the queue
    size: queue -> num |||| Example: size() -> 9
    """
        return self._count

    def is_empty(self):
        """
    tell whether the queue is empty or not
    Consumes: a queue |||| Produces: boolean
    is_empty: queue -> bool |||| Example: is_empty() -> false
    """
        return self._count == 0

    def capacity(self):
        """
    returns how many elements the queue can hold
    Consumes: a queue |||| Produces: number of elements the queue can hold
    capacity: queue -> num |||| Example: capacity() -> 16
    """
        return self._capacity

    def front(self):
        """
    returns first item in queue without removing it (throws empty queue exception if empty)
    Consumes: a queue |||| Produces: the first item in the queue
    front: queue -> any |||| Example: front() -> "sarah"
    Raises: EmptyQueueException: trying to find element from empty queue
    """
        if self._count < 1:
            raise EmptyQueueException("trying to find element from empty queue")
        return self._array[self._head]

    def enqueue(self,any):
        """
    adds an arbitrary type to the end of the queue
    Consumes: a queue and an arbitrary type |||| Produces: nothing
    enqueue: queue * any -> . |||| Example: enqueue("sarah") -> the string sarah is added to the end of the queue
    """
        if self._count == self._capacity: # if full
            new_array = [None] * (self._capacity * 2)
            
            #### copy array ####
            new_count = 0
            while new_count < self._capacity:
                new_array[new_count] = self._array[self._head]
                new_count += 1
                self._head += 1
                if self._head == self._capacity:
                    self._head = 0

            self._array = new_array
            self._head = 0
            self._tail = self._count
            self._capacity *= 2
        if self._tail == self._capacity: # wrap around
            self._tail = 0
        
        self._array[self._tail] = any
        self._count += 1
        self._tail += 1

    def dequeue(self):
        """
    removes and returns first item in queue
    Consumes: a queue |||| Produces: first element in the queue
    dequeue: queue -> any |||| Example: dequeue() -> "sarah"
    Raises: EmptyQueueException: trying to dequeue from empty queue
    """
        if self._count < 1:
            raise EmptyQueueException("trying to dequeue from empty queue")
        item = self._array[self._head]
        self._array[self._head] = None
        self._head += 1
        self._count -= 1
        if self._head == self._capacity:
            self._head = 0

        if self._count <= int(self._capacity / 4) and self._capacity > 3:
            new_array = [None] * int(self._capacity / 2)
            
            #### copy array ####
            new_count = 0
            while new_count < int(self._capacity / 2):
                new_array[new_count] = self._array[self._head]
                new_count += 1
                self._head += 1
                if self._head == self._capacity:
                    self._head = 0

            self._array = new_array
            self._head = 0
            self._tail = self._count
            self._capacity = int(self._capacity / 2)

        return item





# produce stack trace when an error is encountered due to an empty queue
# Consumes: an exception |||| Produces: stack trace
# EmptyQueueException: Exception -> stack trace |||| Example: raise EmptyQueueException
class EmptyQueueException(Exception):
    def __init__(self,value):
        self.value = value
    def __str__(self):
        return repr(self.value)

# produce stack trace when an error is encountered due to an invalid input
# Consumes: an exception |||| Produces: stack trace
# InvalidInputException: Exception -> stack trace |||| Example: raise InvalidInputException
class InvalidInputException(Exception):
    def __init__(self,value):
        self.value = value
    def __str__(self):
        return repr(self.value)