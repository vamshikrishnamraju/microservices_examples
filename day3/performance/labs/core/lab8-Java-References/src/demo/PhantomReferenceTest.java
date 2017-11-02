package demo;

	import java.lang.ref.PhantomReference;
	import java.lang.ref.Reference;
	import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;
	
/**
 Assume we have user in an application, and whenever user logins an huge image loads[say profile photo].
 We need to unload the image, whenever user logs out.
 */
	public class PhantomReferenceTest 
	{
		
		public static void main(String... args) throws InterruptedException
		{
			ReferenceQueue rq= new ReferenceQueue(); 
			A a= new A();
			a.s="hello";
			PhantomReference r= new PhantomReference(a,rq);
		
			a=null;
			

		    
			System.gc();	
		
			Map<Reference,String> tasks = new HashMap<Reference,String>();
			tasks.put(r, "do cleanup");
		   
			Reference ref= (Reference) rq.remove();
			
		//	System.out.println(ref + " "+r);
			if (ref != null) System.out.println(tasks.remove(ref));
			
			
			
			}
	}
	
	class A{
		String s;
	}
	

