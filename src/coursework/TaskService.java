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

    public void printTasks(List<Task> removedTasks) {
        if (removedTasks.isEmpty()) {
            throw new TaskNotFoundException("Архив пустой!");
        }
        removedTasks.forEach(System.out::println);
    }

    public List<Task> getAllByDate(LocalDate localDate) {
        List<Task> mapValues = new ArrayList<>((getTaskMap().values()));
        return mapValues.stream()
                .filter(task -> task.appearsIn(localDate))
                .collect(Collectors.toList());
    }

    /*public void printTasksByDate(List<Task> tasksByDate) {
        tasksByDate.forEach(System.out::println);
    }*/

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

    public void getTasksForNextDay(Map<Integer, Task> taskMap) throws TaskNotFoundException{
        taskMap.entrySet().stream()
                .filter(task -> task.getValue().appearsIn(task.getValue().getLocalDateTime().toLocalDate().plusDays(1)))
                .forEach(task -> System.out.println(task.getKey() + ". " + task.getValue()));
    }

    public void checkTaskAvailability(Map<Integer, Task> map) throws TaskNotFoundException {
        if (map.isEmpty()) {
            throw new TaskNotFoundException("Задач не найдено!");
        }
    }

    public void checkTaskAvailability(List<Task> list) throws TaskNotFoundException{
        if (list.isEmpty()) {
            throw new TaskNotFoundException("Задач не найдено!");
        }
    }
}
