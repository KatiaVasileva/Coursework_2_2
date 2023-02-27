package coursework;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DailyTask extends Task{

    public DailyTask(String title,
                     String description,
                     Type type,
                     LocalDateTime localDateTime) {
        super(title, description, type, localDateTime);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        return true;
    }

    @Override
    public LocalDate getNextDate(LocalDateTime localDateTime) {
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate.plusDays(1);
    }

    @Override
    public String toString() {
        return super.toString() + "\n   Повторяемость: ежедневная";
    }
}

