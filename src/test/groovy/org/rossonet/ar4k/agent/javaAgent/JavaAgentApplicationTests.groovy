package org.rossonet.ar4k.agent.javaAgent

import org.apache.camel.CamelContext;
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan
import static org.junit.Assert.*
import org.springframework.test.context.TestPropertySource

@RunWith(SpringRunner)
@SpringBootTest
@ComponentScan("org.rossonet.ar4k")
class JavaAgentApplicationTests {

	@Autowired
	ApplicationContext applicationContext

	@Autowired
	CamelContext camelContext

	@Autowired
	Anima anima

	@Test
	void contestoCaricato() {
		assertNotNull(anima)
	}

	@Test
	void configuraBroker(){
		anima.avviaBroker("test", 56789)
	}

	@Test
	void configuraRotta(){
		anima.aggiungiCamel("timer:data?fixedRate=true&repeatCount=1&delay=150&period=180000","bean:anima?method=stampa(Rotta aggiunta!)")
	}
	/*
	 @Test
	 void fallisce(){
	 assertNotNull(null)
	 }
	 */
}
