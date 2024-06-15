package org.example;

import java.util.ArrayList;
import java.util.List;

public class TransactionCoordinator {
    private List<Participant> participants = new ArrayList<>();

    public void addParticipant(Participant participant) {
        participants.add(participant);
    }

    public void beginTransaction() {
        boolean allReady = true;

        // Fase de Preparação
        for (Participant participant : participants) {
            if (!participant.prepare()) {
                allReady = false;
                break;
            }
        }

        // Fase de Confirmação
        if (allReady) {
            for (Participant participant : participants) {
                participant.commit();
            }
            System.out.println("Transaction committed.");
        } else {
            for (Participant participant : participants) {
                participant.abort();
            }
            System.out.println("Transaction aborted.");
        }
    }
}
