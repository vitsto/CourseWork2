package repetition;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface Repeatable {
    LocalDateTime getNextDate(LocalDateTime current);
}
