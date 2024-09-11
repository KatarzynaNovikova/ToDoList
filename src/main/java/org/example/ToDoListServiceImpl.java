package org.example;
import java.util.List;
import java.util.Scanner;

public class ToDoListServiceImpl implements ToDoListService {

    List<String> tasks = List.of("Task1", "Task2", "Task3");

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter command");

        String input = scanner.nextLine();
        if(input.equals("get all")) {
            System.out.println(tasks);
        }
    }
}
