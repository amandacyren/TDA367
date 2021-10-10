package com.example.fiamedknuff.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.fiamedknuff.NotImplementedException;
import com.example.fiamedknuff.model.Color;
import com.example.fiamedknuff.model.Game;
import com.example.fiamedknuff.model.GameFactory;
import com.example.fiamedknuff.model.Piece;
import com.example.fiamedknuff.model.Player;
import com.example.fiamedknuff.model.Position;

import java.util.Collection;
import java.util.List;

/**
 * A class responsible for communicating the model to whatever view is interested.
 *
 * Created by
 * @author Emma Stålberg
 */

public class GameViewModel extends ViewModel {

    private Game game;
    private int playerCount;
    private Player currentPlayer;
    private int diceValue;
    private Collection<Piece> movablePieces;
    private Piece selectedPiece;
    private List<String> playerNames;
    private Color[] colors;

    public void init(List<String> playerNames, Color[] colors, boolean[] selectedCPU) throws NotImplementedException {
        this.playerNames = playerNames;
        game = GameFactory.createNewGame(playerNames, colors, selectedCPU);
    }

    /**
     * Moves the clicked piece.
     * @param clickedPiece is the clicked piece
     * @return returns true if the piece if moved, and false if it can´t be moved.
     */

    public boolean move(Piece clickedPiece) {
        // if the rolled dice is already used, we can´t move any piece before another roll has
        // been made
        if (!game.getDice().getIsUsed()) {
            List<Piece> movablePieces = game.getMovablePieces(game.getCurrentPlayer());
            //checks if the clicked piece is movable
            if(movablePieces.contains(clickedPiece)) {
                movePiece(clickedPiece);
                return true;
            }
        }
        return false;
    }

    /**
     * Moves the piece.
     * @param piece is the piece that should be moved
     */
    private void movePiece(Piece piece) {
        try {
            game.move(piece);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes the piece from the model if it is finished.
     * @param piece is the piece that might be finished
     * @return true if the piece is finished, false if it is not
     */
    public boolean removePieceIfFinished(Piece piece) {
        return game.removePieceIfFinished(piece);
    }

    /**
     * Removes the current player from the model if it is finished.
     * @return true if the player is finished, and false otherwise.
     */
    public boolean removePlayerIfFinished() {
        return game.removePlayerIfFinished();
    }

    public LiveData<String> getPlayerName(int index) {
        MutableLiveData<String> data = new MutableLiveData<>();
        data.setValue(playerNames.get(index));
        return data;
    }

    /**
     * If the dice is ready to be rolled (i.e. is used), the dice is rolled.
     * @return the value of the rolled dice (if it´s ready to be rolled), or -1 if
     * it´s not ready to be rolled.
     */
    public int rollDice() {
        // if the dice is used, you may roll again
        if (game.getDice().getIsUsed()) {
            game.rollDice();
            return game.getDice().getRolledValue();
        }
        return -1;
    }

    /**
     * Returns all player pieces from game.
     * @return all player pieces
     */

    public List<Piece> getPieces() {
        return game.getAllPlayerPieces();
    }

    /**
     * Returns the positions from the board.
     * @return the positions from the board.
     */

    public List<Position> getPositions() {
        return game.getPositions();
    }
  
    /**
     * Returns the dicevalue.
     * @return the dicevalue.
     */
    public int getDiceValue() {
        return game.getDice().getRolledValue();
    }

    /**
     * Selects the next player in the model.
     */
    public void selectNextPlayer() {
        game.selectNextPlayer();
    }

    /**
     * Sets the dice to used.
     */
    public void diceIsUsed() {
        game.getDice().setIsUsed(true);
    }

    /**
     * Returns number of active players in the game.
     * @return number of active players.
     */
    public int getPlayerCount() {
        return game.getPlayerCount();
    }

    /**
     * Returns the position of the piece given as a parameter.
     * @param piece is the piece from which you want to know the position
     * @return the position of the given piece
     */
    public Position getPosition(Piece piece) {
        return game.getPosition(piece);
    }

}
