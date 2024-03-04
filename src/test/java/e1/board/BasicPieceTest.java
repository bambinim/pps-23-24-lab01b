package e1.board;

import e1.Pair;

public class BasicPieceTest extends AbstractPieceTest {

    @Override
    Piece createPieceInstance(Pair<Integer, Integer> position) {
        return new BasicPiece(position);
    }
}
