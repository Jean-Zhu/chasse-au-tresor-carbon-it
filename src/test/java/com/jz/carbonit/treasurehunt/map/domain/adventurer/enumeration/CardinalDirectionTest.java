package com.jz.carbonit.treasurehunt.map.domain.adventurer.enumeration;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)

public class CardinalDirectionTest {

    @Test
    public void propertiesTest() {
        Assert.assertEquals("N", CardinalDirection.NORTH.getLibelle());
        Assert.assertEquals(Integer.valueOf(0), CardinalDirection.NORTH.getAddAbsIfGoingStraight());
        Assert.assertEquals(Integer.valueOf(-1), CardinalDirection.NORTH.getAddOrdIfGoingStraight());
        Assert.assertEquals("E", CardinalDirection.EAST.getLibelle());
        Assert.assertEquals(Integer.valueOf(1), CardinalDirection.EAST.getAddAbsIfGoingStraight());
        Assert.assertEquals(Integer.valueOf(0), CardinalDirection.EAST.getAddOrdIfGoingStraight());
        Assert.assertEquals("S", CardinalDirection.SOUTH.getLibelle());
        Assert.assertEquals(Integer.valueOf(0), CardinalDirection.SOUTH.getAddAbsIfGoingStraight());
        Assert.assertEquals(Integer.valueOf(1), CardinalDirection.SOUTH.getAddOrdIfGoingStraight());
        Assert.assertEquals("O", CardinalDirection.WEST.getLibelle());
        Assert.assertEquals(Integer.valueOf(-1), CardinalDirection.WEST.getAddAbsIfGoingStraight());
        Assert.assertEquals(Integer.valueOf(0), CardinalDirection.WEST.getAddOrdIfGoingStraight());
    }

    @Test
    public void getCardinalDirectionFromStringTest() {
        Assert.assertEquals(CardinalDirection.NORTH, CardinalDirection.getCardinalDirectionFromString("N"));
        Assert.assertEquals(CardinalDirection.EAST, CardinalDirection.getCardinalDirectionFromString("E"));
        Assert.assertEquals(CardinalDirection.SOUTH, CardinalDirection.getCardinalDirectionFromString("S"));
        Assert.assertEquals(CardinalDirection.WEST, CardinalDirection.getCardinalDirectionFromString("O"));
        Assert.assertNull(CardinalDirection.getCardinalDirectionFromString("A"));
    }

    @Test
    public void directionOnRightTest() {
        Assert.assertEquals(CardinalDirection.NORTH, CardinalDirection.WEST.directionOnRight());
        Assert.assertEquals(CardinalDirection.EAST, CardinalDirection.NORTH.directionOnRight());
        Assert.assertEquals(CardinalDirection.SOUTH, CardinalDirection.EAST.directionOnRight());
        Assert.assertEquals(CardinalDirection.WEST, CardinalDirection.SOUTH.directionOnRight());
    }

    @Test
    public void directionOnLeftTest() {
        Assert.assertEquals(CardinalDirection.NORTH, CardinalDirection.EAST.directionOnLeft());
        Assert.assertEquals(CardinalDirection.EAST, CardinalDirection.SOUTH.directionOnLeft());
        Assert.assertEquals(CardinalDirection.SOUTH, CardinalDirection.WEST.directionOnLeft());
        Assert.assertEquals(CardinalDirection.WEST, CardinalDirection.NORTH.directionOnLeft());
    }

}
