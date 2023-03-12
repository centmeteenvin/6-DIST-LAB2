package be.uantwerpen.fti.ei.dist.verbergtvincent.lab2.BankAppliccation;

class OwnerException extends Exception {
    OwnerException(String message) {
        super(message);
    }
}

class InsufficientFundsException extends Exception {
    InsufficientFundsException(String message) {
        super(message);
    }
}