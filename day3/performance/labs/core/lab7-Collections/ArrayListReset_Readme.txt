clear() vs removeAll()
======================


the purpose of clear() and removeAll(Collection c) are different in API, clear() 
method is meant to reset a Collection by removing all elements, while removeAll
(Collection c) only removes elements which are present in supplied collection. 


This method is not designed to remove all elements from a Collection.

So, if your intention is to delete all elements from a Collection, then use clear(),
while if you want to remove only some elements, which are present in another Collection,
e.g. list of closed orders, then use removeAll() method .



Clear() vs null
===============

Use Clear if we likely  want to repopulate that list at some point. If it's a single use list and 
we are never going to use it again , then  consider setting to null.



------------------------------------<>--------------------------------------------------------------------------------
There is a popular and widely accepted advice in the world of software development that says:

    We  should always return an empty list instead of null!

There are two considerable advantages:

    We  eliminate the risk of a null pointer error (i.e. NullReferenceException in C#, NullPointerException in Java, etc.)

    We don't have to check for null in client code - your code becomes shorter, more readable and easier to maintain
------------------------------------<>--------------------------------------------------------------------------------
    
    