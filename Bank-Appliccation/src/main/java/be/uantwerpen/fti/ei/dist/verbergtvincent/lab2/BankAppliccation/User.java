package be.uantwerpen.fti.ei.dist.verbergtvincent.lab2.BankAppliccation;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;


interface UserInterface {
    public String getId();
    public void setName(String name);
    public String getName();
    public ArrayList<String> getAccounts();
    public void addAccount(BankAccount account) throws OwnerException;
    public void removeAccount(BankAccount account) throws OwnerException;
    public void deposit(BankAccount ownerAccount, BankAccount targetAccount, double amount) throws InsufficientFundsException, OwnerException;
    public void withdraw(BankAccount ownerAccount, BankAccount targetAccount, double amount) throws InsufficientFundsException, OwnerException;
}
public class User implements UserInterface{
    private String id = UUID.randomUUID().toString();
    private String name;
    private ArrayList<BankAccount> accounts;

    public User() {
    }
    public User(String name) {
        this.name = name;
        this.accounts = new ArrayList<BankAccount>();
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getAccounts() {
        return accounts.stream().map(account -> account.getId()).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public void addAccount(BankAccount account) throws OwnerException {
        if (!accounts.contains(account)) {
            accounts.add(account);
            account.addOwner(this);
            return;
        }
        throw new OwnerException("You are already owner of this account");
    }

    @Override
    public void removeAccount(BankAccount account) throws OwnerException {
        if (accounts.contains(account)) {
            accounts.remove(account);
            account.removeOwner(this);
            return;
        }
        throw new OwnerException("You don't own this account");
    }

    @Override
    public void deposit(BankAccount ownerAccount, BankAccount targetAccount, double amount) throws InsufficientFundsException, OwnerException {
        if (this.accounts.contains(ownerAccount)) {
            ownerAccount.deposit(targetAccount, amount);
            return;
        }
        throw new OwnerException("You don't own this account");
    }

    @Override
    public void withdraw(BankAccount ownerAccount, BankAccount targetAccount, double amount) throws InsufficientFundsException, OwnerException {
        if (this.accounts.contains(ownerAccount)) {
            ownerAccount.withdraw(targetAccount, amount);
            return;
        }
        throw new OwnerException("You don't own this account");
    }

    @Override
    public String toString() {
        return "User(id= " + this.id + ", name= " + this.name + " accounts= " + this.accounts.stream().map(BankAccount::getId).collect(Collectors.toList()) + ")";
    }

}
