package be.uantwerpen.fti.ei.dist.verbergtvincent.lab2.BankAppliccation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    private ArrayList<User> users;
    private ArrayList<BankAccount> accounts;
    private InfiniteBankAccount economy = new InfiniteBankAccount();
    private Logger logger;
    Controller() {
        users = new ArrayList<>();
        accounts = new ArrayList<>(List.of(new InfiniteBankAccount()));
        logger = LoggerFactory.getLogger(Controller.class);
    }

    @GetMapping("/users")
    ArrayList<User> allUsers() {
        return users;
    }

    @GetMapping("/users/{id}")
    User oneUser(@PathVariable String id) {
        User requestedUser = users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
        if (requestedUser != null) {
            return requestedUser;
        }
        return new ResponseEntity<User>(HttpStatusCode.valueOf(404)).getBody();
    }

    @PostMapping("/users")
    User createUser(@RequestBody Map<String,String> body) {
        User newUser = new User(body.get("name"));
        users.add(newUser);
        return newUser;
    }

    @GetMapping("/accounts")
    ArrayList<BankAccount> allAccounts() {
        return accounts;
    }

    @GetMapping("/accounts/{id}")
    BankAccount oneAccount(@PathVariable String id) {
        BankAccount requestedAccount = accounts.stream().filter(account -> account.getId().equals(id)).findFirst().orElse(null);
        if (requestedAccount != null) {
            return requestedAccount;
        }
        return new ResponseEntity<BankAccount>(HttpStatusCode.valueOf(404)).getBody();
    }

    @PostMapping("/users/{id}/accounts")
    ResponseEntity createAccount(@PathVariable String id)  {
        User owner = users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
        if (owner != null) {
            try {
                BankAccount account = new BankAccount();
                owner.addAccount(account);
                economy.setBalance(100);
                economy.deposit(account, economy.getBalance());
                accounts.add(account);
                return new ResponseEntity<BankAccount>(account, HttpStatusCode.valueOf(200));

            } catch (InsufficientFundsException | OwnerException e) {
                return new ResponseEntity<String>(e.getMessage(), HttpStatusCode.valueOf(400));
            }

        }
        return new ResponseEntity<String>("404 user not found", HttpStatusCode.valueOf(404));
    }

    @GetMapping("/users/{id}/accounts")
    ResponseEntity getAccountsOfUser(@PathVariable String id) {
        User requestedUser = users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
        if (requestedUser != null) {
            return new ResponseEntity<ArrayList<String>>(requestedUser.getAccounts(), HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<String>("404 user not found", HttpStatusCode.valueOf(404));
    }

    @PutMapping("/users/{id}/accounts/{own_account_id}/deposit")
    ResponseEntity deposit(@PathVariable String id, @PathVariable String own_account_id, @RequestBody Map<String, String> body) {
        User reqestedUser = users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
        if (reqestedUser != null) {
            try {
                BankAccount ownAccount = accounts.stream().filter(account -> account.getId().equals(own_account_id)).findFirst().orElse(null);
                BankAccount targetAccount = accounts.stream().filter(account -> account.getId().equals(body.get("targetId"))).findFirst().orElse(null);
                if (ownAccount != null && targetAccount != null) {
                    reqestedUser.deposit(ownAccount, targetAccount, Double.parseDouble(body.get("amount")));
                }
                return new ResponseEntity<BankAccount>(ownAccount, HttpStatusCode.valueOf(404));
            } catch (InsufficientFundsException | OwnerException e) {
                return new ResponseEntity<String>(e.getMessage(), HttpStatusCode.valueOf(400));
            }

        }
        return  new ResponseEntity<String>("404 user not found", HttpStatusCode.valueOf(404));
    }

    @PutMapping("/users/{id}/accounts/{own_account_id}/withdraw")
    ResponseEntity withdraw(@PathVariable String id, @PathVariable String own_account_id, @RequestBody Map <String, String> body) {
        User reqestedUser = users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
        if (reqestedUser != null) {
            try {
                BankAccount ownAccount = accounts.stream().filter(account -> account.getId().equals(own_account_id)).findFirst().orElse(null);
                BankAccount targetAccount = accounts.stream().filter(account -> account.getId().equals(body.get("targetId"))).findFirst().orElse(null);
                if (ownAccount != null && targetAccount != null) {
                    reqestedUser.withdraw(ownAccount, targetAccount, Double.parseDouble(body.get("amount")));
                }
                return new ResponseEntity<BankAccount>(ownAccount, HttpStatusCode.valueOf(404));
            } catch (InsufficientFundsException | OwnerException e) {
                return new ResponseEntity<String>(e.getMessage(), HttpStatusCode.valueOf(400));
            }

        }
        return  new ResponseEntity<String>("404 user not found", HttpStatusCode.valueOf(404));
    }

    @PutMapping("/users/{id}/accounts/{account_id}/add")
    ResponseEntity addOwner(@PathVariable String id, @PathVariable String account_id, @RequestBody Map<String, String> body) {
        User reqestedUser = users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
        User additionalOwner = users.stream().filter(user -> user.getId().equals(body.get("userId"))).findFirst().orElse(null);
        if (reqestedUser != null && additionalOwner != null) {
            try {
                BankAccount ownAccount = accounts.stream().filter(account -> account.getId().equals(account_id)).findFirst().orElse(null);
                if (ownAccount != null) {
                    ownAccount.addOwner(additionalOwner);
                }
                return new ResponseEntity<BankAccount>(ownAccount, HttpStatusCode.valueOf(404));
            } catch ( OwnerException e) {
                return new ResponseEntity<String>(e.getMessage(), HttpStatusCode.valueOf(400));
            }

        }
        return  new ResponseEntity<String>("404 user not found", HttpStatusCode.valueOf(404));
    }

}
