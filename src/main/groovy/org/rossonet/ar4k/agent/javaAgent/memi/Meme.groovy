/**
 * configurazione meme
 *
 * <p>configurazione meme astratto</p>
 *
 * <p style="text-justify">
 * in implementazione
 * </p>
 *
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 * @version 0.1-alpha
 * @see org.rossonet.ar4k.agent.javaAgent.Configurazione
 * @see org.rossonet.ar4k.agent.javaAgent.Gestore
 */

package org.rossonet.ar4k.agent.javaAgent.memi

import java.net.URL;
import java.net.URLClassLoader;
import java.util.regex.Pattern;

import org.apache.log4j.Level
import org.apache.log4j.LogManager
import org.apache.log4j.Logger
import org.jolokia.jmx.JsonMBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
import org.springframework.context.annotation.Scope
import org.springframework.context.support.GenericApplicationContext
import org.springframework.core.io.ClassPathResource
import org.springframework.core.type.filter.RegexPatternTypeFilter
import org.springframework.jmx.export.annotation.ManagedOperation
import org.springframework.jmx.export.annotation.ManagedResource
import org.springframework.stereotype.Component
import org.apache.activemq.broker.BrokerService
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.CamelContext
import org.pentaho.di.core.KettleEnvironment
import org.pentaho.di.job.Job
import org.pentaho.di.job.JobMeta

public class Meme {

	@Autowired
	ApplicationContext applicationContext

	@Autowired
	CamelContext camelContext

	public static Logger logger = Logger.getLogger(Meme.class)

	public String urlPacchetto = ''
	public String baseScan = ''
	public String numeroClassiCaricate = 0

	public Meme(String percorso,String scan){
		baseScan=scan
		urlPacchetto = percorso
		caricaPacchettoDaUrl()
	}

	@Override
	public String toString(){
		baseScan+" ["+urlPacchetto+"] classi totali: "+numeroClassiCaricate
	}

	private caricaPacchettoDaUrl(){
		try{
			def classLoader = ClassLoader.systemClassLoader
			// ritrova il classloader master
			while (classLoader.parent) {
				classLoader = classLoader.parent
			}
			// aggiunge un classloader
			def newClassLoader = new URLClassLoader([
				new File(urlPacchetto).toString().toURL()] as URL[], classLoader)

			def appCtx = new GenericApplicationContext();
			// carica contesto spring
			ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(appCtx)
			numeroClassiCaricate = scanner.scan(baseScan)
			appCtx.refresh()
		} catch(Exception errore){
			logger.warn("errore caricamento meme "+urlPacchetto+" "+errore.getMessage())
			//errore.printStackTrace()
		}
	}

	public static Map<String,Map<String,String>> elencoPacchetti(String baseScan){
		final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
		provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*")));
		final Set<BeanDefinition> classes = provider.findCandidateComponents(baseScan);

		Map<String,Map<String,String>> ritorno = new HashMap<String,Map<String,String>>()

		for (BeanDefinition bean: classes) {
			Class<?> clazz = Class.forName(bean.getBeanClassName());
			Map<String,String> parametri = new HashMap<String,String>()
			//parametri.put("Annotations",it.getAnnotations()*.toString())
			//parametri.put("ImplementationTitle",it.getImplementationTitle().toString())
			parametri.put("nome",clazz.toString())
			parametri.put("","")
			ritorno.put(bean.toString())
		}
		return ritorno
	}

}