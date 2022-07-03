package com.jz.carbonit.treasurehunt.properties;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TreasureHuntPropertiesTest {

    @Test
    public void propertiesTest() {
        TreasureHuntProperties properties = TreasureHuntProperties.getInstance();

        Assert.assertEquals("C", properties.getLine().getMapPrefix());
        Assert.assertEquals("M", properties.getLine().getMountainPrefix());
        Assert.assertEquals("T", properties.getLine().getTreasurePrefix());
        Assert.assertEquals("A", properties.getLine().getAdventurerPrefix());
        Assert.assertEquals("#", properties.getLine().getCommentPrefix());

        Assert.assertEquals(System.getProperty("user.dir"), properties.getFile().getDefaultFilepath());
        Assert.assertEquals("test.txt", properties.getFile().getDefaultInputFilename());
        Assert.assertEquals("result_test.txt", properties.getFile().getDefaultOutputFilename());
    }
}
