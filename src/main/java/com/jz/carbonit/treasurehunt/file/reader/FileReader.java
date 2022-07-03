package com.jz.carbonit.treasurehunt.file.reader;

import com.jz.carbonit.treasurehunt.map.util.LineUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    private FileReader() throws IllegalAccessException {
        throw new IllegalAccessException("Utility class");
    }

    /**
     * Permet d'extraire l'ensemble des lignes valides à partir d'un fichier
     *
     * @param inputFilepath le filepath du fichier à lire
     * @param inputFilename le filename du fichier à lire
     * @param test          booléen inqiquant si le fichier de test est à utiliser
     * @return la liste des lignes valides
     * @throws IOException erreur dans l'ouverture du fichier
     */
    public static List<String> getValidLinesFromFile(String inputFilepath, String inputFilename, boolean test) throws IOException {
        List<String> lines = new ArrayList<>();
        InputStream is;
        if (test) {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(inputFilename);
        } else {
            is = Files.newInputStream(Paths.get(inputFilepath, inputFilename));
        }

        if (is != null) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                String line;
                while ((line = br.readLine()) != null) {
                    lines.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return LineUtils.filterInvalidLines(lines);
    }

}
