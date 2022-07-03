package com.jz.carbonit.treasurehunt.map.util;

import com.jz.carbonit.treasurehunt.exception.ParsingLineException;
import com.jz.carbonit.treasurehunt.map.domain.adventurer.Adventurer;
import com.jz.carbonit.treasurehunt.map.domain.adventurer.enumeration.CardinalDirection;
import com.jz.carbonit.treasurehunt.map.domain.adventurer.enumeration.Movement;
import com.jz.carbonit.treasurehunt.map.domain.cell.AMapCell;
import com.jz.carbonit.treasurehunt.map.domain.cell.MountainCell;
import com.jz.carbonit.treasurehunt.map.domain.cell.TreasureCell;
import com.jz.carbonit.treasurehunt.map.domain.position.Position;
import com.jz.carbonit.treasurehunt.map.domain.treasuremap.ITreasureMap;
import com.jz.carbonit.treasurehunt.properties.TreasureHuntProperties;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class LineUtils {

    public static final String SPLIT_REGEX = "-";

    private LineUtils() throws IllegalAccessException {
        throw new IllegalAccessException("Utility class");
    }

    /**
     * Renvoie un booléen indiquant si la ligne contient au moins 'min' éléments après le découpage
     *
     * @param line la ligne
     * @param min  le minimum à attendre
     * @return le booléen
     */
    public static boolean checkMinimumSplitLength(String line, int min) {
        String[] split = line.split(SPLIT_REGEX);
        return split.length >= min;
    }

    /**
     * Renvoie un booléen indiquant si la ligne commence par le préfixe d'une carte
     *
     * @param line la ligne
     * @return le booléen
     */
    public static boolean isAMapLine(String line) {
        return line.startsWith(TreasureHuntProperties.getInstance().getLine().getMapPrefix());
    }

    /**
     * Renvoie un booléen indiquant si la ligne est une ligne décrivant une carte
     *
     * @param line la ligne
     * @return le booléen
     */
    public static boolean isAValidMapLine(String line) {
        return isAMapLine(line) && checkMinimumSplitLength(line, 3);
    }

    /**
     * Renvoie un booléen indiquant si la ligne commence par le préfixe d'une montagne
     *
     * @param line la ligne
     * @return le booléen
     */
    public static boolean isAMountainLine(String line) {
        return line.startsWith(TreasureHuntProperties.getInstance().getLine().getMountainPrefix());
    }

    /**
     * Renvoie un booléen indiquant si la ligne est une ligne décrivant une montagne
     *
     * @param line la ligne
     * @return le booléen
     */
    public static boolean isAValidMountainLine(String line) {
        return isAMountainLine(line) && checkMinimumSplitLength(line, 3);
    }

    /**
     * Renvoie un booléen indiquant si la ligne commence par le préfixe d'un trésor
     *
     * @param line la ligne
     * @return le booléen
     */
    public static boolean isATreasureLine(String line) {
        return line.startsWith(TreasureHuntProperties.getInstance().getLine().getTreasurePrefix());
    }

    /**
     * Renvoie un booléen indiquant si la ligne est une ligne décrivant un trésor
     *
     * @param line la ligne
     * @return le booléen
     */
    public static boolean isAValidTreasureLine(String line) {
        return isATreasureLine(line) && checkMinimumSplitLength(line, 4);
    }

    /**
     * Renvoie un booléen indiquant si la ligne commence par le préfixe d'un aventurier
     *
     * @param line la ligne
     * @return le booléen
     */
    public static boolean isAnAdventurerLine(String line) {
        return line.startsWith(TreasureHuntProperties.getInstance().getLine().getAdventurerPrefix());
    }

    /**
     * Renvoie un booléen indiquant si la ligne est une ligne décrivant un aventurier
     *
     * @param line la ligne
     * @return le booléen
     */
    public static boolean isAValidAdventurerLine(String line) {
        return isAnAdventurerLine(line) && checkMinimumSplitLength(line, 6);
    }

    /**
     * Renvoie un booléen indiquant si la ligne commence par le préfixe d'un commentaire
     *
     * @param line la ligne
     * @return le booléen
     */
    public static boolean isACommentLine(String line) {
        return line.startsWith(TreasureHuntProperties.getInstance().getLine().getCommentPrefix());
    }

    /**
     * Renvoie un booléen indiquant si la ligne est un élément transformable
     *
     * @param line la ligne
     * @return le booléen
     */
    public static boolean isAValidLline(String line) {
        return (isAValidMapLine(line) ||
                isAValidMountainLine(line) ||
                isAValidTreasureLine(line) ||
                isAValidAdventurerLine(line));
    }

    /**
     * Extrait la position d'un élément à partir d'une ligne
     *
     * @param line la ligne
     * @return la position ou null
     */
    public static Optional<Position> getPositionFromLine(String line) {
        String[] split = line.split(SPLIT_REGEX);
        if (split[1].matches("\\d+") && split[2].matches("\\d+")) {
            return Optional.of(new Position(Integer.parseInt(split[1]), Integer.parseInt(split[2])));
        }
        return Optional.empty();
    }

    /**
     * Extrait la position d'un aventurier à partir d'une ligne
     *
     * @param line la ligne
     * @return la position ou null
     */
    public static Optional<Position> getAdventurerPositionFromLine(String line) {
        String[] split = line.split(SPLIT_REGEX);
        if (split[2].matches("\\d+") && split[3].matches("\\d+")) {
            return Optional.of(new Position(Integer.parseInt(split[2]), Integer.parseInt(split[3])));
        }
        return Optional.empty();
    }

    /**
     * Extrait la direction d'un aventurier à partir d'une ligne
     *
     * @param line la ligne
     * @return la direction ou null
     */
    public static Optional<CardinalDirection> getCardinalDirectionFromLine(String line) {
        String[] split = line.split(SPLIT_REGEX);
        return Optional.ofNullable(CardinalDirection.getCardinalDirectionFromString(split[4]));
    }

    /**
     * Extrait la liste des mouvements d'un aventurier à partir d'une ligne
     *
     * @param line la ligne
     * @return la liste des mouvements ou null
     */
    public static List<Movement> getMovementsFromLine(String line) {
        String[] split = line.split(SPLIT_REGEX);
        return getMovementsFromString(split[5]);
    }

    /**
     * Extrait la liste des mouvements d'un aventurier à partir d'une chaîne
     *
     * @param s la chaîne contenant les mouvements
     * @return la liste des mouvements ou null
     */
    public static List<Movement> getMovementsFromString(String s) {
        List<Movement> movements = new ArrayList<>();
        s.chars()
                .mapToObj(i -> (char) i)
                .forEach(character -> {
                    Movement m = Movement.getMovementFromChar(character);
                    if (m != null) {
                        movements.add(m);
                    }
                });

        return movements;
    }

    /**
     * Extrait le nom d'un aventurier à partir d'une ligne
     *
     * @param line la ligne
     * @return le nom d'un aventurier
     */
    public static String getAdventurerNameFromLine(String line) {
        String[] split = line.split(SPLIT_REGEX);
        return split[1];
    }

    /**
     * Extrait le nombre de trésors d'un trésor à partir d'une ligne
     *
     * @param line la ligne
     * @return le nombre de trésors
     */
    public static Optional<Integer> getNbTreasureFromLine(String line) {
        String[] split = line.split(SPLIT_REGEX);
        if (split[3].matches("\\d+")) {
            return Optional.of(Integer.parseInt(split[3]));
        }
        return Optional.empty();
    }

    /**
     * Extrait la position et le nombre de trésors dun trésor à partir d'une ligne
     *
     * @param line la ligne
     * @return la position et le nombre de trésors dun trésor ou null
     */
    public static Optional<Pair<Position, Integer>> getPositionAndNbTreasureFromLine(String line) {
        Optional<Position> position = getPositionFromLine(line);
        Optional<Integer> nbTreasure = getNbTreasureFromLine(line);
        if (position.isPresent() && nbTreasure.isPresent()) {
            return Optional.of(Pair.of(position.get(), nbTreasure.get()));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Renvoie un booléen indiquant si un aventurier sera sur une case montagne
     *
     * @param adventurer   l'aventurier
     * @param iTreasureMap la carte
     * @return le booléen
     */
    public static boolean isAdventurerNotOnMontains(Adventurer adventurer, ITreasureMap iTreasureMap) {
        Position position = adventurer.getPosition();
        AMapCell cell = iTreasureMap.getCell(position);
        return cell != null && !(cell instanceof MountainCell);
    }

    /**
     * Extrait la dimension de la carte à créer
     *
     * @param lines les lignes du fichier
     * @return la dimensions
     * @throws ParsingLineException si aucune ligne ne permet de décrire une carte
     */
    public static Position getMapDimentionsFromLines(List<String> lines) throws ParsingLineException {
        Optional<String> mapLine = lines.stream().filter(LineUtils::isAMapLine).findFirst();
        if (mapLine.isPresent()) {
            lines.removeIf(LineUtils::isAMapLine);
            return new Position(mapLine.get());
        } else {
            throw new ParsingLineException("No line corresponding to map dimension found");
        }
    }

    /**
     * Extrait la liste de montagnes à créer
     *
     * @param lines les lignes d'un fichier
     * @return la liste de montagnes
     */
    public static List<MountainCell> getMountainListFromLines(List<String> lines) {
        List<MountainCell> mountainCellList = new ArrayList<>();
        List<String> mountainLines = lines.stream().filter(LineUtils::isAMountainLine).collect(Collectors.toList());
        mountainLines.stream()
                .map(LineUtils::getPositionFromLine)
                .forEach(opt -> opt.ifPresent(mountain -> mountainCellList.add(new MountainCell(mountain))));
        lines.removeIf(mountainLines::contains);
        return mountainCellList;
    }

    /**
     * Extrait la liste de trésors à créer
     *
     * @param lines les lignes d'un fichier
     * @return la liste de trésors
     */
    public static List<TreasureCell> getTreasureListFromLines(List<String> lines) {
        List<TreasureCell> treasureCellList = new ArrayList<>();
        List<String> treasureLines = lines.stream().filter(LineUtils::isATreasureLine).collect(Collectors.toList());
        treasureLines.stream()
                .map(LineUtils::getPositionAndNbTreasureFromLine)
                .forEach(opt -> opt.ifPresent(treasure -> treasureCellList.add(new TreasureCell(treasure.getLeft(), treasure.getRight()))));
        lines.removeIf(treasureLines::contains);
        return treasureCellList;
    }

    /**
     * Extrait la liste des aventuriers à créer
     *
     * @param lines les lignes d'un fichier
     * @return la liste des aventuriers
     */
    public static List<Adventurer> getAdventurerListFromLines(List<String> lines, ITreasureMap treasureMap) {
        List<Adventurer> adventurerList = new ArrayList<>();
        List<String> adventurerLines = lines.stream().filter(LineUtils::isAnAdventurerLine).collect(Collectors.toList());
        List<Position> adventurerPositions = new ArrayList<>();
        adventurerLines.stream()
                .map(LineUtils::createAdventurerFromLine)
                .forEach(opt -> opt.ifPresent(
                        adventurer -> {
                            if (isAdventurerNotOnMontains(adventurer, treasureMap) &&
                                    !adventurerPositions.contains(adventurer.getPosition())) {
                                adventurerList.add(adventurer);
                                adventurerPositions.add(adventurer.getPosition());
                            }
                        }
                ));
        lines.removeIf(adventurerLines::contains);
        return adventurerList;
    }

    /**
     * Crée un aventurier à partir d'une ligne
     *
     * @param line la ligne
     * @return l'aventurier ou null
     */
    public static Optional<Adventurer> createAdventurerFromLine(String line) {
        String name = getAdventurerNameFromLine(line);
        Optional<Position> position = getAdventurerPositionFromLine(line);
        Optional<CardinalDirection> direction = getCardinalDirectionFromLine(line);
        List<Movement> movementList = getMovementsFromLine(line);

        if (position.isPresent() && direction.isPresent()) {
            return Optional.of(new Adventurer(name, position.get(), direction.get(), movementList));
        }
        return Optional.empty();
    }

    /**
     * Filtre et formate les lignes d'un fichier
     *
     * @param lines les lignes d'un fichier
     * @return les lignes valides et formattées
     */
    public static List<String> filterInvalidLines(List<String> lines) {
        lines.removeIf(line -> !isAValidLline(line) || isACommentLine(line));
        return lines.stream().map(s -> s.replaceAll("\\s", "")).collect(Collectors.toList());
    }
}
