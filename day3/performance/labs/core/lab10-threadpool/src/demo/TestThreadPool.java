package demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreadPool {  
    public static void main(String[] args) throws InterruptedException {  
       ExecutorService executor = Executors.newScheduledThreadPool(5);//creating a pool of 5 threads  
       for (int i = 0; i < 100; i++) {  
           Runnable worker = new WorkerThread("" + i);  
           executor.execute(worker);//calling execute method of ExecutorService  
         }  
       executor.shutdown();  
       while (!executor.isTerminated()) {   }  
 
       System.out.println("Finished all threads");  
       
       Thread.sleep(1000000);
   }  
}  
