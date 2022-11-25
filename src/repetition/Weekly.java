package repetition;

import java.time.LocalDateTime;

public class Weekly extends Periodicity{
    private static Weekly instance;

    public static Weekly getInstance() {
        if (instance == null) {
            instance = new Weekly();
        }
        return instance;
    }
    private Weekly() {
        super("еженедельная");
    }

    @Override
    public LocalDateTime getNextDate(LocalDateTime current) {
        return current.plusWeeks(1);
    }
}
