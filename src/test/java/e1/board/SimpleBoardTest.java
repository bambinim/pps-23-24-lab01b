package e1.board;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import e1.Pair;

public class SimpleBoardTest {

    private static final Pair<Integer, Integer> PAWN_START_POSITION = new Pair<Integer, Integer>(0, 0);
    private static final Pair<Integer, Integer> KNIGHT_START_POSITION = new Pair<Integer, Integer>(0, 1);
    private static final int BOARD_SIZE = 5;
    private Board board;



    @BeforeEach
    void beforeEach() {
        this.board = new SimpleBoard(
            BOARD_SIZE,
            new BasicPiece(PAWN_START_POSITION),
            new Knight(KNIGHT_START_POSITION)
        );
    }

    @Test
    void piecesPositions() {
        assertAll(
            () -> assertEquals(PAWN_START_POSITION, this.board.pawnPosition()),
            () -> assertEquals(KNIGHT_START_POSITION, this.board.knightPosition())
        );
    }

    @Test
    void testMarginsCheck() {
        assertAll(
            () -> assertTrue(this.board.isInsideMargins(new Pair<Integer,Integer>(0, 0))),
            () -> assertTrue(this.board.isInsideMargins(new Pair<Integer,Integer>(BOARD_SIZE - 1, BOARD_SIZE - 1))),
            () -> assertFalse(this.board.isInsideMargins(new Pair<Integer,Integer>(BOARD_SIZE, 0))),
            () -> assertFalse(this.board.isInsideMargins(new Pair<Integer,Integer>(-1, 0))),
            () -> assertFalse(this.board.isInsideMargins(new Pair<Integer,Integer>(0, BOARD_SIZE))),
            () -> assertFalse(this.board.isInsideMargins(new Pair<Integer,Integer>(-1, BOARD_SIZE)))
        );
    }

    @Test
    void testKnightMove() {
        Pair<Integer, Integer> newKnightPosition = new Pair<Integer,Integer>(KNIGHT_START_POSITION.getX() + 1, KNIGHT_START_POSITION.getY() + 2);
        this.board.moveKnightTo(newKnightPosition);
        assertEquals(newKnightPosition, this.board.knightPosition());
    }

    @Test
    void testInvalidKnightMove() {
        Pair<Integer, Integer> newKnightPosition = new Pair<Integer,Integer>(KNIGHT_START_POSITION.getX() + 1, KNIGHT_START_POSITION.getY() + 1);
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> this.board.moveKnightTo(newKnightPosition)),
            () -> assertEquals(KNIGHT_START_POSITION, this.board.knightPosition())
        );
    }

    @Test
    void moveKnightOutOfMargins() {
        assertThrows(IndexOutOfBoundsException.class, () -> this.board.moveKnightTo(new Pair<Integer,Integer>(-1, -1)));
    }

    @Test
    void createBoardWithBothPiecesInSamePosition() {
        assertThrows(IllegalArgumentException.class,() -> new SimpleBoard(
            BOARD_SIZE, new BasicPiece(KNIGHT_START_POSITION), new Knight(KNIGHT_START_POSITION)
        ));
    }

}
