package coursework;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeeklyTask extends Task {

    public WeeklyTask(String title,
                      String description,
                      Type type,
                      LocalDateTime localDateTime) {
        super(title, description, type, localDateTime);
    }

    // Метод для проверки повторяемости задачи
    @Override
    public boolean appearsIn(LocalDate localDate) {
        return getLocalDateTime().getDayOfWeek().equals(localDate.getDayOfWeek());
    }

    // Метод для получения следующей даты выполнения задачи
    @Override
    public LocalDate getNextDate(LocalDateTime localDateTime) {
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate.plusDays( 7);
    }

    @Override
    public String toString() {
        return super.toString() + "\n   Повторяемость: еженедельная";
    }
}
