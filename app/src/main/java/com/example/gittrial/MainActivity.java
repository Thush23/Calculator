package com.example.gittrial;
import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etno1;
    EditText etno2;
    EditText etno3;
    Button btnadd;
    Button btnsub;
    Button btndiv;
    Button btnmul;
    TextView tvresult;
    Button btnshowlogs;
    Button btnsave;

    private List<String> log = new ArrayList<>();

    private boolean isPermissionRequestInProgress = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String message = "Hello";

        etno1 = findViewById(R.id.etno1);
        etno2 = findViewById(R.id.etno2);
        etno3 = findViewById(R.id.etno3);
        btnadd = findViewById(R.id.btnadd);
        btnsub = findViewById(R.id.btnsub);
        btndiv = findViewById(R.id.btndiv);
        btnmul = findViewById(R.id.btnmul);
        btnshowlogs = findViewById(R.id.btnshowlogs);
        tvresult = findViewById(R.id.tvresult);
        btnsave = findViewById(R.id.btnsave);

        readNameFromSharedPreferences();

        btnadd.setOnClickListener(this);
        btnsub.setOnClickListener(this);
        btndiv.setOnClickListener(this);
        btnmul.setOnClickListener(this);
        btnsave.setOnClickListener(this);
        btnshowlogs.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnadd:
                handleAddClick();
                break;
            case R.id.btndiv:
                handleDivClick();
                break;
            case R.id.btnmul:
                handleMulClick();
                break;
            case R.id.btnsub:
                handleSubClick();
                break;
            case R.id.btnsave:
                handleSaveClick();
                break;
            case R.id.btnshowlogs:
                handleLogsClick();
                break;

    }}

    private void handleSaveClick(){
            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPreferences",
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("Name", etno3.getText().toString());
            editor.apply();
        }
        // SHOW LOGS FUNCTION//
        private void handleLogsClick(){
                if (log.size()>0) {
                    writeLogsToFile();
                    writeLogsToExternalStorage();
                }
            Intent intent = new Intent
                    (MainActivity.this, LogsActivity.class);
            intent.putStringArrayListExtra("LogsResult",
                    (ArrayList<String>) log);
            if(!isPermissionRequestInProgress){
                startActivity(intent);
            }
        }
    // SHOWING THE ADDITION//
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
    // SHOWING THE SUBTRACT//
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
    // SHOWING THE DIVISION//
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
    // SHOWING THE MULTIPLICATION//
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

    private void handleAddClick(){
        String result = add(etno1.getText().toString(),
                etno2.getText().toString());
        tvresult.setText(result);
        log.add("Result of Addition: "+result);
    }

    private void handleSubClick(){
        String result = sub(etno1.getText().toString(),
                etno2.getText().toString());
        tvresult.setText(result);
        log.add("Result of Subtract: "+result);
    }

    private void handleDivClick(){
    String result = div(etno1.getText().toString(),
            etno2.getText().toString());
                tvresult.setText(result);
                log.add("Result of Divide: "+result);
    }

    private void handleMulClick(){
    String result = mul(etno1.getText().toString(),
            etno2.getText().toString());
                tvresult.setText(result);
                log.add("Result of Multiply: "+result);
    }

    private void readNameFromSharedPreferences(){
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPreferences",Context.MODE_PRIVATE);
        String name = sharedPreferences.getString("Name", "");
        tvresult.setText(name);
    }
    private void writeLogsToFile() {
        //File file = new File(getFilesDir(), "Logs.txt");
        //FileOutputStream fileOutputStream = null;

        try (FileOutputStream fileOutputStream = openFileOutput("Logs.txt", Context.MODE_PRIVATE)) {

            StringBuilder stringBuilder = new StringBuilder();
            for (String result : log) {
                stringBuilder.append(result);
                stringBuilder.append("\n");


            }
            fileOutputStream.write(stringBuilder.toString().getBytes());

        } catch (IOException iOxception) {
            Toast.makeText(this, "File Not Found", Toast.LENGTH_SHORT).show();
            iOxception.printStackTrace();

        }

    }
    private void writeLogsToExternalStorage(){
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestStoragePermission();
        }else{
            writeFile();

        }
    }

    private void requestStoragePermission(){
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                100);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100 && grantResults.length >0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED){
            writeFile();

        }
    }

    private void writeFile() {
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            File parentFolder = new File(Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS),"Calculator");
            parentFolder.mkdirs();

            File file = new File(parentFolder, "Logs.text");

            try (FileOutputStream fileOutputStream =
                         new FileOutputStream(file)) {

                StringBuilder stringBuilder = new StringBuilder();
                for (String result : log) {
                    stringBuilder.append(result);
                    stringBuilder.append("\n");


                }
                fileOutputStream.write(stringBuilder.toString().getBytes());

            } catch (IOException iOxception) {
                Toast.makeText(this, "File Not Found", Toast.LENGTH_SHORT).show();
                iOxception.printStackTrace();

            }
        }
    }
}
