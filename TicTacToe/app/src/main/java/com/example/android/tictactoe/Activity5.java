package com.example.android.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity5 extends AppCompatActivity implements View.OnClickListener{
    private Button[][] playButton5 = new Button[5][5];

    private Boolean playerXTurn = true;
    //Checks the rounds played
    private int playedRounds;

    private int playerXPoints;
    private int playerYPoints;
    private int draws;

    private TextView playerOneTextView5;
    private TextView playerTwoTextView5;
    private TextView drawTextView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        playerOneTextView5 = findViewById(R.id.playerOne_5);
        playerTwoTextView5 = findViewById(R.id.playerTwo_5);
        drawTextView5 = findViewById(R.id.draw_5);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                String buttonId = "button_" + i + j;
                int resId = getResources().getIdentifier(buttonId, "id", getPackageName());
                playButton5[i][j] = findViewById(resId);
                playButton5[i][j].setOnClickListener(this);
            }
        }
        Button resetButton = findViewById(R.id.resetButton_5);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        //First player plays with x
        if(playerXTurn){
            ((Button)v).setText("x");
        }
        else{
            ((Button)v).setText("o");
             /*
            Single player implementation should go in here
            Algorithm still under construction.
             */
        }
        playedRounds++;

        if(checkWin()){
            if(playerXTurn){
                playerXWins();
            }else{
                playerYWins();
            }
        }else if(playedRounds == 25){
            draw();
        }else{
            playerXTurn = !playerXTurn;
        }
    }
    private Boolean checkWin() {
        String[][] playField = new String[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                playField[i][j] = playButton5[i][j].getText().toString();
            }
        }//Check winner across the rows
        for (int i = 0; i < 5; i++) {
            if (playField[i][0].equals(playField[i][1])
                    && playField[i][0].equals(playField[i][2])
                    && playField[i][0].equals(playField[i][3])
                    && playField[i][0].equals(playField[i][4])
                    && !playField[i][0].equals("")) {
                return true;
            }
        }//Checks winner across the columns
        for (int i = 0; i < 5; i++) {
            if (playField[0][i].equals(playField[1][i])
                    && playField[0][i].equals(playField[2][i])
                    && playField[0][i].equals(playField[3][i])
                    && playField[0][i].equals(playField[4][i])
                    && !playField[0][i].equals("")) {
                return true;
            }
        }//Checks winner across the left diagonal
        if (playField[0][0].equals(playField[1][1])
                && playField[0][1].equals(playField[2][2])
                && playField[0][1].equals(playField[3][3])
                && playField[0][1].equals(playField[4][4])
                && !playField[0][1].equals("")) {
            return true;
        }//Checks winner across the right diagonal
        if (playField[0][4].equals(playField[1][3])
                && playField[0][4].equals(playField[2][2])
                && playField[0][4].equals(playField[3][1])
                && playField[0][4].equals(playField[4][0])
                && !playField[0][4].equals("")) {
            return true;
        }
        return false;
    }
    private void playerXWins(){
        playerXPoints ++;
        Toast.makeText(this,"Player 1 wins!", Toast.LENGTH_SHORT).show();
        updatePointsTv();
        resetBoard();
    }

    private void playerYWins(){
        playerYPoints++;
        Toast.makeText(this,"Player 2 wins!", Toast.LENGTH_SHORT).show();
        updatePointsTv();
        resetBoard();}

    private void draw(){
        draws++;
        Toast.makeText(this,"Draw!",Toast.LENGTH_SHORT).show();
        updateDrawTv();
        resetBoard();
    }
    private void updatePointsTv(){
        playerOneTextView5.setText("Player 1: " + playerXPoints);
        playerTwoTextView5.setText("Player 2: " + playerYPoints);
    }
    private void updateDrawTv(){
        drawTextView5.setText("Draws: " + draws);
    }
    private void resetBoard(){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                playButton5[i][j].setText("");
            }
        }

        playedRounds = 0;
        playerXTurn = true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("rounds played", playedRounds);
        outState.putInt("Player 1 points",playerXPoints);
        outState.putInt("Player 2 points",playerYPoints);
        outState.putInt("draws", draws);
        outState.putBoolean("Player 1 turn", playerXTurn);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        playedRounds = savedInstanceState.getInt("rounds played");
        playerXPoints = savedInstanceState.getInt("Player 1 points");
        playerYPoints = savedInstanceState.getInt("Player 2 points");
        draws = savedInstanceState.getInt("draw");
        playerXTurn = savedInstanceState.getBoolean("Player 1 turn");
    }
}
