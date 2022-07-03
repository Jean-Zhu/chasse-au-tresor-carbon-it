package com.jz.carbonit.treasurehunt.map.domain.adventurer.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Movement {
    STRAIGHT('A'), LEFT('G'), RIGHT('D');

    private final char name;

    private static final Movement[] movements = values();

    public static Movement getMovementFromChar(char c) {
        for (Movement m : movements) {
            if (m.getName() == c) {
                return m;
            }
        }
        return null;
    }
}
