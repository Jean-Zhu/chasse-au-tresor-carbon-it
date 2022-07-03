package com.jz.carbonit.treasurehunt.map.domain.cell;

import com.jz.carbonit.treasurehunt.map.domain.position.Position;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MountainCell extends AMapCell {

    public MountainCell(Position position) {
        super(position, false);
    }

    public String getRepresentation() {
        return "M";
    }

}
