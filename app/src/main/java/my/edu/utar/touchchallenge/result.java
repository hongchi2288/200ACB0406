package my.edu.utar.touchchallenge;



import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.support.v7.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
public class result  extends AppCompatActivity  {

        private TextView mScoreTextView,highScoresTextView;
        private EditText mNameEditText;
        private Button mSubmitButton;
        public int score;
        private ArrayList<HighScore> mHighScores= new ArrayList<>();
        private SharedPreferences mSharedPreferences;

        private static final int MAX_SCORES = 25;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_result);

            mScoreTextView = findViewById(R.id.score_text_view);
            mNameEditText = findViewById(R.id.name_edit_text);
            mSubmitButton = findViewById(R.id.submit_button);

            mSharedPreferences = getSharedPreferences("high_scores", MODE_PRIVATE);
            mHighScores = new ArrayList<>();
            loadHighScores();
            score = getIntent().getIntExtra("score", 0);
            mScoreTextView.setText("Score: " + score);

            mSubmitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = mNameEditText.getText().toString();
                    HighScore highScore = new HighScore(name, score);
                    mHighScores.add(highScore);
                    Collections.sort(mHighScores);
                    saveHighScores();
                    displayHighScores();
                }
            });
        }
        //The high scores are then displayed again, with the updated list. Only the top 25 high scores are displayed.
        private void loadHighScores() {
            for (int i = 0; i < MAX_SCORES; i++) {
                String name = mSharedPreferences.getString("name" + i, null);
                int score = mSharedPreferences.getInt("score" + i, 0);
                if (name != null && score > 0) {
                    HighScore highScore = new HighScore(name, score);
                    mHighScores.add(highScore);
                }
            }
        }
    private void saveHighScores() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        // clear entries for the top scores that are going to be saved
        for (int i = Math.min(mHighScores.size(), MAX_SCORES); i < MAX_SCORES; i++) {
            editor.remove("name" + i);
            editor.remove("score" + i);
        }
        // save the top scores
        for (int i = 0; i < Math.min(mHighScores.size(), MAX_SCORES); i++) {
            editor.putString("name" + i, mHighScores.get(i).getName());
            editor.putInt("score" + i, mHighScores.get(i).getScore());
        }
        editor.apply();
    }

        private void displayHighScores() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < Math.min(mHighScores.size(), MAX_SCORES); i++) {
                sb.append((i + 1) + ". " + mHighScores.get(i).getName() + " - " + mHighScores.get(i).getScore() + "\n");
            }
            highScoresTextView = findViewById(R.id.high_scores_text_view);
            highScoresTextView.setText(sb.toString());
        }
    }




