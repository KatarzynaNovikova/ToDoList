package katarzyna.novikova.domain;

import java.util.List;

public class Task {
    private static long idCounter = 1;
    private final long id;
    private String name;
    private Priority priority;

    public Task(String name, Priority priority) {
        this.id = idCounter++;
        this.name = name;
        this.priority = priority;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return String.format("| %-3d | %-30s | %-7s |", id, name, priority);
    }


}

