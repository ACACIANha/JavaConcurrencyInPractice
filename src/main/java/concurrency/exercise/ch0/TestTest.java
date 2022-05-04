package concurrency.exercise.ch0;

import ch.qos.logback.classic.Level;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public class TestTest {

	public static void main( String[] args ) {

		log.trace( "trace" );

		if ( log.isDebugEnabled() ) {
			log.debug( "debug enabled" );
		} else {
			log.debug( "debug disabled" );
		}

		log.info( "info" );
		log.error( "error" );

		//stringUtilTest();
	}


	public static void stringUtilTest() {
		String s = " 123";

		if ( StringUtils.isNumericSpace( s ) ) {
			log.info( "{} : isNumericSpace", s );
		}

		if ( StringUtils.isNumeric( s ) ) {
			log.info( "{} : isNumeric", s );
		}

		String str = "abcdef -option ghijklmnop";
		String[] defs = StringUtils.split( str, "-option" );
		for ( var temp : defs ) {
			log.info( "split test {}", temp );
		}
	}

}
