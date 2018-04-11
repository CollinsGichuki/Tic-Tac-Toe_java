package com.example.android.tictactoe;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity  implements OnClickListener{
    //2 dimensional array for the buttons
    private Button[][] playButtons =new Button[3][3];
    //The first player's turn,player X
    private Boolean player_1Turn = true;
    //Number of rounds played
    private int roundsPlayed;

    private int player_1Points;
    private int player_2Points;
    private int draw_1Points;
    //Displays the points of the respective players
    private TextView tvPlayer_1;
    private TextView tvPlayer_2;
    private TextView tvDraw_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        tvPlayer_1 = findViewById(R.id.player_1);
        tvPlayer_2 = findViewById(R.id.player_2);
        tvDraw_1 = findViewById(R.id.draw_1);
        //Nested loop for the 2d array whereby i is the first array and j is the second array
        for (int i = 0; i<3; i++){
            for(int j = 0; j<3; j++){
                String buttonId ="button_" + i + j;
                int resourceID = getResources().getIdentifier(buttonId,"id",getPackageName());
                playButtons[i][j] = findViewById(resourceID);
                playButtons[i][j].setOnClickListener(this);
            }
        }
        Button resetButton =findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(!((Button)v).getText().toString().equals("")){
            return;
        }
        if(player_1Turn){
            ((Button)v).setText("X");
        }else{
            ((Button)v).setText("O");
        }
        roundsPlayed++;
        if(checkWin()){
            if(player_1Turn){
                player_1Wins();
            }else{
                player_2Wins();
            }
        }else if(roundsPlayed == 9){
            draw();
        }else {
            player_1Turn =!player_1Turn;
        }

    }
    private void player_1Wins(){
        player_1Points++;
        Toast.makeText( this,"Player 1 wins!",Toast.LENGTH_SHORT).show();
        updatePoints();
        resetGameBoard();
    }
    private void player_2Wins(){
        player_2Points++;
        Toast.makeText( this,"Player 2 wins!",Toast.LENGTH_SHORT).show();
        updatePoints();
        resetGameBoard();
    }
    private void draw(){
        draw_1Points++;
        Toast.makeText(this,"Draw!",Toast.LENGTH_SHORT).show();
        updateDraws();
        resetGameBoard();
    }
    private void updatePoints(){
        tvPlayer_1.setText("Player 1: " + player_1Points);
        tvPlayer_2.setText("Player 2: " + player_2Points);
    }
    private void updateDraws(){
        tvDraw_1.setText("Draw: " + draw_1Points);
    }
    private void resetGameBoard(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                playButtons[i][j].setText("");
            }
        }
        roundsPlayed = 0;
        player_1Turn = true;
    }
    private void resetGame(){
        player_1Points = 0;
        player_2Points = 0;
        updatePoints();
        resetGameBoard();
    }


    private Boolean checkWin(){
        String[][]surfaceOverView = new String[3][3];
        for(int i =0; i<3; i++){
            for(int j=0; j<3; j++){
                surfaceOverView[i][j] = playButtons[i][j].getText().toString();
            }
        }
        for(int i=0; i <3; i++){
            if(surfaceOverView[i][0].equals(surfaceOverView[i][1])
                    &&surfaceOverView[i][0].equals(surfaceOverView[i][2])
                    &&!surfaceOverView[i][0].equals("")){
                return true;
            }
        }
        for(int i=0; i <3; i++){
            if(surfaceOverView[0][i].equals(surfaceOverView[1][i])
                    &&surfaceOverView[0][i].equals(surfaceOverView[2][i])
                    &&!surfaceOverView[0][i].equals("")){
                return true;
            }
        }
        if(surfaceOverView[0][0].equals(surfaceOverView[1][1])
                &&surfaceOverView[0][0].equals(surfaceOverView[2][2])
                &&!surfaceOverView[0][0].equals("")){
            return true;
        }

        if(surfaceOverView[0][2].equals(surfaceOverView[1][1])
                &&surfaceOverView[0][2].equals(surfaceOverView[2][0])
                &&!surfaceOverView[0][2].equals("")){
            return true;
        }
        return false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("rounds played", roundsPlayed);
        outState.putInt("Player 1 points",player_1Points);
        outState.putInt("Player 2 points",player_2Points);
        outState.putBoolean("Player 1 turn", player_1Turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        roundsPlayed = savedInstanceState.getInt("rounds played");
        player_1Points = savedInstanceState.getInt("Player 1 points");
        player_2Points = savedInstanceState.getInt("Player 2 points");
        player_1Turn = savedInstanceState.getBoolean("Player 1 turn");
    }
}










