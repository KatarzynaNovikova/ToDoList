package katarzyna.novikova.service;

import katarzyna.novikova.domain.Commands;
import katarzyna.novikova.domain.Priority;
import katarzyna.novikova.domain.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToDoListServiceImpl implements ToDoListService {

    private final List<Task> tasks = new ArrayList<>();

    //todo
    //1) support task priority: add, display, sort by priority
    // Test get command
    //2) fix all tests, add new tests for tasks & priority
    //3) refactor and cleanup
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter command");

        while (true) {
            String input = scanner.nextLine();
            if (input.equals(Commands.EXIT.getName()) || input.equals(Commands.QUIT.getName()) || input.equals(Commands.Q.getName())) {
                break;
            } else if (input.equals(Commands.GET_ALL.getName())) {
                System.out.println(tasks);
            } else if (input.startsWith(Commands.GET.getName())) {
                //get 1
                String[] result = input.split(" ");
                if(result.length <= 1) {
                    System.out.println("incorrect command usage");
                } else {
                    try {
                        long id = Long.parseLong(result[1]);
                        boolean isTaskFound = false;
                        for(Task task : tasks) {
                            if(task.getId() == id) {
                                isTaskFound = true;
                                System.out.println(task);
                                break;
                            }
                        }
                        if(!isTaskFound) {
                            System.out.printf("Task with ID = %d was not found\n", id);
                        }
                    }
                  catch (NumberFormatException e){
                        System.err.printf("Provided %s is not a number. Please provide a numeric task ID\n", result[1]);
                    }
                }
            } else if (input.startsWith(Commands.ADD.getName())) {
                String[] result = input.split(" ");
                if (result.length <= 1) {
                    System.err.println("incorrect command usage");
                } else {
                    Task task = new Task(input.substring(4), Priority.AVERAGE);
                    tasks.add(task);
                    System.out.println(tasks);
                }
            } else if (input.startsWith(Commands.DELETE.getName())) {
                String taskName = input.substring(7);
                Task taskToRemove = null;
                for (Task current : tasks) {
                    if (current.getName().equals(taskName)) {
                        taskToRemove = current;
                    }
                }
                if (taskToRemove == null) {
                    System.err.println("Task " + taskName + " has not been found");
                } else {
                    tasks.remove(taskToRemove);
                    System.out.println(tasks);
                }
            } else if (input.equals(Commands.HELP.getName())) {
                for (Commands command : Commands.values()) {
                    System.out.println(command);
                }
            } else {
                System.err.println("Unknown command: " + input);
            }
        }
    }
}
