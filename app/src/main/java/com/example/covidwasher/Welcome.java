package com.example.covidwasher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Welcome extends AppCompatActivity {

    Button understood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        understood = (Button) findViewById(R.id.bUnderstood);
        understood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCovidTimer(); //make the result visible
            }
        });


    }

    public void goToCovidTimer() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
