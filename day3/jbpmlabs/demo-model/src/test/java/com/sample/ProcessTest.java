package com.sample;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.Assert;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;

/**
 * This is a sample file to test a process.
 */
public class ProcessTest  {

	@Test
	public void testProcess() {
		KieSession session = KieServices.Factory.get().newKieClasspathContainer().newKieSession("orders");
		Order order = new Order();
		order.setAmount(15);
		Map<String,Object> procVars = new HashMap<String, Object>(1);
		procVars.put("order", order);
		session.startProcess("store.calcDiscount", procVars);
		session.fireAllRules();
		System.out.println("Discount is " + order.getDiscount());
		Assert.assertEquals((Integer) 5, order.getDiscount());
	}

}
