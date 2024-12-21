package katarzyna.novikova;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ToDoListApplicationTest extends BaseTest {

    @Test
    public void shouldAdd1TaskSuccessfullyAndPrintResult() {
        //given
        provideInput(List.of("add task1", "exit"));
        //when
        ToDoListApplication.main(null);
        //then
        List<String> resultOutput = getSystemOutput(); // actual result
        assertThat(resultOutput.get(0)).isEqualTo("Enter command");
        assertThat(resultOutput.get(1)).isEqualTo("[task1]");
    }
}