package e2.grid;

import e2.Pair;

public interface Cell {

    Pair<Integer, Integer> position();

    boolean hasMine();

    boolean hasFlag();

    void toggleFlag();

    boolean isCovered();

    void unconver();

}
