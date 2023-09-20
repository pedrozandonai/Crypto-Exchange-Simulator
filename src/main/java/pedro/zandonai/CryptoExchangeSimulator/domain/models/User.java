package pedro.zandonai.CryptoExchangeSimulator.domain.models;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String celular;
    private String email;
    @OneToOne
    private Address address;
    @OneToMany
    private List<Asset> assets;
    @OneToOne
    private BuyAsset buyAsset;
    @OneToOne
    private SellAsset sellAsset;
    @OneToOne
    private Resources resources;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public BuyAsset getBuyAsset() {
        return buyAsset;
    }

    public void setBuyAsset(BuyAsset buyAsset) {
        this.buyAsset = buyAsset;
    }

    public SellAsset getSellAsset() {
        return sellAsset;
    }

    public void setSellAsset(SellAsset sellAsset) {
        this.sellAsset = sellAsset;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }
}
