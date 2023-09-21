package pedro.zandonai.CryptoExchangeSimulator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pedro.zandonai.CryptoExchangeSimulator.domain.models.Asset;
import pedro.zandonai.CryptoExchangeSimulator.domain.models.User;
import pedro.zandonai.CryptoExchangeSimulator.domain.models.Wallet;
import pedro.zandonai.CryptoExchangeSimulator.service.AssetService;
import pedro.zandonai.CryptoExchangeSimulator.service.UserService;
import pedro.zandonai.CryptoExchangeSimulator.service.WalletService;

import java.net.URI;
import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final WalletService walletService;
    private final AssetService assetService;

    public UserController(UserService userService, WalletService walletService, AssetService assetService) {
        this.userService = userService;
        this.walletService = walletService;
        this.assetService = assetService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        var user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User userToCreate){
        if (userToCreate.getUserWallet().getAssets() != null) {
            userToCreate.getUserWallet().setAssets(new ArrayList<>());
        }
        var userCreated = userService.createUser(userToCreate);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(userCreated);
    }

    @GetMapping("/{id}/wallet-assets")
    public ResponseEntity<Wallet> getWalletInfo(@PathVariable Long id) {
        User user = userService.findById(id);
        Wallet wallet = user.getUserWallet();
        return ResponseEntity.ok(wallet);
    }

    @PostMapping("/{id}/add-balance")
    public ResponseEntity<Wallet> addBalance(@PathVariable Long id, @RequestBody double balanceToAdd){
        // Get user ID with wallet
        User user = userService.findById(id);
        Wallet wallet = user.getUserWallet();
        return ResponseEntity.ok(walletService.addBalance(wallet, balanceToAdd));
    }

    @PostMapping("/{id}/buy-asset")
    public ResponseEntity<Asset> buyAsset(@PathVariable Long id, @RequestBody Asset assetToBuy) {
        User user = userService.findById(id);
        Asset buyAsset = assetService.buy(assetToBuy.getSymbol(), assetToBuy.getQuantity(), user);
        return ResponseEntity.ok(buyAsset);
    }

    @PostMapping("/{id}/sell-asset")
    public ResponseEntity<Asset> sellAsset(@PathVariable Long id, @RequestBody Asset assetToSell) {
        User user = userService.findById(id);
        Asset sellAsset = assetService.sell(assetToSell.getSymbol(), assetToSell.getQuantity(), user);
        return ResponseEntity.ok(sellAsset);
    }
}
