#! /usr/bin/python3

class InvalidInputException(Exception):
    def __init__(self,value):
        self.value = value
    def __str__(self):
        return repr(self.value)


def increment(number):
    """increment: list -> list
    Purpose: Checks if input is valid and then calls increment helper.
    This should throw InvalidInputException if your list is empty or null.
    Consumes: A list of digits representing a number
    Produces: A list of 0's and 1's representing that number + 1
    """
    if number == None: # null input
        raise InvalidInputException("Input is null")
    if len(number) == 0: # empty list
        raise InvalidInputException("List is empty")

    return increment_helper(number)

def increment_helper(number):
    """increment: list -> list
    Purpose: Increments a binary number by 1. This is the method that recurses on itself and actually increments the number
    Consumes: a list of 0's and 1's representing a binary number, k
    Produces: a list of 0's and 1's representing k + 1
    Example:
       increment([1,1,0,0]) -> [1,1,0,1]
    """
    if number.pop() == 0: # right most digit is 0
        number.append(1)
        return number
    else: # right most digit is 1
        if len(number) == 0: # left most digit was 1
            return [1,0]
        new = increment_helper(number) # size of one less
        new.append(0)
        return new
