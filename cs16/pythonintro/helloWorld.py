#! /usr/bin/python3

print("Hello world!")

def say_hello(name):
    """say_hello: string -> nothing
    Purpose: prints a greeting of the form "Hello <name>!"
    Example: say_hello("Doug") -> "Hello Doug!"
    """
    print("Hello " + name + "!") # this is the function body

def factorial(n):
    """factorial: int [n] → int [n!]
    Purpose: Returns the factorial of the argument
    Example: factorial(4) -> 24
    Example: factorial(3) -> 6
    Example: factorial(2) -> 2
    Example: factorial(1) -> 1
    Example: factorial(0) -> 1
    """
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


if __name__ == "__main__":
    say_hello("Futen") # substitute your name
    test_factorial()