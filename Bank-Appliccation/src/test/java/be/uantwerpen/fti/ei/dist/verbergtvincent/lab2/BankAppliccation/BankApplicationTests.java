package be.uantwerpen.fti.ei.dist.verbergtvincent.lab2.BankAppliccation;

import org.junit.jupiter.api.Test;

public class BankApplicationTests {
    @Test
    void testBankApplication() throws OwnerException, InsufficientFundsException {
        User user1 = new User("John");
        User user2 = new User("James");
        System.out.println("User 1 = " + user1);
        System.out.println("User 2 = " + user2);

        BankAccount accountOfUser1 = new BankAccount();
        accountOfUser1.addOwner(user1);

        BankAccount accountOfUser2 = new BankAccount();
        accountOfUser2.addOwner(user2);

        BankAccount sharedAccount = new BankAccount();
        sharedAccount.addOwner(user1);
        sharedAccount.addOwner(user2);

        InfiniteBankAccount infiniteBankAccount = new InfiniteBankAccount();
        infiniteBankAccount.setBalance(1000);

        System.out.println("Account of user 1 = " + accountOfUser1);
        System.out.println("Account of user 2 = " + accountOfUser2);
        System.out.println("Shared account  = " + sharedAccount );
        System.out.println("user 1 = " + sharedAccount);

        System.out.println("initializing accounts with money");
        infiniteBankAccount.deposit(accountOfUser1, 500);
        infiniteBankAccount.deposit(accountOfUser2, 500);

        System.out.println("Account of user 1 = " + accountOfUser1);
        System.out.println("Account of user 2 = " + accountOfUser2);
        System.out.println("Shared account  = " + sharedAccount );

        System.out.println("Transferring money to shared account");

        accountOfUser1.deposit(sharedAccount, 200);
        accountOfUser2.deposit(sharedAccount, 400);

        System.out.println("Account of user 1 = " + accountOfUser1);
        System.out.println("Account of user 2 = " + accountOfUser2);
        System.out.println("Shared account  = " + sharedAccount );

        System.out.println("Withdrawing from shared account");

        accountOfUser1.withdraw(sharedAccount, 550);
        accountOfUser2.withdraw(sharedAccount, sharedAccount.getBalance());

        System.out.println("Account of user 1 = " + accountOfUser1);
        System.out.println("Account of user 2 = " + accountOfUser2);
        System.out.println("Shared account  = " + sharedAccount );
    }
}


