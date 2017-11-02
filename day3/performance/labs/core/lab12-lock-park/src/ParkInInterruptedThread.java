import java.util.concurrent.locks.LockSupport; 

public class ParkInInterruptedThread { 
    public static void main(String[] args) { 
        Thread mainThread = Thread.currentThread(); 
        //mainThread.interrupt(); 
 
        LockSupport.park(); 
 
        System.out.println("park don't work when current thread is interrupted"); 
    } 
}
