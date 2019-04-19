package com.example.tpatel1474.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button)findViewById(R.id.btnStart);
        Button btn2 = (Button)findViewById(R.id.btnMap);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,CreateProduct.class);
                startActivity(i);
            }
        });
    }

    public void btnmap (View view){
        Intent intent = new Intent(getBaseContext(),MapsActivity.class);
        startActivity(intent);
    }
}
