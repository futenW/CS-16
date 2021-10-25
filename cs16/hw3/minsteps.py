#!/usr/bin/python3
class InvalidInputException(Exception):
    def __init__(self,value):
        self.value = value
    def __str__(self):
        return repr(self.value)

def minsteps(array):
    """minsteps: any[] -> int
    Consumes: one python list
    Produces: the min number of steps to get from the first to the last element
    in the array
    Example: minsteps([3, 1, 2, 0, 8]) -> 2
    minsteps([1, 3, 6, 1, 0, 9]) -> 3
    minsteps([0, 5, 2, 7, 1, 3]) -> None
    """

    # error checking on input array -- is it valid?
    if array is None or len(array) == 0:
        raise InvalidInputException("array is None (invalid)")

    if len(array) == 1: # only one element
        return 0
    if array[0] < 1: # can't reach end
        return None
    min_steps = [None] * len(array) # array of same size that keeps track of min steps to get to each index
    min_steps[0] = 0
    
    for i in min_steps:
        if min_steps.index(i) != 0: # first element already initialized
            min = len(array)
            count_j = 0 # array.index(j) always returned 0 for some reason below??? so used this instead criiiiiiiiiiiiiii tHe lOsT hOuRs oF mY lYfEeeeee
            for j in array:
                if count_j < min_steps.index(i): # for every element before i
                    if j >= min_steps.index(i) - count_j:
                        if min_steps[count_j] < min: # if this element has less steps than current min
                            min = min_steps[count_j]
                count_j += 1
            min_steps[min_steps.index(i)] = min + 1

    if min_steps[len(min_steps) - 1] == len(min_steps) + 1: # if end cannot be reached
        return None
    return min_steps[len(min_steps) - 1] # return last element of min_steps

    """
    iterative approach
    """
    # indexCurrent = len(array) - 1 # last element
    # jumpCount = 0

    # while indexCurrent != 0:
    #     farthestIndex = -1 # if no possible jumps, remains -1

    #     for i in array:
    #         if i >= indexCurrent - array.index(i): # if element's value is greater than distance to indexCurrent
    #             if indexCurrent - array.index(i) > farthestIndex: # if this element is farthest away from indexCurrent
    #                 farthestIndex = indexCurrent = array.index(i)

    #     indexCurrent = farthestIndex
    #     jumpCount += 1
    #     if farthestIndex == -1:
    #         return None
    
    # return jumpCount