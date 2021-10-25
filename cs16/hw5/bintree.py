#!/usr/bin/python3
# bintree.py
from queue import Queue

""" Binary Tree module
Implement a node-and-link based Binary Tree structure
"""

class Node:

    def __init__(self, parent, value) : #TODO
        # Input: Node (implicit argument), parent: Node, value: anything |||| Output: Nothing |||| Purpose: constructor for a Node
        self._parent = parent
        self._value = value

        self._left = None
        self._right = None

        if parent == None: # root node
            self._depth = 0
        else:
            self._depth = parent.depth() + 1 # gets parent node's depth in constant time and adds to that

    def parent(self): #TODO
        # Input: Node (implicit argument) |||| Output: Node |||| Purpose: get the parent of this Node (if possible)
        return self._parent

    def left(self): #TODO
        # Input: Node (implicit argument) |||| Output: Node |||| Purpose: get the left child of this node (if possible)
        return self._left

    def right(self): #TODO
        # Input: Node (implicit argument) |||| Output: Node |||| Purpose: get the right child of this node (if possible)
        return self._right

    def addLeft(self, value) : #TODO
        # Input: Node (implicit argument), value: anything |||| Output: Node (the left child) |||| Purpose: add a left child to this
        # node with the given value if there isn't one already and return it. If there is one already, just return the current one
        if self._left == None: # if no left node
            self._left = Node(self, value)
        return self._left

    def addRight(self, value) : #TODO
        # Input: Node (implicit argument), value: anything |||| Output: Node (the right child) |||| Purpose: add a right child to this
        # node with the given value if there isn't one already and return it. If there is one already, just return the current one
        if self._right == None: # if no right node
            self._right = Node(self, value)
        return self._right

    def hasLeft(self): #TODO
        # Input: Node (implicit argument) |||| Output: boolean |||| Purpose: return whether this node has a left child
        if self._left == None:
            return False
        else: # has left node
            return True

    def hasRight(self): #TODO
        # Input: Node (implicit argument) |||| Output: boolean |||| Purpose: return whether the node has a right child
        if self._right == None:
            return False
        else: # has right node
            return True

    def value(self): #TODO
        # Input: Node (implicit argument) |||| Output: anything |||| Purpose: return the value stored at this Node
        return self._value

    def depth(self): #TODO
        # Input: Node (implicit argument) |||| Output: int |||| Purpose: return the depth of this node in the tree in O(1)
        return self._depth

#############################################################
#############################################################
    def __str__(self):
        # Input: Node (implicit argument) |||| Output: String representation of the Node |||| Purpose: printing
        output = ""
        output += "(val: "
        output += repr(self.value())
        output += "; L: "
        if self.hasLeft():
            output += str(self.left())
        else:
            output += "<nothing>"
        output += "; R: "
        if self.hasRight():
            output += str(self.right())
        else:
            output += "<nothing>"
        output += ")"
        return output
#############################################################
#############################################################

