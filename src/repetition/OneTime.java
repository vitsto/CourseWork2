package repetition;

import java.time.LocalDateTime;

public class OneTime extends Periodicity{
    private static OneTime instance;

    public static OneTime getInstance() {
        if (instance == null) {
            instance = new OneTime();
        }
        return instance;
    }

    private OneTime() {
        super("однократная");
    }

    @Override
    public LocalDateTime getNextDate(LocalDateTime current) {
        return null;
    }
}
