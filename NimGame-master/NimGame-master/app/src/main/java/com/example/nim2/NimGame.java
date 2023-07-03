package com.example.nim2;
public class NimGame {
    private int currentPlayer;
    private int[] piles;

    public NimGame() {
        currentPlayer = 1; // Player 1 starts the game
        piles = new int[]{5, 4, 3}; // Initial pile sizes
    }
    public boolean isMoveValid(int pileIndex, int numMatches) {
        if (pileIndex < 0 || pileIndex >= piles.length || numMatches <= 0 || numMatches > piles[pileIndex]) {
            return false; // Invalid move
        }
        return true; // Valid move
    }

    public boolean isMoveValid(int[] move) {
        int pileIndex = move[0];
        int numMatches = move[1];

        if (pileIndex < 0 || pileIndex >= piles.length || numMatches <= 0 || numMatches > piles[pileIndex]) {
            return false; // Invalid move
        }

        return true; // Valid move
    }

    public void makeMove(int[] move) {
        int pileIndex = move[0];
        int numMatches = move[1];

        piles[pileIndex] -= numMatches;
    }

    public boolean isGameOver() {
        for (int pile : piles) {
            if (pile > 0) {
                return false; // Game is not over
            }
        }
        return true; // All piles are empty, game over
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public String getBoardState() {
        StringBuilder boardState = new StringBuilder();
        for (int i = 0; i < piles.length; i++) {
            boardState.append("Pile ").append(i + 1).append(": ");
            for (int j = 0; j < piles[i]; j++) {
                boardState.append("| ");
            }
            boardState.append("\n");
        }
        return boardState.toString();
    }
}
