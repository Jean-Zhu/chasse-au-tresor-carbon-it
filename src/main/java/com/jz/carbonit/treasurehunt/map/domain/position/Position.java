package com.jz.carbonit.treasurehunt.map.domain.position;

import com.jz.carbonit.treasurehunt.exception.ParsingLineException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Position {

    private final int abscissa;
    private final int ordinate;

    /**
     * Constructeur pour la dimension de la carte
     *
     * @param line ligne décrivant une carte
     * @throws ParsingLineException ligne incorrecte
     */
    public Position(String line) throws ParsingLineException {
        String[] splittedLine = line.split("-");
        if (splittedLine.length < 3 ||
                !splittedLine[1].matches("\\d+") ||
                !splittedLine[2].matches("\\d+")) {
            throw new ParsingLineException("Incorrect Line");
        }
        this.abscissa = Integer.parseInt(splittedLine[1]);
        this.ordinate = Integer.parseInt(splittedLine[2]);
    }

    public boolean isOutOfBounds(int mapLength, int mapWidth) {
        return (this.getAbscissa() < 0 || this.getAbscissa() >= mapLength || this.getOrdinate() < 0 || this.getOrdinate() >= mapWidth);
    }

    public boolean isOutOfBounds(Position limits) {
        return isOutOfBounds(limits.getAbscissa(), limits.getOrdinate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (abscissa != position.abscissa) return false;
        return ordinate == position.ordinate;
    }

    @Override
    public int hashCode() {
        int result = abscissa;
        result = 31 * result + ordinate;
        return result;
    }
}

