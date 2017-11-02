package demo;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Unit test for the Hello Demo.
 */
public class HelloTest  {

	@Test
	public void testProcess() {
		KieContainer container = KieServices.Factory.get().getKieClasspathContainer();
		KieSession ksession = container.newKieSession("helloSession");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("person", new Person("John Smith"));
		ksession.startProcess("demo.Hello", params);
	}

}
