/**
 * Seriale
 *
 * <p>Permette la comunicazione seriale o USB</p>
 *
 * <p style="text-justify">
 * Utilizzando la libreria RxTx rappresenta una connessione seriale
 * http://rxtx.qbang.org
 * </p>
 *
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 * @version 0.1-alpha
 * @see org.rossonet.ar4k.agent.javaAgent.Configurazione
 * @see org.rossonet.ar4k.agent.javaAgent.Gestore
 */

package org.rossonet.ar4k.agent.javaAgent

import org.apache.log4j.Level
import org.apache.log4j.LogManager
import org.apache.log4j.Logger
import org.jolokia.jmx.JsonMBean
import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope
import org.springframework.jmx.export.annotation.ManagedOperation
import org.springframework.jmx.export.annotation.ManagedResource
import org.springframework.stereotype.Component
import org.apache.activemq.broker.BrokerService
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.CamelContext
import org.pentaho.di.core.KettleEnvironment
import org.pentaho.di.job.Job
import org.pentaho.di.job.JobMeta

@Component("seriale")
@JsonMBean
public class Seriale {

	@Autowired
	ApplicationContext applicationContext

	@Autowired
	CamelContext camelContext

	public static Logger logger = Logger.getLogger(Seriale.class)

}