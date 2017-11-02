/**
 * Copyright 2014 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jbpm.examples.ejb;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Startup;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

import org.drools.compiler.kie.builder.impl.KieServicesImpl;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.internal.runtime.manager.cdi.qualifier.Singleton;
import org.kie.internal.runtime.manager.context.EmptyContext;

@Startup
@javax.ejb.Singleton
@TransactionManagement(TransactionManagementType.BEAN)
public class ProcessBean implements ProcessLocal {

	@Resource
	private UserTransaction ut;

	@Inject
	@Singleton
	private RuntimeManager runtimeManager;
	
	@Inject
	private Logger logger;

	private KieSession ksession;

	@PostConstruct
	public void configure() {

		RuntimeEngine runtime = runtimeManager.getRuntimeEngine(EmptyContext
				.get());
		ksession = runtime.getKieSession();

	}

	public long startProcess(String recipient) throws Exception {

		long processInstanceId = -1;

		ut.begin();

		try {
			// start a new process instance
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("recipient", recipient);
			ProcessInstance processInstance = ksession.startProcess(
					"com.sample.rewards-basic", params);

			processInstanceId = processInstance.getId();

			logger.log(Level.INFO, "Process started ... : processInstanceId = "
					+ processInstanceId);

			ut.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (ut.getStatus() == Status.STATUS_ACTIVE) {
				ut.rollback();
			}
			throw e;
		}

		return processInstanceId;
	}

	@PreDestroy
	public void shutdown() {
		if (runtimeManager != null) {
			runtimeManager.disposeRuntimeEngine(runtimeManager
					.getRuntimeEngine(EmptyContext.get()));
			runtimeManager.close();
			((KieServicesImpl) KieServices.Factory.get()).nullKieClasspathContainer();
		}
	}

}
