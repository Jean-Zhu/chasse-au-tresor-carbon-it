package com.jz.carbonit.treasurehunt.map.domain.cell;

import com.jz.carbonit.treasurehunt.map.domain.position.Position;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PlainCellTest {

    private final PlainCell plainCell = new PlainCell(new Position(1, 0));

    @Test
    public void propertiesTest() {
        Assert.assertTrue(plainCell.isCanBeCrossed());
    }

    @Test
    public void getRepresentationTest() {
        Assert.assertEquals(".", plainCell.getRepresentation());
    }
}
