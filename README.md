# Rossonet Java Agent

![alt text](http://www.rossonet.org/wp-content/uploads/2015/01/logoRossonet4.png "Rossonet")

[http://www.rossonet.org](http://www.rossonet.org)

Licenza: [LGPL 3.0](https://www.gnu.org/licenses/lgpl.html)
Per maggiori dettagli sulla licenza rimando a [questa voce](http://it.wikipedia.org/wiki/GNU_Lesser_General_Public_License) di Wikipedia

![alt text](https://www.gnu.org/graphics/gplv3-88x31.png "LGPL Logo")


## Esecuzione Jar

If you use the Spring Boot Maven or Gradle plugins to create an executable jar you can run your application using java -jar. For example:


```
$ java -jar target/myproject-0.0.1-SNAPSHOT.jar
```

It is also possible to run a packaged application with remote debugging support enabled. This allows you to attach a debugger to your packaged application:


```
$ java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n \
       -jar target/myproject-0.0.1-SNAPSHOT.jar
```

## Documentazione online

[Documentazione aggiornata del progetto](https://rossonet.github.io/JavaAgent)


## Riferimenti esterni

[Documentazione NodeMcu](http://nodemcu.readthedocs.io/en/latest/)

[Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/index.html)

[RAM](https://github.com/ar4k/RAM)

[Console Ar4k](https://github.com/rossonet/ConsoleAr4k)

[DnsJava](http://www.dnsjava.org/dnsjava-current/doc/)
