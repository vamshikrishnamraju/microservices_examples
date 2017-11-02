package hello;

import java.util.HashMap;
import java.util.Map;

import org.drools.compiler.kproject.ReleaseIdImpl;
import org.kie.api.KieServices;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class HelloProcessTest {

	public static void main(String[] args) {
		System.setProperty("kie.maven.settings.custom", "/home/student/JB427/support/solutions/integrate-bc-remote/settings.xml");
		ReleaseId id = new ReleaseIdImpl("org.jboss.jb427", "hello", "LATEST");
		KieServices services = KieServices.Factory.get();
		KieContainer container = services.newKieContainer(id);

		KieSession ksession = container.newKieSession();
		Map<String, Object> params = new HashMap<String, Object>();
		Person person = new Person();
		person.setName("Jack Jones");
		params.put("person", person);
		ksession.startProcess("hello.hello", params);
		ksession.fireAllRules();

	}

}
