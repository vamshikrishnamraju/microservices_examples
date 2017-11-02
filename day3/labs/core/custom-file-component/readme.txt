This Exercises shows how a custom Camel Endpoint can be used within a Camel Route. This exercise is self-contained
meaning it does not depend on any other component or exercises and therefore can be run standalone or being deployed
within an OSGi container like OSGi.

Deployment:

1) Running it inside an embedded Camel instance:

    mvn clean camel:run

2) Build, Install and Deploy it as OSGi bundle:

    mvn clean install

then please enter this in the Karaf Console:

    osgi:install -s mvn:com.fusesource.training/custom-file-component/2010.07.12

3) Install it as feature (need to add the features URL ahead of time, please see the camel-exercises-features project)

    features:install camel-exercises-custom-file-component

For 2) and 3) you need to deploy the a Spring Context file into the Servicemix 4 /deploy directory which can be found
under 'src/test/deploy/file.comp.spring-context.xml'. This will then create these input and output directories:

    /camel-exercises/file.comp/[in|out]

You can now copy any file inside the 'in' directory and you will see the log files as well as that the files
are copied into the 'out' directory.