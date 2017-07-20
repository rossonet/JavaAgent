/**
 * Camel
 *
 * <p>Configurazione iniziale Contesto Camel</p>
 *
 * <p style="text-justify">
 * Gestisce le rotte Camel
 * </p>
 *
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 * @version 0.1-alpha
 * @see org.rossonet.ar4k.agent.javaAgent.Configurazione
 * @see org.rossonet.ar4k.agent.javaAgent.Gestore
 */
package org.rossonet.ar4k.agent.javaAgent

import org.apache.camel.builder.RouteBuilder
import org.apache.log4j.Logger
import org.springframework.stereotype.Component

@Component
public class Camel extends RouteBuilder {

	public Logger logger = Logger.getLogger(Camel.class)

	@Override
	public void configure() throws Exception {
		from("timer:data?fixedRate=true&repeatCount=1&delay=15000&period=180000").to("bean:anima?method=infoPrint()")
	}
}
