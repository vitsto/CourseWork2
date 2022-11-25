import repetition.Periodicity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task {
    private static int counter = 0;

    private final int id;
    private String title;
    private String description;
    private final TaskType taskType;
    private final LocalDateTime dateTime;
    private final Periodicity periodicity;
    private boolean removed;

    public Task(String title, String description, TaskType taskType, Periodicity periodicity, LocalDateTime dateTime) {
        if (title == null || description.isBlank() || description.isEmpty() || taskType == null || periodicity == null) {
            throw new RuntimeException("Поля не проинициализированы (или неккоректны), повторите ввод");
        }
        this.id = counter++;
        this.title = title;
        this.description = description;
        this.taskType = taskType;
        this.dateTime = dateTime;
        this.periodicity = periodicity;
        this.removed = false;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRemoved() {
        this.removed = true;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public boolean isRemoved() {
        return removed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(title, task.title) && taskType == task.taskType && Objects.equals(periodicity, task.periodicity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, taskType, periodicity);
    }

    @Override
    public String toString() {
        return " заголовок='" + title + '\'' +
                ", описание='" + description + '\'' +
                ", тип задачти=" + taskType +
                ", периодичность=" + periodicity +
                ", дата и время =" + dateTime +
                "}\n";
    }
}
