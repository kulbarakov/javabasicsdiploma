package ru.netology.graphics.image;

public class Schema implements TextColorSchema {
    @Override
    public char convert(int color) {
        char[] textColors = {'#', '$', '@', '%', '*', '+', '-', '\''};
        return textColors[color / 32];
    }
}
