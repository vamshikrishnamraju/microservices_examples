/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package exC;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

import exA.BeanA;

/**
 * A set of routes that watches a directory for new orders, reads them, converts the order 
 * file into a JMS Message and then sends it to the JMS incomingOrders queue hosted 
 * on an embedded ActiveMQ broker instance.
 * 
 * From there a content-based router is used to send the order to either the
 * xmlOrders or csvOrders queue. If an order file does not end with the
 * csv, csl, or xml extension the order is sent to the badOrders queue. 
 * 
 * The Wire Tap EIP is also used to send a copy of each order to the orderAudit queue
 * for auditing.
 *
 * @author janstey
 *
 */
public class DirectDemo {

    public static void main(String args[]) throws Exception {
        // create CamelContext
        CamelContext context = new DefaultCamelContext();
        
       
        // connect to embedded ActiveMQ JMS broker
          
        // add our route to the CamelContext
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
            	
            	
            	BeanA bean = new BeanA();
            	// load file orders from src/data into the JMS queue
                from("file:src/data?noop=true")
              //  .log("output " + body())
               .transform().simple("abc")
                .bean(bean,"disp")
                 .transform().simple("xyz")
                .bean(bean,"disp")
                .to("direct:A");
               // .to("seda:A");
                      
                System.out.println("one");
                
         
                
                 from("file:src/data2?noop=true")
                 .transform().simple("lmn")
                  .bean(bean,"disp")
               .to("direct:A");
              //    .to("seda:A");
                 
             
                 System.out.println("two");
                 
                
                // from("direct:A").to("direct:final");
                
             //   from("seda:A").to("seda:final");
                
               System.out.println("three");
                
                
                 from("direct:A")
                //from("seda:A")
                //.log("output + ${body}")
                .bean(bean,"disp2");
                
              
                
            }
        });

        // start the route and let it do its work
        context.start();
        Thread.sleep(2000);

        // stop the CamelContext
        context.stop();
    }
}
