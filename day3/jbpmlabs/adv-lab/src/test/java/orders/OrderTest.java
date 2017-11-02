package orders;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Persistence;

import junit.framework.Assert;
import model.Order;
import model.OrderItem;

import org.jbpm.bpmn2.handler.ServiceTaskHandler;
import org.jbpm.bpmn2.handler.SignallingTaskHandlerDecorator;
import org.jbpm.test.JBPMHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.event.process.ProcessCompletedEvent;
import org.kie.api.event.process.ProcessEventListener;
import org.kie.api.event.process.ProcessNodeLeftEvent;
import org.kie.api.event.process.ProcessNodeTriggeredEvent;
import org.kie.api.event.process.ProcessStartedEvent;
import org.kie.api.event.process.ProcessVariableChangedEvent;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEnvironment;
import org.kie.api.runtime.manager.RuntimeEnvironmentBuilder;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.manager.RuntimeManagerFactory;
import org.kie.internal.runtime.manager.context.EmptyContext;

public class OrderTest {

	private KieSession session;
	private RuntimeManager manager;

	@BeforeClass
	public static void setUpEngine() {

		JBPMHelper.startH2Server();
		JBPMHelper.setupDataSource();

	}

	@Before
	public void setUpSession() {

		RuntimeEnvironment environment = RuntimeEnvironmentBuilder.Factory
				.get()
				.newClasspathKmoduleDefaultBuilder("appBase", "order")
				.entityManagerFactory(
						Persistence
								.createEntityManagerFactory("org.jbpm.persistence.jpa"))
				.get();
		manager = RuntimeManagerFactory.Factory.get()
				.newSingletonRuntimeManager(environment);
		session = manager.getRuntimeEngine(EmptyContext.get()).getKieSession();
		String eventType = "Error-101";
		SignallingTaskHandlerDecorator signallingTaskWrapper = new SignallingTaskHandlerDecorator(
				ServiceTaskHandler.class, eventType);
		signallingTaskWrapper.setWorkItemExceptionParameterName("bad.order");
		session.getWorkItemManager().registerWorkItemHandler("Service Task",
				signallingTaskWrapper);
	}

	@After
	public void endSession() {

		if (manager != null) {
			manager.disposeRuntimeEngine(manager.getRuntimeEngine(EmptyContext
					.get()));
			manager.close();
		}
	}

	@Test
	public void testBackorder() {

		MyEventListener pel = new MyEventListener();
		session.addEventListener(pel);
		Map<String, Object> procvars = new HashMap<String, Object>();
		Order order = new Order("NC", false, false);
		order.getItems().add(new OrderItem("JY8075", 1));
		order.getItems().add(new OrderItem("FD1925", 22));
		procvars.put("order", order);
		session.startProcess("orders.Process", procvars);
		session.fireAllRules();
		@SuppressWarnings("unchecked")
		List<Order> orders = (List<Order>) pel.getVariable("orders");
		System.out.println(orders);
		Assert.assertEquals(2, orders.size());
		Assert.assertEquals((Integer) 1, order.getBackorderedItems());
		Assert.assertEquals(new BigDecimal("946.75"), order.getOrderTotal());
		Assert.assertEquals(new BigDecimal("56.81"), order.getSalesTax());
		Order backorder = (Order) pel.getVariable("backorder");
		Assert.assertEquals("BACKORDER", backorder.getStatus());
		Assert.assertEquals(new BigDecimal("24.15"), backorder.getOrderTotal());
		Assert.assertEquals(new BigDecimal("1.45"), backorder.getSalesTax());
		Assert.assertEquals(1, backorder.getItems().size());
	}

	@Test
	public void testError() {
		MyEventListener pel = new MyEventListener();
		session.addEventListener(pel);
		Map<String, Object> procvars = new HashMap<String, Object>();
		Order order = new Order("NC", false, false);
		order.getItems().add(new OrderItem("X", 1));
		procvars.put("order", order);
		session.startProcess("orders.Process", procvars);
		session.fireAllRules();
	}

	private class MyEventListener implements ProcessEventListener {

		private Map<String, Object> variables = new HashMap<String, Object>(3);

		@Override
		public void beforeProcessStarted(ProcessStartedEvent event) {
		}

		@Override
		public void afterProcessStarted(ProcessStartedEvent event) {
		}

		@Override
		public void beforeProcessCompleted(ProcessCompletedEvent event) {
		}

		@Override
		public void afterProcessCompleted(ProcessCompletedEvent event) {
		}

		@Override
		public void beforeNodeTriggered(ProcessNodeTriggeredEvent event) {
		}

		@Override
		public void afterNodeTriggered(ProcessNodeTriggeredEvent event) {
		}

		@Override
		public void beforeNodeLeft(ProcessNodeLeftEvent event) {
		}

		@Override
		public void afterNodeLeft(ProcessNodeLeftEvent event) {
		}

		@Override
		public void beforeVariableChanged(ProcessVariableChangedEvent event) {
		}

		@Override
		public void afterVariableChanged(ProcessVariableChangedEvent event) {
			variables.put(event.getVariableId(), event.getNewValue());

		}

		public Object getVariable(String name) {
			return variables.get(name);
		}

	}

}
