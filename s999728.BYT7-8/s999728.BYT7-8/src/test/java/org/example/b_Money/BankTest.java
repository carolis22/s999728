package org.example.b_Money;

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
        SweBank.openAccount("Ulrika");
        SweBank.openAccount("Bob");
        Nordea.openAccount("Bob");
        DanskeBank.openAccount("Gertrud");
        DanskeBank.openAccount("Alex");
    }

    @Test
    public void testGetName() {
        assertEquals("should have the name of the bank SweBank", "SweBank", SweBank.getName());
        assertEquals("should have the name of the bank Nordea", "Nordea", Nordea.getName());
        assertEquals("should have the name of the bank DanskeBank", "DanskeBank", DanskeBank.getName());
    }

    @Test
    public void testGetCurrency() {
        assertEquals("should return correct currency", SEK, SweBank.getCurrency());
        assertEquals("should return correct currency", SEK, Nordea.getCurrency());
        assertEquals("should return correct currency", DKK, DanskeBank.getCurrency());
    }

    @Test
    public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
        SweBank.openAccount("Alice");
        assertNotNull("should assign value after opening account", SweBank.getBalance("Alice"));
    }

    @Test
    public void testDeposit() throws AccountDoesNotExistException {
        Money depositAmount = new Money(500, SEK);
        SweBank.deposit("Ulrika", depositAmount);
        assertEquals("should add money to the balance by depositing method", 500, (int) SweBank.getBalance("Ulrika"));
    }

    @Test
    public void testWithdraw() throws AccountDoesNotExistException {
        Money withdrawalAmount = new Money(200, SEK);
        SweBank.withdraw("Ulrika", withdrawalAmount);
        assertEquals("should withdraw money with amount 200 from Ulrika", -200, (int) SweBank.getBalance("Ulrika"));
    }

    @Test
    public void testGetBalance() throws AccountDoesNotExistException {
        assertEquals("should initialize balance with 0 amount", 0, (int) SweBank.getBalance("Bob"));
    }

    @Test
    public void testTransfer() throws AccountDoesNotExistException {
        Money transferAmount = new Money(100, SEK);
        SweBank.transfer("Ulrika", Nordea, "Bob", transferAmount);
        assertEquals("should decrease money from the account after transfer", -100, (int) SweBank.getBalance("Ulrika"));
        assertEquals("should increase money from the account after transfer", 100, (int) Nordea.getBalance("Bob"));
    }

    @Test
    public void testTransferOnTheSameBank() throws AccountDoesNotExistException {
        Money transferAmount = new Money(100, SEK);
        SweBank.transfer("Ulrika", "Bob", transferAmount);
        assertEquals("should decrease money from the account after transfer", -100, (int) SweBank.getBalance("Ulrika"));
        assertEquals("should increase money from the account after transfer", 100, (int) SweBank.getBalance("Bob"));
    }

    @Test
    public void testTimedPayment() throws AccountDoesNotExistException {
        Money paymentAmount = new Money(50, SEK);
        SweBank.addTimedPayment("Ulrika", "salary", 2, 1, paymentAmount, Nordea, "Bob");

        for (int i = 0; i < 5; i++) {
            SweBank.tick();
        }

        assertEquals("should perform timed payment and decrease balance by 50 * 3", -150, (int) SweBank.getBalance("Ulrika"));
        assertEquals("should perform timed payment and increase balance by 50 * 3",150, (int) Nordea.getBalance("Bob"));
    }
}
