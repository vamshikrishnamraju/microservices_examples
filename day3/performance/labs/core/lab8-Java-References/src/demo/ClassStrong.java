package demo;

import java.lang.ref.WeakReference;

public class ClassStrong {
	 
    public static class Referred {
        protected void finalize() {
            System.out.println("Good bye cruel world");
        }
    }
 
    public static void collect() throws InterruptedException {
        System.out.println("Suggesting collection");
        System.gc();
        System.out.println("Sleeping");
        Thread.sleep(5000);
    }
 
    public static void main(String args[]) throws InterruptedException {
        System.out.println("Creating weak references");
 
        // This is now a weak reference.
        // The object will be collected only if no strong references.
        Referred strong = new Referred();
        Referred strong2 = strong;
 
        // Attempt to claim a suggested reference.
       ClassStrong.collect();
 
        System.out.println("Removing reference");
        // The object may be collected.
        strong = null;
       ClassStrong.collect();
 
    	
		Thread.sleep(1000);
        System.out.println("Done");
    }
 
}