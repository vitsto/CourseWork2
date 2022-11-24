package repetition;

import java.time.LocalDateTime;

public class Annual extends Periodicity{
    private static Annual instance;

    public static Annual getInstance() {
        if (instance == null) {
            instance = new Annual();
        }
        return instance;
    }

    private Annual() {
        super("ежегодная");
    }

    @Override
    public LocalDateTime getNextDate(LocalDateTime current) {
        return current.plusYears(1);
    }
}
