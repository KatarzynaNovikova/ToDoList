package katarzyna.novikova.domain;

public enum Commands {
    GET_ALL("get all", "Gets all tasks from the list"),
    GET("get", "Get single task by ID from the list"),
    DELETE("delete", "Removes given task by ID from the list"),
    ADD("add", "Adds given task to the list; accepts a task text and an optional task priority: high, average (default), low"),
    EXIT("exit", "Closes the program"),
    QUIT("quit", "Closes the program"),
    Q("q", "Closes the program"),
    HELP("help", "Displays all available commands");

    private final String name;
    private final String description;

    Commands(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " [" + description + "]";
    }
}
