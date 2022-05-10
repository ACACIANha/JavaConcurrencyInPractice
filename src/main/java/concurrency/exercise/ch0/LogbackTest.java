package concurrency.exercise.ch0;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.core.joran.spi.JoranException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;

@Slf4j
public class LogbackTest {

	public static void main( String[] args ) throws JoranException {

//		LoggerContext loggerContext = ( LoggerContext ) LoggerFactory.getILoggerFactory();
//		loggerContext.reset();
//		ContextInitializer ci = new ContextInitializer( loggerContext );
//		ci.autoConfig();

		if ( log.isDebugEnabled() ) {
			log.info( "isDebugEnabled" );
		} else {
			log.info( "!isDebugEnabled" );
		}
		log.debug( "debug" );
		log.info( "test" );
		log.error( "error" );
	}
}
