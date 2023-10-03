package Candy;

import Exception.EnumIncorrectException;

public enum ChupaChupsColor {
    ORANGE,
    PINK,
    GREEN,
    BLACK;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
