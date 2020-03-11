package com.example.slotmachine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    public Drawable cherryImage;
    public Drawable grapeImage;
    public Drawable pearImage;
    public Drawable strawberryImage;
    public TextView pointsView;
    public SeekBar speedBar;
    public Button startStopButton;
    public Button rulesButton;
    public Handler handler;
    public boolean running;
    public Integer speed;
    public ImageView slot1;
    public ImageView slot2;
    public ImageView slot3;
    public gameStart gameStartObj;
    public slotOneChange slotOneCh;
    public slotTwoChange slotTwoCh;
    public slotThreeChange slotThreeCh;
    public Integer picIDOne;
    public Integer picIDTwo;
    public Integer picIDThree;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cherryImage = getDrawable(R.drawable.cherry);
        grapeImage = getDrawable(R.drawable.grape);
        pearImage = getDrawable(R.drawable.pear);
        strawberryImage = getDrawable(R.drawable.strawberry);
        speed = 500;
        slot1 = findViewById(R.id.slot1);
        slot2 = findViewById(R.id.slot2);
        slot3 = findViewById(R.id.slot3);
        pointsView = findViewById(R.id.pointsView);
        speedBar = findViewById(R.id.speedBar);
        speedBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress == 0){
                    speed = 500;
                } else {
                    speed = (500 / progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });
        startStopButton = findViewById(R.id.startStopButton);
        rulesButton = findViewById(R.id.rulesButton);
        handler = new Handler();
        running = false;
        gameStartObj = new gameStart();
        slotOneCh = new slotOneChange();
        slotTwoCh = new slotTwoChange();
        slotThreeCh = new slotThreeChange();
        picIDOne = 1;
        picIDTwo = 1;
        picIDThree = 1;
    }

    public void startPressed(View v){
        if(running == false) {
            running = true;
            handler.postDelayed(gameStartObj, 100);
            handler.postDelayed(slotOneCh, speed);
            handler.postDelayed(slotTwoCh, (speed/2));
            handler.postDelayed(slotThreeCh, (speed/4));
        } else {
            running = false;
            handler.removeCallbacks(gameStartObj);
            handler.removeCallbacks(slotOneCh);
            handler.removeCallbacks(slotTwoCh);
            handler.removeCallbacks(slotThreeCh);
            points();
        }
    }

    public class gameStart implements Runnable {

        @Override
        public void run() {

                handler.postDelayed(gameStartObj, 100);

        }
    }

    public void rulePressed(View v){
        Intent i = new Intent (this, RuleActivity.class);
        startActivity(i);
    }

    public void points() {
        if(picIDOne == picIDTwo && picIDTwo == picIDThree){
            pointsView.setText("Points: 100");
        } else if(picIDOne == picIDTwo | picIDOne == picIDThree | picIDTwo == picIDThree){
            pointsView.setText("Points: 50");
        } else {
            pointsView.setText("Points: 10");
        }
    }

    public class slotOneChange implements Runnable {
        @Override
        public void run() {
            if (picIDOne == 1) {
                slot1.setImageDrawable(cherryImage);
            } else if (picIDOne == 2){
                slot1.setImageDrawable(grapeImage);
            } else if (picIDOne == 3){
                slot1.setImageDrawable(pearImage);
            } else {
                slot1.setImageDrawable(strawberryImage);
            }
            if(picIDOne <4) {
                picIDOne++;
            } else {
                picIDOne = 1;
            }
            handler.postDelayed(slotOneCh, speed);
        }
    }
    public class slotTwoChange implements Runnable {
        @Override
        public void run() {
            if (picIDTwo == 1) {
                slot2.setImageDrawable(cherryImage);
            } else if (picIDTwo == 2){
                slot2.setImageDrawable(grapeImage);
            } else if (picIDTwo == 3){
                slot2.setImageDrawable(pearImage);
            } else {
                slot2.setImageDrawable(strawberryImage);
            }
            if(picIDTwo <4) {
                picIDTwo++;
            } else {
                picIDTwo = 1;
            }
            handler.postDelayed(slotTwoCh, (speed/2));
        }
    }
    public class slotThreeChange implements Runnable {
        @Override
        public void run() {
            if (picIDThree == 1) {
                slot3.setImageDrawable(cherryImage);
            } else if (picIDThree == 2){
                slot3.setImageDrawable(grapeImage);
            } else if (picIDThree == 3){
                slot3.setImageDrawable(pearImage);
            } else {
                slot3.setImageDrawable(strawberryImage);
            }
            if(picIDThree <4) {
                picIDThree++;
            } else {
                picIDThree = 1;
            }
            handler.postDelayed(slotThreeCh, (speed/4));
        }
    }
}
