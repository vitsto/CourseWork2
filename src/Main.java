import repetition.*;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    static Diary diary = new Diary();

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                printMenu();
                System.out.print("Выберите пункт меню: ");
                if (scanner.hasNextInt()) {
                    int menu = scanner.nextInt();
                    switch (menu) {
                        case 1:
                            inputTask(scanner);
                            break;
                        case 2:
                            removeTask(scanner);
                            break;
                        case 3:
                            getTaskForDay(scanner);
                            break;
                        case 4:
                            editTask(scanner);
                            break;
                        case 5:
                            System.out.println(diary.getRemovedTasks());
                            break;
                        case 6:
                            System.out.println(diary.groupByDate());
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                    System.out.println("Выберите пункт меню из списка!");
                }
            }
        }
    }

    private static void editTask(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Введите id задачи: ");
        int id = scanner.nextInt();

        Task editedTask = diary.getTask(id);
        System.out.print("Введите новый заголовок: ");
        String title = scanner.nextLine();
        System.out.print("Введите новое описание: ");
        String description = scanner.nextLine();
        editedTask.setTitle(title);
        editedTask.setDescription(description);
    }

    private static void getTaskForDay(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Введите дату, на которую вы хотите получить список задач в формате dd.mm.yyyy : ");
        String enteredDateTime = scanner.nextLine();
        LocalDateTime dateTime = parseDateTime(enteredDateTime);
        System.out.println(diary.getTasksForDay(dateTime));
    }

    private static void inputTask(Scanner scanner) {
        System.out.print("Введите название задачи: ");
        String taskName = scanner.next();
        System.out.print("Введите описание задачи: ");
        scanner.nextLine();
        String description = scanner.nextLine();
        System.out.print("Выберите тип задачи: 1 - личная, 2 - рабочая: ");
        int chosenType = scanner.nextInt();
        TaskType type = chosenType == 1 ? TaskType.WORKED : TaskType.PERSONAL;
        System.out.print("Выберите повторяемость задачи: 1 - однократная, 2 - ежедневная, " +
                "3 - еженедельная, 4 - ежемесячнаяб 5 - ежегодная: ");
        int chosenPeriodicity = scanner.nextInt();
        Periodicity periodicity = parsePeriodicity(chosenPeriodicity);
        scanner.nextLine();
        System.out.print("Введите дату и время задачи в формате dd.mm.yyyy hh:mm : ");
        String enteredDateTime = scanner.nextLine();
        LocalDateTime dateTime = parseDateTime(enteredDateTime);
        diary.addTask(new Task(taskName, description, type, periodicity, dateTime));
    }

    private static void printMenu() {
        System.out.println(" 1. Добавить задачу 2. Удалить задачу 3. Получить задачу на указанный день " +
                "4. Редактировать задачу 5. - Удаленные задачи 6. - Группировать задачи 0. Выход");
    }

    public static void removeTask(Scanner scanner) {
        System.out.print("Введите id задачи, которую хотите удалить: ");
        int id = scanner.nextInt();
        diary.removeTask(id);
    }

    public static LocalDateTime parseDateTime(String str) {
        String[] components = str.split(" ");
        String[] dataPart = components[0].split("\\.");

        int day = 0, month = 0, year = 0;
        if (dataPart.length == 3) {
            day = Integer.parseInt(dataPart[0]);
            month = Integer.parseInt(dataPart[1]);
            year = Integer.parseInt(dataPart[2]);
        }

        int hour = 0, minute = 0;
        if (components.length > 1) {
            String[] timePart = components[1].split(":");
            if (timePart.length == 2) {
                hour = Integer.parseInt(timePart[0]);
                minute = Integer.parseInt(timePart[1]);
            }
        }

        return LocalDateTime.of(year, month, day, hour, minute);
    }

    public static Periodicity parsePeriodicity(int value) {
        switch (value) {
            case 1:
                return OneTime.getInstance();
            case 2:
                return Daily.getInstance();
            case 3:
                return Weekly.getInstance();
            case 4:
                return Monthly.getInstance();
            case 5:
                return Annual.getInstance();
            default:
                throw new RuntimeException("Неизвестная периодичность");
        }
    }
}