package com.fusesource.camel.exercises.cbr;

import org.apache.camel.CamelContext;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class RouteByCurrencyRouterTest {

	private static final String DIRECT_SOURCE = "direct:source";
	private static final String MOCK_UNKNOWN = "mock:unknown";
	private static final String MOCK_US = "mock:us";
	private static final String MOCK_EU = "mock:eu";
	private static final String SAMPLE_DIRECTORY_PROPERTY_NAME = "sample.directory.path";

	private CamelContext camelContext;
	private ProducerTemplate producer;

	private RouteByCurrencyRouter currencyRouter;

	private MockEndpoint mockEu;
	private MockEndpoint mockUs;
	private MockEndpoint mockUnknown;

    private File sampleDataDirectory;

	@Before
	public void setUp() throws Exception {
		camelContext = new DefaultCamelContext();

		currencyRouter = new RouteByCurrencyRouter();
		currencyRouter.sourceUri = DIRECT_SOURCE;
        currencyRouter.euroSinkUri = MOCK_EU;
		currencyRouter.usdSinkUri = MOCK_US;
		currencyRouter.otherSinkUri = MOCK_UNKNOWN;

		mockEu = camelContext.getEndpoint( MOCK_EU,  MockEndpoint.class );
		mockUs = camelContext.getEndpoint( MOCK_US,  MockEndpoint.class );
		mockUnknown = camelContext.getEndpoint( MOCK_UNKNOWN,  MockEndpoint.class );

		camelContext.addRoutes( currencyRouter );
		camelContext.start();

		producer = camelContext.createProducerTemplate();

        sampleDataDirectory = new File(System.getProperty("sample.directory.path"));
	}

	@After
	public void tearDown() throws Exception {
		camelContext.stop();
	}

	@Test
	public void shouldRouteEuroPaymentsToEuroQueue() throws Exception {
		// Set expectations
		mockEu.expectedMessageCount( 1 );
		mockUs.expectedMessageCount( 0 );
		mockUnknown.expectedMessageCount( 0 );

		// Perform Test
		producer.sendBody(
            DIRECT_SOURCE,
            ExchangePattern.InOnly,
            getSampleMessage( "VariousEUPayments.xml" )
        );

		// Assert
		assertMockEndpoints( 100 );
	}

	@Test
	public void shouldRouteUsPaymentsToUsQueue() throws Exception {
		// Set expectations
		mockEu.expectedMessageCount( 0 );
		mockUs.expectedMessageCount( 1 );
		mockUnknown.expectedMessageCount( 0 );

		// Perform Test
		producer.sendBody(
            DIRECT_SOURCE,
            ExchangePattern.InOnly,
                getSampleMessage("VariousUSPayments.xml")
        );

		// Assert
		assertMockEndpoints( 100 );
	}

	@Test
	public void shouldRouteUnknownPaymentsToUnknownQueue() throws Exception {
		// Set expectations
		mockEu.expectedMessageCount( 0 );
		mockUs.expectedMessageCount( 0 );
		mockUnknown.expectedMessageCount( 1 );

		// Perform Test
		producer.sendBody(
            DIRECT_SOURCE,
            ExchangePattern.InOnly,
                getSampleMessage("VariousUnknownPayments.xml")
        );

		// Assert
		assertMockEndpoints( 100 );
	}

    private String getSampleMessage(String pFileName) throws Exception {
		StringBuffer sb = new StringBuffer(1024);

        BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(new File(sampleDataDirectory, pFileName))));

		for (String line = input.readLine(); line != null; line = input.readLine()) {
			sb.append(line);
		}

		return sb.toString();
	}

	private void assertMockEndpoints(long delayMs) throws Exception {
		// Assert
		mockEu.setResultWaitTime(delayMs);
		mockEu.assertIsSatisfied();
		mockUs.setResultWaitTime(delayMs);
		mockUs.assertIsSatisfied();
		mockUnknown.setResultWaitTime(delayMs);
		mockUnknown.assertIsSatisfied();
	}
}
