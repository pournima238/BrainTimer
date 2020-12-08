package com.example.braintimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<Integer>answer= new ArrayList<Integer>();
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    TextView timerTextView;
    TextView sumTextView;
    TextView resultTextView;
    TextView pointsTextView;
    int locationOfCorrectAnswer;
    int score;
    int numberOfQuestions;


    public void generateQuestion(){
        int incorrectAnswer;
        Random rand=new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);
        sumTextView.setText(Integer.toString(a)+"+"+Integer.toString(b));
        locationOfCorrectAnswer=rand.nextInt(4);
        for(int i=0; i<4; i++){
            if(i==locationOfCorrectAnswer){
                answer.add(a+b);
            }else{
                incorrectAnswer=rand.nextInt(41);
                while(incorrectAnswer==a+b){
                    incorrectAnswer=rand.nextInt(41);
                }
                answer.add(incorrectAnswer);
            }

        }
        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));


    }

    public void playAgain (View view){
        score=0;
        numberOfQuestions=0;
        timerTextView.setText("30s");
        playAgainButton.setVisibility(playAgainButton.INVISIBLE);
        generateQuestion();
        new CountDownTimer(30100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000));

            }

            @Override
            public void onFinish() {
                timerTextView.setText("0s");
                resultTextView.setText("Your Score"+Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
                playAgainButton.setVisibility(playAgainButton.VISIBLE);
            }
        }.start();


    }
    public void chooseAnswer(View view){
        if(view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))){
            score++;
            resultTextView.setText("Correct");
        }else{
            resultTextView.setText("Wrong");
        }
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score)+"/"+Integer.toString(numberOfQuestions));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sumTextView=findViewById(R.id.sumTextView);
        pointsTextView=findViewById(R.id.pointsTextView);
        resultTextView=findViewById(R.id.resultTextView);
        button0= findViewById(R.id.button0);
        button1= findViewById(R.id.button1);
        button2= findViewById(R.id.button2);
        button3= findViewById(R.id.button3);
        playAgainButton=findViewById(R.id.playAgainButton);
        timerTextView= findViewById(R.id.timerTextView);

        playAgain(findViewById(R.id.playAgainButton));
    }
}


