package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username,password;
    Button Login;
    TextView txtregister;
    DbHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        Login  = (Button) findViewById(R.id.loginbtn);
        txtregister = (TextView) findViewById(R.id.register);
        DB = new DbHelper(this);

        Login.setOnClickListener(view -> {
            String user = username.getText().toString();
            String upassword = password.getText().toString();
            if(username.getText().length() < 8){
                username.setError("Enter atleast 8 Character password");
            }
            if (user.equals("") || upassword.equals("")) {
                Toast.makeText(MainActivity.this, "Please enter all fields", Toast.LENGTH_LONG).show();
            }
            else{
                Boolean checkuserpass = DB.checkusernamepassword(user,upassword);
                if (checkuserpass == true){
                    Toast.makeText(MainActivity.this,"Login Successfull",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this,"Invalid Credentials",Toast.LENGTH_LONG).show();
                }
            }
        });
        txtregister.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this,RegisterUser.class);
            startActivity(i);
        });

    }
}