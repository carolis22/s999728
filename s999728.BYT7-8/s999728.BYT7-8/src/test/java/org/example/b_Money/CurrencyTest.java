package org.example.b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
    Currency USD, PLN, EUR;

    @Before
    public void setUp() throws Exception {
        USD = new Currency("USD", 1.25);
        PLN = new Currency("PLN", 0.65);
        EUR = new Currency("EUR", 1.5);
    }

    @Test
    public void testGetName() {
        assertEquals("should return expected name USD", "USD", USD.getName());
        assertEquals("should return expected name PLN", "PLN", PLN.getName());
        assertEquals("should return expected name EUR", "EUR", EUR.getName());
    }

    @Test
    public void testGetRate() {
        assertEquals("should return the assigned rate 1.25", Double.valueOf(1.25), USD.getRate());
        assertEquals("should return the assigned rate 0.65", Double.valueOf(0.65), PLN.getRate());
        assertEquals("should return the assigned rate 1.5", Double.valueOf(1.5), EUR.getRate());
    }

    @Test
    public void testSetRate() {
        USD.setRate(0.18);
        assertEquals("should return the new rate which was set by setter", Double.valueOf(0.18), USD.getRate());
    }

    @Test
    public void testGlobalValue() {
        assertEquals("should calculate correctly universal value", Integer.valueOf(12500), USD.universalValue(10000));
        assertEquals("should calculate correctly universal value", Integer.valueOf(9750), PLN.universalValue(15000));
        assertEquals("should calculate correctly universal value", Integer.valueOf(1500), EUR.universalValue(1000));
    }

    @Test
    public void testValueInThisCurrency() {
        assertEquals("should return value in this currency", Integer.valueOf(42), USD.valueInThisCurrency(50, EUR));

        assertEquals("should return value in this currency", Integer.valueOf(26), PLN.valueInThisCurrency(50, USD));

        assertEquals("should return value in this currency", Integer.valueOf(115), EUR.valueInThisCurrency(50, PLN));
    }

}
