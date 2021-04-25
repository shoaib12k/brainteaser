package com.example.brainteaser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int locationOfCorrectAns;
    int totalQues = 0;
    int correctAns = 0;

    TextView resultTextView ;
    TextView timerTextView;
    TextView scoreTextView ;
    TextView questionTextView;

    Button goButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button restartButton;

    ArrayList<Integer> answers = new ArrayList<Integer>();


    public void generateQues(){


        Random random = new Random();
        int num1 = random.nextInt(101);
        int num2 = random.nextInt(101);

        locationOfCorrectAns = random.nextInt(4);

        questionTextView.setText(Integer.toString(num1) + " + " + Integer.toString(num2) );

        for(int i = 0; i < 4; i++){
            if(i==locationOfCorrectAns){
                answers.add(num1+num2);
            } else {
                int wrongAnswer = random.nextInt(200);
                while (wrongAnswer==num1+num2){
                    wrongAnswer = random.nextInt(200);
                }
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

        answers.clear();


    }



    public void chooseAnswer(View view){


        if (Integer.toString(locationOfCorrectAns).equals(view.getTag().toString())) {
            resultTextView.setText("Correct!");
            totalQues++;
            correctAns++;
        } else {
            resultTextView.setText("Wrong!");
            totalQues++;
        }
        scoreTextView.setText(Integer.toString(correctAns) + "/" + Integer.toString(totalQues));
        generateQues();

    }


    public void timerStart(){
        CountDownTimer countDownTimer = new CountDownTimer(60000,1000) {
            @Override
            public void onTick(long l) {
                String seconds = Integer.toString((int)l/1000);
                timerTextView.setText(seconds + "s");
            }

            @Override
            public void onFinish() {

                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
                restartButton.setVisibility(View.VISIBLE);
                resultTextView.setText("Done!");


            }
        }.start();
    }

    public void show(){
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        resultTextView.setVisibility(View.VISIBLE);
        questionTextView.setVisibility(View.VISIBLE);
    }

    public void start(View view){

        timerTextView = findViewById(R.id.timer);
        goButton.setVisibility(view.INVISIBLE);
        show();
        restart(findViewById(R.id.scoreTextView));
        restartButton = findViewById(R.id.restartButton);

    }


    public void restart(View view){
        timerTextView = findViewById(R.id.timer);
        correctAns = 0;
        totalQues = 0;
        generateQues();
        timerTextView.setText("60s");
        scoreTextView.setText(Integer.toString(correctAns) + "/" + Integer.toString(totalQues));
        timerStart();
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        restartButton.setVisibility(View.INVISIBLE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goButton = findViewById(R.id.goButton);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        questionTextView = findViewById(R.id.questionTextView);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);


        restartButton = findViewById(R.id.restartButton);
        restartButton.setVisibility(View.INVISIBLE);




    }
}