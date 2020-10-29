package com.example.registerlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        TextView textView=(TextView)findViewById(R.id.textView);


        Bundle bundle= getIntent().getExtras();
        String showdata= bundle.getString("data");
        textView.setText("user mail Bundle = "+showdata);

    }
}
