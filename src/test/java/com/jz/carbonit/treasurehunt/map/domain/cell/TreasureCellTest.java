package com.jz.carbonit.treasurehunt.map.domain.cell;

import com.jz.carbonit.treasurehunt.map.domain.position.Position;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TreasureCellTest {

    private final TreasureCell treasureCell = new TreasureCell(new Position(1, 0), 3);

    @Test
    public void propertiesTest() {
        Assert.assertTrue(treasureCell.isCanBeCrossed());
    }

    @Test
    public void getRepresentationTest() {
        Assert.assertEquals("T(3)", treasureCell.getRepresentation());
    }
}
