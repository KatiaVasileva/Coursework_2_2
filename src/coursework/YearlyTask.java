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

    // Метод для проверки повторяемости задачи
    @Override
    public boolean appearsIn(LocalDate localDate) {
        return getLocalDateTime().getDayOfMonth() == localDate.getDayOfMonth() &&
                getLocalDateTime().getMonthValue() == localDate.getMonthValue();
    }

    // Метод для получения следующей даты выполнения задачи
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
