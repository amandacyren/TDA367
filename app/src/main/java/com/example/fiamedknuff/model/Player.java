package com.example.fiamedknuff.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A class Player that creates a player
 *
 * Created by
 * @author Amanda Cyrén
 */

public class Player {

    private String name;
    private final Collection<Piece> pieces;

    public Player(String name, Color color) {
        this.name = name;
        pieces = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            pieces.add(new Piece(color));
        }
    }

    /**
     * Sets the name of the player to incoming parameter name
     * @param name takes in a String as name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the player
     * @return the players name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets a list of the players pieces
     * @return a list of pieces
     */
    public Collection<Piece> getPieces() {
        return pieces;
    }
}
