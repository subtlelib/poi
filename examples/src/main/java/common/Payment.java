package common;

import java.math.BigDecimal;

/**
 * An example domain object
 * <p/>
 * Created on 14/05/13
 * @author d.serdiuk
 */
public class Payment {

    private final BigDecimal amount;
    private final String payeeBank;
    private final String currency;
    private final String beneficiary;

    public Payment(BigDecimal amount, String payeeBank, String currency, String beneficiary) {
        this.amount = amount;
        this.payeeBank = payeeBank;
        this.currency = currency;
        this.beneficiary = beneficiary;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getPayeeBank() {
        return payeeBank;
    }

    public String getCurrency() {
        return currency;
    }

    public String getBeneficiary() {
        return beneficiary;
    }
}
