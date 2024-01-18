package org.example.b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
    Currency SEK, DKK, EUR;
    Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;

    @Before
    public void setUp() throws Exception {
        SEK = new Currency("SEK", 0.15);
        DKK = new Currency("DKK", 0.20);
        EUR = new Currency("EUR", 1.5);
        SEK100 = new Money(10000, SEK);
        EUR10 = new Money(1000, EUR);
        SEK200 = new Money(20000, SEK);
        EUR20 = new Money(2000, EUR);
        SEK0 = new Money(0, SEK);
        EUR0 = new Money(0, EUR);
        SEKn100 = new Money(-10000, SEK);
    }

    @Test
    public void testGetAmount() {
        assertEquals("should assigned previously amount", Integer.valueOf(10000), SEK100.getAmount());
        assertEquals("should assigned previously amount", Integer.valueOf(1000), EUR10.getAmount());
    }

    @Test
    public void testGetCurrency() {
        assertEquals("should assigned previously currency", SEK, SEK100.getCurrency());
        assertEquals("should assigned previously currency", EUR, EUR10.getCurrency());
    }

    @Test
    public void testToString() {
        assertEquals("should stringify object in correct way", "100.00 SEK", SEK100.toString());
        assertEquals("should stringify object", "10.00 EUR", EUR10.toString());
    }

    @Test
    public void testGlobalValue() {
        assertEquals("should return correct global universal value", Integer.valueOf(1500), EUR10.universalValue());
        assertEquals("should return correct global universal value", Integer.valueOf(3000), EUR20.universalValue());
    }

    @Test
    public void testEqualsMoney() {
        assertTrue("should check if the passed money object is equal to checked one and return true", SEK100.equals(new Money(10000, SEK)));
        assertTrue("should check if the passed money object is equal to checked one and return true", EUR10.equals(new Money(1000, EUR)));

        assertFalse("should check if the passed money object is equal to checked one and return false", SEK100.equals(EUR10));
        assertFalse("should check if the passed money object is equal to checked one and return false", EUR10.equals(EUR20));
    }

    @Test
    public void testAdd() {
        Money result = SEK100.add(new Money(5000, SEK));
        assertEquals("should correctly perform adding operation on SEK", "150.00 SEK", result.toString());

        result = EUR10.add(new Money(1000, EUR));
        assertEquals("should correctly perform adding operation on euro", "20.00 EUR", result.toString());
    }

    @Test
    public void testSub() {
        Money result = SEK200.sub(new Money(5000, SEK));
        assertEquals("should correctly perform subtract operation on SEK", "150.00 SEK", result.toString());
//assertEquals is giving error
        result = EUR20.sub(new Money(1000, EUR));
        assertEquals("should correctly perform subtract operation on euro", "10.00 EUR", result.toString());
    }

    @Test
    public void testIsZero() {
        assertTrue("check if value is zero", SEK0.isZero());
        assertTrue("check if value is zero", EUR0.isZero());
        assertFalse("check if value is zero", SEK100.isZero());
    }

    @Test
    public void testNegate() {
        Money result = SEK100.negate();
        assertEquals("should correctly negate number and return string as output", "-100.00 SEK", result.toString());
//assertEquals is giving error
        result = EUR10.negate();
        assertEquals("should correctly negate number and return string as output", "-10.00 EUR", result.toString());
    }

    @Test
    public void testCompareTo() {
        assertTrue("should check if numbers are equal", SEK100.compareTo(SEK100) == 0);
        assertTrue("should check if numbers are equal", SEK100.compareTo(SEK200) < 0);
        assertTrue("should check if numbers are equal", SEK200.compareTo(SEK100) > 0);
    }
}
