package com.jz.carbonit.treasurehunt.map.domain.position;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PositionTest {


    @Test
    public void isOutOfBoundsTest() {
        Position position = new Position(3, 0);
        Assert.assertTrue(position.isOutOfBounds(2, 1));
        Assert.assertTrue(position.isOutOfBounds(new Position(2, 1)));
    }
}
