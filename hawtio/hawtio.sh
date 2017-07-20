#!/bin/bash
if (($(java -version 2>&1 | grep version | sed "s/^.*1\.\(.\{1\}\)\..*$/\1/") > 7));
then 
	java -jar hawtio-app-1.5.1.jar
else 
	echo necessita di Java da versione 1.8 in su
	/etc/alternatives/jre_1.8.0/bin/java -jar hawtio-app-1.5.1.jar
fi


