package com.jz.carbonit.treasurehunt.file.util;

import com.jz.carbonit.treasurehunt.file.domain.FileProperties;
import org.apache.commons.cli.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CommandUtilsTest {

    @Test
    public void getInputAndOutputFilepathFilenameTest() {
        String[] args = new String[0];
        FileProperties properties = CommandUtils.getInputAndOutputFilepathFilename(args);
        Assert.assertTrue(properties.isTest());
    }

    @Test
    public void getInputAndOutputFilepathFilenameTest2() {
        String[] args = new String[2];
        args[0] = "-i";
        args[1] = "test.txt";
        FileProperties properties = CommandUtils.getInputAndOutputFilepathFilename(args);
        Assert.assertFalse(properties.isTest());
        Assert.assertEquals("test.txt", properties.getInputFilename());
    }

    @Test
    public void getInputAndOutputFilepathFilenameTest3() {
        String[] args = new String[2];
        args[0] = "-o";
        args[1] = "result.txt";
        FileProperties properties = CommandUtils.getInputAndOutputFilepathFilename(args);
        Assert.assertFalse(properties.isTest());
        Assert.assertEquals("result.txt", properties.getOutputFilename());
    }

    @Test
    public void getInputAndOutputFilepathFilenameTest4() {
        String[] args = new String[4];
        args[0] = "-i";
        args[1] = "test.txt";
        args[2] = "-o";
        args[3] = "result.txt";
        FileProperties properties = CommandUtils.getInputAndOutputFilepathFilename(args);
        Assert.assertFalse(properties.isTest());
        Assert.assertEquals("test.txt", properties.getInputFilename());
        Assert.assertEquals("result.txt", properties.getOutputFilename());
    }

    @Test
    public void getOptionValueOrDefaultTest() throws ParseException {
        Options options = new Options();
        Option input = new Option("i", "input", true, "input file (relative path)");
        input.setRequired(false);
        options.addOption(input);
        CommandLineParser parser = new DefaultParser();
        String[] args = new String[0];
        CommandLine cmd = parser.parse(options, args);
        Assert.assertEquals("defaut", CommandUtils.getOptionValueOrDefault(cmd, input, "defaut"));
    }

    @Test
    public void getOptionValueOrDefaultTest2() throws ParseException {
        Options options = new Options();
        Option input = new Option("i", "input", true, "input file (relative path)");
        input.setRequired(false);
        options.addOption(input);
        CommandLineParser parser = new DefaultParser();
        String[] args = new String[2];
        args[0] = "-i";
        args[1] = "test.txt";
        CommandLine cmd = parser.parse(options, args);
        Assert.assertEquals("test.txt", CommandUtils.getOptionValueOrDefault(cmd, input, "defaut"));
    }
}
