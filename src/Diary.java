import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Diary {
    private final Map<Integer, Task> tasks;

    public Diary() {
        this.tasks = new HashMap<>();
    }

    public void addTask(Task task) {
        this.tasks.put(task.getId(), task);
    }

    public List<Task> getRemovedTasks() {
        return tasks.values().stream().filter(Task::isRemoved).collect(Collectors.toCollection(LinkedList::new));
    }

    public void removeTask(int id) {
        tasks.get(id).setRemoved();
    }

    public List<Task> groupByDate() {
        // здесь inteliJ сам упростил код
        // я сначала через стримы выбрал задачи, которые не удаленные
        // а потом с помощью компаратора сортировал их
        return tasks.values().stream()
                .filter(task -> !task.isRemoved())
                .sorted((a, b) -> a.getDateTime().isAfter(b.getDateTime()) ? 1 : a.getDateTime().isEqual(b.getDateTime()) ? 1 : 0)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public Task getTask(int id) {
        return tasks.get(id);
    }

    public Set<Task> getTasksForDay(LocalDateTime givenDateTime) {
        LocalDate givenDate = givenDateTime.toLocalDate();
        Set<Task> tasksForDay = new HashSet<>();

        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            LocalDateTime taskDate = entry.getValue().getDateTime();
            if (givenDate.isEqual(taskDate.toLocalDate())) { // проверка получения задач "на сегодня"
                tasksForDay.add(entry.getValue());
            }
            while (givenDate.isAfter(taskDate.toLocalDate())) {
                taskDate = entry.getValue().getPeriodicity().getNextDate(taskDate);
                if (givenDate.isEqual(taskDate.toLocalDate())) {
                    tasksForDay.add(entry.getValue());
                }
            }
        }
        return Collections.unmodifiableSet(tasksForDay);
    }

    @Override
    public String toString() {
        return tasks + "";
    }
}
