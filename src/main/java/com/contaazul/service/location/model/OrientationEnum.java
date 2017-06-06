package com.contaazul.service.location.model;

public enum OrientationEnum {
    NORTH("N"),
    EAST("E"),
    SOUTH("S"),
    WEST("W");

    private String orientation;

    OrientationEnum(String orientation) {
        this.orientation = orientation;
    }

    @Override
    public String toString() {
        return orientation;
    }
}