package katarzyna.novikova;

import katarzyna.novikova.service.ToDoListService;
import katarzyna.novikova.service.ToDoListServiceImpl;

public class ToDoListApplication {
    public static void main(String[] args) {
        ToDoListService toDoListService = new ToDoListServiceImpl();
        toDoListService.run();
    }
}