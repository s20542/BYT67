package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000.0, SEK);
		EUR10 = new Money(1000.0, EUR);
		SEK200 = new Money(20000.0, SEK);
		EUR20 = new Money(2000.0, EUR);
		SEK0 = new Money(0.0, SEK);
		EUR0 = new Money(0.0, EUR);
		SEKn100 = new Money(-10000.0, SEK);
	}

	@Test
	public void testGetAmount() {
		assertEquals("10000.0", SEK100.getAmount().toString());
		assertEquals("1000.0", EUR10.getAmount().toString());
		assertEquals("20000.0", SEK200.getAmount().toString());

	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SEK100.getCurrency());
	}

	@Test
	public void testToString() {
		assertEquals("10000.0 SEK", SEK100.toString());
	}

	@Test
	public void testGlobalValue() {
		assertEquals("133333", SEK200.universalValue().toString());
	}

	@Test
	public void testEqualsMoney() {
		assertFalse(EUR10.equals(EUR0));
	}

	@Test
	public void testAdd() {
		assertEquals("3000.0 EUR", EUR10.add(EUR20).toString());
	}

	@Test
	public void testSub() {
		assertEquals("-1000.0 EUR", EUR10.sub(EUR20).toString());
	}

	@Test
	public void testIsZero() {
		assertTrue(EUR0.isZero());
	}

	@Test
	public void testNegate() {
		assertEquals("-2000.0 EUR", EUR20.negate().toString());
	}
}
