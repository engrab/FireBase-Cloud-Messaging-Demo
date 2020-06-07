package com.example.firebasecloudmessaging;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textview);

        if (getIntent() != null && getIntent().hasExtra("key1")){

            mTextView.setText("");
            for (String key : getIntent().getExtras().keySet()) {
                mTextView.append(key+",  Data: "+getIntent().getExtras().getString(key)+"\n");
            }

        }
    }
}