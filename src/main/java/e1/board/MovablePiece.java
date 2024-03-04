package e1.board;

import e1.Pair;

public interface MovablePiece extends Piece {

    boolean canBeMovedTo(final Pair<Integer, Integer> newPosition);

    void moveTo(final Pair<Integer, Integer> newPosition);
}
