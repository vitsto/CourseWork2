import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Diary {
    private final Map<Integer, Task> tasks;

    public Diary() {
        this.tasks = new HashMap<>();
    }

    public int addTask(Task task) {
        this.tasks.put(task.getId(), task);
        return task.getId();
    }

    public List<Task> getRemovedTasks() {
        return tasks.values().stream().filter(Task::isRemoved).collect(Collectors.toCollection(LinkedList::new));
    }

    public void removeTask(int id) {
        Task task = tasks.get(id);
        if (task != null) {
            task.setRemoved();
            System.out.println("Задача " + task.getTitle() + " перенесена в архив");
        } else {
            System.out.println("Задачи с таким id(" + id + ") не существует");
        }
    }

    public List<Task> groupByDate() {
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
            if (givenDate.isEqual(taskDate.toLocalDate())) {
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
