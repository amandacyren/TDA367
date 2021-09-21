package com.example.fiamedknuff;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import com.example.fiamedknuff.model.Board;
import com.example.fiamedknuff.model.Piece;
import com.example.fiamedknuff.model.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for testing Board.java
 */

public class BoardUnitTest {

    //private static Board board4p;
    Board board4p;
    ArrayList<Piece> pieces;

    @Before
    public void createBoard() {
        int playerCount = 4;
        pieces = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            pieces.add(new Piece(Piece.Color.RED));
        }
        for (int i = 0; i < playerCount; i++) {
            pieces.add(new Piece(Piece.Color.GREEN));
        }
        for (int i = 0; i < playerCount; i++) {
            pieces.add(new Piece(Piece.Color.BLUE));
        }
        for (int i = 0; i < playerCount; i++) {
            pieces.add(new Piece(Piece.Color.YELLOW));
        }
        board4p = new Board(playerCount, pieces);
    }

    @Test
    public void boardPositionsInitializesCorrectly() {
        List<Position> positions = board4p.getPositions();
        assertEquals(57+16, positions.size());
        assertEquals(-16, positions.get(0).getPos());
        assertEquals(56, positions.get(positions.size()-1).getPos());
    }

    @Test
    public void boardHashMapInitializesCorrectly() {
        var hm = board4p.getPiecePositionHashMap();
        int i = -16;
        for (Piece piece : pieces) {
            assertEquals(hm.get(piece).getPos(), i++);
        }
    }


}
