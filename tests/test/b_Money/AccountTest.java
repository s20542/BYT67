package b_Money;

import org.junit.Before;
import org.junit.Test;

import java.util.Hashtable;

import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		testAccount = new Account("Hans", SEK);
		//testAccount.deposit(new Money(10000000.0, SEK));
		//SweBank.deposit("Alice", new Money(1000000.0, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		testAccount.addTimedPayment("1", 1, 100,  new Money(1000.0, SEK), SweBank, "ALice");
		assertTrue(testAccount.timedPaymentExists("1"));
		testAccount.removeTimedPayment("1");
		assertFalse(testAccount.timedPaymentExists("1"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		testAccount.addTimedPayment("1", 1, 100,  new Money(1000.0, SEK), SweBank, "ALice");
		assertTrue(testAccount.timedPaymentExists("1"));
	}

	@Test
	public void testAddWithdraw() {
		assertEquals(testAccount.getBalance().compareTo(new Money(0.0, SEK)), 0);
		testAccount.withdraw(new Money(1000.0, SEK));
		assertEquals(testAccount.getBalance().compareTo(new Money(0.0, SEK)), 1);
	}



	@Test
	public void testGetBalance() {
		testAccount.deposit(new Money(100.0, SEK));
		assertEquals("100.0 SEK", testAccount.getBalance().toString());
	}
}
