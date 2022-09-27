package concurrency.exercise.ch0;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringTest {

	public static void main( String[] args ) {

		int count = 100_000;
		String temp = "";
		long start1 = System.currentTimeMillis();
		for ( int i = 0; i < count; ++i ) {
			temp += i;
		}
		long term1 = System.currentTimeMillis() - start1;

		StringBuffer buffer = new StringBuffer();
		long start2 = System.currentTimeMillis();
		for ( int i = 0; i < count; ++i ) {
			buffer.append( i );
		}
		long term2 = System.currentTimeMillis() - start2;

		StringBuilder builder = new StringBuilder();
		long start3 = System.currentTimeMillis();
		for ( int i = 0; i < count; ++i ) {
			builder.append( i );
		}
		long term3 = System.currentTimeMillis() - start3;

		log.info( "{}, {}, {}", term1, term2, term3 );
		log.info( "{}, {}, {}", temp, buffer, builder );


	}
}
