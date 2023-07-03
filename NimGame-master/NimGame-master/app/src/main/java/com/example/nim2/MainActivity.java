package com.example.nim2;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.nim2.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView textViewPlayerTurn;
    private TextView textViewBoard;
    private EditText editTextPile;
    private EditText editTextMatches;
    private Button buttonPlay;

    private NimGame nimGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayerTurn = findViewById(R.id.textViewPlayerTurn);
        textViewBoard = findViewById(R.id.textViewBoard);
        editTextPile = findViewById(R.id.editTextPile);
        editTextMatches = findViewById(R.id.editTextMatches);
        buttonPlay = findViewById(R.id.buttonPlay);

        nimGame = new NimGame();

        updateUI();

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get user input from EditText fields
                int pileIndex = Integer.parseInt(editTextPile.getText().toString());
                int numMatches = Integer.parseInt(editTextMatches.getText().toString());

                // Make a move in the Nim game
                int[] move = {pileIndex - 1, numMatches};
                boolean validMove = nimGame.isMoveValid(move);

                if (validMove) {
                    // Make the move in the Nim game
                    nimGame.makeMove(move);

                    // Switch player turn and update UI
                    nimGame.switchPlayer();
                    updateUI();

                    // Check if the game is over
                    if (nimGame.isGameOver()) {
                        // Display the winner
                        int winner = nimGame.getCurrentPlayer();
                        textViewPlayerTurn.setText(getString(R.string.winner_text, winner));
                        buttonPlay.setEnabled(false); // Disable play button
                    }
                } else {
                    // Display an error message for invalid move
                    textViewPlayerTurn.setText(R.string.invalid_move_text);
                }
            }
        });
    }

    private void updateUI() {
        int currentPlayer = nimGame.getCurrentPlayer();
        textViewPlayerTurn.setText(getString(R.string.player_turn_text, currentPlayer));
        textViewBoard.setText(nimGame.getBoardState());
        editTextPile.setText("");
        editTextMatches.setText("");
    }
}