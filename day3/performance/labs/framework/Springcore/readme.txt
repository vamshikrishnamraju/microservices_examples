Spring Start - "Where do I Start?" For Developers
David Caruana (david@caruana.co.uk)

11th Feb 2005 - Initial Revision

1. Introduction

This bundle provides a small set of code examples that demonstrate
the core features of the Spring Framework (www.springframework.org).

You won't find here a description of the Spring Framework or why
you should use it - that is best left to the Spring developers
(at their site and in resources such "J2EE Developement without EJB"
by Rod Johnson).

However, you will find a collection of concise code examples that
step you through the basic Spring capabilities.  These samples were
created during my first few days with Spring whilst trying to untangle
the large amounts of documentation and complete sample applications. I
hope that other developers who are also just starting out with Spring
will find them useful.

I've concentrated on the heart of Spring to start with; Bean
Factories and AOP.  The samples execute stand-alone so there's no need
for a web or application container which means they're really
easy to get into.  The amount of code to browse is the bare minimum.

This bundle is completely self encapsulated and includes all the
required dependencies to get going.  It was developed with Spring
Framework 1.1.4.

I may continue to provide updated bundles in the future as I continue
to learn Spring.

2. How to use this bundle?

This bundle is best used with the Eclipse IDE.  Everything has been
setup as an Eclipse Project and so the only step should be to import
the Project:

a) Unzip the bundle to a file directory of your choice
b) Start Eclipse
c) File -> Import...
d) Select Existing Project into Workspace
e) Browse to your unzipped file directory of choice
f) Hit OK and Finish
g) The Project is now imported

At this point you can browse the source code which resides in the "src"
directory or select Project | Build All to build everything.

3. Structure of the Samples

Within the "src" directory there are currently two top level packages:

ex01_bean - samples that demonstrate core Bean Factory capability
ex02_aop - samples that demonstrate core AOP capability

Within each top level package, you'll find a sample sub-package which
is typically self-contained.  Each sample contains a Main.java.

Within Eclipse, you can right-click the Main.java to Run it or
Debug it (more useful when learning) to step through the code or
set breakpoints.

The ex01_bean package is commented, however, the ex02_aop package
has yet to be commented, but the samples are still simple enough.

4. References Used

J2EE development without EJB - Rod Johnson
Spring Framework 1.1.4 Reference Guide
Spring Application Samples that come with Spring 1.1.4

5. Feedback

I'm not an expert on Spring (I've spent 3 days with it so far), so if
you find any mistakes or can suggest improvements, please get in
contact (david@caruana.co.uk).

6. Disclaimer

The enclosed examples are provided for tutorial purposes only.

