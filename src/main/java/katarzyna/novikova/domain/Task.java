package katarzyna.novikova.domain;

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
        return String.format("%d | %s | %s", id, name, priority);
    }
}
