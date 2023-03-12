package be.uantwerpen.fti.ei.dist.verbergtvincent.lab2.BankAppliccation;


import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

interface BankAccountInterface {
    public void addOwner(User owner) throws OwnerException;
    public void removeOwner(User owner) throws OwnerException;
    public void deposit(BankAccount account, double amount) throws InsufficientFundsException;
    public void withdraw(BankAccount account, double amount);
    public String getId();
    public double getBalance();
    public ArrayList<String> getOwners();
        }

public class BankAccount implements BankAccountInterface {
    private String id = UUID.randomUUID().toString();
    private ArrayList<User> owners;
    private double balance;

     public BankAccount() {
        this.owners = new ArrayList<User>();
        this.balance = 0;
    }

    @Override
    public void addOwner(User owner) throws OwnerException {
        if (!owners.contains(owner)) {
            owners.add(owner);
            return;
        }
        throw new OwnerException("User is already owner of this account");
    }

    @Override
    public void removeOwner(User owner) throws OwnerException {
        if (owners.contains(owner)) {
            owners.remove(owner);
            return;
        }
        throw new OwnerException("User is not an owner of this account");
    }

    @Override
    public void deposit(BankAccount account, double amount) throws InsufficientFundsException {
         if (this.balance >= amount) {
             this.balance -= amount;
             account.balance += amount;
             return;
         }
         throw new InsufficientFundsException("The account you are withdrawing from does not have the sufficient funds");
    }

    public String getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<String> getOwners() {
        return owners.stream().map(User::getId).collect(Collectors.toCollection(ArrayList::new));
    }
    @Override
    public String toString() {
        return "BankAccount(id= " + this.id + ", balance= " + this.balance + " owners= " + this.owners.stream().map(User::getId).collect(Collectors.toList()) + ")";
    }

}
