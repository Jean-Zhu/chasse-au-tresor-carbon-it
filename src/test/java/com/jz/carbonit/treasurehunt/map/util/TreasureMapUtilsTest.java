package com.jz.carbonit.treasurehunt.map.util;

import com.jz.carbonit.treasurehunt.exception.ParsingLineException;
import com.jz.carbonit.treasurehunt.map.domain.adventurer.Adventurer;
import com.jz.carbonit.treasurehunt.map.domain.adventurer.enumeration.CardinalDirection;
import com.jz.carbonit.treasurehunt.map.domain.adventurer.enumeration.Movement;
import com.jz.carbonit.treasurehunt.map.domain.cell.PlainCell;
import com.jz.carbonit.treasurehunt.map.domain.cell.TreasureCell;
import com.jz.carbonit.treasurehunt.map.domain.position.Position;
import com.jz.carbonit.treasurehunt.map.domain.treasuremap.ITreasureMap;
import com.jz.carbonit.treasurehunt.map.domain.treasuremap.MatrixTreasureMap;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TreasureMapUtilsTest {

    @Test
    public void getTreasureMapRepresentationForPrintTest() throws ParsingLineException {

        List<String> lines = new ArrayList<>();
        lines.add("C-5-1");
        lines.add("M-1-0");
        lines.add("T-2-0-2");
        lines.add("T-4-0-4");

        List<Adventurer> adventurers = new ArrayList<>();
        adventurers.add(new Adventurer("A1", new Position(3, 0), CardinalDirection.NORTH, new ArrayList<>()));
        adventurers.add(new Adventurer("A2", new Position(4, 0), CardinalDirection.SOUTH, new ArrayList<>()));

        ITreasureMap treasureMap = new MatrixTreasureMap(lines);

        String s = System.lineSeparator() +
                String.format("%10s", ".") + // 0-0
                String.format("%10s", "M") + // 1-0
                String.format("%10s", "T(2)") + // 2-0
                String.format("%10s", "A(A1)") + // 3-0
                String.format("%10s", "A(A2)") + // 4-0
                System.lineSeparator();

        Assert.assertEquals(s,
                TreasureMapUtils.getTreasureMapRepresentationForPrint(treasureMap, adventurers));
    }

    @Test
    public void playTest() throws ParsingLineException {

        List<String> lines = new ArrayList<>();
        lines.add("C-2-5");
        lines.add("M-1-1");
        lines.add("T-1-2-2");
        lines.add("T-1-4-1");

        ITreasureMap treasureMap = new MatrixTreasureMap(lines);

        List<Movement> goStraight = new ArrayList<>();
        goStraight.add(Movement.STRAIGHT);

        List<Adventurer> adventurers = new ArrayList<>();
        Adventurer a1 = new Adventurer("A1", new Position(0, 0), CardinalDirection.EAST, goStraight);
        Adventurer a2 = new Adventurer("A2", new Position(0, 1), CardinalDirection.EAST, goStraight);
        Adventurer a3 = new Adventurer("A3", new Position(0, 2), CardinalDirection.EAST, goStraight);
        Adventurer a4 = new Adventurer("A4", new Position(0, 3), CardinalDirection.EAST, goStraight);
        Adventurer a4Bis = new Adventurer("A4Bis", new Position(1, 3), CardinalDirection.EAST, new ArrayList<>());
        Adventurer a5 = new Adventurer("A5", new Position(0, 4), CardinalDirection.EAST, goStraight);

        adventurers.add(a1);
        adventurers.add(a2);
        adventurers.add(a3);
        adventurers.add(a4);
        adventurers.add(a4Bis);
        adventurers.add(a5);

        TreasureMapUtils.play(treasureMap, adventurers);

        Assert.assertEquals(new Position(1, 0), a1.getPosition());
        Assert.assertEquals(new Position(0, 1), a2.getPosition());
        Assert.assertEquals(new Position(1, 2), a3.getPosition());
        Assert.assertEquals(1, a3.getNbTreasure());
        TreasureCell treasureCell = (TreasureCell) treasureMap.getCell(2, 1);
        Assert.assertEquals(1, treasureCell.getNbTreasure());
        Assert.assertEquals(new Position(0, 3), a4.getPosition());
        Assert.assertEquals(new Position(1, 3), a4Bis.getPosition());
        Assert.assertTrue(treasureMap.getCell(4, 1) instanceof PlainCell);

    }

}
