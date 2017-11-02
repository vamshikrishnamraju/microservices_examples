package com.fusesource.training.camel;

import com.fusesource.training.*;
import com.mycompany.cxf.jaxws.javafirst.SayHi;
import com.mycompany.cxf.jaxws.javafirst.SayHi2;

import org.apache.camel.Body;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.datatype.XMLGregorianCalendar;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enrich {


    public SayHi2  createCustomer(@Body String name) {
        
    	SayHi2 say = new SayHi2();
    	
    	say.setArg0(name);
    	return say;
    }

   

}
