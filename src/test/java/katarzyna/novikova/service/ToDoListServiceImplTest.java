package katarzyna.novikova.service;

import katarzyna.novikova.BaseTest;
import katarzyna.novikova.domain.Commands;
import katarzyna.novikova.domain.Priority;
import katarzyna.novikova.service.ToDoListService;
import katarzyna.novikova.service.ToDoListServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintStream;
import java.util.List;

import static katarzyna.novikova.domain.Task.resetTaskCounter;
import static org.assertj.core.api.Assertions.assertThat;

public class ToDoListServiceImplTest extends BaseTest {
    private ToDoListService toDoListService;
    @BeforeEach
    public void beforeEach() {
        toDoListService = new ToDoListServiceImpl();
        resetTaskCounter();
    }

    @Test
    public void shouldAdd1TaskSuccessfullyAndPrintResult() {
        //given
        provideInput(List.of("add task1", "exit"));
        //when
        toDoListService.run();
        //then
        List<String> resultOutput = getSystemOutput(); // actual result
        assertThat(resultOutput.get(0)).isEqualTo("Enter command");
        assertThat(resultOutput.get(1)).contains("1");
        assertThat(resultOutput.get(1)).contains("task1");
        assertThat(resultOutput.get(1)).contains(Priority.AVERAGE.name());
    }

    @Test
    public void shouldAdd2TasksSuccessfullyAndPrintResult() {
        //given
        provideInput(List.of("add task1", "add task2", "exit"));
        //when
        toDoListService.run();
        //then
        List<String> resultOutput = getSystemOutput(); // actual result
        assertThat(resultOutput.get(0)).isEqualTo("Enter command");
        assertThat(resultOutput.get(1)).contains("1");
        assertThat(resultOutput.get(1)).contains("task1");
        assertThat(resultOutput.get(1)).contains(Priority.AVERAGE.name());
        assertThat(resultOutput.get(2)).contains("2");
        assertThat(resultOutput.get(2)).contains("task2");
        assertThat(resultOutput.get(2)).contains(Priority.AVERAGE.name());
    }

    @Test
    public void shouldPrintErrorWhenTryToAddEmptyTask() {
        //given
        provideInput(List.of("add", "exit"));
        //when
        toDoListService.run();
        //then
        List<String> resultOutput = getSystemOutput(); // actual result
        assertThat(resultOutput.get(0)).isEqualTo("Enter command");
        List<String> errOutput = getSystemErrors();
        assertThat(errOutput.get(0)).isEqualTo("incorrect command usage");
    }

    @Test
    public void shouldDelete1TaskSuccessfullyAndPrintResult() {
        //given
        provideInput(List.of("add task1", "delete 1", "get all", "quit"));
        //when
        toDoListService.run();
        //then
        List<String> resultOutput = getSystemOutput(); // actual result
        assertThat(resultOutput.get(0)).isEqualTo("Enter command");
        assertThat(resultOutput.get(1)).contains("1");
        assertThat(resultOutput.get(1)).contains("task1");
        assertThat(resultOutput.get(1)).contains(Priority.AVERAGE.name());
        assertThat(resultOutput.get(2)).contains("1");
        assertThat(resultOutput.get(2)).contains("task1");
        assertThat(resultOutput.get(2)).contains(Priority.AVERAGE.name());
        assertThat(resultOutput.get(3)).isEqualTo("No tasks found");
    }

    @Test
    public void shouldPrintEmptyListSuccessfullyAfterAttemptToDeleteNonExistingTask() {
        //given
        provideInput(List.of("delete 1", "q"));
        //when
        toDoListService.run();
        //then
        List<String> resultOutput = getSystemOutput(); // actual result
        assertThat(resultOutput.get(0)).isEqualTo("Enter command");
        assertThat(resultOutput.get(1)).isEqualTo("Task with ID = 1 was not found");
    }

    @Test
    public void shouldPrintAllGivenTasksSuccessfully() {
        //given
        provideInput(List.of("add task1", "add task2", "get all", "exit"));
        //when
        toDoListService.run();
        //then
        List<String> resultOutput = getSystemOutput(); // actual result
        assertThat(resultOutput.get(0)).isEqualTo("Enter command");
        assertThat(resultOutput.get(1)).contains("1");
        assertThat(resultOutput.get(1)).contains("task1");
        assertThat(resultOutput.get(1)).contains(Priority.AVERAGE.name());
        assertThat(resultOutput.get(2)).contains("2");
        assertThat(resultOutput.get(2)).contains("task2");
        assertThat(resultOutput.get(2)).contains(Priority.AVERAGE.name());
        assertThat(resultOutput.get(3)).contains("1");
        assertThat(resultOutput.get(3)).contains("task1");
        assertThat(resultOutput.get(3)).contains(Priority.AVERAGE.name());
        assertThat(resultOutput.get(4)).contains("2");
        assertThat(resultOutput.get(4)).contains("task2");
        assertThat(resultOutput.get(4)).contains(Priority.AVERAGE.name());
    }

    @Test
    public void shouldPrintEmptyListSuccessfullyAfterAttemptToWriteCommandGetAllToEmptyList() {
        //given
        provideInput(List.of("get all", "quit"));
        //when
        toDoListService.run();
        //then
        List<String> resultOutput = getSystemOutput(); // actual result
        assertThat(resultOutput.get(0)).isEqualTo("Enter command");
        assertThat(resultOutput.get(1)).isEqualTo("No tasks found");
    }

    @Test
    public void shouldPrintAllExistingCommandsSuccessfullyWithTheirDescriptionAfterHelpInput() {
        //given
        provideInput(List.of("help", "exit"));
        //when
        toDoListService.run();
        //then
        List<String> resultOutput = getSystemOutput(); // actual result
        assertThat(resultOutput.get(0)).isEqualTo("Enter command");
        assertThat(resultOutput).contains(Commands.ADD.toString());
        assertThat(resultOutput).contains(Commands.DELETE.toString());
        assertThat(resultOutput).contains(Commands.GET_ALL.toString());
        assertThat(resultOutput).contains(Commands.EXIT.toString());
        assertThat(resultOutput).contains(Commands.QUIT.toString());
        assertThat(resultOutput).contains(Commands.Q.toString());
        assertThat(resultOutput).contains(Commands.HELP.toString());
    }

    @Test
    public void shouldPrintUnknownCommandWithInputAfterAddingUnknownCommand() {
        //given
        provideInput(List.of("abc", "exit"));
        //when
        toDoListService.run();
        //then
        List<String> resultOutput = getSystemOutput(); // actual result
        assertThat(resultOutput.get(0)).isEqualTo("Enter command");
        List<String> resultOutputError = getSystemErrors();
        assertThat(resultOutputError.get(0)).isEqualTo("Unknown command: abc");
    }

}
