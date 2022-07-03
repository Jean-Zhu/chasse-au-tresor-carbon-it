package com.jz.carbonit.treasurehunt.map.domain.adventurer.enumeration;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MovementTest {

    @Test
    public void propertiesTest() {
        Assert.assertEquals('A', Movement.STRAIGHT.getName());
        Assert.assertEquals('G', Movement.LEFT.getName());
        Assert.assertEquals('D', Movement.RIGHT.getName());
    }

    @Test
    public void getMovementFromCharTest() {
        Assert.assertEquals(Movement.STRAIGHT, Movement.getMovementFromChar('A'));
        Assert.assertEquals(Movement.LEFT, Movement.getMovementFromChar('G'));
        Assert.assertEquals(Movement.RIGHT, Movement.getMovementFromChar('D'));
        Assert.assertNull(Movement.getMovementFromChar('V'));
    }

}
