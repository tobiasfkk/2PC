package org.example.server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionCoordinator {
    private List<ParticipantHandler> participants = new ArrayList<>();
    private ServerSocket serverSocket;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Coordinator started on port " + port);

        while (true) {
            Socket socket = serverSocket.accept();
            ParticipantHandler participant = new ParticipantHandler(socket);
            participants.add(participant);
            new Thread(participant).start();
        }
    }

    public void beginTransaction() {
        boolean allReady = true;

        // Fase de Preparação
        for (ParticipantHandler participant : participants) {
            if (!participant.prepare()) {
                allReady = false;
                break;
            }
        }

        // Fase de Confirmação
        if (allReady) {
            for (ParticipantHandler participant : participants) {
                participant.commit();
            }
            System.out.println("Transaction committed.");
        } else {
            for (ParticipantHandler participant : participants) {
                participant.abort();
            }
            System.out.println("Transaction aborted.");
        }
    }

    private class ParticipantHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private boolean readyToCommit = false;

        public ParticipantHandler(Socket socket) throws IOException {
            this.socket = socket;
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }

        @Override
        public void run() {
            // Placeholder for any continuous communication if needed
        }

        public boolean prepare() {
            out.println("PREPARE");
            try {
                String response = in.readLine();
                readyToCommit = "READY".equals(response);
                return readyToCommit;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        public void commit() {
            if (readyToCommit) {
                out.println("COMMIT");
            }
        }

        public void abort() {
            out.println("ABORT");
        }
    }
}
