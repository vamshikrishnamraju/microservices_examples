import java.util.concurrent.TimeUnit; 
import java.util.concurrent.locks.LockSupport; 
 
public class UnparkThread extends Thread{ 
    private Thread mainThreadHolder; 
 
    public UnparkThread(Thread mainThreadHolder) { 
        this.mainThreadHolder = mainThreadHolder; 
    } 
 
    @Override 
    public void run() { 
    	  System.out.println("inside"); 
        while (true){ 
            boolean isMainThreadInterrupted = mainThreadHolder.isInterrupted(); 
            if(isMainThreadInterrupted){ 
                System.out.println("main thread is interrupted"); 
                break; 
            } 
            try { 
                TimeUnit.MICROSECONDS.sleep(1); 
            } catch (InterruptedException e) { 
                System.out.println(e.getMessage()); 
            } 
           LockSupport.unpark(mainThreadHolder); 
            
        } 
    } 
} 