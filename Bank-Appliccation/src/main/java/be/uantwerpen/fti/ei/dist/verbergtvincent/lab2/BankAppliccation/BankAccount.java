package be.uantwerpen.fti.ei.dist.verbergtvincent.lab2.BankAppliccation;


import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

public class BankAccount {
    private String id = UUID.randomUUID().toString();
    private ArrayList<User> users;
    private double balance;

     public BankAccount() {
        this.users = new ArrayList<User>();
        this.balance = 0;
    }

    public String getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<String> getUsers() {
        return users.stream().map(user -> user.getId()).collect(Collectors.toCollection(ArrayList::new));
    }
}
