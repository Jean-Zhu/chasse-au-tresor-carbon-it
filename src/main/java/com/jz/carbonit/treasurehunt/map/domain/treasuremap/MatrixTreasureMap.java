package com.jz.carbonit.treasurehunt.map.domain.treasuremap;

import com.jz.carbonit.treasurehunt.exception.ParsingLineException;
import com.jz.carbonit.treasurehunt.map.domain.cell.AMapCell;
import com.jz.carbonit.treasurehunt.map.domain.cell.MountainCell;
import com.jz.carbonit.treasurehunt.map.domain.cell.PlainCell;
import com.jz.carbonit.treasurehunt.map.domain.cell.TreasureCell;
import com.jz.carbonit.treasurehunt.map.domain.position.Position;
import com.jz.carbonit.treasurehunt.map.util.LineUtils;

import java.util.List;


public class MatrixTreasureMap implements ITreasureMap {

    private final AMapCell[][] mapCells;

    private final Position mapDimensions;

    public MatrixTreasureMap(List<String> lines) throws ParsingLineException {
        mapDimensions = LineUtils.getMapDimentionsFromLines(lines);
        mapCells = new AMapCell[mapDimensions.getOrdinate()][mapDimensions.getAbscissa()];

        fillMapWithPlains(mapDimensions);
        List<MountainCell> mountainCellList = LineUtils.getMountainListFromLines(lines);
        placeCellsOnMap(mountainCellList);
        List<TreasureCell> treasureCellList = LineUtils.getTreasureListFromLines(lines);
        placeCellsOnMap(treasureCellList);
    }

    /**
     * Remplit la carte avec des plaines
     *
     * @param dimensions dimensions de la carte
     */
    public void fillMapWithPlains(Position dimensions) {
        for (int i = 0; i < dimensions.getOrdinate(); i++) {
            for (int j = 0; j < dimensions.getAbscissa(); j++) {
                mapCells[i][j] = new PlainCell(new Position(i, j));
            }
        }
    }

    /**
     * Remplace des cellules sur la carte
     *
     * @param cellList liste des cellules à placer
     * @param <T>      classe sous-type de AMapCell
     */
    public <T extends AMapCell> void placeCellsOnMap(List<T> cellList) {
        cellList.forEach(cell -> setCell(cell.getPosition(), cell));
    }


    public AMapCell getCell(Position position) {
        if (position.isOutOfBounds(mapDimensions)) {
            return null;
        }
        return getCell(position.getOrdinate(), position.getAbscissa());
    }

    public AMapCell getCell(int ordinate, int abcissa) {
        return mapCells[ordinate][abcissa];
    }

    public void setCell(Position position, AMapCell mapCell) {
        if (!position.isOutOfBounds(mapDimensions)) {
            mapCells[mapCell.getPosition().getOrdinate()][mapCell.getPosition().getAbscissa()] = mapCell;
        }
    }

    /**
     * Renvoie les dimensions de la carte
     *
     * @return les dimensions de la carte
     */
    public Position getMapDimensions() {
        return mapDimensions;
    }

}
