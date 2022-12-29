package com.millenialzdev.mytictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btnReset;
    private TextView tvO, tvX, tvTurn;

    int Score_O = 0;
    int Score_X = 0;

    int PLAYER_O = 0;
    int PLAYER_X = 1;

    int activePlayer = PLAYER_O;

    int[] papan = {2,2,2,2,2,2,2,2,2};

    boolean isGameActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btnReset = findViewById(R.id.btnReset);

        tvO = findViewById(R.id.tvO);
        tvO.setText("O : " + String.valueOf(Score_O));

        tvX = findViewById(R.id.tvX);
        tvX.setText("X : " + String.valueOf(Score_X));

        tvTurn = findViewById(R.id.tvTurn);
        tvTurn.setText("O Turn");

        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });


    }

    private void resetGame() {
        activePlayer = PLAYER_O;
        tvTurn.setText("O Turn");

        papan = new int[]{2,2,2,2,2,2,2,2,2};
        btn0.setText("");
        btn1.setText("");
        btn2.setText("");
        btn3.setText("");
        btn4.setText("");
        btn5.setText("");
        btn6.setText("");
        btn7.setText("");
        btn8.setText("");

        btn0.setBackground(getDrawable(android.R.color.darker_gray));
        btn1.setBackground(getDrawable(android.R.color.darker_gray));
        btn2.setBackground(getDrawable(android.R.color.darker_gray));
        btn3.setBackground(getDrawable(android.R.color.darker_gray));
        btn4.setBackground(getDrawable(android.R.color.darker_gray));
        btn5.setBackground(getDrawable(android.R.color.darker_gray));
        btn6.setBackground(getDrawable(android.R.color.darker_gray));
        btn7.setBackground(getDrawable(android.R.color.darker_gray));
        btn8.setBackground(getDrawable(android.R.color.darker_gray));

        isGameActive = true;
    }

    @Override
    public void onClick(View v) {
        //logic

        if (!isGameActive) {
            return;
        }

        Button btnTekan = findViewById(v.getId());
        int tagTekan = Integer.parseInt(v.getTag().toString());

        if (papan[tagTekan] != 2){
            return;
        }

        papan[tagTekan] = activePlayer;

        if (activePlayer == PLAYER_O){
            btnTekan.setText("0");
            btnTekan.setBackground(getDrawable(android.R.color.holo_blue_bright));
            activePlayer = PLAYER_X;
            tvTurn.setText("X Turn");
        }else{
            btnTekan.setText("X");
            btnTekan.setBackground(getDrawable(android.R.color.holo_orange_light));
            activePlayer = PLAYER_O;
            tvTurn.setText("O Turn");
        }

        checkForWin();

    }

    private void checkForWin() {
        int[][] winner = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

        for (int i = 0 ; i < 8 ; i++){
            int val0 = winner[i][0];
            int val1 = winner[i][1];
            int val2 = winner[i][2];

            if (papan[val0] == papan[val1] && papan[val1] == papan[val2]){
                if (papan[val0] != 2){
                    //pemenang

                    isGameActive = false;

                    if (papan[val0] == PLAYER_O){
                        showDialog("0 is Winner");
                        Score_O++;
                        tvO.setText("O : " + String.valueOf(Score_O));
                    }else{
                        showDialog("X is Winner");
                        Score_X++;
                        tvX.setText("X : " + String.valueOf(Score_X));
                    }
                }
            }
        }

        //menentukan draw
        int draw = 0;
        for (int i = 0; i < 9; i++){
            if (papan[i] != 2){
                draw++;
            }
        }
        if (draw==9){
            showDialog("Match is Draw");
        }

    }

    private void showDialog(String Winner) {
        new AlertDialog.Builder(this)
                .setTitle(Winner)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
    }
}