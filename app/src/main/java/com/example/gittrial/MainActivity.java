package com.example.gittrial;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText etno1;
    EditText etno2;
    Button btnadd;
    Button btnsub;
    Button btndiv;
    Button btnmul;
    TextView tvresult;
    Button btnshowlogs;

    private List<String> log = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String message = "Hello";

        etno1 = findViewById(R.id.etno1);
        etno2 = findViewById(R.id.etno2);
        btnadd = findViewById(R.id.btnadd);
        btnsub = findViewById(R.id.btnsub);
        btndiv = findViewById(R.id.btndiv);
        btnmul = findViewById(R.id.btnmul);
        btnshowlogs = findViewById(R.id.btnshowlogs);
        tvresult = findViewById(R.id.tvresult);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = add(etno1.getText().toString(),
                        etno2.getText().toString());
                tvresult.setText(result);
                log.add("Result of Addition: "+result);
            }
        });

        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = sub(etno1.getText().toString(),
                        etno2.getText().toString());
                tvresult.setText(result);
                log.add("Result of Subtract: "+result);
            }
        });

        btndiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = div(etno1.getText().toString(),
                        etno2.getText().toString());
                tvresult.setText(result);
                log.add("Result of Divide: "+result);
            }
        });

        btnmul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = mul(etno1.getText().toString(),
                        etno2.getText().toString());
                tvresult.setText(result);
                log.add("Result of Multiply: "+result);
            }
        });

        btnshowlogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent
                        (MainActivity.this, LogsActivity.class);
                intent.putStringArrayListExtra("LogsResult",
                        (ArrayList<String>)log);
                startActivity(intent);
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

    private String sub(String numberone, String numbertwo){
        if (numberone.equals("")|numbertwo.isEmpty()){
            Toast.makeText(this, "Please enter a valid numer",
                    Toast.LENGTH_SHORT).show();
            return null;
        }
        int a = Integer.parseInt(numberone);
        int b = Integer.parseInt(numbertwo);
        int result = a - b;
        return Integer.toString(result);
    }

    private String div(String numberone, String numbertwo){
        if (numberone.equals("")|numbertwo.isEmpty()){
            Toast.makeText(this, "Please enter a valid numer",
                    Toast.LENGTH_SHORT).show();
            return null;
        }
        int a = Integer.parseInt(numberone);
        int b = Integer.parseInt(numbertwo);
        int result = a / b;
        return Integer.toString(result);
    }

    private String mul(String numberone, String numbertwo){
        if (numberone.equals("")|numbertwo.isEmpty()){
            Toast.makeText(this, "Please enter a valid numer",
                    Toast.LENGTH_SHORT).show();
            return null;
        }
        int a = Integer.parseInt(numberone);
        int b = Integer.parseInt(numbertwo);
        int result = a * b;
        return Integer.toString(result);
    }

}
