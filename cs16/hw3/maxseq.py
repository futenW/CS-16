#!/usr/bin/python3
class InvalidInputException(Exception):
    def __init__(self,value):
        self.value = value
    def __str__(self):
        return repr(self.value)

def maxseq(array):
    """maxseq: any[] -> int
    Consumes: one python list
    Produces: the greatest sum of a subarray + 180
    Example: maxseq([-1, 2, 7, -8, 13, -2]) -> 194
            maxseq([-7, -9, -10]) -> 180
            maxseq([10, -5, 200]) -> 385
    """

    # error checking on input array -- is it valid?
    if array is None:
        raise InvalidInputException("array is None (invalid)")

    if len(array) == 0: # length of array is zero
        return 180

    # if array is all negative, return 180
    all_negative = True
    for i in array:
        if i > 0:
            all_negative = False
    if all_negative == True:
        return 180

    maximum = array[0] # the maximum so far in iteration through the array
    current_ending = 0 # result if ended here

    for i in array:
        current_ending += i
        if current_ending < 0: # if negative, reset to zero
            current_ending = 0
        elif current_ending > maximum: # if this ending is the greatest possible so far
            maximum = current_ending
    
    return 180 + maximum
