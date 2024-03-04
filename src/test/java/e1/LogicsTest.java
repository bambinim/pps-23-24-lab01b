package e1;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.function.BiFunction;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LogicsTest {

    private final static int BOARD_SIZE = 5;
    Logics logics;

    Pair<Integer, Integer> calculateNumberOfTrueAndFalseResults(BiFunction<Integer, Integer, Boolean> method) {
        int numberOfTrueResults = 0;
        int numberOfFalseResults = 0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (method.apply(i, j)) {
                    numberOfTrueResults++;
                } else {
                    numberOfFalseResults++;
                }
            }
        }
        return new Pair<Integer,Integer>(numberOfTrueResults, numberOfFalseResults);
    }

    Pair<Integer, Integer> searchPosition(BiFunction<Integer, Integer, Boolean> method) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (method.apply(i, j)) {
                    return new Pair<Integer,Integer>(i, j);
                }
            }
        }
        return null;
    }

    List<Pair<Integer, Integer>> retrieveValidKnightMoves(Pair<Integer, Integer> knightPosition) {
        List<Pair<Integer, Integer>> validPositions = new ArrayList<>();
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                int x = i - knightPosition.getX();
		        int y = j - knightPosition.getY();
                if (x!=0 && y!=0 && Math.abs(x)+Math.abs(y)==3) {
                    validPositions.add(new Pair<Integer,Integer>(i, j));
                }
            }
        }
        return validPositions;
    }

    @BeforeEach
    void beforeEach() {
        this.logics = new LogicsImpl(BOARD_SIZE);
    }

    @Test
    void testOneKnightInBoard() {
        Pair<Integer, Integer> results = this.calculateNumberOfTrueAndFalseResults(this.logics::hasKnight);
        assertEquals(new Pair<Integer, Integer>(1, BOARD_SIZE * BOARD_SIZE - 1), results);
    }

    @Test
    void testOnePawnInBoard() {
        Pair<Integer, Integer> results = this.calculateNumberOfTrueAndFalseResults(this.logics::hasPawn);
        assertEquals(new Pair<Integer, Integer>(1, BOARD_SIZE * BOARD_SIZE - 1), results);
    }

    @Test
    void hitOutOfBoardRange() {
        assertAll(
            () -> assertThrows(IndexOutOfBoundsException.class, () -> this.logics.hit(-1, 0)),
            () -> assertThrows(IndexOutOfBoundsException.class, () -> this.logics.hit(0, -1)),
            () -> assertThrows(IndexOutOfBoundsException.class, () -> this.logics.hit(-1, -1)),
            () -> assertThrows(IndexOutOfBoundsException.class, () -> this.logics.hit(BOARD_SIZE, 0)),
            () -> assertThrows(IndexOutOfBoundsException.class, () -> this.logics.hit(0, BOARD_SIZE)),
            () -> assertThrows(IndexOutOfBoundsException.class, () -> this.logics.hit(BOARD_SIZE, BOARD_SIZE)),
            () -> assertThrows(IndexOutOfBoundsException.class, () -> this.logics.hit(BOARD_SIZE, -1)),
            () -> assertThrows(IndexOutOfBoundsException.class, () -> this.logics.hit(-1, BOARD_SIZE))
        );
    }

    @Test
    void hitEmptyPosition() {
        final Pair<Integer, Integer> knightPosition = searchPosition(this.logics::hasKnight);
        final Pair<Integer, Integer> pawnPosition = searchPosition(this.logics::hasPawn);
        var knightMoves = this.retrieveValidKnightMoves(knightPosition);
        var validEmptyHit = knightMoves.stream().filter((position) -> position != pawnPosition).findFirst().get();
        final boolean hitResult = this.logics.hit(validEmptyHit.getX(), validEmptyHit.getY());
        assertAll(
            () -> assertFalse(hitResult),
            () -> assertEquals(validEmptyHit, this.searchPosition(this.logics::hasKnight))
        );
    }

    @Test
    void hitInvalidKnightMove() {
        final Pair<Integer, Integer> knightPosition = searchPosition(this.logics::hasKnight);
        int newKnightX = knightPosition.getX() + 1;
        if (newKnightX >= BOARD_SIZE) {
            newKnightX = knightPosition.getX() - 1;
        }
        assertFalse(this.logics.hit(newKnightX, knightPosition.getY()));
    }

    @Test
    void hitPawn() {
        Pair<Integer, Integer> knightPosition = searchPosition(this.logics::hasKnight);
        final Pair<Integer, Integer> pawnPosition = searchPosition(this.logics::hasPawn);
        var random = new Random();
        while (true) {
            var validKnightMoves = this.retrieveValidKnightMoves(knightPosition);
            if (validKnightMoves.stream().filter((position) -> position.equals(pawnPosition)).findFirst().isPresent()) {
                break;
            }
            knightPosition = validKnightMoves.get(random.nextInt(validKnightMoves.size()));
            this.logics.hit(knightPosition.getX(), knightPosition.getY());
        }
        assertTrue(this.logics.hit(pawnPosition.getX(), pawnPosition.getY()));
    }
}
