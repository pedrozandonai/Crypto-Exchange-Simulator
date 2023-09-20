package pedro.zandonai.CryptoExchangeSimulator.domain.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@MappedSuperclass
public abstract class AssetOperations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int amount;
    @Column(precision = 13, scale = 2)
    private BigDecimal totalAmount;
    private Date date;
    @OneToOne(cascade = CascadeType.ALL)
    private Taxes taxes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Taxes getTaxes() {
        return taxes;
    }

    public void setTaxes(Taxes taxes) {
        this.taxes = taxes;
    }
}
