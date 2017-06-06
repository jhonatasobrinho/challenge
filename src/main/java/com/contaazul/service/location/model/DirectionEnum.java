package com.contaazul.service.location.model;

public enum DirectionEnum {
    M,
    L,
    R;

    public static DirectionEnum lookup(String direction) {
        for (DirectionEnum value: values()) {
            if (value.toString().equals(direction)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Comando inválido");
    }
}
