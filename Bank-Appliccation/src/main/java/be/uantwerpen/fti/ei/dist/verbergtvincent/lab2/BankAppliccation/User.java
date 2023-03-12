package be.uantwerpen.fti.ei.dist.verbergtvincent.lab2.BankAppliccation;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

public class User {
    private String id = UUID.randomUUID().toString();
    private String name;
    private ArrayList<BankAccount> accounts;


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
    public String toString() {
        return "BankAccount(id= " + this.id + ", name= " + this.name + " accounts= " + this.accounts.stream().map(BankAccount::getId).collect(Collectors.toList()) + ")";
    }

}
