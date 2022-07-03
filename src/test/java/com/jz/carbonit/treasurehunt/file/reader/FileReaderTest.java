package com.jz.carbonit.treasurehunt.file.reader;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class FileReaderTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    private File file;

    @Before
    public void setup() throws IOException {
        file = temporaryFolder.newFile("test.txt");

        try {
            FileWriter myWriter = new FileWriter(file);
            myWriter.write("C - 3 - 3");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Erreur lors de l'écriture dans le fichier");
            e.printStackTrace();
        }
    }

    @Test
    public void getValidLinesFromFileTest() throws IOException {
        List<String> result = FileReader.getValidLinesFromFile("filepath", "test.txt", true);
        Assert.assertEquals(6, result.size());
    }

    @Test
    public void getValidLinesFromFileTest2() throws IOException {
        List<String> result = FileReader.getValidLinesFromFile(temporaryFolder.getRoot().getPath(), "test.txt", false);
        Assert.assertEquals(1, result.size());
    }
}
