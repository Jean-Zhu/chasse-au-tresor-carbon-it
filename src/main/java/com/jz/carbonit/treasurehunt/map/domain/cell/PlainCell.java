package com.jz.carbonit.treasurehunt.map.domain.cell;

import com.jz.carbonit.treasurehunt.map.domain.position.Position;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlainCell extends AMapCell {

    public PlainCell(Position position) {
        super(position);
    }

    public String getRepresentation() {
        return ".";
    }
}
