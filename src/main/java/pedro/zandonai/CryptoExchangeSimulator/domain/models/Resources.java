package pedro.zandonai.CryptoExchangeSimulator.domain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "tb_resources")
public class Resources {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean allDaySupport;
    private boolean levaregeTrading;
    private boolean affiliateProgram;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAllDaySupport() {
        return allDaySupport;
    }

    public void setAllDaySupport(boolean allDaySupport) {
        this.allDaySupport = allDaySupport;
    }

    public boolean isLevaregeTrading() {
        return levaregeTrading;
    }

    public void setLevaregeTrading(boolean levaregeTrading) {
        this.levaregeTrading = levaregeTrading;
    }

    public boolean isAffiliateProgram() {
        return affiliateProgram;
    }

    public void setAffiliateProgram(boolean affiliateProgram) {
        this.affiliateProgram = affiliateProgram;
    }
}
