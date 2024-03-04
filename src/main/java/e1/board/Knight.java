package e1.board;

import e1.Pair;

public class Knight implements MovablePiece {

    private Piece piece;

    public Knight(final Pair<Integer, Integer> position) {
        this.piece = new BasicPiece(position);
    }

    @Override
    public Pair<Integer, Integer> position() {
        return this.piece.position();
    }

    @Override
    public boolean canBeMovedTo(final Pair<Integer, Integer> newPosition) {
        int x = newPosition.getX() - this.piece.position().getX();
		int y = newPosition.getY() - this.piece.position().getY();
		return x!=0 && y!=0 && Math.abs(x)+Math.abs(y)==3;
    }

    @Override
    public void moveTo(final Pair<Integer, Integer> newPosition) {
        if (newPosition.getX() < 0 || newPosition.getY() < 0) {
            throw new IllegalArgumentException("Coordinates can't be negative");
        }
        if (!this.canBeMovedTo(newPosition)) {
            throw new IllegalArgumentException("Piece can't be moved to specified position");
        }
        this.piece = new BasicPiece(newPosition);
    }

}
