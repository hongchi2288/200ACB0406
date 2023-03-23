package my.edu.utar.touchchallenge;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Homepage extends AppCompatActivity {
Button start,viewrank;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        start= findViewById(R.id.Start);
        viewrank=findViewById(R.id.viewrank);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startgame();

            }
        });
    }
    public void startgame(){
        Intent intent=new Intent(Homepage.this,MainActivity.class);
        startActivity(intent);
    }

}