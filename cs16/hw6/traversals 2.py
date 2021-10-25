#! /usr/bin/python3

class InvalidInputException(Exception):
    def __init__(self,value):
        self.value = value
    def __str__(self):
        return repr(self.value)


""" Preorder, Inorder, Postorder, and Breadth First Traversals of a Binary Tree """

def preorder(bt):
    """preorder: binary tree -> list[Position]
    Purpose: Runs a preoder traveral on the binary tree.
    Consumes: a binary tree
    Produces: a list of Position objects preorder
    Example:       A
       preorder(  / \  ) -> [A B C]
                 B   C
    If tree is empty, should return an empty list. If the tree
    is null, you should throw InvalidInputException.
    """
    if bt == None: # if input is null
        raise InvalidInputException("tree is null :(")
    if bt.isEmpty(): # if tree is empty
        return []
    Nodes = []
    preorderTraverse(bt.root(), Nodes, bt) # will recursively call itself
    return Nodes

# helper method to recursively traverse through tree, takes in a node, a list, and a bintree
# outputs nothing, but modifies list
def preorderTraverse(node, list, tree):
    list.append(node)
    if tree.hasLeft(node): # if has left child
        preorderTraverse(tree.left(node), list, tree)
    if tree.hasRight(node): # if has right child
        preorderTraverse(tree.right(node), list, tree)

def inorder(bt):
    """inorder: binary tree -> list[Position]
    Purpose: Runs a preoder traveral on the binary tree
    Consumes: a binary tree
    Produces: a list of Position objects inorder
    Example:       A
        inorder(  / \  ) -> [B A C]
                 B   C
    If tree is empty, should return an empty list. If the tree
    is null, you should throw InvalidInputException.
    """
    if bt == None: # if input is null
        raise InvalidInputException("tree is null :(")
    if bt.isEmpty(): # if tree is empty
        return []
    Nodes = []
    inorderTraverse(bt.root(), Nodes, bt) # will recursively call itself
    return Nodes

# helper method to recursively traverse through tree, takes in a node, a list, and a bintree
# outputs nothing, but modifies list
def inorderTraverse(node, list, tree):
    if tree.hasLeft(node): # if has left child
        inorderTraverse(tree.left(node), list, tree)
    list.append(node)
    if tree.hasRight(node): # if has right child
        inorderTraverse(tree.right(node), list, tree)

def postorder(bt):
    """postorder: binary tree -> list[Position]
    Purpose: Runs a preoder traveral on the binary tree
    Consumes: a binary tree
    Produces: a list of Position objects postorder
    Example:       A
      postorder(  / \  ) -> [B C A]
                 B   C
    If tree is empty, should return an empty list. If the tree
    is null, you should throw InvalidInputException.
    """
    if bt == None: # if input is null
        raise InvalidInputException("tree is null :(")
    if bt.isEmpty(): # if tree is empty
        return []
    Nodes = []
    postorderTraverse(bt.root(), Nodes, bt) # will recursively call itself
    return Nodes

# helper method to recursively traverse through tree, takes in a node, a list, and a bintree
# outputs nothing, but modifies list
def postorderTraverse(node, list, tree):
    if tree.hasLeft(node): # if has left child
        postorderTraverse(tree.left(node), list, tree)
    if tree.hasRight(node): # if has right child
        postorderTraverse(tree.right(node), list, tree)
    list.append(node)

def breadthfirst(bt):
    """breadthfirst: binary tree -> list[Node]
    Purpose: Runs a breadth first search on a binary tree
    Consumes: a binary tree object
    Produces: a list of Nodes in breadth first search order
    Example:
                    A
    breadthfirst(  / \  ) -> [A B C]
                  B   C
    If tree is empty, should return an empty list. If the tree
    is null, you should throw InvalidInputException.
    """
    if bt == None: # if input is null
        raise InvalidInputException("tree is null :(")
    if bt.isEmpty(): # if tree is empty
        return []
    Nodes = [] # will be list of nodes in order
    QueueNodes = [] # out "queue"

    QueueNodes.insert(0, bt.root()) # take out root
    while len(QueueNodes) > 0: # while there are still nodes to traverse
        node = QueueNodes.pop(len(QueueNodes) - 1)
        Nodes.append(node)
        if bt.hasLeft(node): # if has a left child
            QueueNodes.insert(0, bt.left(node))
        if bt.hasRight(node): # if has a right child
            QueueNodes.insert(0, bt.right(node))
    return Nodes
