package katarzyna.novikova.service;

import katarzyna.novikova.domain.Priority;
import katarzyna.novikova.domain.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static katarzyna.novikova.domain.Task.resetTaskCounter;
import static org.assertj.core.api.Assertions.assertThat;


class TaskServiceImplTest {
    private final TaskService taskService = new TaskServiceImpl();

    @BeforeEach
    public void beforeEach() {
        resetTaskCounter();
    }

    @Test
    void addTaskWithDefaultPriorityAndCheckIfPriorityIsAverage() {
        // given
        String input = "add task 123";
        taskService.add(input);
        Task task = taskService.get("get 1");
        assertThat(task).isNotNull();
        assertThat(task.getId()).isEqualTo(1);
        assertThat(task.getName()).isEqualTo("task 123");
        assertThat(task.getPriority()).isEqualTo(Priority.AVERAGE);
    }


    @Test
    void addTaskWithHighPriorityAndCheckIfPriorityIsHigh() {
        // given
        String input = "add task 123 high";
        taskService.add(input);
        Task task = taskService.get("get 1");
        assertThat(task).isNotNull();
        assertThat(task.getId()).isEqualTo(1);
        assertThat(task.getName()).isEqualTo("task 123");
        assertThat(task.getPriority()).isEqualTo(Priority.HIGH);
    }

    @Test
    void addTaskAndDeleteTaskShouldReturnEmptyTaskList() {
        // given
        String input = "add task 123";
        taskService.add(input);
        taskService.delete("delete 1");
        List<Task> tasks = taskService.getAll();
        assertThat(tasks).isEmpty();
    }

}