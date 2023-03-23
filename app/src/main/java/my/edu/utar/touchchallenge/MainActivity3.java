package my.edu.utar.touchchallenge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import java.util.Locale;
import java.util.Random;

public class MainActivity3 extends AppCompatActivity implements View.OnTouchListener {

    private TextView[] views;
    private int currentViewIndex;
    private int currentLevel = 3;
    private int score;
    private TextView countdownText;
    private long timeLeftInMillisecond;
    private CountDownTimer countDownTimer;;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        countdownText = findViewById(R.id.time);
        Intent intent =getIntent();
        score=intent.getIntExtra("score",score);
        // Initialize the TextView array with the Views
        views = new TextView[] {
                findViewById(R.id.view1),
                findViewById(R.id.view2),
                findViewById(R.id.view3),
                findViewById(R.id.view4),
                findViewById(R.id.view5),
                findViewById(R.id.view6),
                findViewById(R.id.view7),
                findViewById(R.id.view8),
                findViewById(R.id.view10),
                findViewById(R.id.view11),
                findViewById(R.id.view12),
                findViewById(R.id.view13),
                findViewById(R.id.view14),
                findViewById(R.id.view15),
                findViewById(R.id.view16)


        };

        // Set onTouchListener for each View
        for (TextView view : views) {
            view.setOnTouchListener(this);
        }

        // Start the game
        startGame();
    }

    // Start the game with the first level
    private void startGame() {
        setRandomView();
        countdownText = findViewById(R.id.time);
        timeLeftInMillisecond = 6000;
        startTimer();
    }

    // Start time count down
    public void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillisecond, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillisecond = millisUntilFinished;
                updateTimer();
            }

            @Override
            public void onFinish() {
                openActivity4();

            }
        }.start();

    }
    //show the left of the time
    private void updateTimer() {
        int seconds = (int) ( timeLeftInMillisecond /1000);
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d", seconds);
        countdownText.setText("Time:" + timeLeftFormatted);
    }

    // Set a random View to highlight
    private void setRandomView() {
        // Reset all Views to default state
        for (TextView view : views) {
            view.setBackgroundResource(R.drawable.default_view);
        }

        // Choose a random View index to highlight
        Random random = new Random();
        currentViewIndex = random.nextInt(views.length);
        views[currentViewIndex].setBackgroundResource(R.drawable.highlighted_view);
    }

    // Handle onTouch events for Views
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        // Check if the touched View is the current highlighted View
        if (view == views[currentViewIndex]) {
            // Calculate the score based on the time taken to touch the View
            score += 10;
            if (score < 0) {
                score = 0;
            }

            // Proceed to the next level or end the game if all levels completed
            if (currentLevel < 5) {
                currentLevel++;
                setRandomView();
            }
        } else {
            // Penalize the player for touching the wrong View
            score -= 10;
            if (score < 0) {
                score = 0;
            }
        }

        // Update the score on the screen
        TextView scoreTextView = findViewById(R.id.scoreTextView);
        scoreTextView.setText("Score: " + score);

        // Add a delay before highlighting the next View
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setRandomView();
            }
        }, 500);

        return false;
    }

    //Enter next level
    public void openActivity4(){
        Intent intent=new Intent(this,MainActivity4.class);
        intent.putExtra("score",score);
        startActivity(intent);
    }

}