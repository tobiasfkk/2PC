package org.example.server;

import java.io.IOException;

public class CoordinatorMain {
    public static void main(String[] args) {
        TransactionCoordinator coordinator = new TransactionCoordinator();

        try {
            coordinator.start(8080); // Porta onde o coordenador estará ouvindo as conexões
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
