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

    public void addTask(Task task) {
        taskMap.put(task.getId(), task);
    }

    public void printAllTasks(Map<Integer, Task> taskMap) {
        if (taskMap.isEmpty()) {
            throw new TaskNotFoundException("Задач не найдено!");
        }
        taskMap.forEach((key, value) -> System.out.println(key + ". " + value));
    }

    public Task removeTask(int id) {
        for (Map.Entry<Integer, Task> task : getTaskMap().entrySet()) {
            if (task.getKey() == id) {
                return taskMap.remove(id);
            }
        }
        throw new TaskNotFoundException("Задача не найдена!");
    }

    public void addToRemovedTasks(Task task) {
        removedTasks.add(task);
    }

    public void printTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            throw new TaskNotFoundException("Задач не найдено!");
        }
        tasks.forEach(System.out::println);
    }

    public List<Task> getTasksByDate(LocalDate localDate) {
        List<Task> mapValues = new ArrayList<>((getTaskMap().values()));
        return mapValues.stream()
                .filter(task -> task.appearsIn(localDate))
                .collect(Collectors.toList());
    }

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

    public void getInfoAboutNextDateTime(int id) {
        Task task = getTaskMap().get(id);
        System.out.println(task.getNextDate(task.getLocalDateTime()));
    }

    public List<Task> getTasksForNextDay(){
        List<Task> tasksForNextDay = new ArrayList<>((getTaskMap().values()));
        return tasksForNextDay.stream()
                .filter(task -> task.appearsIn(task.getLocalDateTime().toLocalDate().plusDays(1)))
                .collect(Collectors.toList());
    }

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
