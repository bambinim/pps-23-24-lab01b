package e1.board;

import e1.Pair;

public class BasicPiece implements Piece {

    private final Pair<Integer, Integer> position;

    public BasicPiece(final Pair<Integer, Integer> position) {
        if (position.getX() < 0 || position.getY() < 0) {
            throw new IllegalArgumentException("Position's coordinates can't be negative");
        }
        this.position = position;
    }

    @Override
    public Pair<Integer, Integer> position() {
        return this.position;
    }

}
