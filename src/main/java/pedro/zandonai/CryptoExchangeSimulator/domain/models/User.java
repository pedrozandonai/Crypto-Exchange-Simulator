package pedro.zandonai.CryptoExchangeSimulator.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

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
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Wallet userWallet;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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

    public Wallet getUserWallet() {
        return userWallet;
    }

    public void setUserWallet(Wallet userWallet) {
        this.userWallet = userWallet;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }
}
