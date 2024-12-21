package katarzyna.novikova;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

public abstract class BaseTest {

    private final PrintStream standardOut = System.out;
    private final PrintStream standardErr = System.err;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errorStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        System.setErr(new PrintStream(errorStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
        System.setOut(standardErr);
    }

    protected void provideInput(List<String> data) {
        String input = data.stream().reduce("", (a, b)-> a.concat(b) + System.lineSeparator());
        ByteArrayInputStream testIn = new ByteArrayInputStream(input.getBytes());
        System.setIn(testIn);
    }

    protected List<String> getSystemOutput(){
        return Arrays.asList(outputStreamCaptor.toString().trim().split(System.lineSeparator()));
    }

    protected List<String> getSystemErrors(){
        return Arrays.asList(errorStreamCaptor.toString().trim().split(System.lineSeparator()));
    }
}
