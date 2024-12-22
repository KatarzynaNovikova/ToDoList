package katarzyna.novikova.service;

import katarzyna.novikova.domain.Commands;
import katarzyna.novikova.domain.Priority;
import katarzyna.novikova.domain.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static katarzyna.novikova.util.TasksUtil.printAll;

public class ToDoListServiceImpl implements ToDoListService {

    private final List<Task> tasks = new ArrayList<>();

    //todo
    //1) sort by priority
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
                printAll(tasks);
            } else if (input.startsWith(Commands.GET.getName())) {
                //get 1
                Task task = getTaskById(input);
                if(task != null) {
                    System.out.println(task);
                }
            } else if (input.startsWith(Commands.ADD.getName())) {
                String[] result = input.split(" ");
                if (result.length <= 1) {
                    System.err.println("incorrect command usage");
                } else {
                    Task task = getTask(input);
                    tasks.add(task);
                    printAll(tasks);
                }
            } else if (input.startsWith(Commands.DELETE.getName())) {
                Task taskToRemove = getTaskById(input);
                if (taskToRemove == null) {
                    System.err.println("Task has not been found");
                } else {
                    tasks.remove(taskToRemove);
                    printAll(tasks);
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

    private Task getTask(String input) {
        //add test 123 high
        String[] results = input.split(" ");
        StringBuilder taskName = new StringBuilder();
        Priority priority = getPriority(input);
        for (int i = 1; i < results.length; i++) {
            if (i < results.length - 1) {
                taskName.append(results[i]).append(" ");
            } else {
                if (priority == null) {
                    taskName.append(results[i]);
                    priority = Priority.AVERAGE;
                }
            }
        }

        return new Task(taskName.toString().trim(), priority);

    }

    private Priority getPriority(String input) {
        if (input.toUpperCase().endsWith(Priority.HIGH.name())) {
            return Priority.HIGH;
        } else if (input.toUpperCase().endsWith(Priority.AVERAGE.name())) {
            return Priority.AVERAGE;
        } else if (input.toUpperCase().endsWith(Priority.LOW.name())) {
            return Priority.LOW;
        } else {
            return null;
        }
    }

    private Task getTaskById(String input) {
        String[] result = input.split(" ");
        if (result.length <= 1) {
            System.out.println("incorrect command usage");
        } else {
            try {
                long id = Long.parseLong(result[1]);
                for (Task task : tasks) {
                    if (task.getId() == id) {
                        return task;
                    }
                }
                System.out.printf("Task with ID = %d was not found\n", id);
            } catch (NumberFormatException e) {
                System.err.printf("Provided %s is not a number. Please provide a numeric task ID\n", result[1]);
            }
        }
        return null;
    }

}


