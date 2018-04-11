package com.example.android.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity3 extends AppCompatActivity implements OnClickListener {
    private Button[][] playButton = new Button[5][5];

    private Boolean playerXTurn = true;
    //Checks the rounds played
    private int playedRounds;

    private int playerXPoints;
    private int playerYPoints;
    private int draws;

    private TextView playerOneTextView;
    private TextView playerTwoTextView;
    private TextView drawTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        playerOneTextView = findViewById(R.id.player_1);
        playerTwoTextView = findViewById(R.id.player_2);
        drawTextView = findViewById(R.id.draw);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                String buttonId = "button_" + i + j;
                int resId = getResources().getIdentifier(buttonId, "id", getPackageName());
                playButton[i][j] = findViewById(resId);
                playButton[i][j].setOnClickListener(this);
            }
        }
        Button resetButton = findViewById(R.id.reset2_button);
        resetButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
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
                playField[i][j] = playButton[i][j].getText().toString();
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
        playerOneTextView.setText("Player 1: " + playerXPoints);
        playerTwoTextView.setText("Player 2: " + playerYPoints);
    }
    private void updateDrawTv(){
        drawTextView.setText("Draws: " + draws);
    }
    private void resetBoard(){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                playButton[i][j].setText("");
            }
        }

        playedRounds = 0;
        playerXTurn = true;
    }
    private void resetGame(){
        playerXPoints = 0;
        playerYPoints = 0;
        updatePointsTv();
        draws = 0 ;
        updateDrawTv();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("playedRounds", playedRounds);
        outState.putInt("playerXPoints", playerXPoints);
        outState.putInt("playerYPoints", playerYPoints);
        outState.putInt("draws", draws);
        outState.putBoolean("playerXTurn", playerXTurn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        playedRounds = savedInstanceState.getInt("playedRounds");
        playerXPoints = savedInstanceState.getInt("playerXPoints");
        playerYPoints = savedInstanceState.getInt("playerYPoints");
        draws = savedInstanceState.getInt("draws");
        playerXTurn = savedInstanceState.getBoolean("playerXTurn");

    }
}