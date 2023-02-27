package coursework;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class YearlyTask extends Task{

    public YearlyTask(String title,
                      String description,
                      Type type,
                      LocalDateTime localDateTime) {
        super(title, description, type, localDateTime);
    }

    @Override
    public boolean appearsIn(LocalDate localDate) {
        return getLocalDateTime().getDayOfMonth() == localDate.getDayOfMonth() &&
                getLocalDateTime().getMonthValue() == localDate.getMonthValue();
    }

    @Override
    public LocalDate getNextDate(LocalDateTime localDateTime) {
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate.withYear(getLocalDateTime().getYear() + 1);
    }

    @Override
    public String toString() {
        return super.toString() + "\n   Повторяемость: ежегодная";
    }
}
