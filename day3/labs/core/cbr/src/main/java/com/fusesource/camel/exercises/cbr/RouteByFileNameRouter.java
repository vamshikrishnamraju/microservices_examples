package com.fusesource.camel.exercises.cbr;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.io.File;

public class RouteByFileNameRouter
    extends RouteBuilder
        implements InitializingBean, DisposableBean {
    private static Logger LOG = LoggerFactory.getLogger(RouteByFileNameRouter.class);

    private String sourceDirectory;
    private String sinkDirectory;
    private boolean createDirectories;

    private String sourceUri;
    private String eurSinkUri;
    private String usdSinkUri;
    private String otherSinkUri;

    public void setSourceDirectory( String pSourceDirectory ) {
        sourceDirectory = pSourceDirectory;
    }

    public void setSinkDirectory( String pSinkDirectory ) {
        sinkDirectory = pSinkDirectory;
    }

    public void setCreateDirectories( boolean pCreateDirectories ) {
        createDirectories = pCreateDirectories;
    }

	@Override
	public void configure() throws Exception {
		errorHandler(noErrorHandler());
		
		from( sourceUri )
			.convertBodyTo( String.class )
            // An alternative way to print out logging statements is to hand it to the log component through an URI
			.to( "log:com.fusesource.camel.exercises.cbr?level=DEBUG&showHeaders=true" )
			.choice()
				.when()
                    .simple( "${file:onlyname} == 'EUPayments.txt'" )
                        .log( "This is an Euro Payment: ${file:onlyname}" )
	    				.to( eurSinkUri )
				.when()
                    .simple( "${file:onlyname} == 'USPayments.txt'" )
                        .log( "This is an USD Payment: ${file:onlyname}" )
	    				.to( usdSinkUri )
				.otherwise()
                    .log( "This is an Other Currency Payment: ${file:onlyname}" )
					.to( otherSinkUri );
		
        LOG.debug("Starting listening for files in directory '" + sourceUri + "'");
	}

    @Override
	public void afterPropertiesSet() throws Exception {
        // Check the given source directory to make sure it is usable and eventually create the source URI from it
        if( sourceDirectory == null || "".equals( sourceDirectory.trim() ) ) {
            throw new BeanInitializationException(
                "You must specify a value for source directory (sourceDirectory)"
            );
        }

        File lSourceDirectory = new File( sourceDirectory );
        if( !lSourceDirectory.exists() ) {
            boolean lNoDirectory = false;
            if( createDirectories ) {
                lNoDirectory = lSourceDirectory.mkdirs();
            }
            if( !lNoDirectory ) {
                throw new BeanInitializationException( "Given source directory: '" + sourceDirectory + "' is not a directory" );
            }
        }

        if( !lSourceDirectory.canRead() ) {
            throw new BeanInitializationException( "Given source directory: '" + sourceDirectory + "' cannot be read" );
        }
        sourceUri = "file:" + sourceDirectory;

// Check the given sink directory to make sure it is usable and eventually create the sink URI from it
        if( sinkDirectory == null || "".equals( sinkDirectory.trim() ) ) {
            throw new BeanInitializationException(
                "You must specify a value for sink directory (sinkDirectory)"
            );
        }

        File lSinkDirectory = new File( sinkDirectory );
        if( !lSinkDirectory.exists() ) {
            boolean lNoDirectory = false;
            if( createDirectories ) {
                lNoDirectory = lSinkDirectory.mkdirs();
            }
            if( !lNoDirectory ) {
                throw new BeanInitializationException( "Given sink directory: '" + sinkDirectory + "' is not a directory" );
            }
        }

        if( !lSinkDirectory.canWrite() ) {
            throw new BeanInitializationException( "Given sink directory: '" + sinkDirectory + "' cannot be written to" );
        }

        eurSinkUri = "file:" + sinkDirectory + "/eur/?fileExist=Append&fileName=euro-${date:now:yyyyMMdd}.xml";
        usdSinkUri = "file:" + sinkDirectory + "/usd/?fileExist=Append&fileName=usd-${date:now:yyyyMMdd}.xml";
        otherSinkUri = "file:" + sinkDirectory + "/other/?fileExist=Append&fileName=other-${date:now:yyyyMMdd}.xml";
	}

    @Override
	public void destroy() throws Exception {
        LOG.debug("RouteByFileNameRouter shutting down.");
	}
}
