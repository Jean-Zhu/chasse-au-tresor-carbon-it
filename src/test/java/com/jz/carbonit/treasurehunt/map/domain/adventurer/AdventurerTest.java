package com.jz.carbonit.treasurehunt.map.domain.adventurer;

import com.jz.carbonit.treasurehunt.exception.ParsingLineException;
import com.jz.carbonit.treasurehunt.map.domain.adventurer.enumeration.CardinalDirection;
import com.jz.carbonit.treasurehunt.map.domain.adventurer.enumeration.Movement;
import com.jz.carbonit.treasurehunt.map.domain.cell.PlainCell;
import com.jz.carbonit.treasurehunt.map.domain.cell.TreasureCell;
import com.jz.carbonit.treasurehunt.map.domain.position.Position;
import com.jz.carbonit.treasurehunt.map.domain.treasuremap.ITreasureMap;
import com.jz.carbonit.treasurehunt.map.domain.treasuremap.MatrixTreasureMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AdventurerTest {
    private Adventurer adventurer;
    private List<Movement> movements;
    private Position position;

    @Before
    public void steup() {
        movements = new ArrayList<>();
        movements.add(Movement.LEFT);
        movements.add(Movement.RIGHT);
        movements.add(Movement.STRAIGHT);
        position = new Position(1, 1);
        adventurer = new Adventurer("TEST", position, CardinalDirection.NORTH, movements);
    }

    @Test
    public void propertiesTest() {
        Assert.assertEquals("TEST", adventurer.getName());
        Assert.assertEquals(position, adventurer.getPosition());
        Assert.assertEquals(CardinalDirection.NORTH, adventurer.getDirection());
        Assert.assertEquals(movements, adventurer.getMovementlist());
    }


    @Test
    public void getRepresentationTest() {
        Assert.assertEquals("A(TEST)", adventurer.getRepresentation());
    }

    @Test
    public void collectTest() throws ParsingLineException {
        List<String> lines = new ArrayList<>();
        lines.add("C-2-1");
        lines.add("T-0-0-1");
        lines.add("T-1-0-2");

        ITreasureMap treasureMap = new MatrixTreasureMap(lines);
        adventurer.collect(treasureMap, new Position(0, 0));
        adventurer.collect(treasureMap, new Position(1, 0));
        Assert.assertEquals(2, adventurer.getNbTreasure());
        TreasureCell treasureCell = (TreasureCell) treasureMap.getCell(0, 1);
        Assert.assertEquals(1, treasureCell.getNbTreasure());
        Assert.assertTrue(treasureMap.getCell(0, 0) instanceof PlainCell);
    }

    /**
     * Test : aller sur une case "vide"
     *
     * @throws ParsingLineException Problème lors du parsing des lignes
     */
    @Test
    public void doMovementTest() throws ParsingLineException {

        List<String> lines = new ArrayList<>();
        lines.add("C-2-2");

        ITreasureMap treasureMap = new MatrixTreasureMap(lines);
        Assert.assertTrue(adventurer.doMovement(Movement.STRAIGHT, treasureMap, new ArrayList<>()));

        Assert.assertEquals(new Position(1, 0), adventurer.getPosition());
    }

    /**
     * Test : tourner à gauche
     *
     * @throws ParsingLineException Problème lors du parsing des lignes
     */
    @Test
    public void doMovementTest2() throws ParsingLineException {

        List<String> lines = new ArrayList<>();
        lines.add("C-2-2");

        ITreasureMap treasureMap = new MatrixTreasureMap(lines);
        Assert.assertFalse(adventurer.doMovement(Movement.LEFT, treasureMap, new ArrayList<>()));

        Assert.assertEquals(CardinalDirection.WEST, adventurer.getDirection());

    }

    /**
     * Test : tourner à droite
     *
     * @throws ParsingLineException Problème lors du parsing des lignes
     */
    @Test
    public void doMovementTest3() throws ParsingLineException {

        List<String> lines = new ArrayList<>();
        lines.add("C-2-2");

        ITreasureMap treasureMap = new MatrixTreasureMap(lines);
        Assert.assertFalse(adventurer.doMovement(Movement.RIGHT, treasureMap, new ArrayList<>()));

        Assert.assertEquals(CardinalDirection.EAST, adventurer.getDirection());


    }

    /**
     * Test : avancer vers une case occupée
     *
     * @throws ParsingLineException Problème lors du parsing des lignes
     */
    @Test
    public void doMovementTest4() throws ParsingLineException {

        List<String> lines = new ArrayList<>();
        lines.add("C-2-2");

        ITreasureMap treasureMap = new MatrixTreasureMap(lines);
        List<Adventurer> adventurers = new ArrayList<>();
        adventurers.add(new Adventurer("TEST2", new Position(1, 0), CardinalDirection.NORTH, new ArrayList<>()));
        Assert.assertFalse(adventurer.doMovement(Movement.STRAIGHT, treasureMap, adventurers));

        Assert.assertEquals(new Position(1, 1), adventurer.getPosition());
    }

    /**
     * Test : avancer vers une montagne
     *
     * @throws ParsingLineException Problème lors du parsing des lignes
     */
    @Test
    public void doMovementTest5() throws ParsingLineException {

        List<String> lines = new ArrayList<>();
        lines.add("C-2-2");
        lines.add("M-1-0");

        ITreasureMap treasureMap = new MatrixTreasureMap(lines);
        Assert.assertFalse(adventurer.doMovement(Movement.STRAIGHT, treasureMap, new ArrayList<>()));

        Assert.assertEquals(new Position(1, 1), adventurer.getPosition());

    }

    /**
     * Test : avancer en dehors des limites
     *
     * @throws ParsingLineException Problème lors du parsing des lignes
     */
    @Test
    public void doMovementTest6() throws ParsingLineException {

        List<String> lines = new ArrayList<>();
        lines.add("C-2-2");

        ITreasureMap treasureMap = new MatrixTreasureMap(lines);
        adventurer.setDirection(CardinalDirection.EAST);
        Assert.assertFalse(adventurer.doMovement(Movement.STRAIGHT, treasureMap, new ArrayList<>()));

        Assert.assertEquals(new Position(1, 1), adventurer.getPosition());

    }

    @Test
    public void getNextAdventurerPositionTest() {
        Position north = new Position(1, 0);
        Position east = new Position(2, 1);
        Position south = new Position(1, 2);
        Position west = new Position(0, 1);

        adventurer.setDirection(CardinalDirection.NORTH);
        Assert.assertEquals(north, adventurer.getNextAdventurerPosition());

        adventurer.setDirection(CardinalDirection.EAST);
        Assert.assertEquals(east, adventurer.getNextAdventurerPosition());

        adventurer.setDirection(CardinalDirection.SOUTH);
        Assert.assertEquals(south, adventurer.getNextAdventurerPosition());

        adventurer.setDirection(CardinalDirection.WEST);
        Assert.assertEquals(west, adventurer.getNextAdventurerPosition());
    }


}
