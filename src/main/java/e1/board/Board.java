package e1.board;

import e1.Pair;

public interface Board {

    boolean isInsideMargins(final Pair<Integer, Integer> position);

    void moveKnightTo(final Pair<Integer, Integer> newPosition);

    Pair<Integer, Integer> pawnPosition();

    Pair<Integer, Integer> knightPosition();

}
