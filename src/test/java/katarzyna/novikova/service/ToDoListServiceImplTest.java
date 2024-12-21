package katarzyna.novikova.service;

import katarzyna.novikova.BaseTest;
import katarzyna.novikova.domain.Commands;
import katarzyna.novikova.service.ToDoListService;
import katarzyna.novikova.service.ToDoListServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ToDoListServiceImplTest extends BaseTest {
    private final ToDoListService toDoListService = new ToDoListServiceImpl();

    @Test
    public void shouldAdd1TaskSuccessfullyAndPrintResult() {
        //given
        provideInput(List.of("add task1", "exit"));
        //when
        toDoListService.run();
        //then
        List<String> resultOutput = getSystemOutput(); // actual result
        assertThat(resultOutput.get(0)).isEqualTo("Enter command");
        assertThat(resultOutput.get(1)).isEqualTo("[task1]");
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
        assertThat(resultOutput.get(1)).isEqualTo("[task1]");
        assertThat(resultOutput.get(2)).isEqualTo("[task1, task2]");
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
        provideInput(List.of("add task1", "delete task1", "quit"));
        //when
        toDoListService.run();
        //then
        List<String> resultOutput = getSystemOutput(); // actual result
        assertThat(resultOutput.get(0)).isEqualTo("Enter command");
        assertThat(resultOutput.get(1)).isEqualTo("[task1]");
        assertThat(resultOutput.get(2)).isEqualTo("[]");
    }

    @Test
    public void shouldPrintEmptyListSuccessfullyAfterAttemptToDeleteNonExistingTask() {
        //given
        provideInput(List.of("delete task1", "q"));
        //when
        toDoListService.run();
        //then
        List<String> resultOutput = getSystemOutput(); // actual result
        assertThat(resultOutput.get(0)).isEqualTo("Enter command");
        assertThat(resultOutput.get(1)).isEqualTo("[]");
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
        assertThat(resultOutput.get(1)).isEqualTo("[task1]");
        assertThat(resultOutput.get(2)).isEqualTo("[task1, task2]");
        assertThat(resultOutput.get(3)).isEqualTo("[task1, task2]");
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
        assertThat(resultOutput.get(1)).isEqualTo("[]");
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
