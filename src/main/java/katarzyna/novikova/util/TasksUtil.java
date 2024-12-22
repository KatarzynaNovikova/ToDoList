package katarzyna.novikova.util;

import katarzyna.novikova.domain.Task;

import java.util.List;

public class TasksUtil {
    public static void printAll(List<Task> tasks) {
        for (Task task : tasks) {
            System.out.println(task);
        }
    }
}
