package pedro.zandonai.CryptoExchangeSimulator.domain.models;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cellphoneNumber;
    @Column(unique = true)
    private String email;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Asset> assets;
    @OneToOne(cascade = CascadeType.ALL)
    private BuyAsset buyAsset;
    @OneToOne(cascade = CascadeType.ALL)
    private SellAsset sellAsset;
    @OneToOne(cascade = CascadeType.ALL)
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

    public String getCellphoneNumber() {
        return cellphoneNumber;
    }

    public void setCellphoneNumber(String celular) {
        this.cellphoneNumber = celular;
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
