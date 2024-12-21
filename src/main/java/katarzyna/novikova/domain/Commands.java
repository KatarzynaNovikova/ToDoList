package katarzyna.novikova.domain;

public enum Commands {
    GET_ALL("get all", "Gets all tasks from the list"),
    GET("get", "Get single task by ID from the list"),
    DELETE("delete", "Removes given task from the list"),
    ADD("add", "Adds given task to the list"),
    EXIT("exit", "Closes the programm"),
    QUIT("quit", "Closes the programm"),
    Q("q", "Closses the programm"),
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
