package ru.netology.graphics.image;

public class Schema implements TextColorSchema {
    private final char[] textColors = {'#', '$', '@', '%', '*', '+', '-', '\''};

    @Override
    public char convert(int color) {
        return textColors[color / 32];
    }
}
