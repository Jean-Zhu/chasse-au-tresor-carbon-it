package com.jz.carbonit.treasurehunt.map.domain.cell;

import com.jz.carbonit.treasurehunt.map.domain.position.Position;
import lombok.Getter;

@Getter
public abstract class AMapCell {

    private final Position position;
    private final boolean canBeCrossed;

    protected AMapCell(Position position) {
        this.position = position;
        this.canBeCrossed = true;
    }

    protected AMapCell(Position position, boolean canBeCrossed) {
        this.position = position;
        this.canBeCrossed = canBeCrossed;
    }

    public abstract String getRepresentation();
}
