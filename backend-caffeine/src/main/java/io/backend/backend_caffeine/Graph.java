package io.backend.backend_caffeine;

import java.time.LocalDateTime;

public class Graph {
    String val;
    private final LocalDateTime createdAt;

    public Graph(String  val) {

        this.val = val;
        createdAt=LocalDateTime.now();
    }
    public String getValue() {
        return this.val;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "val='" + val + '\'' +
                '}';
    }
}
