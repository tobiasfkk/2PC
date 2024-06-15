package org.example.client;

import org.example.server.Bank;

import java.io.IOException;

public class ParticipantMain {
    public static void main(String[] args) {
        String name = args[0]; // Nome do banco passado como argumento
        String host = args[1]; // Host do coordenador passado como argumento
        int port = Integer.parseInt(args[2]); // Porta do coordenador passada como argumento

        try {
            Bank bank = new Bank(name, host, port);
            bank.listenForCommands();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
