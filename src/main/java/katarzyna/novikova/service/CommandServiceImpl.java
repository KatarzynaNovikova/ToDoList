package katarzyna.novikova.service;

import katarzyna.novikova.domain.Commands;

import java.util.Scanner;

public class CommandServiceImpl implements CommandService {
    private final TaskService taskService;

    public CommandServiceImpl(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter command");

        while (true) {
            String input = scanner.nextLine();
            if (input.equals(Commands.EXIT.getName()) || input.equals(Commands.QUIT.getName()) || input.equals(Commands.Q.getName())) {
                break;
            } else if (input.equals(Commands.GET_ALL.getName())) {
                taskService.printAll();
            } else if (input.startsWith(Commands.GET.getName())) {
                taskService.get(input).print();
            } else if (input.startsWith(Commands.ADD.getName())) {
                taskService.add(input);
            } else if (input.startsWith(Commands.DELETE.getName())) {
                taskService.delete(input);
            } else if (input.equals(Commands.HELP.getName())) {
                for (Commands command : Commands.values()) {
                    System.out.println(command);
                }
            } else {
                System.err.println("Unknown command: " + input);
            }
        }
    }
}


