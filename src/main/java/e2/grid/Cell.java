package e2.grid;

import e2.Pair;
import java.util.List;

public interface Cell {

    Pair<Integer, Integer> position();

    boolean hasMine();

    boolean hasFlag();

    void toggleFlag();

    boolean isCovered();

    void unconver();

}
