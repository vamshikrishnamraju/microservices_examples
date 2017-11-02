package com.fusesource.camel.exercises.component.file;

import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;

import java.io.File;
import java.util.Map;

// This class allows you to create FileEndpoints using information passed from the endpoint URI.
// When Camel encounters a URI prefix "filecomp" it will instantiate an instance of this class.
// This is determined by the presence of a file with the same name as the prefix in the directory
// META-INF/services/org/apache/camel/component

public class FileComponent extends DefaultComponent {
    public FileComponent() {
    }

    public FileComponent(CamelContext context) {
        super(context);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    protected Endpoint createEndpoint(String uri, String remaining, Map parameters) throws Exception {
        File file = new File(remaining);
        FileEndpoint result = new FileEndpoint(file, uri, this);
        setProperties(result, parameters);
        return result;
    }
}
