package com.jz.carbonit.treasurehunt.map.util;

import com.jz.carbonit.treasurehunt.map.domain.adventurer.Adventurer;
import com.jz.carbonit.treasurehunt.map.domain.adventurer.enumeration.Movement;
import com.jz.carbonit.treasurehunt.map.domain.treasuremap.ITreasureMap;

import java.util.List;

public class TreasureMapUtils {

    private TreasureMapUtils() throws IllegalAccessException {
        throw new IllegalAccessException("Utility class");
    }

    /**
     * Renvoie la représentation d'une carte avec les aventuriers sous la forme d'un string
     *
     * @param treasureMap une carte
     * @param adventurers liste des aventuriers
     * @return la représentation
     */
    public static String getTreasureMapRepresentationForPrint(ITreasureMap treasureMap, List<Adventurer> adventurers) {
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());

        String[][] mapRepresentation = new String[treasureMap.getMapDimensions().getOrdinate()][treasureMap.getMapDimensions().getAbscissa()];

        for (int row = 0; row < treasureMap.getMapDimensions().getOrdinate(); row++) {
            for (int col = 0; col < treasureMap.getMapDimensions().getAbscissa(); col++) {
                mapRepresentation[row][col] = treasureMap.getCell(row, col).getRepresentation();
            }
        }

        for (Adventurer adventurer : adventurers) {
            mapRepresentation[adventurer.getPosition().getOrdinate()][adventurer.getPosition().getAbscissa()] = adventurer.getRepresentation();
        }

        for (int row = 0; row < treasureMap.getMapDimensions().getOrdinate(); row++) {
            for (int col = 0; col < treasureMap.getMapDimensions().getAbscissa(); col++) {
                sb.append(String.format("%10s", mapRepresentation[row][col]));
            }
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    /**
     * Simule une "partie" de chasse au trésor
     *
     * @param treasureMap la carte
     * @param adventurers liste des aventuriers
     */
    public static void play(ITreasureMap treasureMap, List<Adventurer> adventurers) {
        int maxNbTurns = adventurers.stream().map(adventurer -> adventurer.getMovementlist().size()).max(Integer::compare).orElse(0);

        for (int currentNbTurns = 0; currentNbTurns < maxNbTurns; currentNbTurns++) {
            for (Adventurer a : adventurers) {
                if (currentNbTurns < a.getMovementlist().size()) {
                    Movement m = a.getMovementlist().get(currentNbTurns);
                    boolean hasMoved = a.doMovement(m, treasureMap, adventurers);
                    if (hasMoved) {
                        a.collect(treasureMap, a.getPosition());
                    }
                }
            }
        }
    }
}
