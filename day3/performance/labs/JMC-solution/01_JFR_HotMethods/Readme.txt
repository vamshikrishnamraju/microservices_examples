This example illustrates the hot methods tab in JRA and JFR. 

1. Make a recording or open the prepared recording (hotmethods_hs.jfr) and check where most of the time is spent. 
2. Can you make the application run faster?

Hints below if you get stuck...













































Tip 1: You can actually make the program run a lot faster by simply changing _one_ line.

Tip 2: We spend a lot of time in the LinkedList.indexOf(Object) method.

Tip 3: Looking at the trace to the hottest method, shows that we are passing through the contains method.

Tip 4: Contains in a linked list is proportional to the number of entries.

Tip 5: A HashSet will, on average, do the trick in constant time.

Tip 6: Find out where contains is being called from (either by taking a look at the Code | Call Tree view, or just by looking at what keeps calling the contains method).

Tip 7 (total spoiler, don't read this): Change line 7 in Initiator.java to read: collection = new HashSet<Integer>(); Run and compare.
