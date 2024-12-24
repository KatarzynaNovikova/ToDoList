package katarzyna.novikova.util;

import katarzyna.novikova.domain.Task;

import java.util.List;

public class TasksUtil {
    public static void printAll(List<Task> tasks) {
        if(tasks.isEmpty()) {
            System.out.println("No tasks found");
        }
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
}
