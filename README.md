# Crypto Exchange Simulator

Crypto Exchange Simulator is a project designed to simulate cryptocurrency exchange operations. It is a Java Spring Boot application that you can add to your portfolio to showcase your skills in building financial software. Below, we'll explore the functionalities, use cases, and methods provided by this project.

## Table of Contents
- [Features](#features)
- [Project Structure](#project-structure)
- [How to Use](#how-to-use)
- [Deployment on Railway](#deployment-on-railway)
- [Explore the API with Swagger](#explore-the-api-with-swagger)
- [Methods and Interfaces](#methods-and-interfaces)
- [UML Diagram](#uml-diagram)

## Versions Used
- **Crypto Exchange Simulator**: v0.1.0 Beta
- **Java OpenJDK**: 17.0.8
- **Spring Boot**: 3.1.3
- **Swagger**: 2.2.0 (springdoc-openapi-starter-webmvc-ui)
- **H2 Database**: 
- **PostgreSQL Driver**: 
- **JUnit (for Testing)**:

## Features<a name="features"></a>
Crypto Exchange Simulator provides the following features:

1. **User Management**
   - Create new users with wallets.
   - Retrieve user information by user ID.
   
2. **Wallet Management**
   - View wallet assets and balances.
   - Add balance to a user's wallet.
   - Buy and sell assets from/to a user's wallet.

3. **Asset Management**
   - Retrieve asset information by ID.
   - Buy and sell assets with users.

4. **Exception Handling**
   - Global exception handling for common error scenarios.

## Project Structure<a name="project-structure"></a>
The project is organized into packages:

- `pedro.zandonai.CryptoExchangeSimulator.controller`: Contains controllers for handling user interactions and API endpoints.
- `pedro.zandonai.CryptoExchangeSimulator.controller.exception`: Defines global exception handlers.
- `pedro.zandonai.CryptoExchangeSimulator.domain.models`: Contains model classes for User, Wallet, Asset, Address, Resources, and Taxes.
- `pedro.zandonai.CryptoExchangeSimulator.domain.repository`: Defines repositories for User, Wallet, and Asset.
- `pedro.zandonai.CryptoExchangeSimulator.service`: Contains service interfaces for Wallet, User, and Asset.
- `pedro.zandonai.CryptoExchangeSimulator.service.impl`: Implements the service interfaces for Wallet and Asset.

## How to Use<a name="how-to-use"></a>
To use this project:

1. Clone or download the project source code.
2. Build and run the application using your preferred Java development environment (e.g., Spring Tool Suite, IntelliJ IDEA, or VS Code).
3. Access the API endpoints using a tool like Postman or a web browser.

## Deployment on Railway<a name="deployment-on-railway"></a>
You can deploy this API for free on Railway with a PostgreSQL database. The information can be stored securely within Railway's infrastructure. Follow these steps to deploy:

1. Sign up or log in to [Railway](https://railway.app/).
2. Create a new project on Railway.
3. Configure your project settings and add your environment variables for database configuration (e.g., database URL, username, password).
4. Connect your project to your GitHub repository where you have the Crypto Exchange Simulator code.
5. Set up a deployment pipeline to automatically deploy changes to Railway.
6. Deploy your project, and Railway will handle the deployment and database management.

## Explore the API with Swagger<a name="explore-the-api-with-swagger"></a>

Crypto Exchange Simulator API is documented using Swagger, which provides an interactive interface for exploring and testing API endpoints. Follow these steps to access the Swagger documentation:

1. Make sure your Crypto Exchange Simulator application is running locally or deployed to Railway as described in the [How to Use](#how-to-use) and [Deployment on Railway](#deployment-on-railway) sections.

2. Open a web browser and navigate to the following URL:

   - **Local Deployment**: `http://localhost:8080/swagger-ui.html`
   - **Railway Deployment**: Replace `localhost:8080` with the deployed Railway project's URL.

3. You will be presented with the Swagger UI interface, where you can:
   - Browse through the available API endpoints.
   - Execute API requests directly from the documentation.
   - View detailed information about request and response structures.
   - Test the API's functionality interactively.

Swagger makes it easy for both developers and users to understand and interact with your API. Feel free to explore the available endpoints, try out different requests, and familiarize yourself with the API's capabilities using Swagger.

![Swagger UI](swagger-ui.png)

Enjoy using Swagger to interact with the Crypto Exchange Simulator API!

## Methods and Interfaces<a name="methods-and-interfaces"></a>
Here are some of the key methods and interfaces provided by the project:

### WalletService Interface
- `Wallet findById(Long id)`: Find a wallet by its ID.
- `Wallet save(Wallet wallet)`: Save a wallet.
- `Wallet addBalance(Wallet wallet, double balance)`: Add balance to a wallet.
- `Wallet addAssetToWallet(Wallet wallet, Asset asset)`: Add an asset to a wallet.
- `Wallet removeAssetFromWallet(Wallet wallet, Asset asset)`: Remove an asset from a wallet.
- `Asset getAssetFromWallet(Wallet wallet, String ticker)`: Retrieve an asset from a wallet by ticker symbol.
- `Wallet balance(Wallet wallet)`: Calculate and update the balance of a wallet.

### UserService Interface
- `User findById(Long id)`: Find a user by their ID.
- `User createUser(User userToCreate)`: Create a new user.

### AssetService Interface
- `Asset findById(Long id)`: Find an asset by its ID.
- `Asset buy(Asset asset, User user)`: Buy an asset and update the user's wallet.
- `Asset sell(Asset asset, User user)`: Sell an asset and update the user's wallet.

These interfaces are implemented in the `pedro.zandonai.CryptoExchangeSimulator.service.impl` package.

### UML Diagram<a name="uml-diagram"></a>
````mermaid
classDiagram
  class CryptoExchangeSimulatorApplication {
    +main(String[] args)
  }

  class WalletService {
    +findById(Long id)
    +save(Wallet wallet)
    +addBalance(Wallet wallet, double balance)
    +addAssetToWallet(Wallet wallet, Asset asset)
    +removeAssetFromWallet(Wallet wallet, Asset asset)
    +getAssetFromWallet(Wallet wallet, String ticker)
    +balance(Wallet wallet)
  }

  class UserService {
    +findById(Long id)
    +createUser(User userToCreate)
  }

  class AssetService {
    +findById(Long id)
    +buy(Asset asset, User user)
    +sell(Asset asset, User user)
  }

  class AssetServiceImpl {
    -assetRepository: AssetRepository
    -walletService: WalletService
    +findById(Long id)
    +buy(Asset asset, User user)
    +sell(Asset asset, User user)
  }

  class UserServiceImpl {
    -userRepository: UserRepository
    +findById(Long id)
    +createUser(User userToCreate)
  }

  class WalletServiceImpl {
    -walletRepository: WalletRepository
    +findById(Long id)
    +save(Wallet wallet)
    +addBalance(Wallet wallet, double balance)
    +addAssetToWallet(Wallet wallet, Asset asset)
    +removeAssetFromWallet(Wallet wallet, Asset asset)
    +getAssetFromWallet(Wallet wallet, String ticker)
    +balance(Wallet wallet)
  }

class User {
    -id: Long
    -name: String
    -cellphoneNumber: String
    -email: String
    -address: Address
    -userWallet: Wallet
    -resources: Resources
  }
  
  class Wallet {
    -id: Long
    -assets: List<Asset>
    -balance: double
  }
  
  class Asset {
    -id: Long
    -name: String
    -ticker: String
    -quantity: int
    -price: double
  }
  
  class Address {
    -id: Long
    -street: String
    -houseNumber: String
    -city: String
    -country: String
    -zipCode: String
  }
  
  class Resources {
    -id: Long
    -allDaySupport: boolean
    -levaregeTrading: boolean
    -affiliateProgram: boolean
  }
  
  class Taxes {
    -id: Long
    -commission: double
    -withdrawal: double
    -deposit: double
  }

  class UserController {
    -userService: UserService
    -walletService: WalletService
    -assetService: AssetService
    +findById(Long id)
    +create(User userToCreate)
    +getWalletInfo(Long id)
    +addBalance(Long id, double balanceToAdd)
    +buyAsset(Long id, Asset assetToBuy)
    +sellAsset(Long id, Asset assetToBuy)
  }
  
  class GlobalExceptionHandler {
    -logger: Logger
    +handle(IllegalArgumentException businessException)
    +handleNotFoundException(NoSuchElementException notFoundExeption)
    +handleUnexpectedException(Throwable unexpectedExeption)
  }

  CryptoExchangeSimulatorApplication --> UserController
  CryptoExchangeSimulatorApplication --> GlobalExceptionHandler
  UserController --> UserService
  UserController --> WalletService
  UserController --> AssetService
  GlobalExceptionHandler --> Logger
  WalletService --|> WalletServiceImpl
  UserService --|> UserServiceImpl
  AssetService --|> AssetServiceImpl
  AssetServiceImpl --> AssetRepository
  UserServiceImpl --> UserRepository
  WalletServiceImpl --> WalletRepository
  User --> Wallet
  User --> Resources
  User --> Address
  Wallet --> Asset
  User ..> User: <<create>>
  Wallet ..> Wallet: <<create>>
  Asset ..> Asset: <<create>>
  Address ..> Address: <<create>>
  Resources ..> Resources: <<create>>
  Taxes ..> Taxes: <<create>>
````

Feel free to explore and modify the code to customize the project as needed for your portfolio. Enjoy showcasing your skills with this Crypto Exchange Simulator project!

**Developed and maintained by: [@pedrozandonai](https://github.com/pedrozandonai)**
