package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("1", new Account("Bob", SEK));
		SweBank.openAccount("2", new Account("Kiryl", DKK));
		Nordea.openAccount("3", new Account("Pall", SEK));
		DanskeBank.openAccount("4", new Account("Fill", DKK));
	}

	@Test
	public void testGetName() {
		assertEquals("SweBank", SweBank.getName());
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SEK, SweBank.getCurrency());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		assertTrue(SweBank.accountExists("1"));
		assertFalse(SweBank.accountExists("34"));
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		assertEquals("0.0", SweBank.getBalance("1").toString());
		SweBank.deposit("1", new Money(1000.0, SEK));
		assertEquals("1000.0", SweBank.getBalance("1").toString());

	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		assertEquals("0.0", SweBank.getBalance("1").toString());
		SweBank.withdraw("1", new Money(1000.0, SEK));
		assertEquals("-1000.0", SweBank.getBalance("1").toString());
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		assertEquals("0.0", SweBank.getBalance("1").toString());
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		SweBank.deposit("1", new Money(1000.0, SEK));
		SweBank.transfer("1", Nordea, "3", new Money(1000.0, SEK));
		assertEquals("1000.0", Nordea.getBalance("3").toString());
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.getAccountlist().get("1").addTimedPayment("1", 1, 100,  new Money(1000.0, SEK), SweBank, "ALice");
		assertTrue(SweBank.getAccountlist().get("1").timedPaymentExists("1"));

	}
}
