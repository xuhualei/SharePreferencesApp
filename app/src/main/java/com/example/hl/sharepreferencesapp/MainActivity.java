package com.example.hl.sharepreferencesapp;

import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.net.ssl.StandardConstants;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button wbt = (Button) findViewById(R.id.button);
        Button rbt = (Button)findViewById(R.id.button2);
        wbt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                OutputStream out = null;
                try{
                    FileOutputStream fileOutputStream = openFileOutput("data",MODE_PRIVATE);
                    out = new BufferedOutputStream(fileOutputStream);
                    String content = "流萤,2015011346";
                    try{
                        out.write(content.getBytes(StandardCharsets.UTF_8));
                    }finally{
                        if (out!=null){
                            out.flush();
                            out.close();
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        rbt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                InputStream in = null;
                try{
                    FileInputStream fileInputStream = openFileInput("data");
                    in = new BufferedInputStream(fileInputStream);
                    BufferedReader reader  = new BufferedReader(new InputStreamReader(in,"UTF-8"));
                    String line = "";
                    String result = null;
                    try{
                        while((line=reader.readLine())!= null){
                            result = line;
                        }
                        Toast.makeText(MainActivity.this,result,Toast.LENGTH_LONG).show();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }catch(Exception e){
                   e.printStackTrace();
                }
            }
        });
    }
}
