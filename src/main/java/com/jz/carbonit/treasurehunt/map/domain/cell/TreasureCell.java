package com.jz.carbonit.treasurehunt.map.domain.cell;

import com.jz.carbonit.treasurehunt.map.domain.position.Position;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreasureCell extends AMapCell {

    private int nbTreasure;

    public TreasureCell(Position position, int nbTreasure) {
        super(position);
        this.nbTreasure = nbTreasure;
    }

    public String getRepresentation() {
        return "T(" + nbTreasure + ")";
    }
}
