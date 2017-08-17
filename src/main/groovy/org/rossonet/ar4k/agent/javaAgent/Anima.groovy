/**
 * Anima
 *
 * <p>Attivata in fase di bootstrap applicativa come Bean cordina l'agente Java</p>
 *
 * <p style="text-justify">
 * Gestisce le funzioni principali e coordina le configurazioni.
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
import org.rossonet.ar4k.agent.javaAgent.memi.Bootstrap
import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.annotation.Scope
import org.springframework.jmx.export.annotation.ManagedOperation
import org.springframework.jmx.export.annotation.ManagedResource
import org.springframework.stereotype.Component
import org.apache.activemq.broker.BrokerService
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.CamelContext
import org.neo4j.graphdb.GraphDatabaseService
import org.neo4j.graphdb.factory.GraphDatabaseFactory
import org.pentaho.di.core.KettleEnvironment
import org.pentaho.di.job.Job
import org.pentaho.di.job.JobMeta
import org.springframework.stereotype.*
import org.springframework.beans.factory.annotation.*
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.*
import org.springframework.context.annotation.Configuration

@ManagedResource(objectName = "bean:name=anima", description = "Gestore principale agente", log = true, logFile = "anima.log", currencyTimeLimit = 15, persistPolicy = "OnUpdate", persistPeriod = 200, persistLocation = "ar4k", persistName = "anima")
@Component("anima")
@EnableMBeanExport
@Scope("singleton")
@JsonMBean
@EnableAutoConfiguration
//@EnableConfigurationProperties
@ImportResource("classpath:beans.xml")
public class Anima {
	public @Value('${seme}')String seme
	public @Value('${dns}')String baseDns
	public @Value('${confdir}')String baseConf
	public @Value('${idagent}')String idAgent
	public @Value('${web}')String web
	public @Value('${jolokia.port}')int portaJolokia
	public @Value('${jolokia.utente}')String utenteJolokia
	public @Value('${jolokia.password}')String passwordJolokia
	public @Value('${server.port}')String portaWeb

	static Bootstrap boot = new Bootstrap()

	@Autowired
	ApplicationContext applicationContext

	@Autowired
	CamelContext camelContext

	public static Logger logger = Logger.getLogger(Anima.class)

	public Anima(){
		stampa("Orchestratore agente avviato")
	}

	public void infoPrint(){
		if (seme==null) seme = UUID.randomUUID()
		if (idAgent==null) idAgent = UUID.randomUUID()
		if (baseDns==null) baseDns = 'agente.rossonet.name'
		if (baseConf==null) baseConf = '~/.ar4k/'
		if (web==null) web = 'https://app.rossonet.name/dati/agenti'
		boot.idAgent = idAgent
		boot.sementeOtp = seme
		boot.baseConf = baseConf
		boot.baseDns = baseDns
		boot.web = web
		stampa("UUID Agente: "+boot.idAgent)
		stampa("--- RICERCA CONFIGURAZIONE ---")
		stampa("dns: "+boot.baseDns)
		stampa("cartella: "+boot.baseConf)
		stampa("web: "+boot.web)
		//stampa("jolokia: http://"+utenteJolokia+":"+passwordJolokia+"@127.0.0.1:"+portaJolokia+"/jolokia/")
		stampa("--- GESTIONE IN RUNTIME ---")
		stampa("jolokia schema: http")
		stampa("jolokia host: "+utenteJolokia+":"+passwordJolokia+"@127.0.0.1")
		stampa("jolokia port: "+portaJolokia)
		stampa("jolokia path: jolokia")
		//stampa("SEMENTE OTP: "+boot.sementeOtp)
		stampa("--- ACCESSO WEB: http://<indirizzo agente>:"+portaWeb+"/")
	}

	@ManagedOperation
	public static void stampa(String testo) throws Throwable {
		logger.info(testo)
	}

	@ManagedOperation
	public void avviaBroker(String nome,int porta,boolean mqtt=true) throws Throwable {
		BrokerService broker = new BrokerService()
		broker.setUseShutdownHook(false)
		broker.setPersistent(false)
		broker.setUseJmx(true)
		broker.setBrokerName(nome)
		if (mqtt==true){
			broker.addConnector("mqtt://localhost:"+porta)
		} else {
			broker.addConnector("tcp://localhost:"+porta)
		}
		broker.start()
	}

	@ManagedOperation
	public void aggiungiCamel(String da,String a) throws Throwable {
		RouteBuilder costruttore = new DynamicRouteBuilder(camelContext,da,a)
		camelContext.addRoutes(costruttore)
	}

	@ManagedOperation
	public String eseguiJobKettle(String filename){
		String risultato = ""
		KettleEnvironment.init()
		JobMeta jobMeta = new JobMeta(filename, null)
		Job job = new Job(null, jobMeta)
		job.start()
		job.waitUntilFinished()
		risultato=job.getResult().getLogText()
		return risultato
	}

	@ManagedOperation
	public void creaNeo4J(String filename){
		GraphDatabaseService graphDb = new GraphDatabaseFactory().newEmbeddedDatabase( filename )
	}
}

class DynamicRouteBuilder extends RouteBuilder {

	private final String from
	private final String to

	private DynamicRouteBuilder(CamelContext camelContext, String from, String to) {
		super(camelContext)
		this.from = from
		this.to = to
	}

	@Override
	public void configure() throws Exception {
		from(from).to(to)
	}
}