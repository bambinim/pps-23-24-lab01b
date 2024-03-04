package e1.board;

import java.util.Random;
import e1.Pair;

public class BoardFactory {

    public static Board randomPositionedPieces(int boardSize) {
        Random random = new Random();
        Pair<Integer,Integer> knightPosition = new Pair<>(random.nextInt(boardSize), random.nextInt(boardSize));
        Pair<Integer,Integer> pawnPosition = new Pair<>(random.nextInt(boardSize), random.nextInt(boardSize));
        while (pawnPosition.equals(knightPosition)) {
            pawnPosition = new Pair<>(random.nextInt(boardSize), random.nextInt(boardSize));
        }
        return new SimpleBoard(boardSize, new BasicPiece(pawnPosition), new Knight(knightPosition));
    }

}
