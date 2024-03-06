package e2.grid;

import e2.Pair;

public interface Grid {

    /**
     * Unconver specified cell; returns true if cell contains a mine
     */
    boolean uncoverCell(Pair<Integer, Integer> cellPosition);

    boolean allEmptyCellUncovered();

    void toggleCellFlag(Pair<Integer, Integer> cellPosition);

    Cell getCell(Pair<Integer, Integer> cellPosition);

}
