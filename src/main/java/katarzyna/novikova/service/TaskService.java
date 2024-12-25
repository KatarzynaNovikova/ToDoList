package katarzyna.novikova.service;

import katarzyna.novikova.domain.Task;

import java.util.List;

public interface TaskService {
    void printAll();

    Task get(String input);

    void add(String input);

    void delete(String input);


}