class BinTree: # Binary Tree class. Implement a node-and-link based Binary Tree structure

    def __init__(self) : #TODO
        # Input: BinTree (implicit argument) |||| Output: Nothing |||| Purpose: Creates an empty binary tree
        self._size = 0
        self._root = None
        self._height = -1 # aka undefined

    def root(self):  #TODO
        # Input: BinTree (implicit argument) |||| Output: Node |||| Purpose: return the root node
        # Throw a EmptyBinTreeException if the tree is empty
        if self._root == None: # empty tree
            raise EmptyBinTreeException("tree is empty")
        else:
            return self._root

    def parent(self, node): #TODO
        # Input: BinTree (implicit argument), node: Node |||| Output: Node |||| Purpose: return the parent node
        # Exceptions: throw an InvalidInputException if input is None
        if node == None:
            raise InvalidInputException("none is None")
        else:
            return node.parent()

    def children(self, node): #TODO
        # Input: BinTree (implicit argument), node: Node |||| Output: List of child nodes |||| Purpose: returns a
        # list of direct child nodes
        # Exceptions: throw an InvalidInputException if input is None
        if node == None:
            raise InvalidInputException("node is None")
        else:
            children_list = []
            if node.hasLeft() == True: # if left child exists
                children_list.append(node.left())
            if node.hasRight() == True: # if right child exists
                children_list.append(node.right())
            return children_list

    def isEmpty(self): #TODO
        # Input: BinTree (implicit argument) |||| Output: boolean |||| Purpose: return true if the tree is empty, false otherwise O(1)
        return self._size == 0

    def size(self): #TODO
        # Input: BinTree (implicit argument) |||| Output: int |||| Purpose: return the size of the tree in O(1)
        return self._size

    def height(self): #TODO
        # Input: BinTree (implicit argument) |||| Output: int |||| Purpose: return the height of the tree in O(1) time
        # Exceptions: throw an EmptyBinTreeException if the height is undefined
        if self._root == None: # tree is empty
            raise EmptyBinTreeException("height is undefined")
        else:
            return self._height # height is constantly updated appropriately as new nodes are added

    def isInternal(self, node): #TODO
        # Input: BinTree (implicit argument), node: Node |||| Output: boolean |||| Purpose: return whether the node is internal.
        # Exceptions: throw an InvalidInputException if input is None
        if node == None:
            raise InvalidInputException("node is None")
        else:
            return node.hasLeft() or node.hasRight() # has to have at least one child

    def isExternal(self, node): #TODO
        # Input: BinTree (implicit argument), node: Node |||| Output: boolean |||| Purpose: return whether the node is external.
        # Exceptions: throw an InvalidInputException if input is None
        if node == None:
            raise InvalidInputException("node is None")
        else:
            return not (node.hasLeft() or node.hasRight()) # can't have any children

    def isRoot(self, node): #TODO
        # Input: BinTree (implicit argument), node: Node |||| Output: boolean |||| Purpose: return whether the node is the root
        # Exceptions: throw an InvalidInputException if input is None
        if node == None:
            raise InvalidInputException("node is None")
        else:
            return node == self._root # must refer to same node (root)

    def left(self, node): #TODO
        # Input: BinTree (implicit argument), node: Node |||| Output: Node |||| Purpose: get the left child of the node (if possible)
        # Exceptions: throw an InvalidInputException if input is None
        if node == None:
            raise InvalidInputException("node is None")
        else:
            return node.left()

    def right(self, node):  #TODO
        # Input: BinTree (implicit argument), node: Node |||| Output: Node |||| Purpose: get the right child of the node (if possible)
        # Exceptions: throw an InvalidInputException if input is None
        if node == None:
            raise InvalidInputException("node is None")
        else:
            return node.right()

    def hasLeft(self, node): #TODO
        # Input: BinTree (implicit argument), node: Node |||| Output: boolean |||| Purpose: return whether the node has a left child
        # Exceptions: throw an InvalidInputException if input is None
        if node == None:
            raise InvalidInputException("node is None")
        else:
            return node.hasLeft() # node's function

    def hasRight(self, node): #TODO
        # Input: BinTree (implicit argument), node: Node |||| Output: boolean |||| Purpose: return whether the node has a right child
        # Exceptions: throw an InvalidInputException if input is None
        if node == None:
            raise InvalidInputException("node is None")
        else:
            return node.hasRight() # node's function

    def addRoot(self, e): #TODO
        # Input: BinTree (implicit argument), e: anything |||| Output: Node (the root node) |||| Purpose: add a root to the tree only
        # if there isn't one already and return it. If there is one already, just return the current one
        if self._root == None:
            self._root = Node(None, e) # create new node with value e
            self._size += 1
            self._height += 1
        return self._root

    def addLeft(self, node, e): #TODO
        # Input: BinTree (implicit argument), node: Node, e: anything |||| Output: the left child of the node |||| Purpose: add a
        # left child to the node only if there isn't one already and return it. If there is one already, just return the current one
        # Exceptions: throw an InvalidInputException if node input is None
        if node == None:
            raise InvalidInputException("node is None")
        else:
            if node.hasLeft() == False:
                node.addLeft(e) # create new node with node's function
                self._size += 1
                if (node.depth() + 1) > self._height: # if this node's depth is greater than any other currently on tree
                    self._height = node.depth() + 1
            return node.left()

    def addRight(self, node, e): #TODO
        # Input: BinTree (implicit argument), node: Node, e: anything |||| Output: the right child of the node |||| Purpose: add a
        # right child to the node only if there isn't one already and return it. If there is one already, return it
        # Exceptions: throw an InvalidInputException if node input is None
        if node == None:
            raise InvalidInputException("node is None")
        else:
            if node.hasRight() == False:
                node.addRight(e) # create enw node with node's function
                self._size += 1
                if (node.depth() + 1) > self._height: # if this node's depth is greater than any other currently on tree
                    self._height = node.depth() + 1
            return node.right()


































#############################################################
#############################################################
    """ Helper methods for tree visualization.
    You DON'T need to touch these """

    def __str__(self):
        """
        Input: BinTree (implicit argument)
        Output: String representation of BinTree
        Purpose: printing
        """
        toReturn = 'Size: ' + str(self.size()) + '\n'
        toReturn += 'Height: ' + str(self.height()) + '\n'
        toReturn += str(self.root())
        return toReturn

    def graphic(self):
        """Returns a representation of this graph as a .dot file.

        In other words, if you pass the string returned by this method into
        the program DOT (or, better yet, NEATO), you can get an image file
        of the graph."""
        strs = ["graph\n{\n"]

        def annex_vertex(v):
            strs.append("\t" + str(v.value()) + ";\n")

        def annex_edge(v):
            if v.hasLeft():
                strs.append("\t" + str(v.value()) + "--" + str(v.left().value()) + ";\n")
            if v.hasRight():
                strs.append("\t" + str(v.value()) + "--" + str(v.right().value()) + ";\n")

        self.parseVerts(annex_vertex, annex_edge)
        strs.append("}\n")
        return ''.join(strs)

    def popup(self):
        """Opens a new window with this graph rendered by DOT.
        Sequential calls to this function will show the window
        once at a time. """
        import os
        tmp = open("./.tmpgraph", "w+")
        tmp.write(self.graphic())
        tmp.close()
        os.system("dot -Tpng ./.tmpgraph | display")


    def parseVerts(self, f1, f2):
        Q = Queue()
        Q.put(self.root())
        while not Q.empty():
            v = Q.get()
            f1(v)
            f2(v)
            if v.hasLeft():
                Q.put(v.left())
            if v.hasRight():
                Q.put(v.right())

class EmptyBinTreeException(Exception):
    def __init__(self, value):
        self.value = value
    def __str__(self):
        return repr(self.value)

class InvalidInputException(Exception):
    def __init__(self,value):
        self.value = value
    def __str__(self):
        return repr(self.value)
#############################################################
#############################################################