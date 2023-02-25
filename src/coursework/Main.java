package coursework;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        TaskService taskService = new TaskService();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите: \n 1 - добавить новую задачу \n 2 - вывести список задач на экран" +
                "\n 3 - удалить задачу \n 4 - вывести список задач на день \n 5 - архив задач (удаленных) " +
                "\n 6 - выйти \n");

        while (scanner.hasNext()) {
            String menu = scanner.nextLine();
            try {
                checkArgument(menu);
            } catch (IncorrectInputException e) {
                System.out.println(e);
                System.out.println("\nВыберите: \n 1 - добавить новую задачу \n 2 - вывести список задач на экран" +
                        "\n 3 - удалить задачу \n 4 - вывести список задач на день \n 5 - архив задач (удаленных) " +
                        "\n 6 - выйти \n");
            }
            switch (menu) {
                case "1":
                    taskService.addTask(getRegularity(getTaskTitle(), getTaskDescription(), getTaskType(), LocalDateTime.now()));
                    System.out.println("\nВыберите: \n 1 - добавить новую задачу \n 2 - вывести список задач на экран" +
                            "\n 3 - удалить задачу \n 4 - вывести список задач на день \n 5 - архив задач (удаленных) " +
                            "\n 6 - выйти \n");
                    break;
                case "2":
                    System.out.println("Список всех задач");
                    try {
                        taskService.checkTaskAvailability(taskService.getTaskMap());
                        taskService.printAllTasks(taskService.getTaskMap());
                    } catch (TaskNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("\nВыберите: \n 1 - добавить новую задачу \n 2 - вывести список задач на экран" +
                            "\n 3 - удалить задачу \n 4 - вывести список задач на день \n 5 - архив задач (удаленных) " +
                            "\n 6 - выйти \n");
                    break;
                case "3":
                    System.out.println("Введите идентификационный номер задачи, которую нужно удалить: ");
                    int taskId = scanner.nextInt();
                    try {
                        taskService.addToRemovedTasks(taskService.removeTask(taskId));
                        System.out.println("Задача удалена.");
                    } catch (TaskNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("\nВыберите: \n 1 - добавить новую задачу \n 2 - вывести список задач на экран" +
                            "\n 3 - удалить задачу \n 4 - вывести список задач на день \n 5 - архив задач (удаленных) " +
                            "\n 6 - выйти \n");
                    break;
                case "4":
                    System.out.println("Введите дату в формате ГГГГ-ММ-ДД: ");
                    String date = scanner.nextLine();
                    try {
                        checkDate(date);
                        LocalDate localDate = LocalDate.parse(date);
                        System.out.println("Список задач на " + localDate + ":");
                        try {
                            taskService.checkTaskAvailability(taskService.getAllByDate(localDate));
                            taskService.printTasksByDate(taskService.getAllByDate(localDate));
                        } catch (TaskNotFoundException e) {
                            System.out.println(e.getMessage());
                        }
                        System.out.println("\nВыберите: \n 1 - добавить новую задачу \n 2 - вывести список задач на экран" +
                            "\n 3 - удалить задачу \n 4 - вывести список задач на день \n 5 - архив задач (удаленных) " +
                            "\n 6 - выйти \n");
                    } catch (DateTimeParseException e) {
                        System.out.println(e.getMessage());
                        System.out.println("\nВыберите: \n 1 - добавить новую задачу \n 2 - вывести список задач на экран" +
                                "\n 3 - удалить задачу \n 4 - вывести список задач на день \n 5 - архив задач (удаленных) " +
                                "\n 6 - выйти \n");
                    }
                    break;
                case "5":
                    System.out.println("Архив задач (удаленных)");
                    try {
                        taskService.printAllRemovedTasks(taskService.getRemovedTasks());
                    } catch (TaskNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    System.out.println("\nВыберите: \n 1 - добавить новую задачу \n 2 - вывести список задач на экран" +
                            "\n 3 - удалить задачу \n 4 - вывести список задач на день \n 5 - архив задач (удаленных) " +
                            "\n 6 - выйти \n");
                    break;
                case "6":
                    System.exit(0);
                default:
                    System.out.println("Ошибка ввода! Введите значение, указанное в меню");
                    System.out.println("\nВыберите: \n 1 - добавить новую задачу \n 2 - вывести список задач на экран" +
                            "\n 3 - удалить задачу \n 4 - вывести список задач на день \n 5 - архив задач (удаленных) " +
                            "\n 6 - выйти \n");
            }
        }
        scanner.close();
    }

    public static String getTaskTitle() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название задачи: ");
        try {
            String argument = scanner.nextLine();
            checkArgument(argument);
            return argument;
        } catch (IncorrectInputException e) {
            System.out.println(e);
            return getTaskTitle();
        }
    }

    public static String getTaskDescription() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите описание задачи: ");
        try {
            String argument = scanner.nextLine();
            checkArgument(argument);
            return argument;
        } catch (IncorrectInputException e) {
            System.out.println(e);
            return getTaskDescription();
        }
    }

    public static Type getTaskType() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите тип задачи (1 - рабочая, 2 - личная): ");
        String typeValue = scanner.nextLine();
        try {
            checkArgument(typeValue);
        } catch (IncorrectInputException e) {
            System.out.println(e);
            return getTaskType();
        }
        switch (typeValue) {
            case "1":
                return Type.WORK;
            case "2":
                return Type.PERSONAL;
            default:
                System.out.println("Тип задачи выбран не верно.");
                return getTaskType();
        }
    }

    public static Task getRegularity(String title, String description, Type type, LocalDateTime localDateTime) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите повторяемость задачи (1 - однократно, 2 - ежедневно, 3 - еженедельно, " +
                "4 - ежемесячно, 5 - ежегодно): ");
        String period = scanner.nextLine();
        try {
            checkArgument(period);
        } catch (IncorrectInputException e) {
            System.out.println(e);
            return getRegularity(title, description, type, localDateTime);
        }
        switch (period) {
            case "1":
                System.out.println("Введите дату в формате ГГГГ-ММ-ДД: ");
                try {
                    String date = scanner.nextLine();
                    LocalDate localDate = LocalDate.parse(date);
                    return new OneTimeTask(title, description, type, LocalDateTime.now(), localDate);
                } catch (DateTimeParseException e) {
                    System.out.println(e.getMessage());
                    return getRegularity(title, description, type, localDateTime);
                }
            case "2":
                return new DailyTask(title, description, type, LocalDateTime.now());
            case "3":
                return new WeeklyTask(title, description, type, LocalDateTime.now());
            case "4":
                return new MonthlyTask(title, description, type, LocalDateTime.now());
            case "5":
                return new YearlyTask(title, description, type, LocalDateTime.now());
            default:
                System.out.println("Ошибка! Введено некорректное значение!");
                return getRegularity(title, description, type, localDateTime);
        }
    }

    // Блок проверки вводимых значений
    public static void checkArgument(String argument) {
        if (argument.isEmpty() || argument.isBlank()) {
            throw new IncorrectInputException(argument);
        }
    }

    public static void checkDate(String argument) {
        LocalDate localDate = LocalDate.parse(argument);
        if (argument.isEmpty() || argument.isBlank() || localDate.isBefore(LocalDate.now())) {
            throw new DateTimeParseException("Введена неправильная дата!", argument, 2);
        }
    }
}