package examples.simple;

import java.math.BigDecimal;
import java.util.Collection;

import com.google.common.collect.ImmutableList;

import common.Payment;

/**
 * Created on 15/05/13
 * @author d.serdiuk
 */
public class SimpleReportModel {
    public Collection<Payment> getPayments() {
        return ImmutableList.of(
                new Payment(BigDecimal.valueOf(512.33), "Credit Suisse", "USD", "Pears AG 10007 Berlin"),
                new Payment(BigDecimal.valueOf(217.02), "UBS", "USD", "Pears AG 10007 Berlin"),
                new Payment(BigDecimal.valueOf(9100.25), "UBS", "USD", "Cherry GmbH 10008 Berlin"),
                new Payment(BigDecimal.valueOf(102.78), "Credit Suisse", "USD", "Cherry GmbH 10008 Berlin"),
                new Payment(BigDecimal.valueOf(35.56), "UBS", "USD", "Cherry GmbH 10008 Berlin"),
                new Payment(BigDecimal.valueOf(1000.25), "UBS", "EUR", "Someone's AG"),
                new Payment(BigDecimal.valueOf(1200.31), "UBS", "EUR", "Someone's AG"),
                new Payment(BigDecimal.valueOf(1500.11), "UBS", "EUR", "Someone's AG")
        );
    }
}
