#!/bin/bash
echo " java -jar build/libs/JavaAgent-*.jar"
env | grep JAVA
java -version
java -jar build/libs/agenteAr4k-*.jar
exit 0
