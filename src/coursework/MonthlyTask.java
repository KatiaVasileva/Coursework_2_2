package coursework;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MonthlyTask extends Task{

    public MonthlyTask(String title,
                       String description,
                       Type type,
                       LocalDateTime localDateTime) {
        super(title, description, type, localDateTime);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        return getLocalDateTime().getDayOfMonth() == localDate.getDayOfMonth();
    }

    public LocalDate getNextDate(LocalDateTime localDateTime) {
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate.withMonth(getLocalDateTime().getMonthValue() + 1);
    }

    @Override
    public String toString() {
        return super.toString() + "\n   Повторяемость: ежемесячная";
    }
}
