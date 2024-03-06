package e2;

import e2.grid.Cell;

public interface Logics {

    /**
     * Unconver cell and returns true if cell contains a mine
     */
    boolean unconverCell(Pair<Integer, Integer> cellPosition);

    boolean toggleFlag(Pair<Integer, Integer> cellPosition);
    
    boolean hasWon();

    Cell getCell(Pair<Integer, Integer> cellPosition);

}
