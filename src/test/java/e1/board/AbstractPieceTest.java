package e1.board;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import e1.Pair;

public abstract class AbstractPieceTest {

    abstract Piece createPieceInstance(Pair<Integer, Integer> position);

    @Test
    void testBasicPiece() {
        Pair<Integer, Integer> position = new Pair<Integer,Integer>(0, 0);
        Piece piece = this.createPieceInstance(position);
        assertEquals(position, piece.position());
    }

    @Test
    void testNegativeCoordinates() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> createPieceInstance(new Pair<Integer,Integer>(0, -1))),
            () -> assertThrows(IllegalArgumentException.class, () -> createPieceInstance(new Pair<Integer,Integer>(-1, 0))),
            () -> assertThrows(IllegalArgumentException.class, () -> createPieceInstance(new Pair<Integer,Integer>(-1, -1)))
        );
    }

}
