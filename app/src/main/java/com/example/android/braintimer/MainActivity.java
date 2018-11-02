package com.example.android.braintimer;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton,button0,button1,button2,button3,playAgain;

    TextView sumTextView,timerTextView,scoreTextView,resultTextView;

    ConstraintLayout relative;

    ArrayList<Integer> answers = new ArrayList<>();

    int locOfCorrectAns;

    int noOfQuestions=0;

    int score=0;

    public void start(View view)
    {
        startButton.setVisibility(View.INVISIBLE);

        relative.setVisibility(ConstraintLayout.VISIBLE);

    }

    public void generateQuestion()
    {
        Random rand= new Random();

        int A= rand.nextInt(21);

        int B= rand.nextInt(21);

        sumTextView.setText(Integer.toString(A)+"+"+Integer.toString(B));

        int inCorrectAns = 0;

        answers.clear();

        locOfCorrectAns=rand.nextInt(4);

        for(int i=0;i<4;i++)
        {
            if(i==locOfCorrectAns)

                answers.add(A+B);

            else {
                inCorrectAns=rand.nextInt(41);

                while (inCorrectAns == A + B)

                    inCorrectAns=rand.nextInt(41);

                answers.add(inCorrectAns);
            }

        }
        button0.setText(Integer.toString(answers.get(0)));

        button1.setText(Integer.toString(answers.get(1)));

        button2.setText(Integer.toString(answers.get(2)));

        button3.setText(Integer.toString(answers.get(3)));

    }

    public void chooseAnswer(View view)
    {
        if(view.getTag().toString().equals(Integer.toString(locOfCorrectAns)))
        {

            score++;

            resultTextView.setText("Correct!");

        }
        else
        {

            resultTextView.setText("Wrong!!");

        }
        noOfQuestions++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(noOfQuestions));
        generateQuestion();
    }

    public  void playAgain(View view)
    {
        score=0;

        noOfQuestions=0;

        timerTextView.setText("30s");

        resultTextView.setText("");

        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(noOfQuestions));

        playAgain.setVisibility(View.INVISIBLE);

        generateQuestion();

        Timer();

    }
    public void Timer()
    {
        new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText(Integer.toString((int)millisUntilFinished/1000)+"s");

            }

            @Override
            public void onFinish() {

                playAgain.setVisibility(View.VISIBLE);

                timerTextView.setText("0s");

                resultTextView.setText("Your Score = "+Integer.toString(score)+"/"+Integer.toString(noOfQuestions));

            }
        }.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         startButton = (Button)findViewById(R.id.startButton);

         sumTextView=(TextView)findViewById(R.id.sumTextView);

        resultTextView=(TextView)findViewById(R.id.label);

        timerTextView=(TextView)findViewById(R.id.timertextView);

        scoreTextView=(TextView)findViewById(R.id.scoretextView);

        relative=(ConstraintLayout)findViewById(R.id.relative);

        button0=(Button)findViewById(R.id.button0);

        button1=(Button)findViewById(R.id.button1);

        button2=(Button)findViewById(R.id.button2);

        button3=(Button)findViewById(R.id.button3);

        playAgain=(Button)findViewById(R.id.playAgain);

        playAgain.setVisibility(View.INVISIBLE);


        playAgain(playAgain);

    }
}
