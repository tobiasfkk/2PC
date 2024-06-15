package org.example;

public class Main {
    public static void main(String[] args) {
        TransactionCoordinator coordinator = new TransactionCoordinator();
        Bank bank1 = new Bank("Bank 1");
        Bank bank2 = new Bank("Bank 2");

        coordinator.addParticipant(bank1);
        coordinator.addParticipant(bank2);

        coordinator.beginTransaction();
    }
}