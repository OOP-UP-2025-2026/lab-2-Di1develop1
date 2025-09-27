package ua.opnu;

public class TimeSpan {

    // Тримаємо інваріанти: hours >= 0, 0 <= minutes <= 59
    private int hours;
    private int minutes;

    TimeSpan(int hours, int minutes) {
        // якщо аргументи невалідні -> інтервал 0:00 (без винятків)
        if (hours < 0 || minutes < 0 || minutes > 59) {
            this.hours = 0;
            this.minutes = 0;
            return;
        }
        this.hours = hours;
        this.minutes = minutes;
        // інваріант вже виконується (minutes у [0..59])
    }

    int getHours() {
        return hours;
    }

    int getMinutes() {
        return minutes;
    }

    // додаємо тільки валідні значення; після додавання нормалізуємо
    void add(int hours, int minutes) {
        if (hours < 0 || minutes < 0 || minutes > 59) return; // ігноруємо невалідний виклик
        this.hours += hours;
        this.minutes += minutes;
        normalize(); // тут переносимо зайві хвилини в години
    }

    void addTimeSpan(TimeSpan timespan) {
        if (timespan == null) return;
        add(timespan.getHours(), timespan.getMinutes()); // вимагається використовувати гетери
    }

    double getTotalHours() {
        return hours + minutes / 60.0;
    }

    int getTotalMinutes() {
        return hours * 60 + minutes;
    }

    void subtract(TimeSpan span) {
        if (span == null) return;
        int thisTotal = getTotalMinutes();
        int other = span.getTotalMinutes();
        if (other > thisTotal) return; // нічого не змінюємо
        int remain = thisTotal - other;
        this.hours = remain / 60;
        this.minutes = remain % 60;
    }

    void scale(int factor) {
        // factor <= 0 — нічого не робимо
        if (factor <= 0) return;
        int total = getTotalMinutes() * factor;
        this.hours = total / 60;
        this.minutes = total % 60;
    }

    // допоміжне: перенос хвилин у години (викликаємо лише після валідних add)
    private void normalize() {
        if (minutes >= 60) {
            hours += minutes / 60;
            minutes = minutes % 60;
        }
    }
}
