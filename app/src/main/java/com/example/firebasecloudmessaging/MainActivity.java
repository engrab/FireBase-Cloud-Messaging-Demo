package com.example.firebasecloudmessaging;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    TextView mTextView;
    Button mSubScribe, mUnSubScribe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textview);
        mSubScribe = findViewById(R.id.btn_subscribe);
        mUnSubScribe = findViewById(R.id.btn_unsubscribe);
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (task.isSuccessful())
                        {
                            Log.d(TAG, "onComplete: "+task.getResult().getToken());
                        }
                    }
                });

        if (getIntent() != null && getIntent().hasExtra("key1")){

            mTextView.setText("");
            for (String key : getIntent().getExtras().keySet()) {
                mTextView.append(key+",  Data: "+getIntent().getExtras().getString(key)+"\n");
            }

        }



        mUnSubScribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unSub();
            }
        });

        mSubScribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sub();
            }
        });
    }

    private void sub() {
        FirebaseMessaging.getInstance().subscribeToTopic("football-topic")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        Toast.makeText(MainActivity.this, "Subscribe", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Subscribe error", Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void unSub() {

        FirebaseMessaging.getInstance().unsubscribeFromTopic("football-topic")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        Toast.makeText(MainActivity.this, "Un Subscribe", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this, "Error Un Subscribe", Toast.LENGTH_SHORT).show();
                    }
                });
    }


}