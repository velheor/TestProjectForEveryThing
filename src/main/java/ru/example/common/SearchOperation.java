package ru.example.common;

/**
 * Оператор сравнения
 */
public enum SearchOperation {

    MORE("MORE"),

    LESS("LESS"),

    EQ("EQ"),

    MOREQ("MOREQ"),

    LESSEQ("LESSEQ");

    private final String value;

    SearchOperation(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
