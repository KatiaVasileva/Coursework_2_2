package coursework;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class TaskService {
    private Map<Integer, Task> taskMap = new HashMap<>();
    private List<Task> removedTasks = new ArrayList<>();

    public Map<Integer, Task> getTaskMap() {
        return taskMap;
    }

    public List<Task> getRemovedTasks() {
        return removedTasks;
    }

    public void setRemovedTasks(List<Task> removedTasks) {
        this.removedTasks = removedTasks;
    }

    public void setTaskMap(Map<Integer, Task> taskMap) {
        this.taskMap = taskMap;
    }

    // Метод для добавления задач в ежедневник
    public void addTask(Task task) {
        taskMap.put(task.getId(), task);
    }

    // Метод для вывода списка всех задач в консоль
    public void printAllTasks(Map<Integer, Task> taskMap) {
        if (taskMap.isEmpty()) {
            throw new TaskNotFoundException("Задач не найдено!");
        }
        taskMap.forEach((key, value) -> System.out.println(key + ". " + value));
    }

    // Метод для удаления задачи из ежедневника по идентификационному номеру задачи
    public Task removeTask(int id) {
        for (Map.Entry<Integer, Task> task : getTaskMap().entrySet()) {
            if (task.getKey() == id) {
                return taskMap.remove(id);
            }
        }
        throw new TaskNotFoundException("Задача не найдена!");
    }

    // Метод для добавления удаленных задач в архив
    public void addToRemovedTasks(Task task) {
        removedTasks.add(task);
    }

    // Метод для вывода задач в консоль (список задач на завтра, список задач на указанную дату, архив удаленных задач)
    public void printTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            throw new TaskNotFoundException("Задач не найдено!");
        }
        tasks.forEach(System.out::println);
    }

    // Метод для получения списка задач на указанную дату
    public List<Task> getTasksByDate(LocalDate localDate) {
        List<Task> mapValues = new ArrayList<>((getTaskMap().values()));
        return mapValues.stream()
                .filter(task -> task.appearsIn(localDate))
                .collect(Collectors.toList());
    }

    // Метод для изменения названия задачи
    public void updateTitle(int id, String title){
        for (Map.Entry<Integer, Task> task : getTaskMap().entrySet()) {
            if (task.getKey() == id) {
                Task updatedTask = getTaskMap().get(id);
                updatedTask.setTitle(title);
                return;
            }
        }
        throw new TaskNotFoundException("Такой задачи не существует!");
    }

    // Метод для изменения описания задачи
    public void updateDescription(int id, String description) {
        for (Map.Entry<Integer, Task> task : getTaskMap().entrySet()) {
            if (task.getKey() == id) {
                Task updatedTask = getTaskMap().get(id);
                updatedTask.setDescription(description);
                return;
            }
        }
        throw new TaskNotFoundException("Такой задачи не существует!");
    }

    // Метод для получения следующей даты выполнения задачи по идентификационному номеру задачи
    public void getInfoAboutNextDateTime(int id) {
        Task task = getTaskMap().get(id);
        System.out.println(task.getNextDate(task.getLocalDateTime()));
    }

    // Метод для получения списка задач на следующий день
    public List<Task> getTasksForNextDay(){
        List<Task> tasksForNextDay = new ArrayList<>((getTaskMap().values()));
        return tasksForNextDay.stream()
                .filter(task -> task.appearsIn(task.getLocalDateTime().toLocalDate().plusDays(1)))
                .collect(Collectors.toList());
    }

    // Метод для получения списка всех задач, сгруппированных по дате
    public Map<LocalDate, List<Task>> getGroupedByDate(Map<Integer, Task> taskMap) {
        List<Task> taskList = new ArrayList<>(taskMap.values());
        List<LocalDate> localDateList = taskList.stream()
                .map(task -> task.getNextDate(task.getLocalDateTime()))
                .sorted(new CompareByDate())
                .collect(Collectors.toList());
        Map<LocalDate,List<Task>> allTasksGroupedByDate = new LinkedHashMap<>();
        for (LocalDate localDate : localDateList) {
            allTasksGroupedByDate.put(localDate, getTasksByDate(localDate));
        }
        return allTasksGroupedByDate;
    }

    // Метод для вывода в консоль списка всех задач, сгруппированных по дате
    public void printGroupedByDate(Map<LocalDate, List<Task>> mapByDate) {
        System.out.println("Список всех задач по датам");
        if (mapByDate.isEmpty()) {
            throw new TaskNotFoundException("Задач не найдено!");
        }
        for (Map.Entry<LocalDate, List<Task>> date : mapByDate.entrySet()) {
            System.out.println(date.getKey() + ":");
            List<Task> tasks = date.getValue();
            for (Task task : tasks) {
                System.out.println(task);
            }
        }
    }
}
