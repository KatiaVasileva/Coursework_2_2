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

    // Метод для проверки повторяемости задачи
    @Override
    public boolean appearsIn(LocalDate localDate) {
        return getLocalDateTime().getDayOfMonth() == localDate.getDayOfMonth();
    }

    // Метод для получения следующей даты выполнения задачи
    public LocalDate getNextDate(LocalDateTime localDateTime) {
        LocalDate localDate = localDateTime.toLocalDate();
        return localDate.withMonth(getLocalDateTime().getMonthValue() + 1);
    }

    @Override
    public String toString() {
        return super.toString() + "\n   Повторяемость: ежемесячная";
    }
}
