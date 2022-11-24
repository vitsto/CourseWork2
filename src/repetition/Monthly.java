package repetition;

import java.time.LocalDateTime;

public class Monthly extends Periodicity{
    private static Monthly instance;

    public static Monthly getInstance() {
        if (instance == null) {
            instance = new Monthly();
        }
        return instance;
    }

    private Monthly() {
        super("ежемесячная");
    }

    @Override
    public LocalDateTime getNextDate(LocalDateTime current) {
        return current.plusMonths(1);
    }
}
