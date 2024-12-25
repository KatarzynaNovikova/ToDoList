package katarzyna.novikova.service;

import katarzyna.novikova.domain.Priority;
import katarzyna.novikova.domain.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskServiceImpl implements TaskService {

    private final List<Task> tasks = new ArrayList<>();

    private Task buildTask(String input) {
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

    @Override
    public void printAll() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found");
        }
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

}
