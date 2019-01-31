package com.example.gittrial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etno1;
    EditText etno2;
    Button btnadd;
    TextView tvresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String message = "Hello";

        etno1 = findViewById(R.id.etno1);
        etno2 = findViewById(R.id.etno2);
        btnadd = findViewById(R.id.btnadd);
        tvresult = findViewById(R.id.tvresult);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = add(etno1.getText().toString(),
                        etno2.getText().toString());
                tvresult.setText(result);
            }
        });

    }
    private String add(String numberone, String numbertwo){
        if (numberone.equals("")|numbertwo.isEmpty()){
            Toast.makeText(this, "Please enter a valid numer",
            Toast.LENGTH_SHORT).show();
            return null;
        }
        int a = Integer.parseInt(numberone);
        int b = Integer.parseInt(numbertwo);
        int result = a + b;
        return Integer.toString(result);
    }

}
