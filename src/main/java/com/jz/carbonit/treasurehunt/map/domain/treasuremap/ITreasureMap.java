package com.jz.carbonit.treasurehunt.map.domain.treasuremap;

import com.jz.carbonit.treasurehunt.map.domain.cell.AMapCell;
import com.jz.carbonit.treasurehunt.map.domain.position.Position;

import java.util.List;

public interface ITreasureMap {

    void fillMapWithPlains(Position dimensions);

    <T extends AMapCell> void placeCellsOnMap(List<T> cellList);

    AMapCell getCell(Position position);

    AMapCell getCell(int ordinate, int abcissa);

    void setCell(Position position, AMapCell mapCell);

    Position getMapDimensions();
}
