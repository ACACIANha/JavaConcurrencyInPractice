package concurrency.exercise.ch0;

import ch.qos.logback.classic.Level;
import concurrency.exercise.ch2.exercise3.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

@Slf4j
public class TestTest {

	public static void main( String[] args ) {

		IntStream.range( 0, 5 ).forEach( i -> {
			log.info( "{}", i );
		} );

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
