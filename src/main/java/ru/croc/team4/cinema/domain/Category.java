package ru.croc.team4.cinema.domain;

public enum Category {
    BAD("Боковушка"),
    GOOD("Не до поцелуев"),
    EXCELLENT("Полное погружение");

    private final String russianName;

    Category(String russianName) {
        this.russianName = russianName;
    }

    public String getRussianName() {
        return russianName;
    }
}

