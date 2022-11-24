package repetition;

import java.time.LocalDateTime;

public class Daily extends Periodicity{
    private static Daily instance;

    public static Daily getInstance() {
        if (instance == null) {
            instance = new Daily();
        }
        return instance;
    }

    private Daily() {
        super("ежедневная");
    }

    @Override
    public LocalDateTime getNextDate(LocalDateTime current) {
        return current.plusDays(1);
    }
}
