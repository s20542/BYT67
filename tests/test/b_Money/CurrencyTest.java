package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		assertEquals("EUR", EUR.getName());
		assertEquals("DKK", DKK.getName());
		assertEquals("SEK", SEK.getName());
	}
	
	@Test
	public void testGetRate() {
		assertEquals("0.15", SEK.getRate().toString());
		assertEquals("0.2", DKK.getRate().toString());
		assertEquals("1.5", EUR.getRate().toString());


	}
	
	@Test
	public void testSetRate() {
		SEK.setRate(0.1);
		assertEquals("0.1", SEK.getRate().toString());
	}
	
	@Test
	public void testGlobalValue() {
		assertEquals( "1500.0", EUR.universalValue(1000).toString());
	}
	
	@Test
	public void testValueInThisCurrency() {
		assertEquals("10000" , EUR.valueInThisCurrency(1000, SEK).toString());
	}

}
