package dimi.zpo.bank3.classes;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class HistoryEntry {
    private LocalDateTime date;
    private String type;
    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;

    public HistoryEntry(LocalDateTime date, String type, String fromAccount, String toAccount, BigDecimal amount) {
        this.date = date;
        this.type = type;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
