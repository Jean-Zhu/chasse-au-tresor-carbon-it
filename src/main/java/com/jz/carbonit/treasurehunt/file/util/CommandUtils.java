package com.jz.carbonit.treasurehunt.file.util;

import com.jz.carbonit.treasurehunt.file.domain.FileProperties;
import com.jz.carbonit.treasurehunt.properties.TreasureHuntProperties;
import org.apache.commons.cli.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandUtils {
    private CommandUtils() throws IllegalAccessException {
        throw new IllegalAccessException("Utility class");
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandUtils.class);
    private static final String INPUT = "input";
    private static final String OUTPUT = "output";

    /**
     * Extrait les propriétés à utiliser pour les fichiers à partir des arguments de la commande
     *
     * @param args les arguments
     * @return les propriétés
     */
    public static FileProperties getInputAndOutputFilepathFilename(String[] args) {

        Options options = new Options();

        Option test = new Option("t", "test", false, "launch test");
        test.setRequired(false);
        options.addOption(test);

        Option filepath = new Option("fp", "filepath", true, "input (and output) filpath");
        filepath.setRequired(false);
        options.addOption(filepath);

        Option input = new Option("i", INPUT, true, "input file (relative path)");
        input.setRequired(false);
        options.addOption(input);

        Option output = new Option("o", OUTPUT, true, "output filename");
        output.setRequired(false);
        options.addOption(output);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
            formatter.printHelp("utility-name", options);
            System.exit(1);
        }


        if (cmd.hasOption(test) ||
                (!cmd.hasOption(filepath) && !cmd.hasOption(input) && !cmd.hasOption(output))) {
            return new FileProperties(
                    TreasureHuntProperties.getInstance().getFile().getDefaultFilepath(),
                    TreasureHuntProperties.getInstance().getFile().getDefaultInputFilename(),
                    TreasureHuntProperties.getInstance().getFile().getDefaultOutputFilename(),
                    true);
        }
        String inputAndOutputFilepath = getOptionValueOrDefault(cmd, filepath, System.getProperty("user.dir"));
        String inputFilename = getOptionValueOrDefault(cmd, input, TreasureHuntProperties.getInstance().getFile().getDefaultInputFilename());
        String outputFilename = getOptionValueOrDefault(cmd, output, TreasureHuntProperties.getInstance().getFile().getDefaultOutputFilename());

        return new FileProperties(inputAndOutputFilepath, inputFilename, outputFilename, false);
    }

    /**
     * Renvoie la valeur pour l'option si la commande possède l'option ou la valeur par défaut
     *
     * @param cmd          la commande
     * @param option       l'option
     * @param defaultValue la valeur par défaut
     * @return la valeur pour l'option ou par défaut
     */
    public static String getOptionValueOrDefault(CommandLine cmd, Option option, String defaultValue) {
        if (cmd.hasOption(option)) {
            return cmd.getOptionValue(option);
        }
        return defaultValue;
    }
}
