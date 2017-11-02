This Exercises shows how an Camel Content Based Router can be used within a Camel Route. This exercise is self-contained
meaning it does not depend on any other component or exercises and therefore can be run standalone or being deployed
within an OSGi container like OSGi.

Deployment:

1) Running it inside an embedded Camel instance:

    mvn clean camel:run

2) Build, Install and Deploy it as OSGi bundle:

    mvn clean install

then please enter this in the Karaf Console:

    osgi:install -s mvn:com.fusesource.training/cbr/2010.07.12

3) Install it as feature (need to add the features URL ahead of time, please see the camel-exercises-features project)

    features:install camel-exercises-cbr

For 2) and 3) the input and output directories are now created under:

    /camel-exercises/cbr/[in|out]/[csv|xml]

You can now copy any XML Payments file into this directory which follows the Payment.xsd schema or the
CSV payment structure.