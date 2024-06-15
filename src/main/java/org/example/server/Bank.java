package org.example.server;

import org.example.client.Participant;

import java.io.*;
import java.net.Socket;

public class Bank implements Participant {
    private String name;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public Bank(String name, String host, int port) throws IOException {
        this.name = name;
        this.socket = new Socket(host, port);
        this.out = new PrintWriter(socket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void listenForCommands() throws IOException {
        String command;
        while ((command = in.readLine()) != null) {
            switch (command) {
                case "PREPARE":
                    if (prepare()) {
                        out.println("READY");
                    } else {
                        out.println("FAILURE");
                    }
                    break;
                case "COMMIT":
                    commit();
                    break;
                case "ABORT":
                    abort();
                    break;
            }
        }
    }

    @Override
    public boolean prepare() {
        // Simulando a lógica de preparação
        boolean readyToCommit = Math.random() > 0.2;  // 80% chance de estar pronto
        System.out.println(name + " prepared: " + readyToCommit);
        return readyToCommit;
    }

    @Override
    public void commit() {
        System.out.println(name + " committed.");
    }

    @Override
    public void abort() {
        System.out.println(name + " aborted.");
    }
}
