package com.example.smarthomegcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner =findViewById(R.id.video_spinner);
        ArrayAdapter.createFromResource(this, R.array.videoList, android.R.layout.simple_spinner_item);
        Button btn = findViewById(R.id.confirm_selection_button);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Object sItem = spinner.getSelectedItem();
                String select = sItem == null ? "Turn on lights" : sItem.toString();
                Intent intent = new Intent(v.getContext(), Screen2.class);
                Bundle bd = new Bundle();
                bd.putString("gid", select);
                intent.putExtras(bd);
                startActivity(intent);
            }
        });
    }
}