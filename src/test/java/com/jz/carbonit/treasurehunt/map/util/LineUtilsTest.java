package com.jz.carbonit.treasurehunt.map.util;

import com.jz.carbonit.treasurehunt.exception.ParsingLineException;
import com.jz.carbonit.treasurehunt.map.domain.adventurer.Adventurer;
import com.jz.carbonit.treasurehunt.map.domain.adventurer.enumeration.CardinalDirection;
import com.jz.carbonit.treasurehunt.map.domain.cell.MountainCell;
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
public class LineUtilsTest {


    @Test
    public void checkMinimumSplitLengthTest() {
        Assert.assertTrue(LineUtils.checkMinimumSplitLength("C-1-1", 3));
        Assert.assertFalse(LineUtils.checkMinimumSplitLength("C-1", 3));
    }

    @Test
    public void isAMapLineTest() {
        Assert.assertTrue(LineUtils.isAMapLine("C-1-1"));
        Assert.assertFalse(LineUtils.isAMapLine("M-3-3"));
    }

    @Test
    public void isAValidMapLineTest() {
        Assert.assertTrue(LineUtils.isAValidMapLine("C-1-1"));
        Assert.assertFalse(LineUtils.isAValidMapLine("C-3"));
    }

    @Test
    public void isAMountainLineTest() {
        Assert.assertTrue(LineUtils.isAMountainLine("M-1-1"));
        Assert.assertFalse(LineUtils.isAMountainLine("C-3-3"));
    }

    @Test
    public void isAValidMountainLineTest() {
        Assert.assertTrue(LineUtils.isAValidMountainLine("M-1-1"));
        Assert.assertFalse(LineUtils.isAValidMountainLine("M-3"));
    }

    @Test
    public void isATreasureLineTest() {
        Assert.assertTrue(LineUtils.isATreasureLine("T-1-1-4"));
        Assert.assertFalse(LineUtils.isATreasureLine("C-3-3"));
    }

    @Test
    public void isAValidTreasureLineTest() {
        Assert.assertTrue(LineUtils.isAValidTreasureLine("T-1-1-4"));
        Assert.assertFalse(LineUtils.isAValidTreasureLine("T-1-1"));
    }

    @Test
    public void isAAdventurerLineTest() {
        Assert.assertTrue(LineUtils.isAnAdventurerLine("A-1-1-E"));
        Assert.assertFalse(LineUtils.isAnAdventurerLine("C-3-3"));
    }

    @Test
    public void isAValidAdventurerLineTest() {
        Assert.assertTrue(LineUtils.isAValidAdventurerLine("A-TEST-1-1-S-G"));
        Assert.assertFalse(LineUtils.isAValidAdventurerLine("A-TEST-1-1"));
    }

    @Test
    public void isACommentLineTest() {
        Assert.assertTrue(LineUtils.isACommentLine("# TEST"));
        Assert.assertFalse(LineUtils.isACommentLine("C-3-3"));
    }

    @Test
    public void isAValidLineTest() {
        Assert.assertTrue(LineUtils.isAValidLline("C-3-3"));
        Assert.assertTrue(LineUtils.isAValidLline("M-3-3"));
        Assert.assertTrue(LineUtils.isAValidLline("T-3-3-3"));
        Assert.assertTrue(LineUtils.isAValidLline("A-TEST-3-3-S-G"));
    }

    @Test
    public void getPositionFromLineTest() {
        Assert.assertTrue(LineUtils.getPositionFromLine("C-31-3").isPresent());
        Assert.assertEquals(new Position(31, 3), LineUtils.getPositionFromLine("C-31-3").get());
        Assert.assertFalse(LineUtils.getPositionFromLine("C-3A-F").isPresent());
    }

    @Test
    public void getAdventurerPositionFromLineTest() {
        Assert.assertTrue(LineUtils.getAdventurerPositionFromLine("A-TEST-31-3-N-A").isPresent());
        Assert.assertEquals(new Position(31, 3), LineUtils.getAdventurerPositionFromLine("A-TEST-31-3-N-A").get());
        Assert.assertFalse(LineUtils.getAdventurerPositionFromLine("A-TEST-3A-F-N-D").isPresent());
    }

    @Test
    public void getCardinalDirectionFromLineTest() {
        Assert.assertTrue(LineUtils.getCardinalDirectionFromLine("A-TEST-31-3-N-AAD").isPresent());
        Assert.assertEquals(CardinalDirection.NORTH, LineUtils.getCardinalDirectionFromLine("A-TEST-31-3-N-AAD").get());
        Assert.assertFalse(LineUtils.getCardinalDirectionFromLine("A-TEST-3A-F-P-V").isPresent());
    }

    @Test
    public void getMovementsFromLineTest() {
        Assert.assertEquals(3, LineUtils.getMovementsFromLine("A-TEST-31-3-N-AAD").size());
        Assert.assertEquals(0, LineUtils.getMovementsFromLine("A-TEST-3A-F-N-V").size());
        Assert.assertEquals(2, LineUtils.getMovementsFromLine("A-TEST-3A-F-N-GVD").size());
    }

