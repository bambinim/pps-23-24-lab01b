package e1.board;

import e1.Pair;

public class SimpleBoard implements Board {

    private final int size;
    private final Piece pawn;
    private final MovablePiece knight;


    protected SimpleBoard(final int size, final Piece pawn, final MovablePiece knight) {
        if (pawn.position().equals(knight.position())) {
            throw new IllegalArgumentException("Pawn and knight can't be in same position");
        }
        this.size = size;
        this.pawn = pawn;
        this.knight = knight;
    }

    @Override
    public boolean isInsideMargins(final Pair<Integer, Integer> position) {
        return !(position.getX() < 0 || position.getY() < 0 || position.getX() >= this.size || position.getY() >= this.size);
    }

    @Override
    public void moveKnightTo(final Pair<Integer, Integer> newPosition) {
        if (!this.isInsideMargins(newPosition)) {
            throw new IndexOutOfBoundsException("Specified position is out of board margins");
        }
        if (!this.knight.canBeMovedTo(newPosition)) {
            throw new IllegalArgumentException("Knight can't be moved to that position");
        }
        this.knight.moveTo(newPosition);
    }

    @Override
    public Pair<Integer, Integer> pawnPosition() {
        return this.pawn.position();
    }

    @Override
    public Pair<Integer, Integer> knightPosition() {
        return this.knight.position();
    }

}
