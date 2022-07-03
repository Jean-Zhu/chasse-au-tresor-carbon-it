package com.jz.carbonit.treasurehunt;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.nio.file.Paths;

@RunWith(MockitoJUnitRunner.class)
public class TreasureHuntApplicationTest {

    String dir = System.getProperty("user.dir");

    @Test
    public void mainTest() throws Exception {
        String[] args = new String[0];
        TreasureHuntApplication.main(args);
        File f = new File(Paths.get(dir, "result_test.txt").toString());
        Assert.assertTrue(f.exists());
    }
}
