#!/usr/bin/python3
class InvalidInputException(Exception):
    def __init__(self,value):
        self.value = value
    def __str__(self):
        return repr(self.value)

def max_word(s):
    """max_word: string -> int
    Purpose: Find the number of occurences of the most common word in a string
    Consumes: a string
    Produces: an int, the number of occurences of the most common word in the string
    Example: max_word("hello world") -> 1
             max_word("the quick brown fox jumped over the lazy dog") -> 2
    """
    # error checking on input string -- is it valid?
    if s is None or s == "":
        raise InvalidInputException("string is invalid")

    words = s.split(" ")
    if words[0] == "": # if string had only spaces
        return 0
    count = {}
    highest = 0 # highest occurences of a word
    for i in words: # not sure how to initialize keys so did this
        count[i] = 0
    for i in words: # tally
        count[i] = count[i] + 1
    for i, j in count.items(): # find highest occurred word
        if j > highest:
            highest = j
    return highest
