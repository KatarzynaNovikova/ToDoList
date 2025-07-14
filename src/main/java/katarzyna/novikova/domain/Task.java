package katarzyna.novikova.domain;

public class Task {
    private static long idCounter = 1;
    private final long id;
    private final String name;
    private final Priority priority;

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

    public Priority getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return String.format("| %-3d | %-30s | %-7s |", id, name, priority);
    }

    public static void resetTaskCounter() {
        idCounter = 1;
    }

    public void print() {
        System.out.println(this);
    }
}

