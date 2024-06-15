package org.example.client;

public interface Participant {
    boolean prepare();
    void commit();
    void abort();
}
