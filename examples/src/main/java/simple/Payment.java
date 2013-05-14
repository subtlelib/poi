package simple;

import java.math.BigDecimal;

import org.joda.time.LocalDateTime;

/**
 * TODO: describe class
 * <p/>
 * Created on 14/05/13
 *
 * @author d.serdiuk
 */
public class Payment {

    private final BigDecimal amount;
    private final BigDecimal date;
    private final LocalDateTime bookingTime;

    public Payment(BigDecimal amount, BigDecimal date, LocalDateTime bookingTime) {
        this.amount = amount;
        this.date = date;
        this.bookingTime = bookingTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getDate() {
        return date;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }
}
