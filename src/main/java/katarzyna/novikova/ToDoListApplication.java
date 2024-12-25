package katarzyna.novikova;

import katarzyna.novikova.service.CommandService;
import katarzyna.novikova.service.CommandServiceImpl;
import katarzyna.novikova.service.TaskServiceImpl;

public class ToDoListApplication {
    public static void main(String[] args) {
        CommandService commandService = new CommandServiceImpl(new TaskServiceImpl());
        commandService.run();
    }
}