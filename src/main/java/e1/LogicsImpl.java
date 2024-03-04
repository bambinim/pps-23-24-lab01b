package e1;

import e1.board.*;

public class LogicsImpl implements Logics {
	
	private final Board board;
	 
    public LogicsImpl(int size){
    	this.board = BoardFactory.randomPositionedPieces(size);
    }
    
	@Override
	public boolean hit(int row, int col) {
		try {
			this.board.moveKnightTo(new Pair<Integer,Integer>(row, col));
		} catch (IllegalArgumentException e) {
			return false;
		}
		return this.board.pawnPosition().equals(this.board.knightPosition());
	}

	@Override
	public boolean hasKnight(int row, int col) {
		return this.board.knightPosition().getX() == row && this.board.knightPosition().getY() == col;
	}

	@Override
	public boolean hasPawn(int row, int col) {
		return this.board.pawnPosition().getX() == row && this.board.pawnPosition().getY() == col;
	}
}
