package org.example;

public interface Participant {
    boolean prepare();
    void commit();
    void abort();
}
