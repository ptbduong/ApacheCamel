package com.vrtoonjava.invoices;

import com.vrtoonjava.banking.Payment;
import com.vrtoonjava.banking.PaymentCreator;
import com.vrtoonjava.banking.PaymentException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.Assert.assertEquals;

public class ForeignPaymentCreatorTest {

    PaymentCreator paymentCreator;

    @Before
    public void setUp() {
        paymentCreator = new ForeignPaymentCreator();
    }

    @Test
    public void testCreatePayment() throws PaymentException {
        Invoice invoice = new Invoice("some-iban", "some-address", null, BigDecimal.TEN);
        Payment payment = paymentCreator.createPayment(invoice);
        assertEquals(payment.getReceiverAccount(), invoice.getIban());
    }

    @Test(expected = PaymentException.class)
    public void testCreatePayment_WithoutIban() throws PaymentException {
        Invoice invoice = new Invoice(null, "some-address", "some-account", BigDecimal.TEN);
        paymentCreator.createPayment(invoice);
    }

}
