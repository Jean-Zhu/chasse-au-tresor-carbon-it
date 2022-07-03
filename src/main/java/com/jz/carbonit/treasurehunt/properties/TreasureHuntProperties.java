package com.jz.carbonit.treasurehunt.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Charge en mémoire les paramètres pour les préfixes, filepath et filanme
 */
@Component
@Getter
public class TreasureHuntProperties {

    private static TreasureHuntProperties instance;

    private final Line line = new Line();
    private final File file = new File();

    public static TreasureHuntProperties getInstance() {
        return instance;
    }

    static {
        try {
            instance = new TreasureHuntProperties();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private TreasureHuntProperties() {
        Properties properties = new Properties();

        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties")) {
            if (is != null) {
                properties.load(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.line.setMapPrefix(properties.getProperty("treasureHunt.line.mapPrefix", "C"));
        this.line.setMountainPrefix(properties.getProperty("treasureHunt.line.mountainPrefix", "M"));
        this.line.setTreasurePrefix(properties.getProperty("treasureHunt.line.treasurePrefix", "T"));
        this.line.setAdventurerPrefix(properties.getProperty("treasureHunt.line.adventurerPrefix", "A"));
        this.line.setCommentPrefix(properties.getProperty("treasureHunt.line.commentPrefix", "#"));

        this.file.setDefaultFilepath(properties.getProperty("treasureHunt.file.defaultFilepath", System.getProperty("user.dir")));
        this.file.setDefaultInputFilename(properties.getProperty("treasureHunt.file.defaultInputFilename", "test.txt"));
        this.file.setDefaultOutputFilename(properties.getProperty("treasureHunt.file.defaultOutputFilename", "result_test.txt"));

    }

    @Getter
    @Setter
    public static class File {
        private String defaultFilepath;
        private String defaultInputFilename;
        private String defaultOutputFilename;
    }

    @Getter
    @Setter
    public static class Line {
        private String mapPrefix;
        private String mountainPrefix;
        private String treasurePrefix;
        private String adventurerPrefix;
        private String commentPrefix;
    }


}
