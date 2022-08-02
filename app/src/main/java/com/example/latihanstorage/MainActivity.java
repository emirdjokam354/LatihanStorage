package com.example.latihanstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnInternal, btnExternal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInternal = (Button) findViewById(R.id.btnInternal);
        btnExternal = (Button) findViewById(R.id.btnExternal);

        btnInternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, InternalActivity.class);
                startActivity(intent);
            }
        });

        btnExternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ExternalStorage.class);
                startActivity(intent);
            }
        });
    }
}