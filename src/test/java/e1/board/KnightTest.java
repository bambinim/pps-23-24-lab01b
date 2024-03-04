package e1.board;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import e1.Pair;

public class KnightTest extends AbstractPieceTest {

    @Override
    MovablePiece createPieceInstance(Pair<Integer, Integer> position) {
        return new Knight(position);
    }

    @Test
    void testValidMovesCheck() {
        MovablePiece piece = createPieceInstance(new Pair<Integer,Integer>(2, 2));
        assertAll(
            () -> assertTrue(piece.canBeMovedTo(new Pair<Integer,Integer>(0, 1))),
            () -> assertTrue(piece.canBeMovedTo(new Pair<Integer,Integer>(0, 3))),
            () -> assertTrue(piece.canBeMovedTo(new Pair<Integer,Integer>(4, 1))),
            () -> assertTrue(piece.canBeMovedTo(new Pair<Integer,Integer>(4, 3))),
            () -> assertTrue(piece.canBeMovedTo(new Pair<Integer,Integer>(1, 0))),
            () -> assertTrue(piece.canBeMovedTo(new Pair<Integer,Integer>(1, 4))),
            () -> assertTrue(piece.canBeMovedTo(new Pair<Integer,Integer>(3, 0))),
            () -> assertTrue(piece.canBeMovedTo(new Pair<Integer,Integer>(3, 4)))
        );
    }

    @Test
    void testInvalidMovesCheck() {
        MovablePiece piece = createPieceInstance(new Pair<Integer,Integer>(2, 2));
        assertAll(
            () -> assertFalse(piece.canBeMovedTo(new Pair<Integer,Integer>(0, 0))),
            () -> assertFalse(piece.canBeMovedTo(new Pair<Integer,Integer>(1, 1))),
            () -> assertFalse(piece.canBeMovedTo(new Pair<Integer,Integer>(2, 2))),
            () -> assertFalse(piece.canBeMovedTo(new Pair<Integer,Integer>(1, 3))),
            () -> assertFalse(piece.canBeMovedTo(new Pair<Integer,Integer>(2, 3)))
        );
    }

    @Test
    void moveToValidPosition() {
        MovablePiece piece = createPieceInstance(new Pair<Integer,Integer>(0, 0));
        Pair<Integer, Integer> newPosition = new Pair<Integer,Integer>(1, 2);
        piece.moveTo(newPosition);
        assertEquals(newPosition, piece.position());
    }

    @Test
    void moveToInvalidPosition() {
        MovablePiece piece = createPieceInstance(new Pair<Integer,Integer>(0, 0));
        assertThrows(IllegalArgumentException.class, () -> piece.moveTo(new Pair<Integer,Integer>(0, 1)));
    }

    @Test
    void moveToNegativeCoordinates() {
        MovablePiece piece = createPieceInstance(new Pair<Integer,Integer>(0, 0));
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> piece.moveTo(new Pair<Integer,Integer>(-1, -1))),
            () -> assertThrows(IllegalArgumentException.class, () -> piece.moveTo(new Pair<Integer,Integer>(-2, -1)))
        );
    }

}
