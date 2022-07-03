package com.jz.carbonit.treasurehunt.map.domain.cell;

import com.jz.carbonit.treasurehunt.map.domain.position.Position;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MountainCellTest {

    private final MountainCell mountainCell = new MountainCell(new Position(1, 0));

    @Test
    public void propertiesTest() {
        Assert.assertFalse(mountainCell.isCanBeCrossed());
    }

    @Test
    public void getRepresentationTest() {
        Assert.assertEquals("M", mountainCell.getRepresentation());
    }
}
