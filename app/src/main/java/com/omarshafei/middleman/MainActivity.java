package com.omarshafei.middleman;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ExampleBroadcastReceiver.OnReceivingInformation {

    ExampleBroadcastReceiver exampleBroadcastReceiver = new ExampleBroadcastReceiver();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exampleBroadcastReceiver.registerView(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter = new IntentFilter("com.omarshafei.EXAMPLE_ACTION");
        registerReceiver(exampleBroadcastReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(exampleBroadcastReceiver);
    }

    @Override
    public void onReceivingInformation(String text) {
        TextView textView = findViewById(R.id.tv);
        textView.setText(text);
    }
}