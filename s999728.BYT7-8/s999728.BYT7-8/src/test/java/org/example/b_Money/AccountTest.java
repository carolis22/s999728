package org.example.b_Money;

import org.junit.Before;
import org.junit.Test;

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
        SweBank.openAccount("Alice");
        testAccount = new Account("Hans", SEK);
        testAccount.deposit(new Money(10000000, SEK));

        SweBank.deposit("Alice", new Money(1000000, SEK));
    }

    @Test
    public void testAddRemoveTimedPayment() {
        assertFalse("should not exists as it was not created yet", testAccount.timedPaymentExists("Payment1"));

        testAccount.addTimedPayment("Payment1", 2, 1, new Money(500, SEK), SweBank, "Alice");
        assertTrue("should exist as it was created", testAccount.timedPaymentExists("Payment1"));

        testAccount.removeTimedPayment("Payment1");
        assertFalse("should not exist after removing", testAccount.timedPaymentExists("Payment1"));
    }

    @Test
    public void testTimedPayment() throws AccountDoesNotExistException {
        testAccount.addTimedPayment("Payment2", 1, 0, new Money(100, SEK), SweBank, "Alice");

        for (int i = 0; i < 3; i++) {
            testAccount.tick();
        }

        assertEquals("should equal to the expected amount", 9999700, (int) testAccount.getBalance().getAmount());
        assertEquals("should equal to the expected amount", 1000300, (int) SweBank.getBalance("Alice"));
    }

    @Test
    public void testAddWithdraw() {
        testAccount.deposit(new Money(50000, SEK));
        assertEquals("should add money to the account", 10050000, (int) testAccount.getBalance().getAmount());

        testAccount.withdraw(new Money(20000, SEK));
        assertEquals("should decrease(withdraw) money from account", 10030000, (int) testAccount.getBalance().getAmount());
    }

    @Test
    public void testGetBalance() {
        assertEquals("should have the proper balance", 10000000, (int) testAccount.getBalance().getAmount());
    }
}
