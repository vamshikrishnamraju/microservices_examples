#!/bin/sh
cd ~/JB427/support/solutions/integrate-bc
mvn clean package
cd $JBOSS_HOME/bin
./standalone.sh -Dorg.uberfire.nio.git.dir=/home/student/JB427/support/solutions/integrate-bc-remote/repo
