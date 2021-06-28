package com.example.new_new_new;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener{
    TextView PersonalData;
    Button app;
    String _name,_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Intent intent = getIntent();
        String message = intent.getStringExtra(SecondActivity.EXTRA_MESSAGE);
        String name = intent.getStringExtra("name");
        String id = intent.getStringExtra("id");
        _name = name;
        _id = id;
        message = message + _name + _id;
        TextView textView = findViewById(R.id.Pesonal_data);
        textView.setText(message);
        app = findViewById(R.id.app);
        app.setOnClickListener(this);
        // Get the Intent that started this activity and extract the string
       // Intent intent2 = getIntent();
       // String message2 = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        //PersonalData = findViewById(R.id.Pesonal_data);
        textView.setText(message);}

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.app){

            Intent intent1 = new Intent(this, FourthActivity.class);
            intent1.putExtra("name", _name);
            startActivity(intent1);
        }
    }
}

