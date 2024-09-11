package org.example;

public class ToDoListApplication {
    public static void main(String[] args) {
        ToDoListService toDoListService = new ToDoListServiceImpl();
        toDoListService.run();
    }
}