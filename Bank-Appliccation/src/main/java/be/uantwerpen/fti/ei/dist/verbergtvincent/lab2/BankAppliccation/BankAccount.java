package be.uantwerpen.fti.ei.dist.verbergtvincent.lab2.BankAppliccation;


import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

interface BankAccountInterface {
    public void addOwner(User owner) throws OwnerException;
    public void removeOwner(User owner);
    public void deposit(BankAccount account);
    public void withdraw(BankAccount account);
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

//    @Override
//    public void addOwner(User owner) throws OwnerException {
//        if (!owners.contains(owner)) {
//            owners.add(owner);
//            return;
//        }
//        throw new OwnerException("User is already owner of this account");
//    }

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
