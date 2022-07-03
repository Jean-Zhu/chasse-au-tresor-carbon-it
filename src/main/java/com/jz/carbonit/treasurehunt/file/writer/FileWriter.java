package com.jz.carbonit.treasurehunt.file.writer;

import com.jz.carbonit.treasurehunt.map.domain.adventurer.Adventurer;
import com.jz.carbonit.treasurehunt.map.domain.cell.MountainCell;
import com.jz.carbonit.treasurehunt.map.domain.cell.TreasureCell;
import com.jz.carbonit.treasurehunt.map.domain.treasuremap.ITreasureMap;
import com.jz.carbonit.treasurehunt.properties.TreasureHuntProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringJoiner;

public class FileWriter {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileWriter.class);

    private FileWriter() throws IllegalAccessException {
        throw new IllegalAccessException("Utility class");
    }

    /**
     * Permet d'écrire le résultat de la chasse au trésor dans un fichier
     *
     * @param filepath    le filepath du fichier de sortie
     * @param filename    le filename du fichier de sortie
     * @param treasureMap la carte
     * @param adventurers les aventuriers
     */
    public static void writeTreasureMapRepresentationInFile(String filepath,
                                                            String filename,
                                                            ITreasureMap treasureMap,
                                                            List<Adventurer> adventurers) {

        String path = Paths.get(filepath, filename).toString();

        try (FileOutputStream fos = new FileOutputStream(path)) {
            String fileContent = getTreasureMapRepresentationForOutputFile(treasureMap, adventurers);
            byte[] fileContentBytes = fileContent.getBytes();
            fos.write(fileContentBytes);
            LOGGER.info("Fichier d'export depose a l'emplacement : [{}]", path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Renvoie la représentation de la carte avec les aventuriers pour le fichier de sortie
     *
     * @param treasureMap la carte
     * @param adventurers les aventuriers
     * @return la représentation
     */
    public static String getTreasureMapRepresentationForOutputFile(ITreasureMap treasureMap, List<Adventurer> adventurers) {

        String separator = " - ";
        StringJoiner sj = new StringJoiner(System.lineSeparator());

        sj.add(TreasureHuntProperties.getInstance().getLine().getMapPrefix() +
                separator +
                treasureMap.getMapDimensions().getAbscissa() +
                separator +
                treasureMap.getMapDimensions().getOrdinate());

        for (int row = 0; row < treasureMap.getMapDimensions().getOrdinate(); row++) {
            for (int col = 0; col < treasureMap.getMapDimensions().getAbscissa(); col++) {
                if (treasureMap.getCell(row, col) instanceof MountainCell) {
                    sj.add(TreasureHuntProperties.getInstance().getLine().getMountainPrefix() +
                            separator +
                            col +
                            separator +
                            row);
                } else if (treasureMap.getCell(row, col) instanceof TreasureCell) {
                    sj.add(TreasureHuntProperties.getInstance().getLine().getTreasurePrefix() +
                            separator +
                            col +
                            separator +
                            row +
                            separator +
                            ((TreasureCell) treasureMap.getCell(row, col)).getNbTreasure());
                }
            }
        }

        for (Adventurer a : adventurers) {
            sj.add(TreasureHuntProperties.getInstance().getLine().getAdventurerPrefix() +
                    separator +
                    a.getName() +
                    separator +
                    a.getPosition().getAbscissa() +
                    separator +
                    a.getPosition().getOrdinate() +
                    separator +
                    a.getDirection().getLibelle() +
                    separator +
                    a.getNbTreasure());
        }

        return sj.toString();
    }
}
