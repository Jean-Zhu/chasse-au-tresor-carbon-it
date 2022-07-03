package com.jz.carbonit.treasurehunt.map.domain.adventurer;

import com.jz.carbonit.treasurehunt.map.domain.adventurer.enumeration.CardinalDirection;
import com.jz.carbonit.treasurehunt.map.domain.adventurer.enumeration.Movement;
import com.jz.carbonit.treasurehunt.map.domain.cell.AMapCell;
import com.jz.carbonit.treasurehunt.map.domain.cell.PlainCell;
import com.jz.carbonit.treasurehunt.map.domain.cell.TreasureCell;
import com.jz.carbonit.treasurehunt.map.domain.position.Position;
import com.jz.carbonit.treasurehunt.map.domain.treasuremap.ITreasureMap;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Adventurer {

    private String name;
    private Position position;
    private CardinalDirection direction;
    private List<Movement> movementlist;
    private int nbTreasure = 0;

    public Adventurer(String name, Position position, CardinalDirection direction, List<Movement> movementlist) {
        this.name = name;
        this.position = position;
        this.direction = direction;
        this.movementlist = movementlist;
    }

    public String getRepresentation() {
        return "A(" + name + ")";
    }

    /**
     * Simule l'action d'arriver sur une case contenant un trésor ou non
     *
     * @param treasureMap la carte
     * @param position    la position
     */
    public void collect(ITreasureMap treasureMap, Position position) {
        AMapCell mapCell = treasureMap.getCell(position);
        if (mapCell instanceof TreasureCell) {
            TreasureCell treasureCell = (TreasureCell) mapCell;
            if (treasureCell.getNbTreasure() > 0) {
                treasureCell.setNbTreasure(treasureCell.getNbTreasure() - 1);
                setNbTreasure(getNbTreasure() + 1);
            }
            if (treasureCell.getNbTreasure() == 0) {
                treasureMap.setCell(position, new PlainCell(position));
            }
        }
    }

    /**
     * Simule un movement et renvoie un booléen indiquant si l'aventurier a bougé
     *
     * @param m           le mouvement
     * @param treasureMap la carte
     * @param adventurers liste des aventuriers
     * @return booléen indiquant si l'aventurier a bougé
     */
    public boolean doMovement(Movement m, ITreasureMap treasureMap, List<Adventurer> adventurers) {
        switch (m) {
            case STRAIGHT:
                Position nextPosition = getNextAdventurerPosition();
                List<Position> adventurerPositions = adventurers.stream().map(Adventurer::getPosition).collect(Collectors.toList());
                if (nextPosition.isOutOfBounds(treasureMap.getMapDimensions()) ||
                        !treasureMap.getCell(nextPosition).isCanBeCrossed() ||
                        adventurerPositions.contains(nextPosition)) {
                    return false;
                } else {
                    this.setPosition(nextPosition);
                    return true;
                }
            case LEFT:
                this.setDirection(this.getDirection().directionOnLeft());
                return false;
            case RIGHT:
                this.setDirection(this.getDirection().directionOnRight());
                return false;
            default:
                return false;
        }
    }

    public Position getNextAdventurerPosition() {
        Position adventurerPosition = this.getPosition();
        return new Position(
                adventurerPosition.getAbscissa() + this.getDirection().getAddAbsIfGoingStraight(),
                adventurerPosition.getOrdinate() + this.getDirection().getAddOrdIfGoingStraight());
    }

}
