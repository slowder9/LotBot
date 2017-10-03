package com.company.lotbot;

public class Space {
    private Transaction transaction;

    public Space() {
    }

    public Space(Transaction transaction) {
        this.transaction = transaction;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
