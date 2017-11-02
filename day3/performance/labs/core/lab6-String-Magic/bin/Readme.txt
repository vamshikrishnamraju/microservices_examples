String object is immutable whereas StringBuffer/StringBuilder objects 
are mutable.


StringBuffer and StringBuilder have the same methods with one difference
and that's of synchronization. StringBuffer is synchronized( which means
 it is thread safe and hence you can use it when you implement threads 
for your methods) whereas StringBuilder is not synchronized( which implies
it isn't thread safe).

StringBuffer is less efficient than StringBuilder.
