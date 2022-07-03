package com.jz.carbonit.treasurehunt;

import com.jz.carbonit.treasurehunt.file.domain.FileProperties;
import com.jz.carbonit.treasurehunt.file.reader.FileReader;
import com.jz.carbonit.treasurehunt.file.util.CommandUtils;
import com.jz.carbonit.treasurehunt.file.writer.FileWriter;
import com.jz.carbonit.treasurehunt.map.domain.adventurer.Adventurer;
import com.jz.carbonit.treasurehunt.map.domain.treasuremap.ITreasureMap;
import com.jz.carbonit.treasurehunt.map.domain.treasuremap.MatrixTreasureMap;
import com.jz.carbonit.treasurehunt.map.util.LineUtils;
import com.jz.carbonit.treasurehunt.map.util.TreasureMapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class TreasureHuntApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(TreasureHuntApplication.class);

    public static void main(String[] args) throws Exception {

        LOGGER.info("Debut du traitement de la commande");
        FileProperties fileProperties = CommandUtils.getInputAndOutputFilepathFilename(args);

        LOGGER.info("Lecture du fichier en entree");
        List<String> validLines = FileReader.getValidLinesFromFile(fileProperties.getFilepath(), fileProperties.getInputFilename(), fileProperties.isTest());

        LOGGER.info("Construction de la carte au tresor");
        ITreasureMap treasureMap = new MatrixTreasureMap(validLines);

        LOGGER.info("Recuperation des aventuriers");
        List<Adventurer> adventurers = LineUtils.getAdventurerListFromLines(validLines, treasureMap);

        LOGGER.info("Lancement de la chasse au tresor");

        LOGGER.debug("Status de la carte au debut :");
        LOGGER.debug(TreasureMapUtils.getTreasureMapRepresentationForPrint(treasureMap, adventurers));
        TreasureMapUtils.play(treasureMap, adventurers);
        LOGGER.debug("Status de la carte a la fin :");
        LOGGER.debug(TreasureMapUtils.getTreasureMapRepresentationForPrint(treasureMap, adventurers));

        LOGGER.info("Stockage du fichier d'output");
        FileWriter.writeTreasureMapRepresentationInFile(fileProperties.getFilepath(),
                fileProperties.getOutputFilename(),
                treasureMap,
                adventurers);

        LOGGER.info("Fin du traitement");
    }

}
