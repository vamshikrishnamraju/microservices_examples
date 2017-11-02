package com.fusesource.camel.exercises.cbr;

import org.apache.camel.Endpoint;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class RouteByCurrencyRouterTest2 extends CamelTestSupport {

    @EndpointInject(uri = "direct:source")
    Endpoint directEndpoint;

    @EndpointInject(uri = "mock:unknown")
    MockEndpoint mockUNKEndpoint;

    @EndpointInject(uri = "mock:us")
    MockEndpoint mockUSEndpoint;

    @EndpointInject(uri = "mock:eu")
    MockEndpoint mockEUEndpoint;

    private static final String SAMPLE_DIRECTORY_PROPERTY_NAME = "sample.directory.path";

    @Produce(uri = "direct:source")
    private ProducerTemplate producer;

    private RouteByCurrencyRouter currencyRouter;

    private static File sampleDataDirectory = new File(System.getProperty("sample.directory.path"));

    @Override
    protected RouteBuilder createRouteBuilder() {
        RouteByCurrencyRouter routeByCurrencyRouter = new RouteByCurrencyRouter();
        routeByCurrencyRouter.setSourceUri("direct:source");
        routeByCurrencyRouter.setEuroSinkUri("mock:eu");
        routeByCurrencyRouter.setUsdSinkUri("mock:us");
        routeByCurrencyRouter.setOtherSinkUri("mock:unknown");
        return routeByCurrencyRouter;
    }


    @Test
    public void shouldRouteEuroPaymentsToEuroQueue() throws Exception {
        // Set expectations
        mockEUEndpoint.expectedMessageCount(1);
        mockUSEndpoint.expectedMessageCount(0);
        mockUNKEndpoint.expectedMessageCount(0);

        // Perform Test
        producer.sendBody(directEndpoint, getSampleMessage("VariousEUPayments.xml"));

        // Assert
        assertMockEndpoints(100);
    }

    @Test
    public void shouldRouteUsPaymentsToUsQueue() throws Exception {
        // Set expectations
        mockEUEndpoint.expectedMessageCount(0);
        mockUSEndpoint.expectedMessageCount(1);
        mockUNKEndpoint.expectedMessageCount(0);

        // Perform Test
        producer.sendBody(
                getSampleMessage("VariousUSPayments.xml")
        );

        // Assert
        assertMockEndpoints(100);
    }

    @Test
    public void shouldRouteUnknownPaymentsToUnknownQueue() throws Exception {
        // Set expectations
        mockEUEndpoint.expectedMessageCount(0);
        mockUSEndpoint.expectedMessageCount(0);
        mockUNKEndpoint.expectedMessageCount(1);

        // Perform Test
        producer.sendBody(
                getSampleMessage("VariousUnknownPayments.xml")
        );

        // Assert
        assertMockEndpoints(100);
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
        mockEUEndpoint.setResultWaitTime(delayMs);
        mockEUEndpoint.assertIsSatisfied();
        mockUSEndpoint.setResultWaitTime(delayMs);
        mockUSEndpoint.assertIsSatisfied();
        mockUNKEndpoint.setResultWaitTime(delayMs);
        mockUNKEndpoint.assertIsSatisfied();
    }
}
