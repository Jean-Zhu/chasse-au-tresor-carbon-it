package com.jz.carbonit.treasurehunt.file.writer;


import com.jz.carbonit.treasurehunt.exception.ParsingLineException;
import com.jz.carbonit.treasurehunt.map.domain.adventurer.Adventurer;
import com.jz.carbonit.treasurehunt.map.domain.adventurer.enumeration.CardinalDirection;
import com.jz.carbonit.treasurehunt.map.domain.position.Position;
import com.jz.carbonit.treasurehunt.map.domain.treasuremap.ITreasureMap;
import com.jz.carbonit.treasurehunt.map.domain.treasuremap.MatrixTreasureMap;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@RunWith(MockitoJUnitRunner.class)
public class FileWriterTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void writeTreasureMapRepresentationInFileTest() throws IOException, ParsingLineException {
        File folder = temporaryFolder.newFolder("test");

        List<String> lines = new ArrayList<>();
        lines.add("C-3-4");
        ITreasureMap treasureMap = new MatrixTreasureMap(lines);
        List<Adventurer> adventurers = new ArrayList<>();

        FileWriter.writeTreasureMapRepresentationInFile(folder.getPath(), "test.txt", treasureMap, adventurers);

        File f = new File(Paths.get(folder.getPath(), "test.txt").toString());

        Assert.assertTrue(f.exists());
    }

    @Test
    public void getTreasureMapRepresentationForOutputFileTest() throws ParsingLineException {
        List<String> lines = new ArrayList<>();
        lines.add("C-2-2");
        lines.add("M-1-0");
        lines.add("T-0-1-1");
        ITreasureMap treasureMap = new MatrixTreasureMap(lines);

        Adventurer adventurer = new Adventurer("TEST", new Position(1, 1), CardinalDirection.NORTH, new ArrayList<>());
        List<Adventurer> adventurers = new ArrayList<>();
        adventurers.add(adventurer);

        StringJoiner sj = new StringJoiner(System.lineSeparator());
        sj.add("C - 2 - 2");
        sj.add("M - 1 - 0");
        sj.add("T - 0 - 1 - 1");
        sj.add("A - TEST - 1 - 1 - N - 0");

        Assert.assertEquals(sj.toString(), FileWriter.getTreasureMapRepresentationForOutputFile(treasureMap, adventurers));
    }

}
