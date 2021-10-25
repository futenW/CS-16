#!/usr/bin/python3

#*********DO NOT CHANGE THIS CODE****************
class InvalidInputException(Exception):

	def __init__(self, value):
		self.value = value

	def __str__(self):
		return repr(self.value)

#************************************************


def fibonacci():
    """fibonacci: . -> .
    Purpose: Prints out the first 100 fibonacci numbers
    """
    n1, n2 = 1, 1
    print(n1)
    print(n2)
    count = 3

    while count < 99:
        print(n1 + n2)
        n2 = n1 + n2
        n1 = n2 - n1
        count += 1

def factorial(n):
    """factorial: int [n] -> int [n!]
    Purpose: Returns the factorial of the argument
    Example: factorial(4) -> 24
    Example: factorial(3) -> 6
    Example: factorial(2) -> 2
    Example: factorial(1) -> 1
    Example: factorial(0) -> 1
    """
    if n < 0:
        raise InvalidInputException('input must be greater than or equal to 0')

    if n == 0:
        return 1
    else:
        return n * factorial(n - 1)

def test_factorial():
    assert factorial(4) == 24, "Test failed: Factorial of 4 is 24"
    assert factorial(3) == 6, "Test failed: Factorial of 3 is 6"
    assert factorial(2) == 2, "Test failed: Factorial of 2 is 2"
    assert factorial(1) == 1, "Test failed: Factorial of 1 is 1"
    assert factorial(0) == 1, "Test failed: Factorial of 0 is 1"

# Please put all other executable code and your asserts to test here
if __name__ == '__main__':
    fibonacci()
    test_factorial()
