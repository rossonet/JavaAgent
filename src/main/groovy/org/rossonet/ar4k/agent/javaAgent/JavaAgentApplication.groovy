/**
 * Classe principale avvio Spring Boot
 */
package org.rossonet.ar4k.agent.javaAgent

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.jms.annotation.EnableJms

@ComponentScan("org.rossonet.ar4k")
@SpringBootApplication
@EnableJms
class JavaAgentApplication {

	public static boolean attivo = true

	static void main(String[] args) {
		SpringApplication.run JavaAgentApplication, args
		while (attivo){
			Thread.sleep(1000 * 60)
		}
	}
}
