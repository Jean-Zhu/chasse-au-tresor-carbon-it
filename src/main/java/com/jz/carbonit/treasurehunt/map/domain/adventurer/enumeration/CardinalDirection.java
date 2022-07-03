package com.jz.carbonit.treasurehunt.map.domain.adventurer.enumeration;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CardinalDirection {

    NORTH("N", 0, -1),
    EAST("E", 1, 0),
    SOUTH("S", 0, 1),
    WEST("O", -1, 0);

    private final String libelle;
    private final Integer addAbsIfGoingStraight;
    private final Integer addOrdIfGoingStraight;

    private static final CardinalDirection[] directions = values();

    /**
     * Renvoie la direction correspondante à la chaîne
     *
     * @param direction la chaîne
     * @return la direction
     */
    public static CardinalDirection getCardinalDirectionFromString(String direction) {
        for (CardinalDirection e : directions) {
            if (e.getLibelle().equals(direction)) {
                return e;
            }
        }
        return null;
    }

    /**
     * Renvoie la direction en tournant à droite
     *
     * @return la direction en tournant à droite
     */
    public CardinalDirection directionOnRight() {
        return directions[(this.ordinal() + 1) % directions.length];
    }

    /**
     * Renvoie la direction en tournant à gauche
     *
     * @return la direction en tournant à gauche
     */
    public CardinalDirection directionOnLeft() {
        return directions[(this.ordinal() - 1 + directions.length) % directions.length];
    }
}
