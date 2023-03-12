package be.uantwerpen.fti.ei.dist.verbergtvincent.lab2.BankAppliccation;


import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

public class BankAccount {
    private String id = UUID.randomUUID().toString();
    private ArrayList<User> owners;
    private double balance;

     public BankAccount() {
        this.owners = new ArrayList<User>();
        this.balance = 0;
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
