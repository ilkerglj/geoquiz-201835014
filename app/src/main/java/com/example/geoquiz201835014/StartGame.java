package com.example.geoquiz201835014;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class StartGame extends AppCompatActivity {

    TextView tvTimer;

    TextView tvResult;

    ImageView ivShowImage;

    HashMap<String, Integer> map = new HashMap<>();

    ArrayList<String> techList = new ArrayList<>();

    int index;

    Button btn1, btn2, btn3, btn4;

    TextView tvPoints;

    int points;

    CountDownTimer countDownTimer;

    long millisUntilFinished;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_game);

        tvTimer = findViewById(R.id.tvTimer);

        tvResult = findViewById(R.id.tvResult);
        ivShowImage = findViewById(R.id.ivShowImage);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        tvPoints = findViewById(R.id.tvPoints);

        index = 0;

        techList.add("China");
        techList.add("France");
        techList.add("Hungary");
        techList.add("India");
        techList.add("Italy");
        techList.add("Japan");
        techList.add("Laos");
        techList.add("Slovakia");
        techList.add("Spain");
        techList.add("Turkey");
        techList.add("UK");
        techList.add("Ukraine");
        techList.add("USA");
        techList.add("China");
        techList.add("Laos");
        techList.add("Italy");

        map.put(techList.get(0), R.drawable.china);
        map.put(techList.get(1), R.drawable.france);
        map.put(techList.get(2), R.drawable.hungary);
        map.put(techList.get(3), R.drawable.india);
        map.put(techList.get(4), R.drawable.italy);
        map.put(techList.get(5), R.drawable.japan);
        map.put(techList.get(6), R.drawable.laos);
        map.put(techList.get(7), R.drawable.slovakia);
        map.put(techList.get(8), R.drawable.spain);
        map.put(techList.get(9), R.drawable.turkey);
        map.put(techList.get(10), R.drawable.uk);
        map.put(techList.get(11), R.drawable.ukraine);
        map.put(techList.get(12), R.drawable.usa);
        map.put(techList.get(13), R.drawable.china);
        map.put(techList.get(14), R.drawable.laos);
        map.put(techList.get(15), R.drawable.italy);

        Collections.shuffle(techList);

        millisUntilFinished = 10000;

        points = 0;

        startGame();
    }

    private void startGame() {

        millisUntilFinished = 10000;

        tvTimer.setText("" + (millisUntilFinished / 1000) + "s");

        tvPoints.setText(points + " / " + techList.size());


        generateQuestions(index);

        countDownTimer = new CountDownTimer(millisUntilFinished, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {

                tvTimer.setText("" + (millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {

                index++;

                if (index > techList.size() - 1){

                    ivShowImage.setVisibility(View.GONE);
                    btn1.setVisibility(View.GONE);
                    btn2.setVisibility(View.GONE);
                    btn3.setVisibility(View.GONE);
                    btn4.setVisibility(View.GONE);

                    Intent intent = new Intent(StartGame.this, GameOver.class);
                    intent.putExtra("points", points);
                    startActivity(intent);

                    finish();
                } else {

                    countDownTimer = null;
                    startGame();
                }
            }
        }.start();
    }

    private void generateQuestions(int index) {

        ArrayList<String> techListTemp = (ArrayList<String>) techList.clone();

        String correctAnswer = techList.get(index);

        techListTemp.remove(correctAnswer);
        Collections.shuffle(techListTemp);

        ArrayList<String> newList = new ArrayList<>();

        newList.add(techListTemp.get(0));
        newList.add(techListTemp.get(1));
        newList.add(techListTemp.get(2));

        newList.add(correctAnswer);

        Collections.shuffle(newList);

        btn1.setText(newList.get(0));
        btn2.setText(newList.get(1));
        btn3.setText(newList.get(2));
        btn4.setText(newList.get(3));

        ivShowImage.setImageResource(map.get(techList.get(index)));
    }

    public void nextQuestion(View view) {

        btn1.setBackgroundColor(Color.parseColor("#2196f3"));
        btn2.setBackgroundColor(Color.parseColor("#2196f3"));
        btn3.setBackgroundColor(Color.parseColor("#2196f3"));
        btn4.setBackgroundColor(Color.parseColor("#2196f3"));

        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);

        countDownTimer.cancel();
        index++;

        if (index > techList.size() - 1){

            ivShowImage.setVisibility(View.GONE);
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
            btn3.setVisibility(View.GONE);
            btn4.setVisibility(View.GONE);

            Intent intent = new Intent(StartGame.this, GameOver.class);
            intent.putExtra("points", points);
            startActivity(intent);

            finish();
        } else {

            countDownTimer = null;
            startGame();
        }
    }

    public void answerSelected(View view) {

        view.setBackgroundColor(Color.parseColor("#17243e"));

        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);

        countDownTimer.cancel();

        String answer = ((Button) view).getText().toString().trim();

        String correctAnswer = techList.get(index);

        if(answer.equals(correctAnswer)){

            points++;

            tvPoints.setText(points + " / " + techList.size());
            tvResult.setText("Correct");
        } else {

            tvResult.setText("Wrong");
        }
    }
}
