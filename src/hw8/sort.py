#!/usr/bin/python3

class InvalidInputException(Exception):
    def __init__(self,value):
        self.value = value
    def __str__(self):
        return repr(self.value)

    
def insertion_sort(array):
    """
    insertion_sort: int array -> int array
	Purpose: Sort the input array of integers in descending order using the insertion sort algorithm
	Consumes: an array of integers
	Produces: an array of integers sorted in descending order
	Example: insertion_sort([4,5,1,3,2]) -> [5,4,3,2,1]
	Throws: InvalidInputException if list is None
	"""
    if array == None:
        raise InvalidInputException("list is None")

    length = len(array) # for indexing through array
    for i in range(1, length):
        j = i - 1 # start at index one before i
        k = array[i]
        while k > array[j] and j >= 0: # while j has not reached beginning
            array[j+1] = array[j]
            j = j - 1 # continously decrement j
        array[j+1] = k
    return array


def selection_sort(array):
    """
    selection_sort: int array -> int array
	Purpose: Sort the input array of integers in descending order using the selection sort algorithm
	Consumes: an array of integers
	Produces: an array of integers sorted in descending order
	Example: selection_sort([4,5,1,3,2]) -> [5,4,3,2,1]
	Throws: InvalidInputException if list is None
	"""
    if array == None:
        raise InvalidInputException("list is None")

    length = len(array) # for indexing through array
    for i in range(length):
        max = i
        for j in range(i + 1, length):
            if array[j] > array[max]: # if element in question is greater than current found maximum
                max = j
        temp = array[i] # swappie
        array[i] = array[max] # swappie
        array[max] = temp # switcharoo
    return array

def merge_sort(array):
    """merge_sort: int array -> int array
        Purpose: Sort the input array of integers in descending order using the merge sort algorithm
        Example: merge_sort([4,5,1,3,2]) -> [5,4,3,2,1]
        Throws: InvalidInputException if list is None
    """
    if array == None:
        raise InvalidInputException("list is None")

    if len(array) > 1:
        leftTraverse = 0 # for traversing left half
        rightTraverse = 0 # for traversing right half
        main = 0 # for main list, to iterate
        middle = len(array) // 2 # divide array here

        r = array[middle:]
        l = array[:middle]
        merge_sort(l) # call self on left half
        merge_sort(r) # call self on right half

        # copy over
        while rightTraverse < len(r) and leftTraverse < len(l):
            if r[rightTraverse] > l[leftTraverse]: # right used
                array[main] = r[rightTraverse]
                rightTraverse += 1 # increment iterator
            else: # left used
                array[main] = l[leftTraverse]
                leftTraverse += 1 # increment iterator
            main += 1

        # everything else
        while rightTraverse < len(r):
            array[main] = r[rightTraverse]
            main += 1
            rightTraverse += 1
        while leftTraverse < len(l):
            array[main] = l[leftTraverse]
            main += 1
            leftTraverse += 1

    return array