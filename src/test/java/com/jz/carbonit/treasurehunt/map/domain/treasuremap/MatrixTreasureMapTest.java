package com.jz.carbonit.treasurehunt.map.domain.treasuremap;

import com.jz.carbonit.treasurehunt.exception.ParsingLineException;
import com.jz.carbonit.treasurehunt.map.domain.cell.AMapCell;
import com.jz.carbonit.treasurehunt.map.domain.cell.MountainCell;
import com.jz.carbonit.treasurehunt.map.domain.cell.PlainCell;
import com.jz.carbonit.treasurehunt.map.domain.position.Position;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MatrixTreasureMapTest {

    private ITreasureMap treasureMap;

    private Position dimensions;

    @Before
    public void setup() throws ParsingLineException {
        List<String> lines = new ArrayList<>();
        lines.add("C-3-4");
        treasureMap = new MatrixTreasureMap(lines);
        dimensions = new Position(3, 4);
    }

    @Test
    public void fillMapWithPlainsTest() {
        treasureMap.fillMapWithPlains(dimensions);
        for (int row = 0; row < dimensions.getOrdinate(); row++) {
            for (int col = 0; col < dimensions.getAbscissa(); col++) {
                Assert.assertTrue(treasureMap.getCell(new Position(col, row)) instanceof PlainCell);
            }
        }
    }

    @Test
    public void placeCellsOnMapTest() {
        List<AMapCell> cellList = new ArrayList<>();
        cellList.add(new MountainCell(new Position(2, 0)));
        treasureMap.placeCellsOnMap(cellList);
        Assert.assertTrue(treasureMap.getCell(new Position(2, 0)) instanceof MountainCell);
    }

    @Test
    public void getCellAndSetSellTest() {
        Assert.assertNull(treasureMap.getCell(new Position(50, 50)));
        MountainCell mountainCell = new MountainCell(new Position(0, 0));
        treasureMap.setCell(new Position(0, 0), mountainCell);
        Assert.assertEquals(mountainCell, treasureMap.getCell(0, 0));
    }

    @Test
    public void getMapDimensionsTest() {
        Assert.assertEquals(dimensions, treasureMap.getMapDimensions());
    }

}
