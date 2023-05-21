package com.example.timerlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class Timer extends AppCompatActivity {

    // UI  //
    Button BTN_start;
    TextView textView;
    SeekBar timer_sb;

    //    // TIMER //
    CountDownTimer countDownTimer;

    boolean timerRunning ;
    int prog;
    TimerProvider timerProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        findViews();
        initTime();
        initProvider();
        initButtons();

    }
    private void initTime() {
        timer_sb.setMax(300);
        timer_sb.setProgress(60*5);
    }

    private void findViews() {
        textView = findViewById(R.id.textView);
        BTN_start = findViewById(R.id.BTN_start);
        timer_sb = findViewById(R.id.timer_sb);
    }

    private void initButtons() {
        BTN_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timerRunning== false){
                    timerRunning= true;
                    timer_sb.setEnabled(true);
                    BTN_start.setText("stop");
                    countDownTimer = new CountDownTimer(timer_sb.getProgress()* 1000, 1000){
                        @Override
                        public void onTick(long l) {
                            prog=  update((int) l/1000 );
                            timerProvider.textUpdate(prog,timer_sb);
                        }

                        @Override
                        public void onFinish() {
                            reset();
                        }
                    }.start();
                }else{
                    reset();
                }

            }
        });

        timer_sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                update(progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
    public void reset(){
        textView.setText("5:00");
        timer_sb.setProgress(60*5);
        countDownTimer.cancel();
        BTN_start.setText("start");
        timer_sb.setEnabled(true);
        timerRunning = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (timerRunning){
            countDownTimer.cancel();
        }
    }
    public int update(int progress ) {
        int minutes = (int) progress / 60;
        int seconds = (int) progress % 60;
        String secondFinal = "";
        if (seconds < 9) {
            secondFinal = "0" + seconds;
        } else {
            secondFinal = "" + seconds;
        }
        textView.setText("" + minutes + ":" + secondFinal);
        return progress;

    }
    private void initProvider() {
        timerProvider = new TimerProvider();
    }


}