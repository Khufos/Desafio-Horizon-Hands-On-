package com.example.hands;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void chamartela2(View view) {
       Intent intent = new Intent(this,MainActivity2.class);
       startActivity(intent);
    }

    public void chamarlista(View view){
        Intent intent = new Intent(this,MainActivity3.class);
        startActivity(intent);

    }
}