    @Test
    public void getMovementsFromStringTest() {
        Assert.assertEquals(3, LineUtils.getMovementsFromString("AAD").size());
        Assert.assertEquals(0, LineUtils.getMovementsFromString("V").size());
        Assert.assertEquals(2, LineUtils.getMovementsFromString("GVD").size());
    }

    @Test
    public void getAdventurerNameFromLineTest() {
        Assert.assertEquals("TEST", LineUtils.getAdventurerNameFromLine("A-TEST-31-3-N-AAD"));
    }

    @Test
    public void getNbTreasureFromLineTest() {
        Assert.assertTrue(LineUtils.getNbTreasureFromLine("T-2-2-4").isPresent());
        Assert.assertEquals(Integer.valueOf(4), LineUtils.getNbTreasureFromLine("T-2-2-4").get());
        Assert.assertFalse(LineUtils.getNbTreasureFromLine("T-2-2-A").isPresent());
    }

    @Test
    public void getPositionAndNbTreasureFromLineTest() {
        Assert.assertTrue(LineUtils.getPositionAndNbTreasureFromLine("T-2-2-4").isPresent());
        Assert.assertFalse(LineUtils.getPositionAndNbTreasureFromLine("T-2-E-4").isPresent());
        Assert.assertFalse(LineUtils.getPositionAndNbTreasureFromLine("T-2-2-E").isPresent());
    }

    @Test
    public void isAdventurerNotOnMontainsTest() throws ParsingLineException {
        List<String> lines = new ArrayList<>();
        lines.add("C-2-1");
        lines.add("M-1-0");

        ITreasureMap treasureMap = new MatrixTreasureMap(lines);
        Adventurer a1 = new Adventurer("A1", new Position(0, 0), CardinalDirection.NORTH, new ArrayList<>());
        Adventurer a2 = new Adventurer("A2", new Position(1, 0), CardinalDirection.NORTH, new ArrayList<>());
        Assert.assertTrue(LineUtils.isAdventurerNotOnMontains(a1, treasureMap));
        Assert.assertFalse(LineUtils.isAdventurerNotOnMontains(a2, treasureMap));
    }

    @Test(expected = ParsingLineException.class)
    public void getMapDimentionsFromLinesFailTest() throws ParsingLineException {
        List<String> lines = new ArrayList<>();
        Position dimensions = LineUtils.getMapDimentionsFromLines(lines);
    }

    @Test
    public void getMapDimentionsFromLinesTest() throws ParsingLineException {
        List<String> lines = new ArrayList<>();
        lines.add("C-2-1");
        Position dimensions = LineUtils.getMapDimentionsFromLines(lines);
        Assert.assertEquals(2, dimensions.getAbscissa());
        Assert.assertEquals(1, dimensions.getOrdinate());
    }

    @Test
    public void getMountainListFromLinesTest() {
        List<String> lines = new ArrayList<>();
        lines.add("C-5-1");
        lines.add("M-1-0");
        lines.add("T-2-0-2");
        lines.add("A-TEST-3-0-S-G");

        List<MountainCell> mountainCellList = LineUtils.getMountainListFromLines(lines);
        Assert.assertEquals(1, mountainCellList.size());

    }

    @Test
    public void getTreasureListFromLinesTest() {
        List<String> lines = new ArrayList<>();
        lines.add("C-5-1");
        lines.add("M-1-0");
        lines.add("T-2-0-2");
        lines.add("A-TEST-3-0-S-G");

        List<TreasureCell> treasureCellList = LineUtils.getTreasureListFromLines(lines);
        Assert.assertEquals(1, treasureCellList.size());

    }

    @Test
    public void getAdventurerListFromLinesTest() throws ParsingLineException {
        List<String> lines = new ArrayList<>();
        lines.add("C-5-1");
        lines.add("M-1-0");
        lines.add("T-2-0-2");
        lines.add("A-TEST-3-0-S-G");

        ITreasureMap treasureMap = new MatrixTreasureMap(lines);

        List<Adventurer> adventurerList = LineUtils.getAdventurerListFromLines(lines, treasureMap);
        Assert.assertEquals(1, adventurerList.size());
    }

    @Test
    public void createAdventurerFromLineTest() {
        Assert.assertTrue(LineUtils.createAdventurerFromLine("A-TEST-3-0-S-G").isPresent());
        Assert.assertFalse(LineUtils.createAdventurerFromLine("A-TEST2-3-0-V-G").isPresent());
    }

    @Test
    public void filterInvalidLinesTest() {
        List<String> lines = new ArrayList<>();
        lines.add("C-5-1");
        lines.add("M-1-0");
        lines.add("M-60");
        lines.add("T-2-0-2");
        lines.add("A-TEST-3-0-S-G");
        lines.add("A-TEST2-3-0");
        Assert.assertEquals(4, LineUtils.filterInvalidLines(lines).size());
    }
}
