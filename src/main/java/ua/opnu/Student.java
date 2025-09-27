package ua.opnu;

import java.util.Arrays;

public class Student {
    private final String name;
    private int year; // 1..4
    private String[] courses = new String[4];
    private int count = 0;

    public Student(String name, int year) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required");
        }
        if (year < 1 || year > 4) {
            throw new IllegalArgumentException("Year must be in [1..4]");
        }
        this.name = name;
        this.year = year;
    }

    public void addCourse(String courseName) {
        if (courseName == null || courseName.isBlank()) return;
        ensureCapacity();
        courses[count++] = courseName;
    }

    public void dropAll() {
        Arrays.fill(courses, 0, count, null);
        count = 0;
    }

    public int getCourseCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year < 1 || year > 4) return; // “м’яко”: ігноруємо невалідні значення
        this.year = year;
    }

    public int getTuition() {
        return year * 20000;
    }

    private void ensureCapacity() {
        if (count == courses.length) {
            courses = Arrays.copyOf(courses, courses.length * 2);
        }
    }
}
