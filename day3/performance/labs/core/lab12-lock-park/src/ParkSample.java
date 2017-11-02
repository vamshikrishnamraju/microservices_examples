
import java.util.concurrent.locks.LockSupport; 
 
 
public class ParkSample { 
    public static void main(String[] args) throws InterruptedException { 
        Thread mainThread = Thread.currentThread(); 
        UnparkThread unparkThread = new UnparkThread(mainThread); 
        unparkThread.setDaemon(true); 
        unparkThread.setName("greet");
        unparkThread.start(); 
 
     
        for (int i = 1; i < 11; i++) { 
            System.out.println("park has set " + i + " ones"); 
            LockSupport.park(); 
        } 
        
        LockSupport.unpark(unparkThread);
        
        Thread.sleep(100000);
    } 
}