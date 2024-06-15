package org.example;

public class Bank implements Participant {
    private String name;
    private boolean readyToCommit;

    public Bank(String name) {
        this.name = name;
    }

    @Override
    public boolean prepare() {
        // Simulando a lógica de preparação
        readyToCommit = Math.random() > 0.2;  // 80% chance de estar pronto
        System.out.println(name + " prepared: " + readyToCommit);
        return readyToCommit;
    }

    @Override
    public void commit() {
        if (readyToCommit) {
            System.out.println(name + " committed.");
        }
    }

    @Override
    public void abort() {
        System.out.println(name + " aborted.");
    }
}
