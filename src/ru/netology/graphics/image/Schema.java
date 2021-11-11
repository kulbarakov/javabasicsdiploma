package ru.netology.graphics.image;

public class Schema implements TextColorSchema {
    private static final char[] TEXT_COLORS = {'#', '$', '@', '%', '*', '+', '-', '\''};

    @Override
    public char convert(int color) {
        return TEXT_COLORS[color / 32];
    }
}
