package dimi.zpo.bank3.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Comparator;

@Entity
@Table(name = "transfer")
public class TransferEntity implements Comparator<TransferEntity> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "from_account")
    private String fromAccount;
    @Column(name = "to_account")
    private String toAccount;
    private BigDecimal amount;
    private Date date;

    @Override
    public int compare(TransferEntity transfer1, TransferEntity transfer2) {
        // Compare by amount (ascending order)
        return transfer1.getAmount().compareTo(transfer2.getAmount());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
