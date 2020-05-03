//Name: Ritoshree Gupta
//CIS 436 - Two Dice Pig Game

package application.example.project1_twodicepig;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView dice1, dice2;
    TextView player1, player2, turnTotal, currentPlayer, playerWins;
    Random num;
    Button buttonRoll, buttonHold;
    int player1points = 0, player2points = 0;
    boolean curr_Turn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonRoll = (Button) findViewById(R.id.rollButton);
        buttonHold = (Button) findViewById(R.id.holdButton);

        dice1 = (ImageView) findViewById(R.id.dice1);
        dice2 = (ImageView) findViewById(R.id.dice2);

        player1 = (TextView) findViewById(R.id.player1);
        player2 = (TextView) findViewById(R.id.player2);

        turnTotal = (TextView) findViewById(R.id.turn_total);
        currentPlayer = (TextView) findViewById(R.id.currPlayer);

        playerWins = (TextView) findViewById(R.id.PlayerWins);
        num = new Random();


        // for roll dice button
        buttonRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int dice1val = num.nextInt(6) + 1;
                final int dice2val = num.nextInt(6) + 1;

                //set dice 1 and 2 with the random numbers
                setDice1(dice1val);
                setDice2(dice2val);

                int total = dice1val + dice2val;

                //switch turns
                if(curr_Turn == false){
                    currentPlayer.setText("Current Player: P1");
                }
                else{
                    currentPlayer.setText("Current Player: P2");
                }

                //turn total conditions
                if((dice1val == 1) || (dice2val == 1)){
                    turnTotal.setText("Turn Total: 0");
                }
                else{
                    turnTotal.setText("Turn Total: " + total);
                }


                //player1 game rules
                if(curr_Turn == false){ // player1

                    // if not equal to 1
                    if((dice1val != 1) && (dice2val != 1)){
                        player1points = player1points + total;
                        player1.setText("Player1: " + player1points);

                        // winning condition for player1
                        if((int) player1points >= 50){
                            playerWins.setText("Player 1 Wins! ");
                            buttonRoll.setEnabled(false);
                        }
                    }

                    //if one of the dice is equal to 1
                    if((dice1val == 1) || (dice2val == 1)){
                        curr_Turn = true;
                        currentPlayer.setText("Current Player: P1");
                    }

                    //if both dice equal to 1
                    if((dice1val == 1) && (dice2val == 1)){
                        player1points = 0;
                        player1.setText("Player1: " + player1points);
                        curr_Turn = true;
                    }

                    //if same face dices but not 1
                    if((dice1val == dice2val) && (dice1val != 1) && (dice2val != 1)){
                        buttonHold.setEnabled(false); // hold button disable
                    }
                    else{
                        buttonHold.setEnabled(true); // hold button active
                    }
                }

                //player2 game rules
                else if(curr_Turn == true){ //player2

                    //if dices are not equal to 1
                    if((dice1val != 1) && (dice2val != 1)){
                        player2points = player2points + total;
                        player2.setText("Player2: " + player2points);

                        //winning conditions
                        if((int) player2points >= 50){
                            playerWins.setText("Player 2 Wins! ");
                            buttonRoll.setEnabled(false);
                        }
                    }

                    // if one of the dice equal to 1
                    if((dice1val == 1) || (dice2val == 1)){
                        curr_Turn = false;
                        currentPlayer.setText("Current Player: P2");
                    }

                    //if both dices are 1
                    if((dice1val == 1) && (dice2val == 1)){
                        player2points = player2points + total;
                        player2points = 0;
                        player2.setText("Player1: " + player2points);
                        curr_Turn = false;
                    }


                    // if both dices are same faces but not equal to 1
                    if((dice1val == dice2val) && (dice1val != 1) && (dice2val != 1)){
                        buttonHold.setEnabled(false); // hold button disable
                    }
                    else{
                        buttonHold.setEnabled(true); // hold button active
                    }
                }


                // dice rotate
                Animation rotate = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
                dice1.startAnimation(rotate);
                dice2.startAnimation(rotate);


                //for Hold button
                buttonHold.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(dice1val != dice2val){
                            if(curr_Turn == false){
                                curr_Turn = true;
                            }
                            else{
                                curr_Turn = false;
                            }
                        }
                    }
                });
            }
        });



    }

    // set dice images
    public void setDice1(int n){
        if(n == 1){
            dice1.setImageResource(R.drawable.dice1);
        }
        else if(n == 2){
            dice1.setImageResource(R.drawable.dice2);
        }
        else if(n == 3){
            dice1.setImageResource(R.drawable.dice3);
        }
        else if(n == 4){
            dice1.setImageResource(R.drawable.dice4);
        }
        else if(n == 5){
            dice1.setImageResource(R.drawable.dice5);
        }
        else if(n == 6){
            dice1.setImageResource(R.drawable.dice6);
        }

    }

    public void setDice2(int n){
        if(n == 1){
            dice2.setImageResource(R.drawable.dice1);
        }
        else if(n == 2){
            dice2.setImageResource(R.drawable.dice2);
        }
        else if(n == 3){
            dice2.setImageResource(R.drawable.dice3);
        }
        else if(n == 4){
            dice2.setImageResource(R.drawable.dice4);
        }
        else if(n == 5){
            dice2.setImageResource(R.drawable.dice5);
        }
        else if(n == 6){
            dice2.setImageResource(R.drawable.dice6);
        }
    }
}
