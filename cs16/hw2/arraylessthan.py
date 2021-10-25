#!/usr/bin/python3
class InvalidInputException(Exception):
    def __init__(self,value):
        self.value = value
    def __str__(self):
        return repr(self.value)

def array_less_than(p, q):
    """array_less_than: any[] * any[] -> boolean
    Consumes: two python lists
    Produces: a boolean signifying whether all elements in p are smaller than
            their corresponding elements in q
    Purpose: Check if array p and q are equal in size and p < q in contents
    Example: array_less_than([4, 0, -4, 1], [5, 2, 5, 23]) -> True
            array_less_than([1, 4, 2], [4, 1, 2]) -> False
            array_less_than([5, 3], [6, 4, 5, 3]) -> False
    """
    # error checking on input arrays -- are they valid?
    if p is None:
        raise InvalidInputException("array p is None (invalid)")
    if q is None:
        raise InvalidInputException("array p is None (invalid)")

    if len(p) == 0 and len(q) == 0: # if empty arrays
        return False
    if len(p) == len(q):
        for i, j in zip(p, q):
            if i >= j: # if not less than
                return False
    else: # if different lengths
        return False
    return True
